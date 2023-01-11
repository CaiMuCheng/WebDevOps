package com.mucheng.web.devops.support

import com.mucheng.web.devops.support.LanguageKeys.*
import java.util.*

/**
 * 本地语言支持
 * */
@Suppress("FunctionName")
object LanguageSupport {

    private val langMap: MutableMap<LanguageKeys, String> = EnumMap(LanguageKeys::class.java)

    private const val ZH = "zh"
    private const val EN = "en"

    init {
        fetchText(Locale.getDefault().language)
    }

    fun getText(keys: LanguageKeys): String {
        return langMap[keys] ?: "null"
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun fetchText(lang: String) {
        when (lang) {
            EN -> langMap.English()
            else -> langMap.Chinese()
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun MutableMap<LanguageKeys, String>.Chinese() {
        put(PermissionRequestReason, "以下为软件运行的核心权限, 请同意")
        put(PermissionRequestOK, "确定")
        put(PermissionRequestCancel, "没有权限你将无法使用此软件")
        put(HomePage, "主页")
        put(CommonPage, "通用")
        put(SettingPage, "设置")
        put(Cancel, "取消")
        put(Create, "创建")
        put(AddCreationInfo, "添加创建信息....")
        put(CannotLoadPlugin, "无法加载插件")
        put(CannotResolvePlugin, "无法解析插件")
        put(Exception, "异常")
        put(CannotDispatchCreateInfo, "无法分发 CreateInfo")
        put(Disagree, "拒绝")
        put(Agree, "同意")
        put(PrivacyPolicy, "隐私政策")
        put(ThanksList, "感谢名单")
        put(Mucute, "沐川")
        put(MostStr, "帮助发现了很多 Bug & 提供了 PHP for Android aarch64 的编译方式")
        put(FoundBug, "帮助发现了很多 Bug")
        put(ThanksListBelow, "以下为感谢名单（排名不分先后）:")
        put(ProvideUIAdvice, "提供 UI 建议")
        put(ProvideLotsOfAdvice, "提供了许多建议")
        put(HelpForBugFinding, "帮助发现了很多致命 Bug")
        put(HelpForBugFinding2, "提供了很多建议, 发现了一个致命 Bug")
        put(HelpForHardBugFinding, "帮助发现了非常难发现的 Bug")
        put(AboutApp, "关于软件")
        put(Friends, "合作伙伴")
        put(DependenceUse, "依赖使用")
        put(Copyright, "版权")
        put(DarkTheme, "深色主题")
        put(AppTypeface, "应用主题")
        put(AutoCompletion, "自动补全")
        put(OperatorCompletion, "运算符补全")
        put(CursorAnimationEnabled, "光标动画")
        put(LineNumberEnabled, "显示行号")
        put(WordWrapEnabled, "自动换行")
        put(StickyLineNumberEnabled, "粘性行号")
        put(CursorAnimation, "光标动画")
        put(OperatorInputCharTable, "运算符插入栏")
        put(EditorTypeface, "代码编辑器字体")
        put(ManagePlugin, "插件管理")
        put(AboutAPP, "关于软件")
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun MutableMap<LanguageKeys, String>.English() {
        put(PermissionRequestReason, "The following are the core permissions for running the software, please agree")
        put(PermissionRequestOK, "Sure")
        put(PermissionRequestCancel, "Without permission you will not be able to use this APP")
        put(HomePage, "Home")
        put(CommonPage, "Common")
        put(SettingPage, "Settings")
        put(Cancel, "Cancel")
        put(Create, "Create")
        put(AddCreationInfo, "Adding creation information....")
        put(CannotLoadPlugin, "Cannot load plugin")
        put(CannotResolvePlugin, "Cannot resolve plugin")
        put(Exception, "Exception")
        put(CannotDispatchCreateInfo, "Cannot dispatch CreateInfo")
        put(Disagree, "Disagree")
        put(Agree, "Agree")
        put(PrivacyPolicy, "Privacy Policy")
        put(ThanksList, "Thanks for the list")
        put(Mucute, "MuCute")
        put(MostStr, "Helped find a lot of bugs & Provided how PHP for Android aarch64 compiled")
        put(FoundBug, "Helped find a lot of bugs")
        put(ThanksListBelow, "The following is a list of thanks (in no particular order):")
        put(ProvideUIAdvice, "Provide UI advice")
        put(ProvideLotsOfAdvice, "Provide lots of advice")
        put(HelpForBugFinding, "Helped find a lot of fatal bugs")
        put(HelpForBugFinding2, "A lot of suggestions were provided, and a fatal bug was found")
        put(HelpForHardBugFinding, "Helps find bugs that are very difficult to find")
        put(AboutApp, "About APP")
        put(Friends, "Partners")
        put(DependenceUse, "Dependence Use")
        put(Copyright, "Copyright")
        put(DarkTheme, "Dark Theme")
        put(AppTypeface, "App Typeface")
        put(AutoCompletion, "Auto Completion")
        put(OperatorCompletion, "Operator Completion")
        put(CursorAnimationEnabled, "Cursor Animation Enabled")
        put(LineNumberEnabled, "Line Number Enabled")
        put(WordWrapEnabled, "Word Wrap")
        put(StickyLineNumberEnabled, "Sticky Line-number Enabled")
        put(CursorAnimation, "Cursor Animation")
        put(OperatorInputCharTable, "Operator Input Char-table")
        put(EditorTypeface, "Editor Typeface")
        put(ManagePlugin, "Manage Plugin")
        put(AboutAPP, "About APP")
    }

}

enum class LanguageKeys {
    PermissionRequestReason, PermissionRequestOK, PermissionRequestCancel,
    HomePage, CommonPage, SettingPage,
    Cancel, Create, AddCreationInfo,
    CannotLoadPlugin, CannotResolvePlugin,
    Exception, CannotDispatchCreateInfo, Disagree, Agree,
    PrivacyPolicy, ThanksList, Mucute, MostStr,
    FoundBug, ThanksListBelow, ProvideUIAdvice,
    ProvideLotsOfAdvice, HelpForBugFinding, HelpForBugFinding2,
    HelpForHardBugFinding, AboutApp, Friends, Depend, DependenceUse,
    Copyright, DarkTheme, AppTypeface, AutoCompletion, OperatorCompletion,
    CursorAnimationEnabled, LineNumberEnabled, WordWrapEnabled,
    StickyLineNumberEnabled,
    CursorAnimation, OperatorInputCharTable, EditorTypeface,
    ManagePlugin, AboutAPP
}