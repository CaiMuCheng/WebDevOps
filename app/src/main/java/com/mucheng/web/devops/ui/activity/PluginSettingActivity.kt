package com.mucheng.web.devops.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.switchmaterial.SwitchMaterial
import com.mucheng.web.devops.base.BaseActivity
import com.mucheng.web.devops.databinding.ActivityPluginSettingBinding
import com.mucheng.web.devops.manager.PluginManager
import com.mucheng.web.devops.plugin.Plugin
import com.mucheng.web.devops.ui.adapter.PluginConfigAdapter
import com.mucheng.webops.plugin.data.PluginConfigItem

class PluginSettingActivity : BaseActivity(), PluginConfigAdapter.SettingItemCallback {

    private lateinit var viewBinding: ActivityPluginSettingBinding

    private var plugin: Plugin? = null

    private var pluginConfigAdapter: PluginConfigAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityPluginSettingBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val packageName = intent.getStringExtra("packageName") ?: return finish()
        plugin = PluginManager.findPluginByPackageName(packageName) ?: return finish()

        val toolbar = viewBinding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
        }

        val recyclerView = viewBinding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        pluginConfigAdapter = PluginConfigAdapter(this, requirePluginConfigItems(plugin!!)).also {
            it.setSettingItemCallback(this)
        }
        recyclerView.adapter = pluginConfigAdapter
    }

    override fun onSettingItemClick(
        view: View,
        settingItem: PluginConfigItem.ClickableItem,
        position: Int
    ) {
        if (plugin != null && pluginConfigAdapter != null) {
            try {
                plugin!!.pluginMain.onPluginConfigItemClick(
                    view,
                    settingItem,
                    position,
                    pluginConfigAdapter!!
                )
            } catch (e: Throwable) {
                MaterialAlertDialogBuilder(this)
                    .setTitle("无法委托事件给插件")
                    .setMessage("异常: ${e.stackTraceToString()}")
                    .setPositiveButton("确定", null)
                    .setCancelable(false)
                    .show()
            }
        }
    }

    override fun onSettingItemChecked(
        view: SwitchMaterial,
        settingItem: PluginConfigItem.SwitchItem,
        position: Int,
        isChecked: Boolean
    ) {
        if (plugin != null && pluginConfigAdapter != null) {
            try {
                plugin!!.pluginMain.onPluginConfigItemCheck(
                    view,
                    settingItem,
                    position,
                    isChecked,
                    pluginConfigAdapter!!
                )
            } catch (e: Throwable) {
                MaterialAlertDialogBuilder(this)
                    .setTitle("无法委托事件给插件")
                    .setMessage("异常: ${e.stackTraceToString()}")
                    .setPositiveButton("确定", null)
                    .setCancelable(false)
                    .show()
            }
        }
    }

    private fun requirePluginConfigItems(plugin: Plugin): List<PluginConfigItem> {
        return try {
            plugin.pluginMain.onCreatePluginConfig()
        } catch (e: Throwable) {
            MaterialAlertDialogBuilder(this)
                .setTitle("插件配置项无法加载")
                .setMessage("异常: ${e.stackTraceToString()}")
                .setPositiveButton("确定", null)
                .setCancelable(false)
                .show()
            emptyList()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            android.R.id.home -> {
                finish()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

}