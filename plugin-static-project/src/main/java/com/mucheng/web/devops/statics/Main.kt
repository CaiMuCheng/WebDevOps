package com.mucheng.web.devops.statics

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
import com.mucheng.web.devops.openapi.editor.lang.xml.impl.XmlLanguage
import com.mucheng.web.devops.openapi.util.FileUtil
import com.mucheng.web.devops.openapi.util.TimeUtil
import com.mucheng.web.devops.openapi.view.LoadingComponent
import com.mucheng.webops.plugin.PluginActivity
import com.mucheng.webops.plugin.PluginMain
import com.mucheng.webops.plugin.check.ProjectCreationChecker
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

@Suppress("PrivatePropertyName")
class Main : PluginMain() {

    private lateinit var FrameworkDir: File

    private lateinit var workspace: Workspace

    private lateinit var htmlIcon: Drawable

    private lateinit var cssIcon: Drawable

    private lateinit var javaScriptIcon: Drawable

    private val baseClassPath = Main::class.java.name

    private val staticProjectId = "$baseClassPath/StaticProject"

    @Suppress("DEPRECATION")
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onInit(
        applicationContext: Context,
        resources: Resources,
        appCoroutine: CoroutineScope,
        files: Files
    ) {
        super.onInit(applicationContext, resources, appCoroutine, files)

        FrameworkDir = File("$StoreDir/frameworks")
        FrameworkDir.mkdirs()

        addProjects()

        this.htmlIcon = resources.getDrawable(R.drawable.ic_file_html)
        this.cssIcon = resources.getDrawable(R.drawable.ic_file_css)
        this.javaScriptIcon = resources.getDrawable(R.drawable.ic_file_js)
    }

    override fun onOpenProject(
        activity: AppCompatActivity,
        workspace: Workspace,
        editor: CodeEditor,
        observableProgress: ObservableValue<Int>
    ) {
        super.onOpenProject(activity, workspace, editor, observableProgress)
        this.workspace = workspace
        observableProgress.setValue(100)
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

            fileName.endsWith(".json") -> {
                editor.setEditorLanguage(JsonLanguage())
            }

            fileName.endsWith(".xml") -> {
                editor.setEditorLanguage(XmlLanguage())
            }

            else -> editor.setEditorLanguage(null)

        }

    }

    override fun onCreateExecuteActivity(): PluginActivity {
        return ExecuteProjectActivity(resources)
    }

    override suspend fun onCreateInfo(createInfo: CreateInfo) {
        super.onCreateInfo(createInfo)
        if (createInfo.projectId == staticProjectId) {
            createInfo
                .addInputInfo(hint = "工程名称")
                .addSelectorInfo(fetchFrameworks())
                .onConfirm { result ->
                    onConfirm(result.createInfo, createInfo.activity)
                }
        }
    }

    override fun isSupportedConfig(): Boolean {
        return false
    }

    private suspend fun fetchFrameworks(): Array<String> {
        return withContext(Dispatchers.IO) {
            val list: MutableList<String> = ArrayList()
            list.add("无框架")

            val frameworkFiles = FrameworkDir.listFiles() ?: emptyArray()
            for (frameworkFile in frameworkFiles) {
                if (frameworkFile.isFile && frameworkFile.name.endsWith(".zip")) {
                    // 属于框架
                    list.add(frameworkFile.nameWithoutExtension)
                }
            }
            list.toTypedArray()
        }
    }

    private fun onConfirm(createInfo: List<ComponentInfo>, activity: AppCompatActivity): Boolean {
        val projectNameInfo = createInfo[0] as ComponentInfo.InputInfo
        val frameworkInfo = createInfo[1] as ComponentInfo.SelectorInfo
        val projectName = projectNameInfo.title ?: ""
        val selectPosition = frameworkInfo.position
        val frameworkItem = frameworkInfo.items[selectPosition]
        val frameworkPath = "$FrameworkDir/$frameworkItem.zip"
        val useFramework = frameworkItem == "无框架"

        if (!ProjectCreationChecker.checkProjectName(
                applicationContext,
                files.ProjectDir,
                projectName
            )
        ) {
            return false
        }

        val loadingComponent = LoadingComponent(activity)
        loadingComponent.setContent("准备创建工程....")
        loadingComponent.show()

        appCoroutine.launch(CoroutineName("CreateStaticProjectCoroutine")) {
            if (selectPosition == 0) {
                createProjectWithoutFramework(projectName, loadingComponent, activity)
            } else {
                createProjectWithFramework(
                    projectName,
                    frameworkPath,
                    loadingComponent,
                    activity
                )
            }
        }
        return true
    }

    private suspend fun createProjectWithoutFramework(
        projectName: String,
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
            workspace.setProjectId(staticProjectId)
            workspace.setCreationTime(TimeUtil.getFormattedTime())
            workspace.setOpenFile("$rootDir/index.html")
            workspace.set("indexPage", "$rootDir/index.html")
            workspace.storeTo(workspaceFile)

            val bufferedInputStream =
                resources.assets.open("static-project-template.zip").buffered()
            val zipInputStream = ZipInputStream(bufferedInputStream)
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

    private suspend fun createProjectWithFramework(
        projectName: String,
        frameworkPath: String,
        loadingComponent: LoadingComponent,
        activity: AppCompatActivity
    ) {
        val projectDir = files.ProjectDir
        return withContext(Dispatchers.IO) {

        }
    }

    @Suppress("DEPRECATION")
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun addProjects() {
        addProject(
            "静态工程",
            staticProjectId,
            "包含 Html + Css + JavaScript 的 Web 静态工程",
            resources.getDrawable(R.drawable.ic_static_project)
        )
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