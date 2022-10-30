package com.mucheng.web.devops.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.mucheng.web.devops.base.BaseActivity
import com.mucheng.web.devops.databinding.ActivityAppCoroutineCrashBinding
import es.dmoral.toasty.Toasty

class AppCrashHandlerActivity : BaseActivity() {

    private lateinit var viewBinding: ActivityAppCoroutineCrashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityAppCoroutineCrashBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val info = intent.getStringExtra("info") ?: "Unknown"
        viewBinding.info.text = info
    }

    override fun onBackPressed() {
        val type = intent.getStringExtra("type") ?: "Unknown"
        if (type != "thread-crash") {
            super.onBackPressed()
        } else {
            Toasty.info(this, "所在线程崩溃, 不允许返回").show()
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val info = intent?.getStringExtra("info") ?: "Unknown"
        viewBinding.info.text = info
    }

}