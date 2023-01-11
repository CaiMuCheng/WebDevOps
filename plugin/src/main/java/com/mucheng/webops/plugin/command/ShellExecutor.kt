package com.mucheng.webops.plugin.command

import android.annotation.SuppressLint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.lang.reflect.Field

object ShellExecutor {

    private var pidField: Field? = null

    @SuppressLint("DiscouragedPrivateApi")
    @Synchronized
    fun getPid(process: Process): Int {
        return try {
            if (pidField == null) {
                pidField = Class.forName("java.lang.UNIXProcess").getDeclaredField("pid")
                pidField!!.isAccessible = true
            }
            pidField!!.getInt(process)
        } catch (e: Throwable) {
            -1
        }
    }

    @Synchronized
    fun killProcess(process: Process): Boolean {
        val pid = getPid(process)
        if (pid != -1) {
            android.os.Process.killProcess(pid)
            return true
        }
        return false
    }

    suspend fun execSuspend(workDir: File, cmd: List<String>): Process {
        return withContext(Dispatchers.IO) {
            val process = ProcessBuilder()
                .directory(workDir)
                .command(cmd)
                .start()
            process
        }
    }

    suspend fun newExec(workDir: File): Process {
        return withContext(Dispatchers.IO) {
            ProcessBuilder()
                .directory(workDir)
                .start()
        }
    }

    fun killall(processName: String): Process? {
        return Runtime.getRuntime().exec("killall $processName")
    }

    fun exec(workDir: File, cmd: List<String>): Process? {
        return ProcessBuilder()
            .directory(workDir)
            .command(cmd)
            .start()
    }

}