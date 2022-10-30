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

import android.graphics.Canvas
import android.graphics.Paint
import com.mucheng.editor.base.AbstractComponent
import com.mucheng.editor.tool.dp
import com.mucheng.editor.view.MuCodeEditor


@Suppress("MemberVisibilityCanBePrivate")
open class TextSelectHandle(editor: MuCodeEditor) : AbstractComponent(editor) {

    companion object {
        const val LEFT_TEXT_SELECT_HANDLE = "TextSelectHandleLeft"
        const val RIGHT_TEXT_SELECT_HANDLE = "TextSelectHandleRight"
        var radius: Float = 0f
    }

    var startX = 0f
        private set

    var startY = 0f
        private set

    var endX = 0f
        private set

    var endY = 0f
        private set

    init {
        val size = editor.context.dp(22)
        radius = size / 2
    }

    /**
     * 此绘制算法来源于 Sora Editor，
     * 作者 Rosemoe
     * */
    @Suppress("UnnecessaryVariable")
    open fun draw(canvas: Canvas, x: Float, y: Float, paint: Paint, who: String) {
        val mode = who == LEFT_TEXT_SELECT_HANDLE
        val cx = if (mode) (x - radius) else x + radius
        canvas.drawCircle(cx, (y + radius), radius, paint)

        val drawStartX = if (mode) cx else cx - radius
        val drawStartY = y
        val drawEndX = if (mode) cx + radius else cx
        val drawEndY = (y + radius)

        this.startX = drawStartX
        this.startY = drawStartY
        this.endX = drawEndX
        this.endY = drawEndY

        if (who == LEFT_TEXT_SELECT_HANDLE) {
            this.startX -= radius
            this.endY += cx
        }

        if (who == RIGHT_TEXT_SELECT_HANDLE) {
            this.endX += radius
            this.endY += cx
        }

        canvas.drawRect(
            drawStartX,
            drawStartY,
            drawEndX,
            drawEndY,
            paint
        )
    }

}