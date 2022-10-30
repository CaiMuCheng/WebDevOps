package com.mucheng.web.devops.plugin

import androidx.annotation.Keep
import com.mucheng.webops.plugin.PluginMain

@Keep
data class Plugin(
    val pluginName: String,
    val packageName: String,
    val pluginMain: PluginMain,
    val installedPath: String,
    val versionCode: Long
) {

    fun isSupported(projectId: String): Boolean {
        val projects = pluginMain.getProjects()
        for (project in projects) {
            if (project.projectId == projectId) {
                return true
            }
        }
        return false
    }

}