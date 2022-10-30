package com.mucheng.web.devops.handler

import android.content.Intent
import android.os.Build
import android.os.Process
import com.mucheng.web.devops.ui.activity.AppCrashHandlerActivity
import com.mucheng.web.devops.util.Context
import kotlin.reflect.full.staticProperties

object AppThreadCrashHandler : Thread.UncaughtExceptionHandler {

    override fun uncaughtException(t: Thread, e: Throwable) {
        e.printStackTrace()

        // 获取当前线程组名称
        val threadGroupName = t.threadGroup?.name ?: "null"

        // 获取当前线程名称
        val threadName = t.name

        // 构建一个 Map 进行 Report
        val info = buildList {
            add("线程中发生了异常....")
            add("设备信息:")

            // 遍历设备信息
            val buildClass = Build::class
            val members = buildClass.staticProperties
            for (member in members) {
                val name = member.name
                val value = member.get()
                add("$name: " + buildString {
                    if (value is Array<*>) {
                        append(value.contentToString())
                    } else {
                        append(value)
                    }
                })
            }

            add("")
            add("在线程组: $threadGroupName")
            add("在线程: $threadName")
            add("异常信息: ${e.stackTraceToString()}")
        }.joinToString(separator = "\n")

        // Application 的 Context
        val applicationContext = Context
        val intent = Intent(applicationContext, AppCrashHandlerActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra("info", info)
        intent.putExtra("type", "thread-crash")
        applicationContext.startActivity(intent)

        Process.killProcess(Process.myPid())
    }

}