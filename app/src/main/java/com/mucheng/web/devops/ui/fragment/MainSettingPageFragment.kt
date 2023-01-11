package com.mucheng.web.devops.ui.fragment

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Process
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.switchmaterial.SwitchMaterial
import com.mucheng.web.devops.base.BaseFragment
import com.mucheng.web.devops.config.CursorAnimationType
import com.mucheng.web.devops.config.GlobalConfig
import com.mucheng.web.devops.data.model.*
import com.mucheng.web.devops.databinding.FragmentMainSettingPageBinding
import com.mucheng.web.devops.support.LanguageKeys
import com.mucheng.web.devops.ui.activity.AboutActivity
import com.mucheng.web.devops.ui.activity.ManagePluginActivity
import com.mucheng.web.devops.ui.adapter.SettingAdapter
import com.mucheng.web.devops.ui.view.ComposableDialog
import com.mucheng.web.devops.util.supportedText
import com.mucheng.webops.plugin.data.info.ComponentInfo
import es.dmoral.toasty.Toasty
import java.io.File

class MainSettingPageFragment : BaseFragment(), SettingAdapter.SettingItemCallback {

    companion object {

        private val DarkTheme = supportedText(LanguageKeys.DarkTheme)
        private val AppTypeface = supportedText(LanguageKeys.AppTypeface)

        private val AutoCompletion = supportedText(LanguageKeys.AutoCompletion)
        private val OperatorCompletion = supportedText(LanguageKeys.OperatorCompletion)
        private val CursorAnimationEnabled = supportedText(LanguageKeys.CursorAnimationEnabled)
        private val LineNumberEnabled = supportedText(LanguageKeys.LineNumberEnabled)
        private val WordWrapEnabled = supportedText(LanguageKeys.WordWrapEnabled)
        private val StickyLineNumberEnabled = supportedText(LanguageKeys.StickyLineNumberEnabled)
        // Switch title end

        private val CursorAnimation = supportedText(LanguageKeys.CursorAnimation)
        private val OperatorInputCharTable = supportedText(LanguageKeys.OperatorInputCharTable)
        private val EditorTypeface = supportedText(LanguageKeys.EditorTypeface)

        private val ManagePlugin = supportedText(LanguageKeys.ManagePlugin)
        private val AboutAPP = supportedText(LanguageKeys.AboutAPP)
    }

    private lateinit var viewBinding: FragmentMainSettingPageBinding

    private val settingItemList: MutableList<SettingItem> = ArrayList()

    private val settingAdapter by lazy {
        SettingAdapter(requireContext(), requireSettingItemList()).also {
            it.setSettingItemCallback(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentMainSettingPageBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = viewBinding.recyclerView
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = settingAdapter
    }

    private fun requireSettingItemList(): List<SettingItem> {
        val globalConfig = GlobalConfig.getInstance()
        settingItemList.clear()
        settingItemList.addAll(
            listOf(
                TitleSettingItem("通用"),
                SwitchSettingItem(DarkTheme, "启用深色主题", GlobalConfig.isDarkThemeEnabled()),
                ClickableSettingItem(AppTypeface, "应用的全局字体"),
                TitleSettingItem("代码编辑器"),
                SwitchSettingItem(
                    AutoCompletion,
                    "通过自动补全栏补全代码",
                    globalConfig.isAutoCompletionEnabled()
                ),
                SwitchSettingItem(
                    OperatorCompletion,
                    "通过运算符补全栏补全代码",
                    globalConfig.isOperatorPanelEnabled()
                ),
                SwitchSettingItem(
                    CursorAnimationEnabled,
                    "光标动画",
                    globalConfig.isCursorAnimationEnabled()
                ),
                SwitchSettingItem(
                    LineNumberEnabled,
                    "显示行号",
                    globalConfig.isLineNumberEnabled()
                ),
                SwitchSettingItem(
                    WordWrapEnabled,
                    "自动换行",
                    globalConfig.isLineNumberEnabled()
                ),
                SwitchSettingItem(
                    StickyLineNumberEnabled,
                    "行号总是置于上层",
                    globalConfig.isStickyLineNumberEnabled()
                ),
                ClickableSettingItem(
                    CursorAnimation,
                    getCursorAnimationDescription(globalConfig.getCursorAnimationType())
                ),
                ClickableSettingItem(
                    OperatorInputCharTable,
                    globalConfig.getOperatorInputCharTable().joinToString(separator = " ")
                ),
                ClickableSettingItem(EditorTypeface, "编辑器文本字体"),
                TitleSettingItem("其它"),
                ClickableSettingItem(ManagePlugin, "删除或配置插件"),
                ClickableSettingItem(AboutAPP, "关于此软件的信息")
            )
        )
        return settingItemList
    }

    private fun getCursorAnimationDescription(cursorAnimationType: CursorAnimationType): String {
        return when (cursorAnimationType) {
            CursorAnimationType.TranslationAnimation -> "平移动画"
            CursorAnimationType.ScaleAnimation -> "缩放动画"
            CursorAnimationType.FadeAnimation -> "透明动画"
        }
    }

    private fun getCursorAnimationTypeByDescription(type: String): CursorAnimationType {
        return when (type) {
            "缩放动画" -> CursorAnimationType.ScaleAnimation
            "透明动画" -> CursorAnimationType.FadeAnimation
            else -> CursorAnimationType.TranslationAnimation
        }
    }

    override fun onSettingItemClick(view: View, settingItem: ClickableSettingItem, position: Int) {
        val globalConfig = GlobalConfig.getInstance()
        when (settingItem.title) {

            AppTypeface -> {
                var type = globalConfig.getAppTypefacePath()
                if (type == "null") {
                    type = ""
                }
                ComposableDialog(requireContext())
                    .setTitle("设置全局字体路径 (默认留空)")
                    .setComponents(
                        listOf(
                            ComponentInfo.InputInfo(
                                title = type,
                                hint = "字体路径",
                                isSingleLine = true
                            )
                        )
                    )
                    .onComplete {
                        val inputInfo = it[0] as ComponentInfo.InputInfo
                        val input = inputInfo.title ?: ""
                        val inputFile = File(input)
                        if (input.isEmpty()) {
                            globalConfig.setAppTypefacePath(null)
                            globalConfig.apply()
                            showRestartDialog()
                            return@onComplete true
                        }
                        if (!inputFile.exists()) {
                            Toasty.info(requireContext(), "输入路径不存在").show()
                            return@onComplete false
                        }
                        if (inputFile.isDirectory) {
                            Toasty.info(requireContext(), "请输入文件路径").show()
                            return@onComplete false
                        }

                        try {
                            val checkedTypeface = Typeface.createFromFile(inputFile)
                            if (checkedTypeface == Typeface.DEFAULT) {
                                Toasty.error(requireContext(), "设置字体失败").show()
                                return@onComplete false
                            }
                        } catch (e: Throwable) {
                            Toasty.error(requireContext(), "无法设置字体: ${e.message}").show()
                            return@onComplete false
                        }

                        globalConfig.setAppTypefacePath(input)
                        globalConfig.apply()
                        showRestartDialog()
                        true
                    }
                    .setCancelable(false)
                    .setNeutralButton("取消", null)
                    .setPositiveButton("确定", null)
                    .show()
            }

            CursorAnimation -> {
                val array = arrayOf("平移动画", "缩放动画", "透明动画")
                ComposableDialog(requireContext())
                    .setTitle("选择光标动画")
                    .setComponents(
                        listOf(
                            ComponentInfo.SelectorInfo(
                                array,
                                array.indexOf(settingItem.description)
                            )
                        )
                    )
                    .onComplete {
                        val selectorInfo = it[0] as ComponentInfo.SelectorInfo
                        val items = selectorInfo.items
                        val selectedPosition = selectorInfo.position
                        val text = items[selectedPosition]
                        val type = getCursorAnimationTypeByDescription(text)
                        globalConfig.setCursorAnimationType(type)
                        globalConfig.apply()
                        settingItem.description = text
                        settingAdapter.notifyItemChanged(position)
                        true
                    }
                    .setCancelable(true)
                    .setNeutralButton("取消", null)
                    .setPositiveButton("确定", null)
                    .show()
            }

            OperatorInputCharTable -> {
                ComposableDialog(requireContext())
                    .setTitle("运算符插入栏 (空格分割)")
                    .setComponents(
                        listOf(
                            ComponentInfo.InputInfo(
                                title = settingItem.description,
                                hint = "运算符",
                                isSingleLine = true
                            )
                        )
                    )
                    .onComplete {
                        val inputInfo = it[0] as ComponentInfo.InputInfo
                        val input = inputInfo.title ?: ""
                        if (input.isEmpty()) {
                            Toasty.info(requireContext(), "运算符不能为空").show()
                            return@onComplete false
                        }
                        globalConfig.setOperatorInputCharTable(input)
                        globalConfig.apply()
                        settingItem.description = input
                        settingAdapter.notifyItemChanged(position)
                        true
                    }
                    .setCancelable(false)
                    .setNeutralButton("取消", null)
                    .setPositiveButton("确定", null)
                    .show()
            }

            EditorTypeface -> {
                var type = globalConfig.getEditorTypefacePath()
                if (type == "null") {
                    type = ""
                }
                ComposableDialog(requireContext())
                    .setTitle("设置字体路径 (默认留空)")
                    .setComponents(
                        listOf(
                            ComponentInfo.InputInfo(
                                title = type,
                                hint = "字体路径",
                                isSingleLine = true
                            )
                        )
                    )
                    .onComplete {
                        val inputInfo = it[0] as ComponentInfo.InputInfo
                        val input = inputInfo.title ?: ""
                        val inputFile = File(input)
                        if (input.isEmpty()) {
                            globalConfig.setEditorTypefacePath(null)
                            globalConfig.apply()
                            return@onComplete true
                        }
                        if (!inputFile.exists()) {
                            Toasty.info(requireContext(), "输入路径不存在").show()
                            return@onComplete false
                        }
                        if (inputFile.isDirectory) {
                            Toasty.info(requireContext(), "请输入文件路径").show()
                            return@onComplete false
                        }

                        try {
                            val checkedTypeface = Typeface.createFromFile(inputFile)
                            if (checkedTypeface == Typeface.DEFAULT) {
                                Toasty.error(requireContext(), "设置字体失败").show()
                                return@onComplete false
                            }
                        } catch (e: Throwable) {
                            Toasty.error(requireContext(), "无法设置字体: ${e.message}").show()
                            return@onComplete false
                        }

                        globalConfig.setEditorTypefacePath(input)
                        globalConfig.apply()
                        true
                    }
                    .setCancelable(false)
                    .setNeutralButton("取消", null)
                    .setPositiveButton("确定", null)
                    .show()
            }

            ManagePlugin -> {
                val intent = Intent(requireContext(), ManagePluginActivity::class.java)
                startActivity(intent)
            }

            AboutAPP -> {
                val intent = Intent(requireContext(), AboutActivity::class.java)
                startActivity(intent)
            }

        }
    }

    override fun onColorSettingItemClick(
        view: View,
        colorSettingItem: ColorSettingItem,
        position: Int
    ) {
    }

    override fun onSettingItemChecked(
        view: SwitchMaterial,
        settingItem: SwitchSettingItem,
        position: Int,
        isChecked: Boolean
    ) {
        val globalConfig = GlobalConfig.getInstance()
        when (settingItem.title) {
            DarkTheme -> {
                GlobalConfig.setDarkThemeEnabled(isChecked)
                showRestartDialog()
            }

            AutoCompletion -> {
                globalConfig.setAutoCompletionEnabled(isChecked)
                globalConfig.apply()
            }

            OperatorCompletion -> {
                globalConfig.setOperatorPanelEnabled(isChecked)
                globalConfig.apply()
            }

            CursorAnimationEnabled -> {
                globalConfig.setCursorAnimationEnabled(isChecked)
                globalConfig.apply()
            }

            LineNumberEnabled -> {
                globalConfig.setLineNumberEnabled(isChecked)
                globalConfig.apply()
            }

            WordWrapEnabled -> {
                globalConfig.setWordWrapEnabled(isChecked)
                globalConfig.apply()
            }

            StickyLineNumberEnabled -> {
                globalConfig.setStickyLineNumberEnabled(isChecked)
                globalConfig.apply()
            }
        }
    }

    private fun showRestartDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("重启应用")
            .setMessage("你需要重启才能应用配置修改")
            .setPositiveButton("重启") { _, _ ->
                val intent =
                    requireContext().packageManager.getLaunchIntentForPackage(requireContext().packageName)
                intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)

                //杀掉以前进程
                Process.killProcess(Process.myPid())
            }
            .setCancelable(false)
            .show()
    }

}