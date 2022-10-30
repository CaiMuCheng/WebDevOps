package com.mucheng.web.devops.ui.viewstate

import com.mucheng.web.devops.data.model.DisplayPluginItem

sealed class DisplayPluginState {

    object None : DisplayPluginState()

    object Loading : DisplayPluginState()

    data class Success(val displayPluginItem: DisplayPluginItem) : DisplayPluginState()

    data class Failure(val e: Throwable) : DisplayPluginState()

}