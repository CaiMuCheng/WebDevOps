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
                TitleSettingItem("??????"),
                SwitchSettingItem(DarkTheme, "??????????????????", GlobalConfig.isDarkThemeEnabled()),
                ClickableSettingItem(AppTypeface, "?????????????????????"),
                TitleSettingItem("???????????????"),
                SwitchSettingItem(
                    AutoCompletion,
                    "?????????????????????????????????",
                    globalConfig.isAutoCompletionEnabled()
                ),
                SwitchSettingItem(
                    OperatorCompletion,
                    "????????????????????????????????????",
                    globalConfig.isOperatorPanelEnabled()
                ),
                SwitchSettingItem(
                    CursorAnimationEnabled,
                    "????????????",
                    globalConfig.isCursorAnimationEnabled()
                ),
                SwitchSettingItem(
                    LineNumberEnabled,
                    "????????????",
                    globalConfig.isLineNumberEnabled()
                ),
                SwitchSettingItem(
                    WordWrapEnabled,
                    "????????????",
                    globalConfig.isLineNumberEnabled()
                ),
                SwitchSettingItem(
                    StickyLineNumberEnabled,
                    "????????????????????????",
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
                ClickableSettingItem(EditorTypeface, "?????????????????????"),
                TitleSettingItem("??????"),
                ClickableSettingItem(ManagePlugin, "?????????????????????"),
                ClickableSettingItem(AboutAPP, "????????????????????????")
            )
        )
        return settingItemList
    }

    private fun getCursorAnimationDescription(cursorAnimationType: CursorAnimationType): String {
        return when (cursorAnimationType) {
            CursorAnimationType.TranslationAnimation -> "????????????"
            CursorAnimationType.ScaleAnimation -> "????????????"
            CursorAnimationType.FadeAnimation -> "????????????"
        }
    }

    private fun getCursorAnimationTypeByDescription(type: String): CursorAnimationType {
        return when (type) {
            "????????????" -> CursorAnimationType.ScaleAnimation
            "????????????" -> CursorAnimationType.FadeAnimation
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
                    .setTitle("???????????????????????? (????????????)")
                    .setComponents(
                        listOf(
                            ComponentInfo.InputInfo(
                                title = type,
                                hint = "????????????",
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
                            Toasty.info(requireContext(), "?????????????????????").show()
                            return@onComplete false
                        }
                        if (inputFile.isDirectory) {
                            Toasty.info(requireContext(), "?????????????????????").show()
                            return@onComplete false
                        }

                        try {
                            val checkedTypeface = Typeface.createFromFile(inputFile)
                            if (checkedTypeface == Typeface.DEFAULT) {
                                Toasty.error(requireContext(), "??????????????????").show()
                                return@onComplete false
                            }
                        } catch (e: Throwable) {
                            Toasty.error(requireContext(), "??????????????????: ${e.message}").show()
                            return@onComplete false
                        }

                        globalConfig.setAppTypefacePath(input)
                        globalConfig.apply()
                        showRestartDialog()
                        true
                    }
                    .setCancelable(false)
                    .setNeutralButton("??????", null)
                    .setPositiveButton("??????", null)
                    .show()
            }

            CursorAnimation -> {
                val array = arrayOf("????????????", "????????????", "????????????")
                ComposableDialog(requireContext())
                    .setTitle("??????????????????")
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
                    .setNeutralButton("??????", null)
                    .setPositiveButton("??????", null)
                    .show()
            }

            OperatorInputCharTable -> {
                ComposableDialog(requireContext())
                    .setTitle("?????????????????? (????????????)")
                    .setComponents(
                        listOf(
                            ComponentInfo.InputInfo(
                                title = settingItem.description,
                                hint = "?????????",
                                isSingleLine = true
                            )
                        )
                    )
                    .onComplete {
                        val inputInfo = it[0] as ComponentInfo.InputInfo
                        val input = inputInfo.title ?: ""
                        if (input.isEmpty()) {
                            Toasty.info(requireContext(), "?????????????????????").show()
                            return@onComplete false
                        }
                        globalConfig.setOperatorInputCharTable(input)
                        globalConfig.apply()
                        settingItem.description = input
                        settingAdapter.notifyItemChanged(position)
                        true
                    }
                    .setCancelable(false)
                    .setNeutralButton("??????", null)
                    .setPositiveButton("??????", null)
                    .show()
            }

            EditorTypeface -> {
                var type = globalConfig.getEditorTypefacePath()
                if (type == "null") {
                    type = ""
                }
                ComposableDialog(requireContext())
                    .setTitle("?????????????????? (????????????)")
                    .setComponents(
                        listOf(
                            ComponentInfo.InputInfo(
                                title = type,
                                hint = "????????????",
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
                            Toasty.info(requireContext(), "?????????????????????").show()
                            return@onComplete false
                        }
                        if (inputFile.isDirectory) {
                            Toasty.info(requireContext(), "?????????????????????").show()
                            return@onComplete false
                        }

                        try {
                            val checkedTypeface = Typeface.createFromFile(inputFile)
                            if (checkedTypeface == Typeface.DEFAULT) {
                                Toasty.error(requireContext(), "??????????????????").show()
                                return@onComplete false
                            }
                        } catch (e: Throwable) {
                            Toasty.error(requireContext(), "??????????????????: ${e.message}").show()
                            return@onComplete false
                        }

                        globalConfig.setEditorTypefacePath(input)
                        globalConfig.apply()
                        true
                    }
                    .setCancelable(false)
                    .setNeutralButton("??????", null)
                    .setPositiveButton("??????", null)
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
            .setTitle("????????????")
            .setMessage("???????????????????????????????????????")
            .setPositiveButton("??????") { _, _ ->
                val intent =
                    requireContext().packageManager.getLaunchIntentForPackage(requireContext().packageName)
                intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)

                //??????????????????
                Process.killProcess(Process.myPid())
            }
            .setCancelable(false)
            .show()
    }

}