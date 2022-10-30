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

package com.mucheng.editor.animation

import android.animation.Animator
import android.animation.ValueAnimator
import com.mucheng.editor.base.animation.CursorAnimation
import com.mucheng.editor.tool.dp
import com.mucheng.editor.view.MuCodeEditor


@Suppress("LeakingThis")
open class CursorScaleAnimation(protected val editor: MuCodeEditor) : CursorAnimation,
    ValueAnimator.AnimatorUpdateListener {

    private val scaleUpAnimator = ValueAnimator()

    private val scaleDownAnimator = ValueAnimator()

    private val defaultStrokeWidth by lazy { editor.context.dp(1.5f) }

    private var startX = 0f

    private var startY = 0f

    private var endX = 0f

    private var endY = 0f

    private var lineHighlightY = 0f

    private var duration: Long = 120

    private var phaseEnded = false

    private var lastAnimatedTime: Long = 0

    init {
        scaleUpAnimator.addUpdateListener(this)
        scaleDownAnimator.addUpdateListener(this)
    }

    override fun markAnimationBegin(line: Int, row: Int) {
        val layout = editor.layout
        val painters = editor.styleManager.painters
        startX = layout.measureLineRow(line, 0, row)
        startY = (painters.getLineHeight() * line).toFloat()
        lineHighlightY = (painters.getLineHeight() * line).toFloat()
    }

    override fun markAnimationFinish(line: Int, row: Int) {
        val layout = editor.layout
        val painters = editor.styleManager.painters
        endX = layout.measureLineRow(line, 0, row)
        endY = (painters.getLineHeight() * line).toFloat()
        lineHighlightY = (painters.getLineHeight() * line).toFloat()

        scaleDownAnimator.setFloatValues(defaultStrokeWidth, 0f)
        scaleDownAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationCancel(animator: Animator) {}
            override fun onAnimationRepeat(animator: Animator) {}
            override fun onAnimationStart(animator: Animator) {
                phaseEnded = false
            }

            override fun onAnimationEnd(animator: Animator) {
                phaseEnded = true
            }
        })
        scaleDownAnimator.duration = duration

        scaleUpAnimator.setFloatValues(0f, defaultStrokeWidth)
        scaleUpAnimator.startDelay = duration
        scaleUpAnimator.duration = duration
        // 若多次进行动画说明需要瞬间执行
        if (System.currentTimeMillis() - lastAnimatedTime < 100) {
            scaleDownAnimator.duration = 0
            scaleUpAnimator.startDelay = 0
            scaleUpAnimator.duration = 0
        }
    }
    
    override fun start() {
        if (!editor.functionManager.isCursorAnimationEnabled) {
            return
        }

        scaleDownAnimator.start()
        scaleUpAnimator.start()
        lastAnimatedTime = System.currentTimeMillis()
    }

    override fun cancel() {
        scaleDownAnimator.cancel()
        scaleUpAnimator.cancel()
    }

    override fun setDuration(duration: Long) {
        this.duration = duration
    }

    override fun getDuration(): Long {
        return duration
    }

    override fun isRunning(): Boolean {
        return scaleUpAnimator.isRunning || scaleDownAnimator.isRunning
    }

    override fun animatedX(): Float {
        if (phaseEnded) {
            return endX
        }
        return startX
    }

    override fun animatedY(): Float {
        if (phaseEnded) {
            return endY
        }
        return startY
    }

    override fun animatedHighlightLineBottomY(): Float {
        return lineHighlightY
    }

    override fun onAnimationUpdate(animation: ValueAnimator) {
        val cursorPainter = editor.styleManager.painters.cursorPainter
        cursorPainter.strokeWidth = animation.animatedValue as Float
        editor.postInvalidateOnAnimation()
    }

}