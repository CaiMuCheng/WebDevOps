package com.mucheng.web.devops.ui.viewmodel

import com.mucheng.web.devops.base.BaseViewModel
import com.mucheng.web.devops.data.depository.Depository
import com.mucheng.web.devops.tryeval.catchAll
import com.mucheng.web.devops.tryeval.tryEval
import com.mucheng.web.devops.ui.viewstate.DisplayPluginState
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DisplayPluginViewModel : BaseViewModel() {

    private val _displayPluginItemStateFlow: MutableStateFlow<DisplayPluginState> =
        MutableStateFlow(DisplayPluginState.None)

    val displayPluginItemStateFlow: StateFlow<DisplayPluginState>
        get() {
            return _displayPluginItemStateFlow
        }

    private var url: String? = null

    fun fetchDisplayPluginItem(url: String) {
        viewModelScopeX.launch(CoroutineName("FetchDisplayPluginItemCoroutine")) {
            tryEval {
                _displayPluginItemStateFlow.value = DisplayPluginState.Loading
                _displayPluginItemStateFlow.value = DisplayPluginState.Success(
                    Depository.fetchDisplayPluginItem(url)
                )
            } catchAll { e ->
                _displayPluginItemStateFlow.value = DisplayPluginState.Failure(e)
            }
        }
    }

    fun setUrl(url: String) {
        this.url = url
    }

    fun getUrl(): String? {
        return url
    }

}