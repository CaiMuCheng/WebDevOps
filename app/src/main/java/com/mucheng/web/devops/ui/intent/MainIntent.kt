package com.mucheng.web.devops.ui.intent

import com.mucheng.web.devops.data.model.DrawerItem
import com.mucheng.web.devops.ui.viewstate.MainState

sealed class MainIntent {

    data class SetTitleIntent(val title: CharSequence) : MainIntent()

    data class SetPageIntent(val page: MainState.PageEnum) : MainIntent()

    data class SetDrawerItemIntent(val drawerItemList: List<DrawerItem>) : MainIntent()

    object CloseDrawer : MainIntent()

}
