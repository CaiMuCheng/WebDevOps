package com.mucheng.web.devops.data.model

import android.graphics.drawable.Drawable
import com.mucheng.web.devops.plugin.Plugin

data class DisplayPluginItem(
    val icon: Drawable,
    val title: String,
    val version: String,
    val versionCode: Long,
    val id: String,
    val size: String,
    val description: String,
    val bit: Int,
    val runMode: String,
    val platform: String,
    val downloadUrl: String,
    val samePlugin: Plugin?,
    val isDownloaded: Boolean,
    val neededUpdate: Boolean,
    val isSupported: Boolean
)