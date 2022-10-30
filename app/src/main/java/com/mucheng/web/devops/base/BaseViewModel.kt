package com.mucheng.web.devops.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mucheng.web.devops.handler.AppCoroutineCrashHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.plus

private const val JOB_KEY =
    "androidx.lifecycle.ViewModelCoroutineScopeX.JOB_KEY"

abstract class BaseViewModel : ViewModel() {

    private var _viewModelScopeX: CoroutineScope? = null

    val viewModelScopeX: CoroutineScope
        get() {
            val scope = _viewModelScopeX
            if (_viewModelScopeX != null) {
                return scope!!
            }

            synchronized(this::class.java) {
                if (_viewModelScopeX == null) {
                    _viewModelScopeX =
                        viewModelScope + CoroutineName("ViewModelScopeX-${this::class.simpleName}") + AppCoroutineCrashHandler
                }
            }
            return _viewModelScopeX!!
        }

}