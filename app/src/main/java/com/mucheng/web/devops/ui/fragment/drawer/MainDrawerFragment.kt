package com.mucheng.web.devops.ui.fragment.drawer

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.material.card.MaterialCardView
import com.mucheng.web.devops.R
import com.mucheng.web.devops.base.BaseFragment
import com.mucheng.web.devops.data.model.DrawerItem
import com.mucheng.web.devops.databinding.FragmentMainDrawerBinding
import com.mucheng.web.devops.support.LanguageKeys
import com.mucheng.web.devops.ui.adapter.MainDrawerAdapter
import com.mucheng.web.devops.ui.intent.MainIntent
import com.mucheng.web.devops.ui.viewmodel.MainViewModel
import com.mucheng.web.devops.ui.viewstate.MainState
import com.mucheng.web.devops.util.supportedText
import kotlinx.coroutines.launch

class MainDrawerFragment : BaseFragment(), MainDrawerAdapter.OnItemClickListener {

    private lateinit var viewBinding: FragmentMainDrawerBinding

    private val mainViewModel: MainViewModel by activityViewModels()

    private val adapter by lazy {
        MainDrawerAdapter(
            requireContext(),
            mainViewModel.drawerItemList
        ).also { it.setOnItemClickListener(this) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentMainDrawerBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = viewBinding.recyclerView
        recyclerView.layoutManager =
            FlexboxLayoutManager(requireContext(), FlexDirection.COLUMN, FlexWrap.NOWRAP)
        recyclerView.adapter = adapter

        setDrawerItemList()
        receiveState()
    }

    private fun setDrawerItemList() {
        sendIntent(MainIntent.SetDrawerItemIntent(fetchDrawerItemList()))
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun fetchDrawerItemList(): List<DrawerItem> {
        return listOf(
            DrawerItem(R.drawable.ic_home, supportedText(LanguageKeys.HomePage)),
            DrawerItem(R.drawable.ic_package, supportedText(LanguageKeys.CommonPage)),
            DrawerItem(R.drawable.ic_settings, supportedText(LanguageKeys.SettingPage))
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun receiveState() {
        viewLifecycleOwner.lifecycleScope.launch {
            mainViewModel.state.collect {
                when (it) {
                    // 当设置侧滑栏项目时执行
                    is MainState.SetDrawerItemState -> {
                        adapter.notifyDataSetChanged()
                    }

                    is MainState.Loading -> {}

                    else -> {}
                }
            }
        }
    }

    private fun sendIntent(intent: MainIntent) {
        lifecycleScope.launch {
            mainViewModel.intent.send(intent)
        }
    }

    override fun onItemClick(card: MaterialCardView, drawerItem: DrawerItem, position: Int) {
        when (drawerItem.text) {
            supportedText(LanguageKeys.HomePage) -> {
                sendIntent(MainIntent.SetPageIntent(MainState.PageEnum.HomePage))
            }
            supportedText(LanguageKeys.CommonPage) -> {
                sendIntent(MainIntent.SetPageIntent(MainState.PageEnum.CommonPage))
            }
            supportedText(LanguageKeys.SettingPage) -> {
                sendIntent(MainIntent.SetPageIntent(MainState.PageEnum.SettingPage))
            }
        }
        // 关闭状态栏
        sendIntent(MainIntent.CloseDrawer)
    }

}