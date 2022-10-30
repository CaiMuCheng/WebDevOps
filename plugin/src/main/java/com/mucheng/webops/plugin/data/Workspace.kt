package com.mucheng.webops.plugin.data

import java.io.File
import java.util.Properties

class Workspace {

    companion object {
        private const val NAME = "Name"
        private const val PROJECT_ID = "ProjectId"
        private const val CREATION_TIME = "CreationTime"
        private const val OPEN_FILE = "OpenFile"
    }

    private val props = Properties()

    fun setName(name: String) {
        props.setProperty(NAME, name)
    }

    fun getName(): String {
        return props.getProperty(NAME) ?: throw IllegalArgumentException()
    }

    fun setProjectId(projectId: String) {
        props.setProperty(PROJECT_ID, projectId)
    }

    fun getProjectId(): String {
        return props.getProperty(PROJECT_ID) ?: throw IllegalArgumentException()
    }

    fun setCreationTime(time: String) {
        props.setProperty(CREATION_TIME, time)
    }

    fun getCreationTime(): String {
        return props.getProperty(CREATION_TIME) ?: throw IllegalArgumentException()
    }

    fun setOpenFile(path: String) {
        props.setProperty(OPEN_FILE, path)
    }

    fun getOpenFile(): String {
        return props.getProperty(OPEN_FILE) ?: throw IllegalArgumentException()
    }

    fun set(key: String, value: String) {
        props.setProperty(key, value)
    }

    fun get(key: String): String? {
        return props.getProperty(key)
    }

    fun loadFrom(file: File) {
        props.loadFromXML(file.inputStream().buffered())
    }

    fun storeTo(file: File) {
        props.storeToXML(file.outputStream().buffered(), "UTF-8")
    }

    fun getMap(): Properties {
        return props
    }

}