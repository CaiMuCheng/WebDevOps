package com.mucheng.web.devops.handler

import android.content.Intent
import android.os.Build
import com.mucheng.web.devops.ui.activity.AppCrashHandlerActivity
import com.mucheng.web.devops.util.Context
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.full.staticProperties

object AppCoroutineCrashHandler : AbstractCoroutineContextElement(CoroutineExceptionHandler),
    CoroutineExceptionHandler {

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        exception.printStackTrace()

        // 在此处理协程异常
        // 获取当前线程
        val currentThread = Thread.currentThread()

        // 获取当前线程组名称
        val threadGroupName = currentThread.threadGroup?.name ?: "null"

        // 获取当前线程名称
        val threadName = currentThread.name

        // 获取协程名称
        val coroutineName = context[CoroutineName]?.name ?: "null"

        // 构建一个 Map 进行 Report
        val info = buildList {
            add("协程中发生了异常....")
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
            add("在协程: $coroutineName")
            add("异常信息: ${exception.stackTraceToString()}")
        }.joinToString(separator = "\n")

        // Application 的 Context
        val applicationContext = Context
        val intent = Intent(applicationContext, AppCrashHandlerActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra("info", info)
        intent.putExtra("type", "coroutine-crash")
        applicationContext.startActivity(intent)
    }

}