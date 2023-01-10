package com.mucheng.webops.plugin

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.Keep
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.switchmaterial.SwitchMaterial
import com.mucheng.web.devops.openapi.util.FileUtil
import com.mucheng.webops.plugin.data.CreateInfo
import com.mucheng.webops.plugin.data.Files
import com.mucheng.webops.plugin.data.ObservableValue
import com.mucheng.webops.plugin.data.PluginConfigItem
import com.mucheng.webops.plugin.data.Project
import com.mucheng.webops.plugin.data.Workspace
import io.github.rosemoe.sora.widget.CodeEditor
import kotlinx.coroutines.CoroutineScope
import java.io.File

/**
 * 项目插件接口
 * */
@Keep
abstract class PluginMain {

    companion object {
        const val REFRESH_PROJECT = 200
        const val REFRESH = "refresh"
    }

    private val projects: MutableList<Project> = ArrayList()

    protected lateinit var applicationContext: Context

    protected lateinit var resources: Resources

    protected lateinit var appCoroutine: CoroutineScope

    protected lateinit var files: Files

    protected lateinit var StoreDir: File

    protected var isAutoCompletionEnabled = false
        private set

    /**
     * 当插件初始化时调用
     * @param applicationContext 应用上下文
     * */
    @CallSuper
    open fun onInit(
        applicationContext: Context,
        resources: Resources,
        appCoroutine: CoroutineScope,
        files: Files
    ) {
        this.applicationContext = applicationContext
        this.resources = resources
        this.appCoroutine = appCoroutine
        this.files = files

        StoreDir = File("${files.PluginStoreDir}/${this::class.java.`package`?.name}")
        StoreDir.mkdirs()
    }

    open suspend fun onInstall(activity: AppCompatActivity) {}

    open suspend fun onUpdate(activity: AppCompatActivity) {}

    @CallSuper
    open suspend fun onUninstall(activity: AppCompatActivity) {
        FileUtil.deleteFile(StoreDir)
    }

    open fun onOpenProject(
        activity: AppCompatActivity,
        workspace: Workspace,
        editor: CodeEditor,
        observableProgress: ObservableValue<Int>
    ) {
    }

    open fun onCloseProject(
        activity: AppCompatActivity,
        workspace: Workspace,
        editor: CodeEditor
    ) {

    }

    open fun onOpenFile(
        activity: AppCompatActivity,
        file: File,
        editor: CodeEditor
    ) {

    }

    open suspend fun onRenameProject(
        renamedWorkspace: Workspace,
        beforePath: String,
        afterPath: String
    ) {
    }

    abstract fun onCreateExecuteActivity(): PluginActivity

    fun addProject(
        name: String,
        projectId: String,
        description: String? = null,
        icon: Drawable? = null
    ) {
        projects.add(Project(name, projectId, description, icon))
    }

    open suspend fun onCreateInfo(createInfo: CreateInfo) {}

    open fun onCreatePluginConfig(): List<PluginConfigItem> {
        return emptyList()
    }

    open fun onPluginConfigItemClick(
        view: View,
        settingItem: PluginConfigItem.ClickableItem,
        position: Int,
        adapter: RecyclerView.Adapter<*>
    ) {

    }

    open fun onPluginConfigItemCheck(
        view: SwitchMaterial,
        settingItem: PluginConfigItem.SwitchItem,
        position: Int,
        isChecked: Boolean,
        adapter: RecyclerView.Adapter<*>
    ) {

    }

    fun setAutoCompletionEnabled(isAutoCompletionEnabled: Boolean) {
        this.isAutoCompletionEnabled = isAutoCompletionEnabled
    }

    open fun isSupportedConfig(): Boolean {
        return false
    }

    open fun getFileItemIcon(file: File): Drawable? {
        return null
    }

    fun getProjects(): List<Project> {
        return projects
    }

}