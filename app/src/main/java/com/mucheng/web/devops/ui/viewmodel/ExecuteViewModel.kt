package com.mucheng.web.devops.ui.viewmodel

import com.mucheng.web.devops.base.BaseViewModel
import com.mucheng.web.devops.plugin.Plugin
import com.mucheng.webops.plugin.PluginActivity
import com.mucheng.webops.plugin.data.Workspace

class ExecuteViewModel : BaseViewModel() {

    private var workspace: Workspace? = null

    private var plugin: Plugin? = null

    private var pluginActivity: PluginActivity? = null

    fun setWorkspace(workspace: Workspace) {
        this.workspace = workspace
    }

    fun getWorkspace(): Workspace? {
        return workspace
    }

    fun setPlugin(plugin: Plugin) {
        this.plugin = plugin
    }

    fun getPlugin(): Plugin? {
        return plugin
    }

    fun setPluginActivity(pluginActivity: PluginActivity) {
        this.pluginActivity = pluginActivity
    }

    fun getPluginActivity(): PluginActivity? {
        return pluginActivity
    }

}