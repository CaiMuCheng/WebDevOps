package com.mucheng.web.devops.ui.activity

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mucheng.web.devops.BuildConfig
import com.mucheng.web.devops.R
import com.mucheng.web.devops.base.BaseActivity
import com.mucheng.web.devops.data.model.AboutItem
import com.mucheng.web.devops.data.model.ClickableAboutItem
import com.mucheng.web.devops.data.model.TitleAboutItem
import com.mucheng.web.devops.databinding.ActivityAboutBinding
import com.mucheng.web.devops.dialog.PrivacyPolicyDialog
import com.mucheng.web.devops.dialog.ThanksDialog
import com.mucheng.web.devops.openapi.util.ContextUtil.openBrowser
import com.mucheng.web.devops.ui.adapter.AboutAdapter
import es.dmoral.toasty.Toasty

@Suppress("SpellCheckingInspection")
class AboutActivity : BaseActivity(), AboutAdapter.AboutItemCallback {

    @Suppress("unused")
    companion object {
        private const val TOPIC_DISCUSSION = "官方交流群"
        private const val WEB_PAGE = "软件官网"
        private const val DEVELOPER = "开发者"
        private const val PRIVACY_POLICY = "隐私政策"
        private const val THANKS = "感谢名单"
        private const val SPONSOR = "爱发电赞助"

        private const val RELATION_ZHUAN_ZHU_CI_YUAN = "专注次元"

        private const val LIB_KTX = "Ktx"
        private const val LIB_APPCOMPAT = "AppCompat"
        private const val LIB_MATERIAL = "Material2"
        private const val LIB_TOASTY = "Toasty v1.5.2"
        private const val LIB_MU_CODE_EDITOR = "MuCodeEditor v1.2.1.5"
        private const val LIB_TEXT_MODEL = "TextModel"
        private const val LIB_RXJAVA = "RxJava v2.1.1"
        private const val LIB_LEAN_CLOUD = "LeanCloud v8.2.10"
        private const val LIB_SWIPE_REFRESH_LAYOUT = "SwipeRefreshLayout v1.1.0"
        private const val LIB_PERMISSION_X = "PermissionX v1.6.4"
        private const val COPYRIGHT = "2022 Mu Team All rights reserved."
    }

    private lateinit var viewBinding: ActivityAboutBinding

    private val adapter by lazy {
        AboutAdapter(this, requireAboutItemList()).apply {
            setAboutItemCallback(this@AboutActivity)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val toolbar = viewBinding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        viewBinding.version.text = "v ${BuildConfig.VERSION_NAME}"

        val recyclerView = viewBinding.recyclerView
        recyclerView.layoutManager =
            object : LinearLayoutManager(this, VERTICAL, false) {

                override fun canScrollVertically(): Boolean {
                    return false
                }

            }
        recyclerView.adapter = adapter
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

    private fun requireAboutItemList(): List<AboutItem> {
        return listOf(
            TitleAboutItem("关于软件"),
            ClickableAboutItem(R.drawable.ic_topic_discussion, TOPIC_DISCUSSION),
            ClickableAboutItem(R.drawable.ic_web_page, WEB_PAGE),
            ClickableAboutItem(R.drawable.ic_every_user, DEVELOPER),
            ClickableAboutItem(R.drawable.ic_agreement, PRIVACY_POLICY),
            ClickableAboutItem(R.drawable.ic_hands, THANKS),
            ClickableAboutItem(R.drawable.ic_thumbs_up, SPONSOR),

            TitleAboutItem("合作伙伴"),
            ClickableAboutItem(R.mipmap.zhuanzhuciyuantubiao, RELATION_ZHUAN_ZHU_CI_YUAN, false),

            TitleAboutItem("依赖使用"),
            ClickableAboutItem(R.drawable.ic_android, LIB_KTX),
            ClickableAboutItem(R.drawable.ic_android, LIB_APPCOMPAT),
            ClickableAboutItem(R.drawable.ic_android, LIB_MATERIAL),
            ClickableAboutItem(R.mipmap.toasty, LIB_TOASTY, false),
            ClickableAboutItem(R.drawable.ic_write, LIB_MU_CODE_EDITOR),
            ClickableAboutItem(R.drawable.ic_cube_three, LIB_TEXT_MODEL),
            ClickableAboutItem(R.drawable.ic_connection_point, LIB_RXJAVA),
            ClickableAboutItem(R.mipmap.leancloud, LIB_LEAN_CLOUD, false),
            ClickableAboutItem(R.drawable.ic_loading_four, LIB_SWIPE_REFRESH_LAYOUT),
            ClickableAboutItem(R.drawable.ic_permissions, LIB_PERMISSION_X),

            TitleAboutItem("版权"),
            ClickableAboutItem(R.drawable.ic_copyright, COPYRIGHT)
        )
    }

    @Suppress("SpellCheckingInspection")
    override fun onAboutItemClick(view: View, aboutItem: ClickableAboutItem, position: Int) {
        when (aboutItem.title) {
            TOPIC_DISCUSSION -> joinOfficialGroup()
            WEB_PAGE -> Toasty.info(this, "暂无官网", 0).show()
            DEVELOPER -> openBrowser("https://github.com/CaiMuCheng")
            PRIVACY_POLICY -> PrivacyPolicyDialog(this).show()
            THANKS -> ThanksDialog(this).show()
            SPONSOR -> openBrowser("https://afdian.net/@sumucheng")

            RELATION_ZHUAN_ZHU_CI_YUAN -> openBrowser("https://jq.qq.com/?_wv=1027&k=D66rfJ1i")

            LIB_KTX -> openBrowser("https://developer.android.google.cn/kotlin/ktx?hl=zh-cn")
            LIB_APPCOMPAT -> openBrowser("https://developer.android.google.cn/jetpack/androidx/releases/appcompat?hl=zh_cn")
            LIB_MATERIAL -> openBrowser("https://developer.android.google.cn/guide/topics/ui/look-and-feel?hl=zh_cn")
            LIB_TOASTY -> openBrowser("https://github.com/GrenderG/Toasty")
            LIB_MU_CODE_EDITOR -> openBrowser("https://github.com/CaiMuCheng/MuCodeEditor")
            LIB_TEXT_MODEL -> openBrowser("https://github.com/CaiMuCheng/TextModel")
            LIB_RXJAVA -> openBrowser("https://github.com/ReactiveX/RxJava")
            LIB_LEAN_CLOUD -> openBrowser("https://www.leancloud.cn/")
            LIB_SWIPE_REFRESH_LAYOUT -> openBrowser("https://developer.android.google.cn/jetpack/androidx/releases/swiperefreshlayout?hl=zh_cn")
            LIB_PERMISSION_X -> openBrowser("https://github.com/guolindev/PermissionX")
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun Context.openBrowser(url: String) {
        openBrowser(this, url)
    }

    @Suppress("SpellCheckingInspection")
    private fun joinOfficialGroup() {
        val intent = Intent()
        intent.data =
            Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26jump_from%3Dwebapi%26k%3DCTAFeW6Z0VcVZcZe2Qciufc6L3_vEDgK")
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toasty.info(this, "你还没有安装 QQ", 0).show()
        }
    }

}