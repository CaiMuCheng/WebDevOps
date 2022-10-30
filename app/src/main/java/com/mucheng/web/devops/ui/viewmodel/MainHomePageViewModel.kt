package com.mucheng.web.devops.ui.viewmodel

import com.mucheng.web.devops.base.BaseViewModel
import com.mucheng.web.devops.data.depository.Depository
import com.mucheng.webops.plugin.data.Workspace

class MainHomePageViewModel : BaseViewModel() {

    val list: MutableList<Workspace> = ArrayList()

    suspend fun fetchWorkspaces(): List<Workspace> {
        return Depository.fetchWorkspaces()
    }

    suspend fun renameProject(workspace: Workspace, projectName: String) {
        return Depository.renameProject(workspace, projectName)
    }

    suspend fun deleteProject(workspace: Workspace) {
        return Depository.deleteProject(workspace)
    }

}