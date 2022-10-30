package com.mucheng.web.devops.ui.viewstate

import com.mucheng.web.devops.data.model.DrawerItem

sealed class MainState {

    object NONE : MainState()

    data class SetTitleState(val title: CharSequence) : MainState()

    data class SetPageState(val page: PageEnum, val lastPageEnum: PageEnum?) : MainState()

    enum class PageEnum {
        HomePage,
        CommonPage,
        SettingPage
    }

    data class SetDrawerItemState(val drawerItemList: List<DrawerItem>) : MainState()

    object CloseDrawerState : MainState()

    data class Loading(val message: String) : MainState()


}