package com.mucheng.web.devops.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mucheng.web.devops.R
import com.mucheng.web.devops.base.BaseActivity
import com.mucheng.web.devops.databinding.ActivityManagePluginBinding
import com.mucheng.web.devops.manager.PluginManager
import com.mucheng.web.devops.openapi.util.FileUtil
import com.mucheng.web.devops.openapi.view.LoadingComponent
import com.mucheng.web.devops.path.PluginDir
import com.mucheng.web.devops.plugin.Plugin
import com.mucheng.web.devops.tryeval.catchAllWithUnit
import com.mucheng.web.devops.tryeval.tryEval
import com.mucheng.web.devops.ui.adapter.ManagePluginAdapter
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.nio.ByteBuffer

class ManagePluginActivity : BaseActivity(), ManagePluginAdapter.ManagePluginCallback {

    companion object {
        private const val RESULT = 200
    }

    private lateinit var viewBinding: ActivityManagePluginBinding

    private val plugins: MutableList<Plugin> = ArrayList()

    private val managePluginAdapter by lazy {
        ManagePluginAdapter(this, plugins).also {
            it.setManagePluginCallback(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityManagePluginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val toolbar = viewBinding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
        }

        plugins.clear()
        plugins.addAll(PluginManager.getPlugins())

        val recyclerView = viewBinding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = managePluginAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_manage_plugin, menu)
        return super.onCreateOptionsMenu(menu)
    }

    @Suppress("DEPRECATION")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            android.R.id.home -> {
                finish()
                return true
            }

            R.id.load_local_plugin -> {
                val intent = Intent(this, FileSelectorActivity::class.java)
                startActivityForResult(intent, RESULT)
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    @Suppress("DEPRECATION")
    @Deprecated(
        "Deprecated in Java", ReplaceWith(
            "super.onActivityResult(requestCode, resultCode, data)",
            "com.mucheng.web.devops.base.BaseActivity"
        )
    )
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT && resultCode == FileSelectorActivity.RESULT_CODE && data != null) {
            val path = data.getStringExtra("path") ?: return
            val file = File(path)
            if (!file.exists()) {
                Toasty.error(this, "此文件不存在").show()
                return
            }

            if (!file.name.endsWith(".apk")) {
                Toasty.info(this, "请选择 apk 文件").show()
                return
            }

            val packageName = PluginManager.getApkPackageName(file)
            if (packageName != null) {
                if (PluginManager.findPluginByPackageName(packageName) != null) {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("覆盖插件")
                        .setMessage("已加载的插件中含有此插件, 是否覆盖?")
                        .setNeutralButton("取消", null)
                        .setPositiveButton("确定") { _, _ ->
                            loadCopyLocalPlugin(file, packageName)
                        }.show()
                } else {
                    loadCopyLocalPlugin(file, packageName)
                }
            } else {
                Toasty.error(this@ManagePluginActivity, "加载插件失败: 无法解析插件").show()
            }
        }
    }

    private fun loadCopyLocalPlugin(file: File, packageName: String) {
        var isActive = true
        val loadingComponent = LoadingComponent(this)
        loadingComponent.setContent("准备加载插件....")
        loadingComponent.setPositiveButton("取消") { _, _ ->
            isActive = false
        }
        loadingComponent.show()

        val outputFile = File("$PluginDir/$packageName.apk")
        mainScope.launch(CoroutineName("LoadLocalPluginCoroutine") + Dispatchers.IO) {
            tryEval {
                val updatedPlugin = PluginManager.findPluginByPackageName(packageName)
                if (updatedPlugin != null) {
                    updatedPlugin.pluginMain.onUpdate(this@ManagePluginActivity)
                    PluginManager.removePlugin(updatedPlugin)
                }

                val inputStream = file.inputStream()
                val outputStream = outputFile.outputStream()
                val inputChannel = inputStream.channel
                val outputChannel = outputStream.channel

                inputStream.use {
                    outputStream.use {
                        inputChannel.use {
                            outputChannel.use {
                                val totalBytes: Long = file.length()
                                var currentBytes = 0f
                                val buffer = ByteBuffer.allocate(4096)
                                var len: Int
                                while (inputChannel.read(buffer).also { len = it } != -1) {
                                    if (!isActive) {
                                        FileUtil.deleteFile(outputFile)
                                        return@launch
                                    }
                                    withContext(Dispatchers.Main) {
                                        val progress = (currentBytes * 100 / totalBytes).toInt()
                                        loadingComponent.setContent("正在复制插件.... (${progress}%)")
                                    }
                                    buffer.flip()
                                    outputChannel.write(buffer)
                                    buffer.clear()
                                    currentBytes += len
                                }
                            }
                        }
                    }
                }

                val loadedPlugin = PluginManager.loadExternalPlugin(outputFile)
                loadedPlugin.pluginMain.onInstall(this@ManagePluginActivity)
                withContext(Dispatchers.Main) {
                    Toasty.success(this@ManagePluginActivity, "加载插件成功").show()
                    loadingComponent.dismiss()
                    val length = plugins.size
                    plugins.add(loadedPlugin)
                    managePluginAdapter.notifyItemInserted(length)
                }
            } catchAllWithUnit {
                withContext(Dispatchers.Main) {
                    it.printStackTrace()
                    Toasty.error(this@ManagePluginActivity, "加载插件失败: ${it.message}").show()
                    loadingComponent.dismiss()
                }
            }
        }
    }

    override fun onManagePlugin(view: View, plugin: Plugin, position: Int) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.gravity = GravityCompat.END or Gravity.BOTTOM

        val menuInflater = popupMenu.menuInflater
        val menu = popupMenu.menu
        if (plugin.pluginMain.isSupportedConfig()) {
            menuInflater.inflate(R.menu.menu_manage_plugin, menu)
        } else {
            menuInflater.inflate(R.menu.menu_manage_plugin_without_config, menu)
        }
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {

                R.id.deletePlugin -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("删除插件")
                        .setMessage("你确定要删除插件 ${plugin.pluginName} 吗?")
                        .setNeutralButton("取消", null)
                        .setPositiveButton("确定") { _, _ ->
                            deletePlugin(plugin, position)
                        }
                        .show()
                }

                R.id.pluginSetting -> {
                    val intent = Intent(this, PluginSettingActivity::class.java)
                    intent.putExtra("packageName", plugin.packageName)
                    startActivity(intent)
                }

            }
            true
        }
        popupMenu.show()
    }

    private fun deletePlugin(plugin: Plugin, position: Int) {
        val packageName = plugin.packageName
        if (packageName == "com.mucheng.web.devops.statics") {
            Toasty.info(this, "你不能删除默认插件").show()
            return
        }

        val loadingComponent = LoadingComponent(this)
        loadingComponent.setContent("正在删除插件....")
        loadingComponent.show()

        mainScope.launch(CoroutineName("DeletePluginCoroutine") + Dispatchers.IO) {
            val e = runCatching {
                FileUtil.deleteFile(File(plugin.installedPath))
                plugin.pluginMain.onUninstall(this@ManagePluginActivity)
                PluginManager.removePlugin(plugin)
            }.exceptionOrNull()
            withContext(Dispatchers.Main) {
                loadingComponent.dismiss()
                if (e == null) {
                    Toasty.success(this@ManagePluginActivity, "插件删除成功").show()
                    plugins.removeAt(position)
                    managePluginAdapter.notifyItemRemoved(position)
                } else {
                    Toasty.error(
                        this@ManagePluginActivity,
                        "插件删除失败: ${e.message}"
                    ).show()
                }
            }
        }
    }

}