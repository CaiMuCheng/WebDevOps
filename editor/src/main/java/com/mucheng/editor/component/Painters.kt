/*
 * CN:
 * 作者：SuMuCheng
 * 我的 QQ: 3578557729
 * Github 主页：https://github.com/CaiMuCheng
 * 项目主页: https://github.com/CaiMuCheng/MuCodeEditor
 *
 * 你可以免费使用、商用以下代码，也可以基于以下代码做出修改，但是必须在你的项目中标注出处
 * 例如：在你 APP 的设置中添加 “关于编辑器” 一栏，其中标注作者以及此编辑器的 Github 主页
 *
 * 此代码使用 MPL 2.0 开源许可证，你必须标注作者信息
 * 若你要修改文件，请勿删除此注释
 * 若你违反以上条例我们有权向您提起诉讼!
 *
 * EN:
 * Author: SuMuCheng
 * My QQ-Number: 3578557729
 * Github Homepage: https://github.com/CaiMuCheng
 * Project Homepage: https://github.com/CaiMuCheng/MuCodeEditor
 *
 * You can use the following code for free, commercial use, or make modifications based on the following code, but you must mark the source in your project.
 * For example: add an "About Editor" column in your app's settings, which identifies the author and the Github home page of this editor.
 *
 * This code uses the MPL 2.0 open source license, you must mark the author information.
 * Do not delete this comment if you want to modify the file.
 * If you violate the above regulations we have the right to sue you!
 */

package com.mucheng.editor.component

import android.graphics.Paint
import com.mucheng.editor.base.AbstractComponent
import com.mucheng.editor.tool.dp
import com.mucheng.editor.tool.sp
import com.mucheng.editor.view.MuCodeEditor

@Suppress("unused")
open class Painters(editor: MuCodeEditor) : AbstractComponent(editor) {

    private val Number.sp: Float
        get() {
            return editor.context.sp(this)
        }

    private val Number.dp: Float
        get() {
            return editor.context.dp(this)
        }

    private val mLineSpacingMultiplier = 1f

    private val mLineSpacingAdd = 0f

    open val customBackgroundPainter = Paint().apply {
        isAntiAlias = true
    }

    open val lineNumberPainter = Paint().apply {
        textSize = 18.sp
        isAntiAlias = true
        textAlign = Paint.Align.RIGHT
    }

    open val lineNumberBackgroundPainter = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
    }

    open val lineNumberDividingLinePainter = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
    }

    open val lineHighlightPainter = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
    }

    open val codeTextPainter = Paint().apply {
        textSize = 18.sp
        isAntiAlias = true
        textAlign = Paint.Align.LEFT
    }

    open val cursorPainter = Paint().apply {
        strokeWidth = 1.5f.dp
        isAntiAlias = true
    }

    open val textSelectHandleBackgroundPainter = Paint().apply {
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    open val textSelectHandlePainter = Paint().apply {
        isAntiAlias = true
    }

    open fun getLineHeight(): Int {
        val metrics = lineNumberPainter.fontMetrics
        return ((metrics.bottom - metrics.top) * mLineSpacingMultiplier + mLineSpacingAdd).toInt()
    }

    open fun getLineBaseLine(line: Int): Int {
        return getLineHeight() * (line + 1) - lineNumberPainter.fontMetricsInt.descent
    }

    open fun getLineRectHeight(): Int {
        val metrics = lineNumberPainter.fontMetrics
        return (metrics.bottom - metrics.top).toInt()
    }

}