package com.mucheng.web.devops.data.model

import android.graphics.drawable.Drawable

data class MainCommonItem(
    val icon: Drawable,
    val title: String,
    val simpleName: String,
    val version: String,
    val size: String,
    val description: String,
    val displayUrl: String
)