package com.mucheng.web.devops.dialog

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mucheng.web.devops.data.model.ThanksItem
import com.mucheng.web.devops.support.LanguageKeys
import com.mucheng.web.devops.util.supportedText

class ThanksDialog(context: Context) : MaterialAlertDialogBuilder(context) {

    init {
        setTitle(supportedText(LanguageKeys.ThanksList))
        setMessage(buildList())
        setPositiveButton(supportedText(LanguageKeys.PermissionRequestOK), null)
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun buildList(): String {
        val itemList = listOf(
            ThanksItem(
                supportedText(LanguageKeys.Mucute),
                "244048880",
                supportedText(LanguageKeys.MostStr)
            ),
            ThanksItem("Answer", "2903536884", supportedText(LanguageKeys.FoundBug)),
            ThanksItem("专注次元", "1628624278", supportedText(LanguageKeys.FoundBug)),
            ThanksItem("摸鱼的明欲", "1905065952", supportedText(LanguageKeys.ProvideUIAdvice)),
            ThanksItem("狗崽呀~", "3201557995", supportedText(LanguageKeys.ProvideLotsOfAdvice)),
            ThanksItem("隐私权.", "1419818104", supportedText(LanguageKeys.HelpForBugFinding)),
            ThanksItem("趙逍遥", "1007583732", supportedText(LanguageKeys.HelpForBugFinding2)),
            ThanksItem("鑫鑫工具箱官方", "1402832033", supportedText(LanguageKeys.HelpForHardBugFinding))
        )
        return buildString {
            append(supportedText(LanguageKeys.ThanksListBelow))
            repeat(2) {
                appendLine()
            }

            for ((index, item) in itemList.withIndex()) {
                append(item.name).appendLine()
                append("QQ: ").append(item.qq).appendLine()
                append(item.why)
                if (index < itemList.lastIndex) {
                    repeat(2) {
                        appendLine()
                    }
                }
            }
        }
    }

}