package com.mucheng.web.devops.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mucheng.web.devops.base.BaseActivity
import com.mucheng.web.devops.data.model.DisplayPluginItem
import com.mucheng.web.devops.databinding.ActivityDisplayPluginBinding
import com.mucheng.web.devops.manager.PluginManager
import com.mucheng.web.devops.openapi.util.FileUtil
import com.mucheng.web.devops.openapi.view.LoadingComponent
import com.mucheng.web.devops.path.PluginDir
import com.mucheng.web.devops.support.AbiSupport
import com.mucheng.web.devops.tryeval.catchAllWithUnit
import com.mucheng.web.devops.tryeval.tryEval
import com.mucheng.web.devops.ui.viewmodel.DisplayPluginViewModel
import com.mucheng.web.devops.ui.viewstate.DisplayPluginState
import com.mucheng.web.devops.util.await
import com.mucheng.web.devops.util.request
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.nio.ByteBuffer
import java.nio.channels.Channels

class DisplayPluginActivity : BaseActivity() {

    private lateinit var viewBinding: ActivityDisplayPluginBinding

    private val displayPluginViewModel: DisplayPluginViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityDisplayPluginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val title = intent.getStringExtra("title") ?: return finish()
        val url = intent.getStringExtra("url") ?: return finish()

        val toolbar = viewBinding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
            viewBinding.title.text = title
        }

        lifecycleScope.launch {
            displayPluginViewModel.displayPluginItemStateFlow.collect {
                when (it) {
                    is DisplayPluginState.Loading -> {
                        viewBinding.includedDisplayPluginLayout.root.visibility = View.GONE
                        viewBinding.includedLoadingLayout.root.visibility = View.VISIBLE
                    }

                    is DisplayPluginState.Success -> {
                        val displayPluginItem = it.displayPluginItem
                        viewBinding.includedLoadingLayout.root.visibility = View.GONE
                        initViewByDisplayPluginItem(displayPluginItem)
                        viewBinding.includedDisplayPluginLayout.root.visibility = View.VISIBLE
                    }

                    is DisplayPluginState.Failure -> {
                        val e = it.e
                        e.printStackTrace()
                        viewBinding.includedLoadingLayout.root.visibility = View.GONE
                        viewBinding.errorLayout.visibility = View.VISIBLE
                        viewBinding.reason.text = "异常: ${e.message}"
                    }

                    DisplayPluginState.None -> {}
                }
            }
        }
        displayPluginViewModel.setUrl(url)
        displayPluginViewModel.fetchDisplayPluginItem(url)
    }

    @SuppressLint("SetTextI18n")
    private fun initViewByDisplayPluginItem(item: DisplayPluginItem) {
        val root = viewBinding.includedDisplayPluginLayout
        Glide.with(this)
            .load(item.icon)
            .into(root.icon)

        root.title.text = item.title
        root.version.text = item.version
        root.size.text = item.size
        root.bit.text = "${item.bit} Bit"
        root.runtimeMode.text = item.runMode
        root.architecture.text = item.platform
        root.description.text = item.description

        initDownloadButton(item)
    }

    private fun initDownloadButton(item: DisplayPluginItem) {
        val root = viewBinding.includedDisplayPluginLayout
        if (!item.isSupported) {
            root.downloadText.text = "不支持"
            root.download.setOnClickListener {
                showUnsupportedDialog(item)
            }
            return
        }

        if (item.neededUpdate) {
            root.downloadText.text = "更新"
            root.download.setOnClickListener {
                MaterialAlertDialogBuilder(this)
                    .setTitle("更新插件")
                    .setMessage("你确定要将当前插件更新吗?")
                    .setNeutralButton("取消", null)
                    .setPositiveButton("确定") { _, _ ->
                        showUpdateDialog(item)
                    }
                    .show()
            }
            return
        }

        if (item.isDownloaded) {
            root.downloadText.text = "已下载"
            root.download.setOnClickListener(null)
            return
        }

        root.downloadText.text = "下载"
        root.download.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("下载插件")
                .setMessage("你确定要下载插件 ${item.title} 吗?")
                .setNeutralButton("取消", null)
                .setPositiveButton("确定") { _, _ ->
                    showDownloadDialog(item)
                }
                .show()
        }
    }

    private fun showUnsupportedDialog(item: DisplayPluginItem) {
        MaterialAlertDialogBuilder(this)
            .setTitle("不支持的插件")
            .setMessage(buildString {
                append("这个插件在你的设备上不被支持").appendLine()
                append("详情: ").appendLine()
                append("插件的目标架构: ${item.platform}").appendLine()
                append("当前的目标架构: ${AbiSupport.getCPUAbi()}")
            })
            .setPositiveButton("确定", null)
            .show()
    }

    private fun showUpdateDialog(item: DisplayPluginItem) {
        var isActive = true
        val loadingDialog = LoadingComponent(this)
        loadingDialog.setContent("准备更新插件....")
        loadingDialog.setPositiveButton("取消") { _, _ ->
            isActive = false
        }
        loadingDialog.show()

        val plugin = item.samePlugin!!
        val installedPath = plugin.installedPath

        mainScope.launch(CoroutineName("UpdatePluginCoroutine") + Dispatchers.IO) {
            plugin.pluginMain.onUpdate(this@DisplayPluginActivity)
            PluginManager.removePlugin(plugin)

            val downloadUrl = item.downloadUrl
            tryEval {
                val body = request(downloadUrl).await()
                val inputStream = body.byteStream().buffered()
                val outputStream = File(installedPath).outputStream()
                val inputChannel = Channels.newChannel(inputStream)
                val outputChannel = outputStream.channel
                val total = body.contentLength()
                var currentBytes = 0f
                val buffer = ByteBuffer.allocate(4096)
                var len: Int
                inputStream.use {
                    outputStream.use {
                        inputChannel.use {
                            outputChannel.use {
                                while (inputChannel.read(buffer).also { len = it } != -1) {
                                    if (!isActive) {
                                        FileUtil.deleteFile(File(installedPath))
                                        return@launch
                                    }
                                    withContext(Dispatchers.Main) {
                                        val progress = (currentBytes / total * 100).toInt()
                                        loadingDialog.setContent("正在更新插件.... (${progress}%)")
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
                PluginManager.loadExternalPlugin(File(installedPath))
                withContext(Dispatchers.Main) {
                    loadingDialog.dismiss()
                    Toasty.success(this@DisplayPluginActivity, "更新成功").show()
                    displayPluginViewModel.fetchDisplayPluginItem(displayPluginViewModel.getUrl()!!)
                }
            } catchAllWithUnit {
                FileUtil.deleteFile(File(installedPath))
                withContext(Dispatchers.Main) {
                    loadingDialog.dismiss()
                    Toasty.error(this@DisplayPluginActivity, "更新失败: ${it.message}").show()
                    displayPluginViewModel.fetchDisplayPluginItem(displayPluginViewModel.getUrl()!!)
                }
            }
        }
    }

    private fun showDownloadDialog(item: DisplayPluginItem) {
        var isActive = true
        val loadingDialog = LoadingComponent(this)
        loadingDialog.setContent("准备下载插件....")
        loadingDialog.setPositiveButton("取消") { _, _ ->
            isActive = false
        }
        loadingDialog.show()

        val installedPath = "$PluginDir/${item.id}.apk"
        mainScope.launch(CoroutineName("DownloadPluginCoroutine") + Dispatchers.IO) {
            val downloadUrl = item.downloadUrl
            tryEval {
                val body = request(downloadUrl).await()
                val inputStream = body.byteStream()
                val outputStream = File(installedPath).outputStream()
                val inputChannel = Channels.newChannel(inputStream)
                val outputChannel = outputStream.channel
                val total: Long = body.contentLength()
                var currentBytes = 0f
                val buffer = ByteBuffer.allocate(4096)
                var len: Int
                inputStream.use {
                    outputStream.use {
                        inputChannel.use {
                            outputChannel.use {
                                while (inputChannel.read(buffer).also { len = it } != -1) {
                                    if (!isActive) {
                                        FileUtil.deleteFile(File(installedPath))
                                        return@launch
                                    }
                                    withContext(Dispatchers.Main) {
                                        val progress = (currentBytes / total * 100).toInt()
                                        loadingDialog.setContent("正在下载插件.... (${progress}%)")
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
                val loadedPlugin = PluginManager.loadExternalPlugin(File(installedPath))
                loadedPlugin.pluginMain.onInstall(this@DisplayPluginActivity)
                withContext(Dispatchers.Main) {
                    loadingDialog.dismiss()
                    Toasty.success(this@DisplayPluginActivity, "下载成功").show()
                    displayPluginViewModel.fetchDisplayPluginItem(displayPluginViewModel.getUrl()!!)
                }
            } catchAllWithUnit {
                FileUtil.deleteFile(File(installedPath))
                withContext(Dispatchers.Main) {
                    loadingDialog.dismiss()
                    Toasty.error(this@DisplayPluginActivity, "下载失败: ${it.message}").show()
                    displayPluginViewModel.fetchDisplayPluginItem(displayPluginViewModel.getUrl()!!)
                }
            }
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