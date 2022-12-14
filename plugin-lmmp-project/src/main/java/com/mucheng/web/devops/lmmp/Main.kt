package com.mucheng.web.devops.lmmp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import com.mucheng.web.devops.openapi.editor.lang.css.impl.CssLanguage
import com.mucheng.web.devops.openapi.editor.lang.html.impl.HtmlLanguage
import com.mucheng.web.devops.openapi.editor.lang.javascript.impl.JavaScriptLanguage
import com.mucheng.web.devops.openapi.editor.lang.json.impl.JsonLanguage
import com.mucheng.web.devops.openapi.editor.lang.php.impl.PhpLanguage
import com.mucheng.web.devops.openapi.editor.lang.xml.impl.XmlLanguage
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
import io.github.rosemoe.sora.widget.CodeEditor
import kotlinx.coroutines.*
import net.lingala.zip4j.io.inputstream.ZipInputStream
import java.io.File
import java.net.URL

class Main : PluginMain() {

    private lateinit var workspace: Workspace

    private val baseClassPath = Main::class.java.name

    private val lmmpProjectId = "$baseClassPath/PhpProject"

    private lateinit var htmlIcon: Drawable

    private lateinit var cssIcon: Drawable

    private lateinit var javaScriptIcon: Drawable

    private lateinit var phpIcon: Drawable

    @Suppress("DEPRECATION")
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onInit(
        applicationContext: Context,
        resources: Resources,
        appCoroutine: CoroutineScope,
        files: Files
    ) {
        super.onInit(applicationContext, resources, appCoroutine, files)

        addProject("LMMP ??????", lmmpProjectId, "?????? Nginx + Php + Mysql ??????")
        this.htmlIcon = resources.getDrawable(R.drawable.ic_file_html)
        this.cssIcon = resources.getDrawable(R.drawable.ic_file_css)
        this.javaScriptIcon = resources.getDrawable(R.drawable.ic_file_js)
        this.phpIcon = resources.getDrawable(R.drawable.ic_file_php)
    }

    @Suppress("SpellCheckingInspection")
    override fun onOpenProject(
        activity: AppCompatActivity,
        workspace: Workspace,
        editor: CodeEditor,
        observableProgress: ObservableValue<Int>
    ) {
        super.onOpenProject(activity, workspace, editor, observableProgress)
        this.workspace = workspace

        val projectPath = "${files.ProjectDir}/${workspace.getName()}"
        val nginxConfPath = "$projectPath/.WebDevOps/nginx.conf"
        val phpIniConfPath = "$projectPath/.WebDevOps/php.ini"
        val usrDir = File("${files.FilesDir}/lmmp/usr")
        val binDir = File("$usrDir/bin")
        var port = workspace.get("port")?.toIntOrNull()
        port = if (port != null) {
            Integer.min(65535, Integer.max(1025, port))
        } else {
            8080
        }

        appCoroutine.launch(CoroutineName("RunLmmpCoroutine")) {
            withContext(Dispatchers.IO) {
                ShellExecutor.execSuspend(files.FilesDir, listOf("chmod", "777", "-R", "lmmp"))
                    .waitFor()
            }
            observableProgress.setValue(25)

            withContext(Dispatchers.IO) {
                ShellExecutor.execSuspend(binDir, listOf("./nginx", "-c", nginxConfPath))
                    .waitFor()
            }
            observableProgress.setValue(50)

            withContext(Dispatchers.IO) {
                ShellExecutor.execSuspend(binDir, listOf("./php-fpm", "-c", phpIniConfPath))
                    .waitFor()
            }
            observableProgress.setValue(75)

            launch(Dispatchers.IO) {
                val process = ShellExecutor.execSuspend(binDir, listOf("./mysqld"))
                process.waitFor()
            }
            withContext(Dispatchers.IO) {
                while (isActive) {
                    delay(60)
                    try {
                        val url = URL("http://localhost:$port")
                        val connection = url.openConnection()
                        connection.connect()
                        val input = connection.getInputStream().bufferedReader().readText()
                        if (!((input.contains("Uncaught mysqli_sql_exception:") && input.contains("No such file or directory in")) || (input.contains(
                                "Uncaught mysqli_sql_exception:"
                            ) && input.contains("Connection refused in")))
                        ) {
                            break
                        }
                    } catch (e: Throwable) {
                        break
                    }
                }
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

    @Suppress("SpellCheckingInspection")
    override fun onCloseProject(
        activity: AppCompatActivity,
        workspace: Workspace,
        editor: CodeEditor
    ) {
        super.onCloseProject(activity, workspace, editor)
        val binDir = File("${files.FilesDir}/lmmp/usr/bin")
        ShellExecutor.exec(binDir, listOf("./nginx", "-s", "stop"))
        ShellExecutor.killall("php-fpm")
        ShellExecutor.killall("mysqld")
    }

    override fun onOpenFile(
        activity: AppCompatActivity,
        file: File,
        editor: CodeEditor
    ) {
        super.onOpenFile(activity, file, editor)
        val fileName = file.name
        when {
            fileName.endsWith(".html") || fileName.endsWith(".htm") -> {
                editor.setEditorLanguage(HtmlLanguage())
            }

            fileName.endsWith(".css") -> {
                editor.setEditorLanguage(CssLanguage())
            }

            fileName.endsWith(".js") -> {
                editor.setEditorLanguage(JavaScriptLanguage())
            }

            fileName.endsWith(".php") -> {
                editor.setEditorLanguage(PhpLanguage())
            }

            fileName.endsWith(".json") -> {
                editor.setEditorLanguage(JsonLanguage())
            }

            fileName.endsWith(".xml") -> {
                editor.setEditorLanguage(XmlLanguage())
            }

            else -> editor.setEditorLanguage(null)
        }
    }

    override suspend fun onRenameProject(
        renamedWorkspace: Workspace,
        beforePath: String,
        afterPath: String
    ) {
        super.onRenameProject(renamedWorkspace, beforePath, afterPath)
        return withContext(Dispatchers.IO) {
            val nginxConfFile = File("$afterPath/.WebDevOps/nginx.conf")
            nginxConfFile.writeText(
                nginxConfFile.readText().replace(beforePath, afterPath)
            )
        }
    }

    override suspend fun onInstall(activity: AppCompatActivity) {
        super.onInstall(activity)
        withContext(Dispatchers.Main) {
            val loadingComponent = LoadingComponent(activity)
            loadingComponent.setContent("???????????? Lmmp ?????????....")
            loadingComponent.show()
            extraLmmpRuntimeZip(loadingComponent)
        }
    }

    override suspend fun onUninstall(activity: AppCompatActivity) {
        super.onUninstall(activity)
        withContext(Dispatchers.Main) {
            val loadingComponent = LoadingComponent(activity)
            loadingComponent.setContent("???????????? Lmmp ?????????....")
            loadingComponent.show()
            withContext(Dispatchers.IO) {
                FileUtil.deleteFile(File("${files.FilesDir}/lmmp"))
            }
            loadingComponent.dismiss()
        }
    }

    override suspend fun onCreateInfo(createInfo: CreateInfo) {
        super.onCreateInfo(createInfo)
        createInfo
            .addInputInfo(hint = "????????????")
            .addInputInfo(hint = "??????", title = "8080")
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
            Toasty.info(activity, "??????????????????").show()
            return false
        }

        val portInt = port.toIntOrNull()
        if (portInt == null) {
            Toasty.info(activity, "??????????????????").show()
            return false
        }

        if (portInt < 1025) {
            Toasty.info(activity, "?????????????????? 1025").show()
            return false
        }

        if (portInt > 65535) {
            Toasty.info(activity, "?????????????????? 65535").show()
            return false
        }

        val loadingComponent = LoadingComponent(activity)
        loadingComponent.setContent("??????????????????")
        loadingComponent.show()
        appCoroutine.launch(CoroutineName("CreateLmmpProjectCoroutine")) {
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
            workspace.setProjectId(lmmpProjectId)
            workspace.setCreationTime(TimeUtil.getFormattedTime())
            workspace.setOpenFile("$rootDir/index.php")
            workspace.set("port", port.toString())
            workspace.storeTo(workspaceFile)

            resources.assets.open("nginx.conf").bufferedReader().use {
                val nginxFile = File("$workspaceDir/nginx.conf")
                nginxFile.writeText(
                    it.readText()
                        .replace("\$PROJECT_DIR", rootDir.absolutePath)
                        .replace("\$PORT", port.toString())

                )
            }

            resources.assets.open("php.ini").bufferedReader().use {
                val phpIniFile = File("$workspaceDir/php.ini")
                phpIniFile.writeText(
                    it.readText()
                )
            }

            val lmmpProjectTemplateBufferedInputStream =
                resources.assets.open("lmmp-project-template.zip").buffered()
            val zipInputStream = ZipInputStream(lmmpProjectTemplateBufferedInputStream)
            FileUtil.extraZipInputStream(
                rootDir.absolutePath, zipInputStream
            ) { fileName ->
                withContext(Dispatchers.Main) {
                    loadingComponent.setContent("??????????????????: $fileName")
                }
            }

            withContext(Dispatchers.Main) {
                loadingComponent.dismiss()
                Toasty.success(activity, "??????????????????").show()

                activity.setResult(REFRESH_PROJECT, Intent().apply {
                    putExtra("action", REFRESH)
                })
                activity.finish()
            }
        }
    }

    private suspend fun extraLmmpRuntimeZip(loadingComponent: LoadingComponent) {
        return withContext(Dispatchers.IO) {
            val lmmpRuntimeBufferedInputStream =
                resources.assets.open("lmmp-runtime.zip").buffered()
            val zipInputStream = ZipInputStream(lmmpRuntimeBufferedInputStream)
            FileUtil.extraZipInputStream(
                files.FilesDir.absolutePath, zipInputStream
            ) { fileName ->
                withContext(Dispatchers.Main) {
                    loadingComponent.setContent("??????????????????: $fileName")
                }
            }

            withContext(Dispatchers.Main) {
                loadingComponent.dismiss()
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
                "php" -> phpIcon
            }
        }
        return super.getFileItemIcon(file)
    }

}