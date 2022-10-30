package com.mucheng.webops.plugin.data

import android.graphics.drawable.Drawable
import androidx.annotation.Keep

@Keep
data class Project(
    val name: String,
    val projectId: String,
    val description: String?,
    val icon: Drawable?
)