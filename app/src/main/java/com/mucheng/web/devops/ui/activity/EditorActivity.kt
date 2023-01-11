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
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.mucheng.web.devops.R
import com.mucheng.web.devops.base.BaseActivity
import com.mucheng.web.devops.config.CursorAnimationType.*
import com.mucheng.web.devops.config.GlobalConfig
import com.mucheng.web.devops.data.model.FileItem
import com.mucheng.web.devops.databinding.ActivityEditorBinding
import com.mucheng.web.devops.manager.PluginManager
import com.mucheng.web.devops.openapi.editor.colorScheme.AtomOneDarkColorScheme
import com.mucheng.web.devops.openapi.editor.colorScheme.QuietLightColorScheme
import com.mucheng.web.devops.openapi.util.FileUtil
import com.mucheng.web.devops.openapi.view.LoadingComponent
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
import io.github.rosemoe.sora.event.ContentChangeEvent
import io.github.rosemoe.sora.text.ContentCreator
import io.github.rosemoe.sora.widget.CodeEditor
import io.github.rosemoe.sora.widget.component.EditorAutoCompletion
import io.github.rosemoe.sora.widget.component.Magnifier
import io.github.rosemoe.sora.widget.getComponent
import io.github.rosemoe.sora.widget.style.builtin.FadeCursorAnimator
import io.github.rosemoe.sora.widget.style.builtin.MoveCursorAnimator
import io.github.rosemoe.sora.widget.style.builtin.ScaleCursorAnimator
import io.github.rosemoe.sora.widget.subscribeEvent
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.sync.Mutex
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

        editorViewModel.plugin = PluginManager.findPluginByProjectId(workspace.getProjectId()) ?: return finish()
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
        val symbolTable = viewBinding.symbolTable
        val globalConfig = GlobalConfig.getInstance()
        val handle = buildList {
            add("→")
            addAll(globalConfig.getOperatorInputCharTable())
        }
        val typedArray = handle.toTypedArray()
        symbolTable.bindEditor(editor)
        symbolTable.addSymbols(
            typedArray,
            typedArray.mapIndexed { index, str -> if (index == 0) "\t" else str }.toTypedArray()
        )
        symbolTable.forEachButton {
            it.typeface = TypefaceUtil.getTypeface()
        }
        editor.apply {
            setLineSpacing(2f, 1.1f)
            val typeface = try {
                val fontPath = globalConfig.getEditorTypefacePath()
                if (fontPath == "null") Typeface.createFromAsset(
                    assets,
                    "font/JetBrainsMono-Regular.ttf"
                ) else Typeface.createFromFile(globalConfig.getEditorTypefacePath())
            } catch (e: Throwable) {
                TypefaceUtil.getTypeface()
            }
            typefaceLineNumber = typeface
            typefaceText = typeface
            nonPrintablePaintingFlags =
                CodeEditor.FLAG_DRAW_WHITESPACE_LEADING or CodeEditor.FLAG_DRAW_LINE_SEPARATOR or CodeEditor.FLAG_DRAW_WHITESPACE_IN_SELECTION
            colorScheme = if (GlobalConfig.isDarkThemeEnabled()) AtomOneDarkColorScheme() else QuietLightColorScheme()

            getComponent<Magnifier>().isEnabled = true
            getComponent<EditorAutoCompletion>().isEnabled = globalConfig.isAutoCompletionEnabled()

            if (!globalConfig.isOperatorPanelEnabled()) {
                viewBinding.symbolTableContainer.visibility = View.GONE
            }
            isCursorAnimationEnabled = globalConfig.isCursorAnimationEnabled()
            isLineNumberEnabled = globalConfig.isLineNumberEnabled()
            setPinLineNumber(globalConfig.isStickyLineNumberEnabled())

            cursorAnimator = when (globalConfig.getCursorAnimationType()) {
                TranslationAnimation -> MoveCursorAnimator(this)
                ScaleAnimation -> ScaleCursorAnimator(this)
                FadeAnimation -> FadeCursorAnimator(this)
            }

            isWordwrap = globalConfig.isWordWrapEnabled()
        }

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

        editor.subscribeEvent<ContentChangeEvent> { _, _ ->
            AppCoroutine.launch(CoroutineName("SaveFileCoroutine") + Dispatchers.IO) {
                val currentFile = editorViewModel.getCurrentFile() ?: return@launch
                try {
                    val bufferedWriter = currentFile.bufferedWriter()
                    bufferedWriter.use {
                        val content = editor.text
                        val lineCount = content.lineCount
                        var workLine = 0
                        while (workLine <= lineCount) {
                            val contentLine = content.getLine(workLine)
                            bufferedWriter.write(contentLine.toString())
                            bufferedWriter.flush()
                            if (workLine + 1 < lineCount) {
                                bufferedWriter.write(contentLine.lineSeparator.content)
                                bufferedWriter.flush()
                            }
                            ++workLine
                        }
                    }
                } catch (e: Throwable) {
                    if (e is CancellationException) {
                        throw e
                    }
                }
            }
        }

    }

    override fun onDestroy() {
        val workspace = editorViewModel.workspace!!
        val editor = viewBinding.editor
        editorViewModel.plugin!!.pluginMain.apply {
            onCloseProject(this@EditorActivity, workspace, editor)
        }
        editor.release()
        super.onDestroy()
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
        val loadingComponent = LoadingComponent(this)
        loadingComponent.setContent("正在统计文件....")
        loadingComponent.show()

        mainScope.launch(CoroutineName("StatisticsFileCoroutine") + Dispatchers.IO) {
            statisticsCoroutineLock.lock()
            try {
                val editor = viewBinding.editor
                val totalBytes = editor.text.length.toLong()
                val formatBytes = FileUtil.formatBytes(totalBytes)
                val totalLine = editor.text.lineCount + 1
                val builtText = buildString {
                    append("文件名称: ${editorViewModel.getCurrentFile()?.name}").appendLine()
                    append("字节数: $formatBytes ($totalBytes bytes)").appendLine()
                    append("总行数: $totalLine")
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
        val indicator = viewBinding.indicator
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
        val editor = viewBinding.editor
        when (item.itemId) {

            android.R.id.home -> {
                viewBinding.drawerLayout.openDrawer(GravityCompat.START)
            }

            R.id.undo -> {
                editor.undo()
            }

            R.id.redo -> {
                editor.redo()
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
            } else {
                val theFileTab = findFileTab(file)
                if (theFileTab?.isSelected == false) {
                    theFileTab.select()
                }
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
                val content = ContentCreator.fromReader(file.reader())
                withContext(Dispatchers.Main) {
                    editor.setText(content)
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
        val tabLayout = viewBinding.tabLayout
        val tab = tabLayout.getTabAt(tabLayout.selectedTabPosition) ?: return
        val target = tab.tag!! as File
        if (target.absolutePath == file.absolutePath) {
            return
        }

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

                            val theTab = findFileTab(file)
                            if (theTab != null) {
                                theTab.text = file.absolutePath.replaceFirst(
                                    "$ProjectDir/${editorViewModel.workspace?.getName()}/",
                                    ""
                                )
                                theTab.tag = renamedFile
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