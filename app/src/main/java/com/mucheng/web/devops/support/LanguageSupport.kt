package com.mucheng.web.devops.support

import java.util.*

enum class LanguageKeys {
    PermissionRequestReason,
    PermissionRequestOK,
    PermissionRequestCancel,
    HomePage,
    CommonPage,
    SettingPage,
    LocalLogin,
    Login,
    Register,
    ForgetPassword,
    NeededInputQQ,
    TrySendCheckCode,
    ReadCheckCode,
    Registering,
    LoggingIn,
    Checking
}

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
        put(LanguageKeys.PermissionRequestReason, "以下为软件运行的核心权限, 请同意")
        put(LanguageKeys.PermissionRequestOK, "确定")
        put(LanguageKeys.PermissionRequestCancel, "没有权限你将无法使用此软件")
        put(LanguageKeys.HomePage, "主页")
        put(LanguageKeys.CommonPage, "通用")
        put(LanguageKeys.SettingPage, "设置")
        put(LanguageKeys.LocalLogin, "正在本地登录....")
        put(LanguageKeys.Login, "登录")
        put(LanguageKeys.Register, "注册")
        put(LanguageKeys.ForgetPassword, "忘记密码")
        put(LanguageKeys.NeededInputQQ, "请输入 QQ 号")
        put(LanguageKeys.TrySendCheckCode, "正在发送验证码....")
        put(LanguageKeys.ReadCheckCode, "正在进行环境验证....")
        put(LanguageKeys.Registering, "正在注册....")
        put(LanguageKeys.LoggingIn, "正在登录....")
        put(LanguageKeys.Checking, "正在验证....")
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun MutableMap<LanguageKeys, String>.English() {
        put(
            LanguageKeys.PermissionRequestReason,
            "The following are the core permissions for running the software, please agree"
        )
        put(LanguageKeys.PermissionRequestOK, "Sure")
        put(
            LanguageKeys.PermissionRequestCancel,
            "Without permission you will not be able to use this APP"
        )
        put(LanguageKeys.HomePage, "Home")
        put(LanguageKeys.CommonPage, "Common")
        put(LanguageKeys.SettingPage, "Settings")
        put(LanguageKeys.LocalLogin, "Logging in locally....")
        put(LanguageKeys.Login, "Login")
        put(LanguageKeys.Register, "Register")
        put(LanguageKeys.ForgetPassword, "Forget Password")
        put(LanguageKeys.NeededInputQQ, "Please enter your QQ number")
        put(LanguageKeys.TrySendCheckCode, "Sending verification code....")
        put(LanguageKeys.ReadCheckCode, "Environment verification in progress....")
        put(LanguageKeys.Registering, "Registering....")
        put(LanguageKeys.LoggingIn, "Logging in....")
        put(LanguageKeys.Checking, "Checking....")
    }

}