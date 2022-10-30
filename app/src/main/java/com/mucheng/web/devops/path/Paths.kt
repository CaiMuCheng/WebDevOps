package com.mucheng.web.devops.path

import android.annotation.SuppressLint

import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import com.mucheng.web.devops.util.Context
import java.io.File

@SuppressLint("SdCardPath")
val StorageDir = Environment.getExternalStorageDirectory() ?: File("/sdcard")

val CacheDir: File = Context.cacheDir

val FilesDir: File = Context.filesDir

val PluginDir: File = File("$FilesDir/plugin")

val ConfigDir: File = File("$FilesDir/config")

val OatDir: File = File("$FilesDir/oat")

val MainDir: File = File("$StorageDir/WebDevOps")

val ProjectDir: File = File("$MainDir/project")

val PluginStoreDir: File = File("$MainDir/store")

val TypefaceDir: File = File("$MainDir/typeface")

val OutCacheDir: File = File("$MainDir/cache")

val GlobalConfigFile: File = File("$ConfigDir/GlobalConfig.xml")

val EditorTypefaceFile: File = File("$TypefaceDir/RecMonoLinear-Regular.ttf")

@Suppress("SpellCheckingInspection")
private val NomediaFile: File = File("$MainDir/.nomedia")

@Suppress("FunctionName")
fun AppCompatActivity.CreateCoreFiles() {
    runCatching {
        // 处理 App 目录下的文件
        PluginDir.mkdirs()
        ConfigDir.mkdirs()
        OatDir.mkdirs()

        //处理 /storage/emulated/0 路径下的文件
        MainDir.mkdirs()
        ProjectDir.mkdirs()
        PluginStoreDir.mkdirs()
        TypefaceDir.mkdirs()
        OutCacheDir.mkdirs()
    }.exceptionOrNull()?.printStackTrace()

    runCatching {
        GlobalConfigFile.createNewFile()
        EditorTypefaceFile.createNewFile()
        NomediaFile.createNewFile()
    }
}

@Suppress("FunctionName")
fun AppCompatActivity.CopyNativePlugins() {
    runCatching {
        // 复制 Assets 下的插件到插件目录
        val assetsPlugins = arrayOf(
            "plugin-static-project-release.apk"
        )
        val nativeNames = arrayOf(
            "com.mucheng.web.devops.statics"
        )

        val assets = assets
        for ((index, plugin) in assetsPlugins.withIndex()) {
            val path = "plugin/$plugin"
            val bis = assets.open(path).buffered()
            val bos = File("$PluginDir/${nativeNames[index]}.apk").outputStream().buffered()
            bis.use {
                bos.use {
                    bis.copyTo(bos)
                }
            }
        }
    }.exceptionOrNull()?.printStackTrace()
}