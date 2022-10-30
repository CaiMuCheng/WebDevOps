package com.mucheng.web.devops.ui.viewmodel

import com.mucheng.web.devops.base.BaseFragment
import com.mucheng.web.devops.base.BaseViewModel
import com.mucheng.web.devops.data.depository.Depository
import com.mucheng.web.devops.data.model.DrawerItem
import com.mucheng.web.devops.data.model.MainCommonItem
import com.mucheng.web.devops.ui.intent.MainIntent
import com.mucheng.web.devops.ui.viewstate.MainState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {

    private val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState.NONE)

    private val _drawerItemList: MutableList<DrawerItem> = ArrayList()

    val intent: Channel<MainIntent> = Channel(Channel.UNLIMITED)

    private var lastPage: MainState.PageEnum? = MainState.PageEnum.HomePage

    val fragments: MutableList<BaseFragment> = ArrayList()

    val mainCommonItems: MutableList<MainCommonItem> = ArrayList()

    val state: StateFlow<MainState>
        get() {
            return _state
        }

    val drawerItemList: List<DrawerItem>
        get() {
            return _drawerItemList
        }

    init {
        viewModelScopeX.launch {
            intent.consumeAsFlow().collect {
                handleIntent(it)
            }
        }
    }

    suspend fun fetchMainCommonItems(): List<MainCommonItem> {
        return Depository.fetchMainCommonItem()
    }

    private fun handleIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.SetTitleIntent -> {
                _state.value = MainState.SetTitleState(intent.title)
            }

            is MainIntent.SetPageIntent -> {
                _state.value = MainState.SetPageState(intent.page, lastPage)
                lastPage = intent.page
            }

            is MainIntent.SetDrawerItemIntent -> {
                _drawerItemList.clear()
                _drawerItemList.addAll(intent.drawerItemList)
                _state.value = MainState.SetDrawerItemState(intent.drawerItemList)
            }

            MainIntent.CloseDrawer -> {
                _state.value = MainState.CloseDrawerState
            }
        }
    }

}