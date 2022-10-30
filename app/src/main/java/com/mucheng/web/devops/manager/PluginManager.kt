package com.mucheng.web.devops.manager

import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.annotation.Keep
import com.mucheng.web.devops.exceptions.PluginException
import com.mucheng.web.devops.path.CacheDir
import com.mucheng.web.devops.path.FilesDir
import com.mucheng.web.devops.path.MainDir
import com.mucheng.web.devops.path.OatDir
import com.mucheng.web.devops.path.PluginDir
import com.mucheng.web.devops.path.PluginStoreDir
import com.mucheng.web.devops.path.ProjectDir
import com.mucheng.web.devops.path.StorageDir
import com.mucheng.web.devops.plugin.Plugin
import com.mucheng.web.devops.plugin.PluginResources
import com.mucheng.web.devops.util.AppCoroutine
import com.mucheng.web.devops.util.Context
import com.mucheng.webops.plugin.PluginMain
import com.mucheng.webops.plugin.data.Files
import dalvik.system.DexClassLoader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

@Keep
object PluginManager {

    private val plugins: MutableList<Plugin> = ArrayList()

    fun loadPlugins(onError: (e: Throwable) -> Unit = {}) {
        plugins.clear()
        val listFiles = PluginDir.listFiles() ?: emptyArray()
        for (file in listFiles) {
            if (file.isFile && file.name.endsWith(".apk")) {
                try {
                    loadExternalPlugin(file)
                } catch (e: Throwable) {
                    onError(e)
                }
            }
        }
    }

    fun getApkPackageName(file: File): String? {
        val appContext = Context
        val packageManager = appContext.packageManager
        val packageInfo = packageManager.getPackageArchiveInfo(
            file.absolutePath,
            PackageManager.GET_ACTIVITIES or
                    PackageManager.GET_SERVICES or
                    PackageManager.GET_META_DATA or
                    PackageManager.GET_PROVIDERS
        ) ?: return null
        val applicationInfo = packageInfo.applicationInfo
        applicationInfo.sourceDir = file.absolutePath
        applicationInfo.publicSourceDir = file.absolutePath
        return applicationInfo.packageName
    }

    @Suppress("DEPRECATION")
    fun loadExternalPlugin(file: File): Plugin {
        val appContext = Context
        val packageManager = appContext.packageManager
        val packageInfo = packageManager.getPackageArchiveInfo(
            file.absolutePath,
            PackageManager.GET_ACTIVITIES or
                    PackageManager.GET_SERVICES or
                    PackageManager.GET_META_DATA or
                    PackageManager.GET_PROVIDERS
        ) ?: throw PluginException("Cannot resolve the path: ${file.absolutePath}")
        val applicationInfo = packageInfo.applicationInfo
        applicationInfo.sourceDir = file.absolutePath
        applicationInfo.publicSourceDir = file.absolutePath

        val metaData = packageInfo.applicationInfo.metaData
        val name = applicationInfo.loadLabel(packageManager).toString()
        val packageName = applicationInfo.packageName
        val loadedPlugin = findPluginByPackageName(packageName)
        if (loadedPlugin != null) {
            Log.e("PluginManager", "The plugin '$packageName' has already loaded.")
            return loadedPlugin
        }

        val launchMain = metaData.getString("launchMain")
            ?: throw PluginException("The plugin '${file.absolutePath}' has no 'launchMain' meta-data.")

        val dexClassLoader = DexClassLoader(
            file.absolutePath,
            OatDir.absolutePath,
            null,
            appContext.classLoader
        )

        val resources =
            PluginResources(packageManager.getResourcesForApplication(applicationInfo))

        val pluginMain = dexClassLoader.loadClass(launchMain).newInstance() as PluginMain
        // 调用初始化方法
        pluginMain.onInit(
            appContext, resources, AppCoroutine, Files(
                StorageDir,
                CacheDir,
                FilesDir,
                PluginDir,
                OatDir,
                MainDir,
                ProjectDir,
                PluginStoreDir
            )
        )

        val versionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            packageInfo.longVersionCode
        } else {
            packageInfo.versionCode.toLong()
        }
        val plugin =
            Plugin(name, packageName, pluginMain, file.absolutePath, versionCode)
        plugins.add(plugin)
        return plugin
    }

    suspend fun findPluginByProjectIdSuspend(projectId: String): Plugin? {
        return withContext(Dispatchers.IO) {
            findPluginByProjectId(projectId)
        }
    }

    fun findPluginByProjectId(projectId: String): Plugin? {
        for (plugin in plugins) {
            if (plugin.isSupported(projectId)) {
                return plugin
            }
        }
        return null
    }

    suspend fun findPluginByPackageNameSuspend(packageName: String): Plugin? {
        return withContext(Dispatchers.IO) {
            findPluginByPackageName(packageName)
        }
    }

    fun findPluginByPackageName(packageName: String): Plugin? {
        for (plugin in plugins) {
            if (plugin.packageName == packageName) {
                return plugin
            }
        }
        return null
    }

    fun removePluginByPackageName(packageName: String) {
        for (plugin in plugins) {
            if (plugin.packageName == packageName) {
                plugins.remove(plugin)
            }
        }
    }

    fun removePlugin(plugin: Plugin): Boolean {
        return plugins.remove(plugin)
    }

    fun getPlugins(): List<Plugin> {
        return plugins
    }

}