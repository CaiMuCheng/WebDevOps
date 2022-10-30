package com.mucheng.web.devops.dialog

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mucheng.web.devops.data.model.ThanksItem

class ThanksDialog(context: Context) : MaterialAlertDialogBuilder(context) {

    init {
        setTitle("感谢名单")
        setMessage(buildList())
        setPositiveButton("确定", null)
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun buildList(): String {
        val itemList = listOf(
            ThanksItem(
                "沐川",
                "244048880",
                "帮助发现了很多 Bug & 提供了 PHP for Android aarch64 的编译方式"
            ),
            ThanksItem("Answer", "2903536884", "帮助发现了很多 Bug"),
            ThanksItem("专注次元", "1628624278", "帮助发现了很多 Bug"),
            ThanksItem("摸鱼的明欲", "1905065952", "提供 UI 建议"),
            ThanksItem("狗崽呀~", "3201557995", "提供了许多建议"),
            ThanksItem("隐私权.", "1419818104", "帮助发现了很多致命 Bug"),
            ThanksItem("趙逍遥", "1007583732", "提供了很多建议, 发现了一个致命 Bug"),
            ThanksItem("鑫鑫工具箱官方", "1402832033", "帮助发现了灰常难发现的 Bug")
        )
        return buildString {
            append("以下为感谢名单（排名不分先后）:")
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