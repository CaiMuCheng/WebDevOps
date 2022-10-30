package com.mucheng.web.devops.data.model

import android.graphics.drawable.Drawable
import java.io.File

data class FileItem(
    var name: String,
    var file: File,
    var icon: Drawable
)