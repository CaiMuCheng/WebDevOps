package com.mucheng.web.devops.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mucheng.web.devops.base.BaseActivity
import com.mucheng.web.devops.databinding.ActivitySelectNewProjectBinding
import com.mucheng.web.devops.manager.PluginManager
import com.mucheng.web.devops.openapi.view.LoadingComponent
import com.mucheng.web.devops.plugin.Plugin
import com.mucheng.web.devops.ui.adapter.SelectNewProjectAdapter
import com.mucheng.web.devops.ui.view.CreateInfoDialog
import com.mucheng.webops.plugin.data.CreateInfo
import com.mucheng.webops.plugin.data.Project
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.withContext

class SelectNewProjectActivity : BaseActivity(),
    SelectNewProjectAdapter.OnSelectNewProjectListener {

    private lateinit var viewBinding: ActivitySelectNewProjectBinding

    private val adapter by lazy {
        SelectNewProjectAdapter(
            this,
            projects
        ).also { it.setOnSelectNewProjectListener(this) }
    }

    private val lock = Mutex()

    private val projects: MutableList<Project> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySelectNewProjectBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val toolbar = viewBinding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
        }

        val recyclerView = viewBinding.recyclerView
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = adapter

        // 更新
        updateNewProject()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateNewProject() {
        mainScope.launch(CoroutineName("FetchNewProjectCoroutine")) {
            lock.lock()
            viewBinding.recyclerView.visibility = View.GONE
            viewBinding.includedLoadingLayout.root.visibility = View.VISIBLE
            try {
                projects.clear()
                projects.addAll(fetchNewProjectList())
                adapter.notifyDataSetChanged()
            } catch (e: Throwable) {
                e.printStackTrace()
                if (e is CancellationException) {
                    throw e
                }
            } finally {
                viewBinding.includedLoadingLayout.root.visibility = View.GONE
                viewBinding.recyclerView.visibility = View.VISIBLE
                lock.unlock()
            }
        }
    }

    private suspend fun fetchNewProjectList(): List<Project> {
        return withContext(Dispatchers.IO) {
            val projectList: MutableList<Project> = ArrayList()
            val plugins = PluginManager.getPlugins()
            for (plugin in plugins) {
                try {
                    projectList.addAll(plugin.pluginMain.getProjects())
                } catch (e: Throwable) {
                    e.printStackTrace()
                    withContext(Dispatchers.Main) {
                        MaterialAlertDialogBuilder(this@SelectNewProjectActivity)
                            .setTitle("无法加载插件 ${plugin.pluginName}")
                            .setMessage("异常: ${e.stackTraceToString()}")
                            .setCancelable(false)
                            .setPositiveButton("确定", null)
                            .show()
                    }
                }
            }
            projectList
        }
    }

    override fun receiveState() {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onSelectNewProject(view: View, project: Project, position: Int) {
        val loadingComponent = LoadingComponent(this)
        loadingComponent.setContent("添加创建信息....")
        loadingComponent.show()

        mainScope.launch(CoroutineName("AddCreateInfo")) {
            val projectId = project.projectId
            val plugin = PluginManager.findPluginByProjectId(projectId)
            if (plugin != null) {
                buildCreateInfo(plugin, projectId, project, loadingComponent)
            } else {
                loadingComponent.dismiss()
            }
        }
    }

    private suspend fun buildCreateInfo(
        plugin: Plugin,
        projectId: String,
        project: Project,
        loadingComponent: LoadingComponent
    ) {
        val createInfo = CreateInfo(projectId, this)
        withContext(Dispatchers.Main) {
            plugin.pluginMain.onCreateInfo(createInfo)
        }

        CreateInfoDialog(this)
            .setTitle("创建/${project.name}")
            .setCreateInfo(createInfo)
            .setCancelable(false)
            .setNeutralButton("取消", null)
            .show()

        loadingComponent.dismiss()
    }

}