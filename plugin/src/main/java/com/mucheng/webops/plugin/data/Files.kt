package com.mucheng.webops.plugin.data

import java.io.File

data class Files(
    val StorageDir: File,
    val CacheDir: File,
    val FilesDir: File,
    val PluginDir: File,
    val OatDir: File,
    val MainDir: File,
    val ProjectDir: File,
    val PluginStoreDir: File
)