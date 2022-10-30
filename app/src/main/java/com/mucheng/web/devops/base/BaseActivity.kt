package com.mucheng.web.devops.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mucheng.web.devops.handler.AppCoroutineCrashHandler
import com.mucheng.web.devops.util.statusBarColor
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.plus


@Suppress("MemberVisibilityCanBePrivate", "LeakingThis")
open class BaseActivity : AppCompatActivity() {

    val mainScope: CoroutineScope =
        MainScope() + CoroutineName("MainScopeCoroutine-${this::class.simpleName}") + AppCoroutineCrashHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statusBarColor()
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }

    protected open fun receiveState() {}

}