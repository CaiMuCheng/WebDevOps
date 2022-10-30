package com.mucheng.web.devops.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mucheng.web.devops.R
import com.mucheng.web.devops.base.BaseActivity
import com.mucheng.web.devops.base.BaseFragment
import com.mucheng.web.devops.databinding.ActivityMainBinding
import com.mucheng.web.devops.dialog.PrivacyPolicyDialog
import com.mucheng.web.devops.manager.PluginManager
import com.mucheng.web.devops.openapi.view.LoadingComponent
import com.mucheng.web.devops.path.CopyNativePlugins
import com.mucheng.web.devops.path.CreateCoreFiles
import com.mucheng.web.devops.support.LanguageKeys
import com.mucheng.web.devops.support.Updater
import com.mucheng.web.devops.ui.fragment.MainCommonPageFragment
import com.mucheng.web.devops.ui.fragment.MainHomePageFragment
import com.mucheng.web.devops.ui.fragment.MainSettingPageFragment
import com.mucheng.web.devops.ui.fragment.drawer.MainDrawerFragment
import com.mucheng.web.devops.ui.intent.MainIntent
import com.mucheng.web.devops.ui.viewmodel.MainViewModel
import com.mucheng.web.devops.ui.viewstate.MainState
import com.mucheng.web.devops.util.supportedText
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.launch


class MainActivity : BaseActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var viewBinding: ActivityMainBinding

    private var firstTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        CreateCoreFiles()
        CopyNativePlugins()

        val toolbar = viewBinding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
        }

        PrivacyPolicyDialog(this)
            .setNeutralButton("拒绝") { _, _ ->
                finish()
            }.require()

        Updater.updateIfNeeded(this)

        mainViewModel.fragments.clear()
        mainViewModel.fragments.addAll(
            listOf(
                MainHomePageFragment(),
                MainCommonPageFragment(),
                MainSettingPageFragment()
            )
        )

        setTitle()
        setupFragment()
        loadPlugins()
        receiveState()
    }

    override fun onBackPressed() {
        val secondTime = System.currentTimeMillis()
        if (secondTime - firstTime > 2000) {
            firstTime = secondTime
            Toasty.info(this, "再按一次退出程序").show()
        } else {
            super.onBackPressed()
        }
    }


    private fun loadPlugins() {
        PluginManager.loadPlugins {
            MaterialAlertDialogBuilder(this@MainActivity)
                .setTitle("此插件无法加载")
                .setMessage("异常: ${it.stackTraceToString()}")
                .setPositiveButton("确定", null)
                .setCancelable(false)
                .show()
            it.printStackTrace()
        }
    }

    private fun sendIntent(intent: MainIntent) {
        mainScope.launch {
            mainViewModel.intent.send(intent)
        }
    }

    private fun setTitle() {
        sendIntent(MainIntent.SetTitleIntent(supportedText(LanguageKeys.HomePage)))
    }

    @SuppressLint("CommitTransaction")
    private fun setupFragment() {
        val fragments = mainViewModel.fragments
        val containerFragments = supportFragmentManager.fragments
        val transaction = supportFragmentManager.beginTransaction()
        for (containerFragment in containerFragments) {
            if (containerFragment !is MainDrawerFragment) {
                transaction.remove(containerFragment)
            }
        }
        for (fragment in fragments) {
            transaction.add(R.id.container, fragment)
            if (fragment !is MainHomePageFragment) {
                transaction.hide(fragment)
            }
        }
        transaction.commit()
    }

    override fun receiveState() {
        val loadingComponent = LoadingComponent(this)
        lifecycleScope.launch {
            mainViewModel.state.collect {
                val intent = mainViewModel.intent
                when (it) {
                    // 当设置标题时
                    is MainState.SetTitleState -> {
                        viewBinding.title.text = it.title
                    }

                    // 当切换页面时
                    is MainState.SetPageState -> {
                        if (it.lastPageEnum == it.page) {
                            return@collect
                        }
                        val fragments = mainViewModel.fragments
                        when (it.page) {
                            MainState.PageEnum.HomePage -> {
                                navigatePage(fragments[0])
                                intent.send(MainIntent.SetTitleIntent(supportedText(LanguageKeys.HomePage)))
                            }

                            MainState.PageEnum.CommonPage -> {
                                navigatePage(fragments[1])
                                intent.send(MainIntent.SetTitleIntent(supportedText(LanguageKeys.CommonPage)))
                            }

                            MainState.PageEnum.SettingPage -> {
                                navigatePage(fragments[2])
                                intent.send(MainIntent.SetTitleIntent(supportedText(LanguageKeys.SettingPage)))
                            }
                        }
                    }

                    // 当关闭侧滑栏时
                    is MainState.CloseDrawerState -> {
                        viewBinding.drawerLayout.closeDrawer(GravityCompat.START)
                    }

                    is MainState.Loading -> {
                        loadingComponent.setContent(it.message)
                        loadingComponent.show()
                    }

                    // Skip these branches.
                    else -> {}
                }
            }
        }
    }

    private fun navigatePage(fragment: BaseFragment) {
        val fragments = mainViewModel.fragments.filter { it != fragment }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.show(fragment)
        transaction.setTransition(TRANSIT_FRAGMENT_FADE)
        for (hiddenFragment in fragments) {
            transaction.hide(hiddenFragment)
        }
        transaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                viewBinding.drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}