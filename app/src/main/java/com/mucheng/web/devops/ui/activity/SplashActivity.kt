package com.mucheng.web.devops.ui.activity

import android.Manifest
import android.content.Intent
import android.os.Bundle
import com.mucheng.web.devops.base.BaseActivity
import com.mucheng.web.devops.databinding.ActivitySplashBinding
import com.mucheng.web.devops.support.LanguageKeys
import com.mucheng.web.devops.util.TypefaceUtil
import com.mucheng.web.devops.util.supportedText
import com.permissionx.guolindev.PermissionX
import es.dmoral.toasty.Toasty

class SplashActivity : BaseActivity() {

    private lateinit var viewBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // 配置 Toasty
        configureToasty()

        // 请求 Permission
        requestCorePermissions()
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun configureToasty() {
        Toasty.Config.getInstance()
            .allowQueue(true)
            .setToastTypeface(TypefaceUtil.getTypeface())
            .apply()
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun requestCorePermissions() {
        val permissions: List<String> = listOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

        PermissionX.init(this)
            .permissions(permissions)
            .onExplainRequestReason { scope, _, _ ->
                scope.showRequestReasonDialog(
                    permissions,
                    supportedText(LanguageKeys.PermissionRequestReason),
                    supportedText(LanguageKeys.PermissionRequestOK)
                )
            }.request { allGranted, _, _ ->
                if (allGranted) {
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    Toasty.error(this, supportedText(LanguageKeys.PermissionRequestCancel)).show()
                }
                finish()
            }
    }

}