package com.mucheng.web.devops.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import com.mucheng.web.devops.base.BaseActivity
import com.mucheng.web.devops.manager.PluginManager
import com.mucheng.web.devops.ui.viewmodel.ExecuteViewModel
import com.mucheng.webops.plugin.data.Workspace
import java.io.File

class ExecuteActivity : BaseActivity() {

    private val executeViewModel: ExecuteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val workspacePath = intent.getStringExtra("workspacePath") ?: return finish()
        val projectId = intent.getStringExtra("projectId") ?: return finish()

        val workspace = Workspace()
        workspace.loadFrom(File(workspacePath))
        executeViewModel.setWorkspace(workspace)

        val plugin = PluginManager.findPluginByProjectId(projectId) ?: return finish()
        executeViewModel.setPlugin(plugin)

        val pluginActivity = plugin.pluginMain.onCreateExecuteActivity()
        executeViewModel.setPluginActivity(pluginActivity)

        pluginActivity.onInit(this, mainScope, workspace)
        pluginActivity.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        executeViewModel.getPluginActivity()?.onStart()
    }

    override fun onRestart() {
        super.onRestart()
        executeViewModel.getPluginActivity()?.onRestart()
    }

    override fun onResume() {
        super.onResume()
        executeViewModel.getPluginActivity()?.onResume()
    }

    override fun onPause() {
        super.onPause()
        executeViewModel.getPluginActivity()?.onPause()
    }

    override fun onStop() {
        super.onStop()
        executeViewModel.getPluginActivity()?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        executeViewModel.getPluginActivity()?.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return executeViewModel.getPluginActivity()?.onCreateOptionsMenu(menu)
            ?: super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return executeViewModel.getPluginActivity()?.onOptionsItemSelected(item)
            ?: super.onOptionsItemSelected(item)
    }

}