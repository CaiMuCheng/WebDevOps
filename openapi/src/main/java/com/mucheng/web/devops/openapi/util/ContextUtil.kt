package com.mucheng.web.devops.openapi.util

import android.content.Context
import android.content.Intent
import android.net.Uri


object ContextUtil {

    fun openBrowser(context: Context, url: String, title: String = "通过浏览器打开") {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        val chooser = Intent.createChooser(intent, title)
        context.startActivity(chooser)
    }

}