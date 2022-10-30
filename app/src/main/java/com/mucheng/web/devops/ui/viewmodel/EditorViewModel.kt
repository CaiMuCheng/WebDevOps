package com.mucheng.web.devops.ui.viewmodel

import com.mucheng.web.devops.base.BaseViewModel
import com.mucheng.web.devops.data.model.FileItem
import com.mucheng.web.devops.plugin.Plugin
import com.mucheng.webops.plugin.data.Workspace
import java.io.File

class EditorViewModel : BaseViewModel() {

    private var currentDir: File? = null

    private var currentFile: File? = null

    val list: MutableList<FileItem> = ArrayList()

    var plugin: Plugin? = null

    var workspace: Workspace? = null

    fun setCurrentDir(currentDir: File) {
        this.currentDir = currentDir
    }

    fun getCurrentDir(): File? {
        return this.currentDir
    }

    fun setCurrentFile(file: File) {
        this.currentFile = file
    }

    fun getCurrentFile(): File? {
        return this.currentFile
    }

}