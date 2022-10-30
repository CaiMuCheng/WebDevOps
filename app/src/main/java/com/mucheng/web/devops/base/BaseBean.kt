package com.mucheng.web.devops.base

import java.util.*

open class BaseBean {

    private val properties = Properties()

    protected open fun set(key: Enum<*>, value: String) {
        properties.setProperty(key.name, value)
    }

    protected open fun get(key: Enum<*>): String? {
        return properties.getProperty(key.name)
    }

    fun getProperties(): Properties {
        return properties
    }

}