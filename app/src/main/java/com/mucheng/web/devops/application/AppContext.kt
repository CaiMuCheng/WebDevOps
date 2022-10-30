package com.mucheng.web.devops.application

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.mucheng.web.devops.config.GlobalConfig
import com.mucheng.web.devops.handler.AppCoroutineCrashHandler
import com.mucheng.web.devops.handler.AppThreadCrashHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

@Suppress("MemberVisibilityCanBePrivate")
@SuppressLint("StaticFieldLeak")
class AppContext : Application() {

    companion object {

        @JvmStatic
        val AppCoroutine: CoroutineScope =
            CoroutineScope(Dispatchers.Main + CoroutineName("AppCoroutine") + SupervisorJob() + AppCoroutineCrashHandler)

        @JvmStatic
        lateinit var Context: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        Context = applicationContext

        // 初始化 CrashHandler
        Thread.setDefaultUncaughtExceptionHandler(AppThreadCrashHandler)

        if (GlobalConfig.isDarkThemeEnabled()) {
            useDarkTheme()
        } else {
            useLightTheme()
        }
    }


    @Suppress("NOTHING_TO_INLINE")
    private inline fun useDarkTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun useLightTheme() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

    override fun onTerminate() {
        AppCoroutine.cancel()
        super.onTerminate()
    }

}