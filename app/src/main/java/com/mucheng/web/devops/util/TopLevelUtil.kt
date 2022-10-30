package com.mucheng.web.devops.util

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.WindowCompat
import com.mucheng.web.devops.application.AppContext
import com.mucheng.web.devops.manager.PluginManager
import com.mucheng.web.devops.path.ProjectDir
import com.mucheng.web.devops.support.LanguageKeys
import com.mucheng.web.devops.support.LanguageSupport
import com.mucheng.webops.plugin.data.Workspace
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.IOException
import java.net.ConnectException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


inline val AppCoroutine: CoroutineScope
    get() {
        return AppContext.AppCoroutine
    }

inline val Context: Context
    get() {
        return AppContext.Context
    }

fun Context.isSystemInDarkTheme(): Boolean {
    val uiMode = resources.configuration.uiMode
    return (uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
}

fun AppCompatActivity.statusBarColor(
    color: Int = Color.TRANSPARENT,
    isDark: Boolean = isSystemInDarkTheme()
) {
    val window = window
    val decorView = window.decorView
    WindowCompat.setDecorFitsSystemWindows(window, true)
    val controller = WindowCompat.getInsetsController(window, decorView)
    controller.isAppearanceLightStatusBars = !isDark
    window.statusBarColor = color
}

@Suppress("NOTHING_TO_INLINE")
inline fun supportedText(keys: LanguageKeys): String {
    return LanguageSupport.getText(keys)
}

@Suppress("NOTHING_TO_INLINE")
inline fun request(url: String): Call {
    val okHttpClient = OkHttpClient()
    return okHttpClient.newCall(
        Request.Builder()
            .url(url)
            .get()
            .build()
    )
}

suspend inline fun Call.await(): okhttp3.ResponseBody {
    return suspendCancellableCoroutine { continuation ->
        enqueue(object : okhttp3.Callback {

            override fun onResponse(call: Call, response: okhttp3.Response) {
                val body = response.body
                if (body != null) {
                    continuation.resume(body)
                } else {
                    continuation.resumeWithException(ConnectException("Response body is null"))
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                continuation.resumeWithException(e)
            }

        })
    }
}

fun Workspace.isSupported(): Boolean {
    val plugins = PluginManager.getPlugins()
    val projectId = getProjectId()
    for (plugin in plugins) {
        if (plugin.isSupported(projectId)) {
            return true
        }
    }
    return false
}

fun Workspace.getFile(): File {
    return File("$ProjectDir/${getName()}/.WebDevOps/Workspace.xml")
}

fun Context.installApk(fileSavePath: String) {
    val file = File(fileSavePath)
    val intent = Intent(Intent.ACTION_VIEW)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    val data: Uri
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        data = FileProvider.getUriForFile(this, "com.mucheng.web.devops.fileProvider", file)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    } else {
        data = Uri.fromFile(file)
    }
    intent.setDataAndType(data, "application/vnd.android.package-archive")
    startActivity(intent)
}