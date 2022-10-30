package com.mucheng.web.devops.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout.SimpleDrawerListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.mucheng.editor.animation.CursorFadeAnimation
import com.mucheng.editor.animation.CursorScaleAnimation
import com.mucheng.editor.animation.CursorTranslationAnimation
import com.mucheng.editor.data.OperatorItem
import com.mucheng.editor.event.ContentChangedEvent
import com.mucheng.editor.layout.TextModelLayout
import com.mucheng.editor.view.MuCodeEditor
import com.mucheng.text.model.AndroidTextModelWorker
import com.mucheng.web.devops.R
import com.mucheng.web.devops.base.BaseActivity
import com.mucheng.web.devops.config.CursorAnimationType
import com.mucheng.web.devops.config.GlobalConfig
import com.mucheng.web.devops.config.MeasureType
import com.mucheng.web.devops.data.model.FileItem
import com.mucheng.web.devops.databinding.ActivityEditorBinding
import com.mucheng.web.devops.manager.PluginManager
import com.mucheng.web.devops.openapi.util.FileUtil
import com.mucheng.web.devops.openapi.view.LoadingComponent
import com.mucheng.web.devops.path.EditorTypefaceFile
import com.mucheng.web.devops.path.ProjectDir
import com.mucheng.web.devops.ui.adapter.FileSelectorAdapter
import com.mucheng.web.devops.ui.view.ComposableDialog
import com.mucheng.web.devops.ui.viewmodel.EditorViewModel
import com.mucheng.web.devops.util.AppCoroutine
import com.mucheng.web.devops.util.TypefaceUtil
import com.mucheng.web.devops.util.getFile
import com.mucheng.webops.plugin.data.ObservableValue
import com.mucheng.webops.plugin.data.Workspace
import com.mucheng.webops.plugin.data.info.ComponentInfo
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.withContext
import java.io.File

class EditorActivity : BaseActivity(), FileSelectorAdapter.FileSelectorCallback {

    companion object {
        private const val CLOSE = "关闭"
        private const val CLOSE_OTHER = "关闭其它"
        private const val RENAME = "重命名"
        private const val DELETE = "删除"
    }

    private lateinit var viewBinding: ActivityEditorBinding

    private val editorViewModel: EditorViewModel by viewModels()

    private val fileItemCoroutineLock = Mutex()

    private val openFileCoroutineLock = Mutex()

    private val statisticsCoroutineLock = Mutex()

    private val fileSelectorAdapter by lazy {
        FileSelectorAdapter(this, editorViewModel.list).also {
            it.setFileSelectorCallback(this)
        }
    }

    private val drawerListener = object : SimpleDrawerListener() {

        override fun onDrawerOpened(drawerView: View) {
            super.onDrawerOpened(drawerView)
            val functionManager = viewBinding.editor.functionManager
            functionManager.setOperatorPanelEnabled(false)
            functionManager.setToolOptionPanelEnabled(false)
            functionManager.setAutoCompletionPanelEnabled(false)
        }

        override fun onDrawerClosed(drawerView: View) {
            super.onDrawerClosed(drawerView)
            val functionManager = viewBinding.editor.functionManager
            functionManager.setOperatorPanelEnabled(true)
            functionManager.setToolOptionPanelEnabled(true)
            functionManager.setAutoCompletionPanelEnabled(true)
        }

    }

    private val progressStateFlow = MutableStateFlow(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityEditorBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        val path = intent.getStringExtra("path") ?: return finish()
        val workspace = Workspace().apply { loadFrom(File(path)) }

        editorViewModel.workspace = workspace

        val toolbar = viewBinding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            viewBinding.title.text = workspace.getName()
        }

        val recyclerView = viewBinding.fileRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = fileSelectorAdapter

        viewBinding.drawerLayout.addDrawerListener(drawerListener)

        editorViewModel.plugin = PluginManager.findPluginByProjectId(workspace.getProjectId())
        editorViewModel.setCurrentDir(File(workspace.getOpenFile()).parentFile!!)
        viewBinding.swipeRefreshLayout.setOnRefreshListener {
            val currentDir = editorViewModel.getCurrentDir()
            if (currentDir != null) {
                refresh(currentDir)
            }
        }

        viewBinding.drawerToolbar.setNavigationOnClickListener {
            val currentDir = editorViewModel.getCurrentDir()
            if (currentDir != null) {
                if (currentDir.absolutePath == "$ProjectDir/${workspace.getName()}") {
                    Toasty.info(this, "不允许离开工程目录").show()
                    return@setNavigationOnClickListener
                }
                val parent = currentDir.parentFile ?: return@setNavigationOnClickListener
                editorViewModel.setCurrentDir(parent)
                refresh(parent)
            }
        }
        viewBinding.drawerToolbar.setOnMenuItemClickListener {
            when (it.itemId) {

                R.id.add -> {
                    ComposableDialog(this)
                        .setTitle("创建文件")
                        .setComponents(
                            listOf(
                                ComponentInfo.InputInfo(
                                    title = null,
                                    hint = "文件名称",
                                    isSingleLine = true
                                ),
                                ComponentInfo.SelectorInfo(
                                    items = arrayOf(
                                        "文件",
                                        "文件夹"
                                    )
                                )
                            )
                        )
                        .onComplete { info ->
                            val inputInfo = info[0] as ComponentInfo.InputInfo
                            val selectorInfo = info[1] as ComponentInfo.SelectorInfo
                            val currentDir = editorViewModel.getCurrentDir()!!
                            val title = inputInfo.title ?: ""
                            val selected = selectorInfo.position

                            if (title.isEmpty()) {
                                Toasty.info(this, "文件名称不能为空").show()
                                return@onComplete false
                            }

                            if (File(currentDir, title).exists()) {
                                Toasty.info(this, "文件已存在").show()
                                return@onComplete false
                            }

                            if (selected == 0) {
                                if (title.contains('/')) {
                                    Toasty.info(this, "不合法的文件名").show()
                                    return@onComplete false
                                }
                                try {
                                    File(currentDir, title).createNewFile()
                                    refresh(currentDir)
                                } catch (e: Throwable) {
                                    Toasty.error(this, "创建文件失败: ${e.message}").show()
                                }
                            } else {
                                File("$currentDir/$title").mkdirs()
                                refresh(currentDir)
                            }

                            true
                        }
                        .setCancelable(false)
                        .setNeutralButton("取消", null)
                        .setPositiveButton("确定", null)
                        .show()
                }

                R.id.statistics_project -> {
                    statisticsProject()
                }

                R.id.statistics_file -> {
                    statisticsFile()
                }

            }
            true
        }

        val tabLayout = viewBinding.tabLayout
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab) {
                val file = tab.tag as? File ?: return
                openFile(file)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {
                val position = tab.position
                val beforeTab = tabLayout.getTabAt(position - 1)
                val afterTab = tabLayout.getTabAt(position + 1)

                val popupMenu = PopupMenu(this@EditorActivity, tab.view, Gravity.BOTTOM)
                val menu = popupMenu.menu
                menu.add(CLOSE)
                menu.add(CLOSE_OTHER)
                popupMenu.setOnMenuItemClickListener {
                    when (it.title) {
                        CLOSE -> {
                            if (beforeTab == null && afterTab == null) {
                                Toasty.info(this@EditorActivity, "不能关闭最后一个文件").show()
                            } else {
                                tabLayout.removeTabAt(position)
                            }
                        }

                        CLOSE_OTHER -> {
                            if (tabLayout.tabCount > 1) {
                                tabLayout.removeOnTabSelectedListener(this)
                                while (tabLayout.tabCount - 1 > position) {
                                    val index = tabLayout.tabCount - 1
                                    tabLayout.removeTabAt(index)
                                }

                                while (tab.position > 0) {
                                    tabLayout.removeTabAt(0)
                                }
                                tabLayout.selectTab(tab)
                                tabLayout.addOnTabSelectedListener(this)
                            }
                        }
                    }
                    true
                }
                popupMenu.show()
            }

        })

        val editor = viewBinding.editor
        initEditor(editor)
        editorViewModel.plugin!!.pluginMain.apply {
            onOpenProject(
                this@EditorActivity,
                workspace,
                editor,
                ObservableValue(progressStateFlow)
            )
        }

        val openFile = File(workspace.getOpenFile())
        if (openFile.exists() && openFile.isFile) {
            openFile(openFile) {
                if (!hasFileTab(openFile)) {
                    addFileTab(openFile)
                }
            }
        }

        val eventManager = editor.eventManager
        eventManager.subscribeEvent(ContentChangedEvent::class.java, object : ContentChangedEvent {

            override fun onContentChanged() {
                AppCoroutine.launch(Dispatchers.IO + CoroutineName("SaveFileCoroutine")) {
                    val currentFile = editorViewModel.getCurrentFile()
                    if (currentFile != null && currentFile.exists() && currentFile.isFile) {
                        val copiedTextModel =
                            AndroidTextModelWorker.copyFromTextModel(editor.getText()) {
                                isActive
                            }
                        AndroidTextModelWorker.outputToWriter(
                            copiedTextModel,
                            currentFile.writer()
                        ) {
                            isActive
                        }
                    }
                }
            }

        })
    }

    override fun onDestroy() {
        val workspace = editorViewModel.workspace!!
        val editor = viewBinding.editor
        editorViewModel.plugin!!.pluginMain.apply {
            onCloseProject(this@EditorActivity, workspace, editor)
        }
        super.onDestroy()
    }

    private fun initEditor(editor: MuCodeEditor) {
        val globalConfig = GlobalConfig.getInstance()
        editor.setDarkTheme(GlobalConfig.isDarkThemeEnabled())

        val functionManager = editor.functionManager
        functionManager.setAutoCompletionPanelEnabled(globalConfig.isAutoCompletionEnabled())
        functionManager.setOperatorPanelEnabled(globalConfig.isOperatorPanelEnabled())
        functionManager.setCursorVisibleAnimationEnabled(globalConfig.isCursorVisibleAnimationEnabled())
        functionManager.setCursorAnimationEnabled(globalConfig.isCursorAnimationEnabled())
        functionManager.setLineNumberEnabled(globalConfig.isLineNumberEnabled())
        functionManager.setStickyLineNumberEnabled(globalConfig.isStickyLineNumberEnabled())
        functionManager.setDividingLineEnabled(globalConfig.isDividingLineEnabled())

        val animationManager = editor.animationManager
        when (globalConfig.getCursorAnimationType()) {
            CursorAnimationType.TranslationAnimation -> {
                animationManager.setCursorAnimation(CursorTranslationAnimation(editor))
            }

            CursorAnimationType.ScaleAnimation -> {
                animationManager.setCursorAnimation(CursorScaleAnimation(editor))
            }

            CursorAnimationType.FadeAnimation -> {
                animationManager.setCursorAnimation(CursorFadeAnimation(editor))
            }
        }

        val layout = editor.layout
        if (layout is TextModelLayout) {
            if (globalConfig.getMeasureType() == MeasureType.LineVisible) {
                layout.setMeasureType(TextModelLayout.MeasureType.LineVisible)
            } else {
                layout.setMeasureType(TextModelLayout.MeasureType.LineRowVisible)
            }
        }

        val text = globalConfig.getOperatorInputCharTable().map {
            OperatorItem(it)
        }
        val operatorPanel = editor.languageManager.operatorPanel
        operatorPanel.clearOperatorItems()
        operatorPanel.addOperatorItem(OperatorItem("→", "    "))
        operatorPanel.addAllOperatorItem(text)
        operatorPanel.notifyOperatorItemChanged()

        val styleManager = editor.styleManager
        val typefacePath = globalConfig.getEditorTypefacePath()
        if (typefacePath == "null") {
            val typefaceFile = File(EditorTypefaceFile.absolutePath)
            try {
                if (typefaceFile.exists()) {
                    val typeface = Typeface.createFromFile(typefaceFile)
                    if (typeface != Typeface.DEFAULT) {
                        styleManager.setTypeface(typeface)
                    } else {
                        styleManager.setTypeface(TypefaceUtil.getTypeface())
                    }
                } else {
                    styleManager.setTypeface(TypefaceUtil.getTypeface())
                }
            } catch (e: Throwable) {
                styleManager.setTypeface(Typeface.DEFAULT)
            }
        } else {
            val typefaceFile = File(typefacePath)
            try {
                if (typefaceFile.exists()) {
                    val typeface = Typeface.createFromFile(typefaceFile)
                    if (typeface != Typeface.DEFAULT) {
                        styleManager.setTypeface(typeface)
                    } else {
                        styleManager.setTypeface(TypefaceUtil.getTypeface())
                    }
                } else {
                    styleManager.setTypeface(TypefaceUtil.getTypeface())
                }
            } catch (e: Throwable) {
                styleManager.setTypeface(Typeface.DEFAULT)
            }
        }
    }

    private fun statisticsProject() {
        val workspace = editorViewModel.workspace ?: return
        val loadingComponent = LoadingComponent(this)
        loadingComponent.setContent("正在统计项目....")
        loadingComponent.show()

        mainScope.launch(CoroutineName("StatisticsProjectCoroutine") + Dispatchers.IO) {
            statisticsCoroutineLock.lock()
            try {
                val rootDir = File("$ProjectDir/${workspace.getName()}")
                val totalBytes = FileUtil.getTotalBytes(rootDir)
                val formatBytes = FileUtil.formatBytes(totalBytes)
                val fileCount = FileUtil.getFileCount(rootDir)
                val builtText = buildString {
                    append("工程名称: ${workspace.getName()}").appendLine()
                    append("工程标识: ${workspace.getProjectId()}").appendLine()
                    append("创建时间: ${workspace.getCreationTime()}").appendLine()
                    append("起始文件: ${workspace.getOpenFile()}").appendLine()
                    append("磁盘占用: $formatBytes ($totalBytes Bytes)").appendLine()
                    append("总文件数: $fileCount")
                }
                withContext(Dispatchers.Main) {
                    loadingComponent.dismiss()
                    MaterialAlertDialogBuilder(this@EditorActivity)
                        .setTitle("工程统计")
                        .setMessage(builtText)
                        .setPositiveButton("确定", null)
                        .show()
                }
            } finally {
                statisticsCoroutineLock.unlock()
            }
        }
    }

    private fun statisticsFile() {
        val editor = viewBinding.editor
        val textModel = editor.getText()
        val loadingComponent = LoadingComponent(this)
        loadingComponent.setContent("正在统计文件....")
        loadingComponent.show()

        mainScope.launch(CoroutineName("StatisticsFileCoroutine") + Dispatchers.IO) {
            statisticsCoroutineLock.lock()
            try {
                val totalCapacity = textModel.capacity
                val formatCapacity = FileUtil.formatBytes(totalCapacity)
                val totalBytes = textModel.length.toLong()
                val formatBytes = FileUtil.formatBytes(totalBytes)
                val useMemoryBytes = totalCapacity + totalBytes
                val useMemoryFormatBytes = FileUtil.formatBytes(useMemoryBytes)
                val totalLine = textModel.lastLine
                val codeBlocks = editor.languageManager.autoCompletionPanel.getCodeBlocks()
                val builtText = buildString {
                    append("文件名称: ${editorViewModel.getCurrentFile()?.name}").appendLine()
                    append("花费容量: $formatCapacity ($totalCapacity bytes)").appendLine()
                    append("字节数: $formatBytes ($totalBytes bytes)").appendLine()
                    append("共用内存: $useMemoryFormatBytes ($useMemoryBytes bytes)").appendLine()
                    append("总行数: $totalLine").appendLine()
                    if (codeBlocks.isEmpty()) {
                        append("代码块: 无")
                    } else {
                        append("代码块: ").appendLine()
                        val tab = "\t"
                        for ((index, codeBlock) in codeBlocks.withIndex()) {
                            append("${codeBlock.language::class.java.simpleName}: ").appendLine()
                            append("{").appendLine()
                            append(tab).append("\"startLine\": ${codeBlock.startLine}").appendLine()
                            append(tab).append("\"startRow\": ${codeBlock.startRow}").appendLine()
                            append(tab).append("\"endLine\": ${codeBlock.endLine}").appendLine()
                            append(tab).append("\"endRow\": ${codeBlock.endRow}").appendLine()
                            append("}")
                            if (index < codeBlocks.lastIndex) {
                                repeat(2) { appendLine() }
                            }
                        }
                    }
                }
                withContext(Dispatchers.Main) {
                    loadingComponent.dismiss()
                    MaterialAlertDialogBuilder(this@EditorActivity)
                        .setTitle("文件统计")
                        .setMessage(builtText)
                        .setPositiveButton("确定", null)
                        .show()
                }
            } finally {
                statisticsCoroutineLock.unlock()
            }
        }
    }

    private fun addFileTab(file: File, isSelectedTab: Boolean = false) {
        val tabLayout = viewBinding.tabLayout
        val workspace = editorViewModel.workspace
        val tab = tabLayout.newTab()
        tab.text =
            file.absolutePath.replaceFirst(
                "$ProjectDir/${workspace?.getName()}/",
                ""
            )
        tab.tag = file
        tabLayout.addTab(tab)
        if (isSelectedTab) {
            tabLayout.selectTab(tab)
        }
    }

    private fun hasFileTab(file: File): Boolean {
        val tabLayout = viewBinding.tabLayout
        val len = tabLayout.tabCount
        var index = 0
        while (index < len) {
            val tab = tabLayout.getTabAt(index)!!
            val currentFile = tab.tag!! as File
            if (currentFile.absolutePath == file.absolutePath) {
                return true
            }
            ++index
        }
        return false
    }

    private fun findFileTab(file: File): TabLayout.Tab? {
        val tabLayout = viewBinding.tabLayout
        val len = tabLayout.tabCount
        var index = 0
        while (index < len) {
            val tab = tabLayout.getTabAt(index)!!
            val currentFile = tab.tag!! as File
            if (currentFile.absolutePath == file.absolutePath) {
                return tab
            }
            ++index
        }
        return null
    }

    override fun onStart() {
        super.onStart()
        val currentDir = editorViewModel.getCurrentDir()
        if (currentDir != null) {
            refresh(currentDir)
        }
    }

    private fun setDrawerTitle(currentDir: File) {
        val title = currentDir.absolutePath.replaceFirst("$ProjectDir/", "")
        viewBinding.drawerTitle.text = title
    }

    private fun refresh(currentDir: File) {
        mainScope.launch(CoroutineName("RefreshFileCoroutine")) {
            fileItemCoroutineLock.lock()
            try {
                viewBinding.swipeRefreshLayout.isRefreshing = true
                viewBinding.fileRecyclerView.isEnabled = false
                refreshFiles(currentDir)
                viewBinding.drawerTitle.text =
                    currentDir.absolutePath.replaceFirst("$ProjectDir/", "")
                viewBinding.swipeRefreshLayout.isRefreshing = false
                viewBinding.fileRecyclerView.isEnabled = true
            } finally {
                fileItemCoroutineLock.unlock()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private suspend fun refreshFiles(currentDir: File) {
        return withContext(Dispatchers.IO) {
            val fileItems = (currentDir.listFiles() ?: emptyArray()).toMutableList().map {
                FileItem(it.name, it, getFileItemIcon(it))
            }.sortedWith { o1, o2 ->
                if (o1.name == ".WebDevOps" && o1.file.isDirectory) {
                    -1
                } else if (o1.file.isFile && o2.file.isDirectory) {
                    1
                } else if (o1.file.isDirectory && o2.file.isFile) {
                    -1
                } else {
                    0
                }
            }

            editorViewModel.list.clear()
            editorViewModel.list.addAll(fileItems)
            withContext(Dispatchers.Main) {
                fileSelectorAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun getFileItemIcon(file: File): Drawable {
        val fileDrawable = ContextCompat.getDrawable(this, R.drawable.ic_file)!!
        val folderDrawable = ContextCompat.getDrawable(this, R.drawable.ic_folder)!!
        val plugin = editorViewModel.plugin!!
        val pluginMain = plugin.pluginMain
        return pluginMain.getFileItemIcon(file) ?: if (file.isFile) {
            fileDrawable
        } else {
            folderDrawable
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_activity_editor, menu)
        val menuItem = menu.findItem(R.id.run)
        val indicator: LinearProgressIndicator = viewBinding.indicator

        mainScope.launch(CoroutineName("IndicatorObserverCoroutine")) {
            progressStateFlow.collect {
                val progress = it
                menuItem.isEnabled = false
                indicator.visibility = View.VISIBLE
                indicator.progress = progress
                if (progress == 100) {
                    menuItem.isEnabled = true
                    indicator.visibility = View.GONE
                }
            }
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            android.R.id.home -> {
                viewBinding.drawerLayout.openDrawer(GravityCompat.START)
            }

            R.id.undo -> {
                viewBinding.editor.undo()
            }

            R.id.redo -> {
                viewBinding.editor.redo()
            }

            R.id.run -> {
                val workspace = editorViewModel.workspace!!
                val intent = Intent(this, ExecuteActivity::class.java)
                intent.putExtra("workspacePath", workspace.getFile().absolutePath)
                intent.putExtra("projectId", workspace.getProjectId())
                startActivity(intent)
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onFileItemClick(view: View, fileItem: FileItem, position: Int) {
        val file = fileItem.file
        if (file.isDirectory) {
            editorViewModel.setCurrentDir(file)
            refresh(file)
        } else {
            viewBinding.drawerLayout.closeDrawer(GravityCompat.START)
            if (!hasFileTab(file)) {
                addFileTab(file, isSelectedTab = true)
            }
        }
    }

    private fun openFile(file: File, callback: () -> Unit = {}) {
        val editor = viewBinding.editor
        val loadingComponent = LoadingComponent(this)
        loadingComponent.setContent("正在打开文件....")
        loadingComponent.show()

        mainScope.launch(CoroutineName("OpenFileCoroutine") + Dispatchers.IO) {
            openFileCoroutineLock.lock()
            try {
                editorViewModel.setCurrentFile(file)
                editor.setText(AndroidTextModelWorker.createFromReader(file.reader()) { isActive })
                withContext(Dispatchers.Main) {
                    loadingComponent.dismiss()
                    callback()
                    viewBinding.tabLayout.visibility = View.VISIBLE
                    editorViewModel.plugin!!.pluginMain.onOpenFile(
                        this@EditorActivity, file, editor
                    )
                }
            } finally {
                openFileCoroutineLock.unlock()
            }
        }
    }

    override fun onFileItemLongClick(view: View, fileItem: FileItem, position: Int) {
        val file = fileItem.file
        val popupMenu = PopupMenu(this, view, Gravity.BOTTOM or GravityCompat.END)
        val menu = popupMenu.menu
        menu.add(RENAME)
        menu.add(DELETE)
        popupMenu.setOnMenuItemClickListener {
            when (it.title) {
                RENAME -> {
                    ComposableDialog(this)
                        .setTitle("重命名文件")
                        .setComponents(
                            listOf(
                                ComponentInfo.InputInfo(
                                    title = null,
                                    hint = "文件名称",
                                    isSingleLine = true
                                )
                            )
                        )
                        .onComplete { list ->
                            val inputInfo = list[0] as ComponentInfo.InputInfo
                            val title = inputInfo.title ?: ""

                            if (title.isEmpty()) {
                                Toasty.info(this, "文件名不能为空").show()
                                return@onComplete false
                            }

                            if (title.contains('/')) {
                                Toasty.info(this, "不合法的文件名").show()
                                return@onComplete false
                            }

                            if (File("${file.parent}/$title").exists()) {
                                Toasty.info(this, "文件已存在").show()
                                return@onComplete false
                            }

                            val renamedFile = File("${file.parent}/$title")
                            FileUtil.renameTo(file, renamedFile)
                            fileItem.name = title
                            fileItem.file = renamedFile
                            fileItem.icon = getFileItemIcon(renamedFile)
                            fileSelectorAdapter.notifyItemChanged(position)

                            val tab = findFileTab(file)
                            if (tab != null) {
                                tab.text = file.absolutePath.replaceFirst(
                                    "$ProjectDir/${editorViewModel.workspace?.getName()}/",
                                    ""
                                )
                                tab.tag = renamedFile
                            }
                            true
                        }
                        .setCancelable(false)
                        .setNeutralButton("取消", null)
                        .setPositiveButton("确定", null)
                        .show()
                }

                DELETE -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("删除文件")
                        .setMessage("你确定删除文件 ${file.name} 吗?")
                        .setNeutralButton("取消", null)
                        .setPositiveButton("确定") { _, _ ->
                            FileUtil.deleteFile(file)
                            editorViewModel.list.removeAt(position)
                            fileSelectorAdapter.notifyItemRemoved(position)
                        }
                        .show()
                }
            }
            true
        }
        popupMenu.show()
    }

}