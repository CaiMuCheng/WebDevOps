package com.mucheng.web.devops.lmmp

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.textview.MaterialTextView
import com.mucheng.web.devops.openapi.R
import com.mucheng.web.devops.openapi.util.NetworkUtil
import com.mucheng.web.devops.openapi.view.WebViewX
import com.mucheng.webops.plugin.PluginActivity
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ExecuteProjectActivity(resources: Resources, val port: Int) :
    PluginActivity(resources) {

    private val progressStateFlow = MutableStateFlow(0)

    private lateinit var webView: WebViewX

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity.setContentView(R.layout.layout_execute_project)

        val toolbar: MaterialToolbar = findViewById(R.id.toolbar)
        val titleView: MaterialTextView = findViewById(R.id.title)
        val indicator: LinearProgressIndicator = findViewById(R.id.indicator)

        activity.setSupportActionBar(toolbar)
        activity.supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            titleView.text = "WebDevOps"
        }

        mainScope.launch(CoroutineName("IndicatorObserverCoroutine")) {
            progressStateFlow.collect {
                val progress = it
                indicator.visibility = View.VISIBLE
                indicator.progress = progress
                if (progress == 100) {
                    indicator.visibility = View.GONE
                }
            }
        }


        webView = findViewById(R.id.webView)
        webView.webChromeClient = object : WebChromeClient() {

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                progressStateFlow.value = newProgress
            }

            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
                titleView.text = title
            }

            override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
                val level = consoleMessage.messageLevel()
                val message = consoleMessage.message()
                if (level == ConsoleMessage.MessageLevel.ERROR) {
                    MaterialAlertDialogBuilder(activity)
                        .setTitle("未捕获的 JS 异常")
                        .setMessage(buildString {
                            consoleMessage.sourceId()
                            append("异常: $message").appendLine()
                            append("在行: ${consoleMessage.lineNumber()}").appendLine()
                            append("在源文件: ${consoleMessage.sourceId()}")
                        })
                        .setPositiveButton("确定", null)
                        .show()
                }
                return super.onConsoleMessage(consoleMessage)
            }

        }
        webView.loadUrl("http://localhost:$port")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        activity.menuInflater.inflate(R.menu.menu_execute_project, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.undo -> {
                if (webView.canGoBack()) {
                    webView.goBack()
                }
            }

            R.id.redo -> {
                if (webView.canGoForward()) {
                    webView.goForward()
                }
            }

            R.id.reload -> {
                webView.reload()
            }

            R.id.copyLink -> {
                if (webView.url != null) {
                    val manager =
                        activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clipData = ClipData.newPlainText("链接", webView.url)
                    manager.setPrimaryClip(clipData)
                    Toasty.success(activity, "复制成功").show()
                }
            }

            R.id.copyPrivateLink -> {
                val manager =
                    activity.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clipData =
                    ClipData.newPlainText("链接", "http://${NetworkUtil.localIPAddress}:$port")
                manager.setPrimaryClip(clipData)
                Toasty.success(activity, "复制成功").show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        webView.onResume()
    }

    override fun onPause() {
        super.onPause()
        webView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        webView.destroy()
    }

}