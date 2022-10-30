package com.mucheng.web.devops.httpd

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import com.mucheng.editor.language.css.CssLanguage
import com.mucheng.editor.language.ecmascript.EcmaScriptLanguage
import com.mucheng.editor.language.html.HtmlLanguage
import com.mucheng.editor.sample.language.TextLanguage
import com.mucheng.editor.view.MuCodeEditor
import com.mucheng.web.devops.openapi.util.FileUtil
import com.mucheng.web.devops.openapi.util.TimeUtil
import com.mucheng.web.devops.openapi.view.LoadingComponent
import com.mucheng.webops.plugin.PluginActivity
import com.mucheng.webops.plugin.PluginMain
import com.mucheng.webops.plugin.check.ProjectCreationChecker
import com.mucheng.webops.plugin.command.ShellExecutor
import com.mucheng.webops.plugin.data.CreateInfo
import com.mucheng.webops.plugin.data.Files
import com.mucheng.webops.plugin.data.ObservableValue
import com.mucheng.webops.plugin.data.Workspace
import com.mucheng.webops.plugin.data.info.ComponentInfo
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.lingala.zip4j.io.inputstream.ZipInputStream
import java.io.File

class Main : PluginMain() {

    private lateinit var workspace: Workspace

    private val baseClassPath = Main::class.java.name

    private val httpdProjectId = "$baseClassPath/HttpdProject"

    private lateinit var htmlIcon: Drawable

    private lateinit var cssIcon: Drawable

    private lateinit var javaScriptIcon: Drawable

    @Suppress("DEPRECATION")
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onInit(
        applicationContext: Context,
        resources: Resources,
        appCoroutine: CoroutineScope,
        files: Files
    ) {
        super.onInit(applicationContext, resources, appCoroutine, files)

        addProject(
            "Httpd 工程",
            httpdProjectId,
            "稳定级静态 Web 服务器",
            resources.getDrawable(R.mipmap.httpd_logo)
        )

        this.htmlIcon = resources.getDrawable(R.drawable.ic_file_html)
        this.cssIcon = resources.getDrawable(R.drawable.ic_file_css)
        this.javaScriptIcon = resources.getDrawable(R.drawable.ic_file_js)
    }

    override fun onOpenProject(
        activity: AppCompatActivity,
        workspace: Workspace,
        editor: MuCodeEditor,
        observableProgress: ObservableValue<Int>
    ) {
        this.workspace = workspace

        val projectPath = "${files.ProjectDir}/${workspace.getName()}"
        val httpdConfPath = "$projectPath/.WebDevOps/httpd.conf"
        val usrDir = File("${files.FilesDir}/httpd/usr")
        val binDir = File("$usrDir/bin")

        appCoroutine.launch(CoroutineName("RunHttpdCoroutine")) {
            withContext(Dispatchers.IO) {
                ShellExecutor.execSuspend(files.FilesDir, listOf("chmod", "777", "-R", "httpd"))
                    .waitFor()
            }
            observableProgress.setValue(50)

            withContext(Dispatchers.IO) {
                ShellExecutor.execSuspend(binDir, listOf("./httpd", "-f", httpdConfPath))
                    .waitFor()
            }
            observableProgress.setValue(100)
        }.invokeOnCompletion {
            if (it != null) {
                activity.runOnUiThread {
                    observableProgress.setValue(100)
                }
            }
        }
    }

    override fun onCloseProject(
        activity: AppCompatActivity,
        workspace: Workspace,
        editor: MuCodeEditor
    ) {
        super.onCloseProject(activity, workspace, editor)
        val binDir = File("${files.FilesDir}/httpd/usr/bin")
        ShellExecutor.exec(binDir, listOf("./httpd", "-k", "stop"))
    }

    override suspend fun onRenameProject(
        renamedWorkspace: Workspace,
        beforePath: String,
        afterPath: String
    ) {
        super.onRenameProject(renamedWorkspace, beforePath, afterPath)
        val httpdConfFile = File("$afterPath/.WebDevOps/httpd.conf")
        httpdConfFile.writeText(
            httpdConfFile.readText().replace(beforePath, afterPath)
        )
    }

    override fun onOpenFile(activity: AppCompatActivity, file: File, editor: MuCodeEditor) {
        super.onOpenFile(activity, file, editor)
        val fileName = file.name
        when {
            fileName.endsWith(".html") || fileName.endsWith(".htm") -> {
                editor.setLanguage(HtmlLanguage.getInstance())
            }

            fileName.endsWith(".css") -> {
                editor.setLanguage(CssLanguage)
            }

            fileName.endsWith(".js") -> {
                editor.setLanguage(EcmaScriptLanguage.getInstance())
            }

            else -> editor.setLanguage(TextLanguage)
        }
    }

    override suspend fun onInstall(activity: AppCompatActivity) {
        super.onInstall(activity)
        withContext(Dispatchers.Main) {
            val loadingComponent = LoadingComponent(activity)
            loadingComponent.setContent("准备解压 Httpd 运行包....")
            loadingComponent.show()
            extraHttpdRuntimeZip(loadingComponent)
        }
    }

    override suspend fun onUninstall(activity: AppCompatActivity) {
        super.onUninstall(activity)
        withContext(Dispatchers.Main) {
            val loadingComponent = LoadingComponent(activity)
            loadingComponent.setContent("正在删除 Httpd 运行包....")
            loadingComponent.show()
            withContext(Dispatchers.IO) {
                FileUtil.deleteFile(File("${files.FilesDir}/httpd"))
            }
            loadingComponent.dismiss()
        }
    }

    private suspend fun extraHttpdRuntimeZip(loadingComponent: LoadingComponent) {
        return withContext(Dispatchers.IO) {
            val httpdRuntimeBufferedInputStream =
                resources.assets.open("httpd-runtime.zip").buffered()
            val zipInputStream = ZipInputStream(httpdRuntimeBufferedInputStream)
            FileUtil.extraZipInputStream(
                files.FilesDir.absolutePath, zipInputStream
            ) { fileName ->
                withContext(Dispatchers.Main) {
                    loadingComponent.setContent("正在解压文件: $fileName")
                }
            }

            withContext(Dispatchers.Main) {
                loadingComponent.dismiss()
            }
        }
    }

    override suspend fun onCreateInfo(createInfo: CreateInfo) {
        super.onCreateInfo(createInfo)
        createInfo
            .addInputInfo(hint = "工程名称")
            .addInputInfo(hint = "端口", title = "8080")
            .onConfirm { result ->
                onConfirm(result.createInfo, createInfo.activity)
            }
    }

    private fun onConfirm(createInfo: List<ComponentInfo>, activity: AppCompatActivity): Boolean {
        val projectNameInfo = createInfo[0] as ComponentInfo.InputInfo
        val portInfo = createInfo[1] as ComponentInfo.InputInfo
        val projectName = projectNameInfo.title ?: ""
        val port = portInfo.title ?: ""

        if (!ProjectCreationChecker.checkProjectName(
                applicationContext,
                files.ProjectDir,
                projectName
            )
        ) {
            return false
        }

        if (port.isEmpty()) {
            Toasty.info(activity, "端口不能为空").show()
            return false
        }

        val portInt = port.toIntOrNull()
        if (portInt == null) {
            Toasty.info(activity, "请输入正整数").show()
            return false
        }

        if (portInt < 1025) {
            Toasty.info(activity, "端口不能小于 1025").show()
            return false
        }

        if (portInt > 65535) {
            Toasty.info(activity, "端口不能大于 65535").show()
            return false
        }

        val loadingComponent = LoadingComponent(activity)
        loadingComponent.setContent("准备创建工程")
        loadingComponent.show()
        appCoroutine.launch(CoroutineName("CreateHttpdProjectCoroutine")) {
            createProject(projectName, portInt, loadingComponent, activity)
        }
        return true
    }

    private suspend fun createProject(
        projectName: String,
        port: Int,
        loadingComponent: LoadingComponent,
        activity: AppCompatActivity
    ) {
        val projectDir = files.ProjectDir
        return withContext(Dispatchers.IO) {
            val rootDir = File("$projectDir/$projectName")
            rootDir.mkdirs()

            val workspaceDir = File("$rootDir/.WebDevOps")
            workspaceDir.mkdirs()

            val workspaceFile = File("$workspaceDir/Workspace.xml")
            workspaceFile.createNewFile()

            val workspace = Workspace()
            workspace.setName(projectName)
            workspace.setProjectId(httpdProjectId)
            workspace.setCreationTime(TimeUtil.getFormattedTime())
            workspace.setOpenFile("$rootDir/index.html")
            workspace.set("port", port.toString())
            workspace.storeTo(workspaceFile)

            resources.assets.open("httpd.conf").bufferedReader().use {
                val httpdFile = File("$workspaceDir/httpd.conf")
                httpdFile.writeText(
                    it.readText()
                        .replace("\$PROJECT_DIR", rootDir.absolutePath)
                        .replace("\$PORT", port.toString())

                )
            }

            val httpdProjectTemplateBufferedInputStream =
                resources.assets.open("httpd-project-template.zip").buffered()
            val zipInputStream = ZipInputStream(httpdProjectTemplateBufferedInputStream)
            FileUtil.extraZipInputStream(
                rootDir.absolutePath, zipInputStream
            ) { fileName ->
                withContext(Dispatchers.Main) {
                    loadingComponent.setContent("正在解压文件: $fileName")
                }
            }

            withContext(Dispatchers.Main) {
                loadingComponent.dismiss()
                Toasty.success(activity, "工程创建成功").show()

                activity.setResult(REFRESH_PROJECT, Intent().apply {
                    putExtra("action", REFRESH)
                })
                activity.finish()
            }
        }
    }

    override fun onCreateExecuteActivity(): PluginActivity {
        var port = workspace.get("port")?.toIntOrNull()
        port = if (port != null) {
            Integer.min(65535, Integer.max(1025, port))
        } else {
            8080
        }
        return ExecuteProjectActivity(resources, port)
    }

    override fun getFileItemIcon(file: File): Drawable? {
        if (file.isFile) {
            when (file.extension) {
                "html", "htm" -> return htmlIcon
                "css" -> return cssIcon
                "js" -> return javaScriptIcon
            }
        }
        return super.getFileItemIcon(file)
    }

}