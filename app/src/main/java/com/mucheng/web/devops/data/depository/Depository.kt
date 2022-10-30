package com.mucheng.web.devops.data.depository

import android.graphics.BitmapFactory
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import com.mucheng.web.devops.R
import com.mucheng.web.devops.data.model.DisplayPluginItem
import com.mucheng.web.devops.data.model.MainCommonItem
import com.mucheng.web.devops.manager.PluginManager
import com.mucheng.web.devops.openapi.util.FileUtil
import com.mucheng.web.devops.path.ProjectDir
import com.mucheng.web.devops.plugin.Plugin
import com.mucheng.web.devops.support.AbiSupport
import com.mucheng.web.devops.tryeval.catchAll
import com.mucheng.web.devops.tryeval.tryEval
import com.mucheng.web.devops.util.Context
import com.mucheng.web.devops.util.await
import com.mucheng.web.devops.util.isSupported
import com.mucheng.web.devops.util.request
import com.mucheng.webops.plugin.data.Workspace
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

object Depository {

    // 云端接口
    private const val BASE_URL = "https://sumucheng.mucute.cn/webdev"
    private const val API_URL = "$BASE_URL/api"

    // 访问的 API 链接
    private const val MAIN_COMMON_ITEMS = "$API_URL/mainCommonItems.json"

    // 文件名称
    private const val LocalUserDataFileName = "localUserData.conf"
    private const val LocalCheckCodeDataFileName = "localCheckCodeData.conf"

    suspend fun fetchWorkspaces(): List<Workspace> {
        return withContext(Dispatchers.IO) {
            buildList {
                val files = ProjectDir.listFiles() ?: emptyArray()
                files.sortByDescending {
                    it.lastModified()
                }

                for (rootFile in files) {
                    if (rootFile.isFile) continue
                    val workspaceDir = File(rootFile, ".WebDevOps")
                    if (workspaceDir.isFile) continue
                    val workspaceFile = File(workspaceDir, "Workspace.xml")
                    if (workspaceFile.isDirectory) continue
                    tryEval {
                        val workspace = Workspace()
                        workspace.loadFrom(workspaceFile)
                        if (workspace.isSupported()) {
                            add(workspace)
                        }
                    } catchAll {
                        it.printStackTrace()
                    }
                }
            }
        }
    }

    suspend fun renameProject(workspace: Workspace, projectName: String) {
        return withContext(Dispatchers.Main) {
            val beforePath = "$ProjectDir/${workspace.getName()}"
            val afterPath = "$ProjectDir/$projectName"
            val sourceDir = File(beforePath)
            val targetDir = File(afterPath)
            val workspaceFile = File("$targetDir/.WebDevOps/Workspace.xml")

            FileUtil.renameTo(sourceDir, targetDir)
            workspace.setName(projectName)
            val props = workspace.getMap()
            val keys = props.stringPropertyNames()
            for (key in keys) {
                val value = props.getProperty(key)
                if (value.contains(sourceDir.absolutePath)) {
                    props.setProperty(
                        key,
                        value.replaceFirst(sourceDir.absolutePath, targetDir.absolutePath)
                    )
                }
            }
            workspace.storeTo(workspaceFile)
            val projectId = workspace.getProjectId()
            val plugin = PluginManager.findPluginByProjectIdSuspend(projectId)
            plugin?.pluginMain?.onRenameProject(workspace, beforePath, afterPath)
        }
    }

    suspend fun deleteProject(workspace: Workspace) {
        return withContext(Dispatchers.IO) {
            val rootDir = File(ProjectDir, workspace.getName())
            FileUtil.deleteFile(rootDir)
        }
    }

    suspend fun fetchMainCommonItem(): List<MainCommonItem> {
        return tryEval {
            val body = request(MAIN_COMMON_ITEMS).await()
            parseMainCommonItemList(body.string())
        } catchAll {
            it.printStackTrace()
            emptyList()
        }
    }

    private suspend fun parseMainCommonItemList(topLevelText: String): List<MainCommonItem> {
        val result: MutableList<MainCommonItem> = ArrayList()
        val jsonArray = JSONArray(topLevelText)
        val len = jsonArray.length()
        var index = 0
        while (index < len) {
            try {
                result.add(parseMainCommonItem(jsonArray.getJSONObject(index)))
            } catch (e: Throwable) {
                e.printStackTrace()
            }
            ++index
        }
        return result
    }

    private suspend fun parseMainCommonItem(jsonObject: JSONObject): MainCommonItem {
        val iconUrl = jsonObject.getString("iconUrl")
        val title = jsonObject.getString("title")
        val simpleName = jsonObject.getString("simpleName")
        val version = jsonObject.getString("version")
        val size = jsonObject.getString("size")
        val description = jsonObject.getString("description")
        val pluginDisplayUrl = jsonObject.getString("pluginDisplayUrl")

        return coroutineScope {
            val icon = async {
                try {
                    BitmapFactory.decodeStream(request(iconUrl).await().byteStream())
                        .toDrawable(Context.resources)
                } catch (e: Throwable) {
                    if (e is CancellationException) {
                        throw e
                    }
                    ContextCompat.getDrawable(Context, R.drawable.ic_cube_three)!!
                }
            }

            MainCommonItem(
                icon.await(),
                title,
                simpleName,
                version,
                size,
                description,
                pluginDisplayUrl
            )
        }
    }

    suspend fun fetchDisplayPluginItem(url: String): DisplayPluginItem {
        return withContext(Dispatchers.IO) {
            val body = request(url).await()
            parseDisplayPluginItem(body.string())
        }
    }

    private suspend fun parseDisplayPluginItem(topLevelText: String): DisplayPluginItem {
        val jsonObject = JSONObject(topLevelText)
        val iconUrl = jsonObject.getString("iconUrl")
        val title = jsonObject.getString("title")
        val version = jsonObject.getString("version")
        val versionCode = jsonObject.getLong("versionCode")
        val id = jsonObject.getString("id")
        val size = jsonObject.getString("size")
        val descriptionUrl = jsonObject.getString("descriptionUrl")
        val bit = jsonObject.getInt("bit")
        val runMode = jsonObject.getString("runMode")
        val platformJSONArray = jsonObject.getJSONArray("platform")
        val downloadUrl = jsonObject.getString("downloadUrl")

        return coroutineScope {
            val icon = async {
                try {
                    BitmapFactory.decodeStream(request(iconUrl).await().byteStream())
                        .toDrawable(Context.resources)
                } catch (e: Throwable) {
                    if (e is CancellationException) {
                        throw e
                    }
                    ContextCompat.getDrawable(Context, R.drawable.ic_cube_three)!!
                }
            }

            val description = async {
                request(descriptionUrl).await().string()
            }

            var samePlugin: Plugin? = null
            val plugins = PluginManager.getPlugins()
            for (plugin in plugins) {
                if (plugin.packageName == id) {
                    samePlugin = plugin
                    break
                }
            }

            val neededUpdate = if (samePlugin == null) {
                false
            } else {
                samePlugin.versionCode < versionCode
            }

            val platforms: MutableList<String> = ArrayList()
            val length = platformJSONArray.length()
            var index = 0
            while (index < length) {
                platforms.add(platformJSONArray.getString(index))
                ++index
            }

            val isDownloaded = samePlugin != null
            val containsIndex = platforms.indexOf(AbiSupport.getCPUAbi())
            val isSupported = containsIndex != -1
            val platform = if (isSupported) platforms[containsIndex] else platforms[0]

            DisplayPluginItem(
                icon.await(),
                title,
                version,
                versionCode,
                id,
                size,
                description.await(),
                bit,
                runMode,
                platform,
                downloadUrl,
                samePlugin,
                isDownloaded,
                neededUpdate,
                isSupported
            )
        }
    }

}