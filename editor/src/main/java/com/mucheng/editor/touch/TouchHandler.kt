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

package com.mucheng.editor.touch

import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.OverScroller
import com.mucheng.editor.component.TextSelectHandle
import com.mucheng.editor.debug.MyLog
import com.mucheng.editor.tool.executeAnimation
import com.mucheng.editor.tool.sp
import com.mucheng.editor.view.MuCodeEditor
import com.mucheng.text.model.position.CharRangePosition
import com.mucheng.text.model.standard.CharTable
import com.mucheng.text.model.standard.TextRow
import kotlinx.coroutines.Runnable
import kotlin.math.max
import kotlin.math.min

open class TouchHandler(val editor: MuCodeEditor) : GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener, ScaleGestureDetector.OnScaleGestureListener {

    companion object {
        private const val TAG = "TouchHandler"
    }

    private val textSizeMax = editor.context.sp(24f)
    private val textSizeMin = editor.context.sp(12f)
    private var isScaling = false
    private var clickX = 0f
    private var clickY = 0f
    private var isTouchingTextSelectHandleStart = false
    private var isTouchingTextSelectHandleEnd = false
    private val scrollingTask = ScrollingTask()

    override fun onDown(e: MotionEvent): Boolean {
        isTouchingTextSelectHandleStart = false
        isTouchingTextSelectHandleEnd = false

        if (editor.isEnabled && editor.functionManager.isEditable && editor.actionManager.selectingText) {
            clickX = e.x
            clickY = e.y

            editor.closeToolOptionPanel()

            val textSelectHandleStartParams = editor.getTextSelectHandleStartParams()
            val textSelectHandleEndParams = editor.getTextSelectHandleEndParams()

            val firstTextSelectHandleStartX = textSelectHandleStartParams[0]
            val firstTextSelectHandleStartY = textSelectHandleStartParams[1]
            val firstTextSelectHandleEndX = textSelectHandleStartParams[2]
            val firstTextSelectHandleEndY = textSelectHandleStartParams[3]

            val secondTextSelectHandleStartX = textSelectHandleEndParams[0]
            val secondTextSelectHandleStartY = textSelectHandleEndParams[1]
            val secondTextSelectHandleEndX = textSelectHandleEndParams[2]
            val secondTextSelectHandleEndY = textSelectHandleEndParams[3]

            val touchedFirst = isTouchingTextSelectHandle(
                firstTextSelectHandleStartX,
                firstTextSelectHandleStartY,
                firstTextSelectHandleEndX,
                firstTextSelectHandleEndY
            )
            val touchedSecond = isTouchingTextSelectHandle(
                secondTextSelectHandleStartX,
                secondTextSelectHandleStartY,
                secondTextSelectHandleEndX,
                secondTextSelectHandleEndY
            )
            if (touchedFirst) {
                isTouchingTextSelectHandleStart = true
                return true
            }

            if (touchedSecond) {
                isTouchingTextSelectHandleEnd = true
                return true
            }
        }
        return editor.isEnabled
    }

    @Suppress("NOTHING_TO_INLINE", "ConvertTwoComparisonsToRangeCheck")
    private inline fun isTouchingTextSelectHandle(
        startX: Float,
        startY: Float,
        endX: Float,
        endY: Float
    ): Boolean {
        return startX <= clickX && clickX <= endX && startY <= clickY && clickY <= endY
    }

    override fun onShowPress(e: MotionEvent) {}

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        val actionManager = editor.actionManager
        val functionManager = editor.functionManager
        val animationManager = editor.animationManager
        val layout = editor.layout
        actionManager.cancelSelectingText()
        editor.closeAutoCompletionPanel()
        if (functionManager.isEditable) {
            val scroller = editor.getScroller()
            scroller.forceFinished(true)
            editor.showSoftInput()
            val cursor = editor.getCursor()
            val line = layout.getCursorOffsetLine(e.y + editor.getOffsetY())
            val row = layout.getCursorOffsetRow(line, e.x + editor.getOffsetX())
            cursor.executeAnimation(animationManager.cursorAnimation) {
                cursor.moveToPosition(line, row)
            }
            editor.reachToCursor(cursor)
        }
        return true
    }

    open fun onUp(e: MotionEvent) {
        isTouchingTextSelectHandleStart = false
        isTouchingTextSelectHandleEnd = false
        stopScrollingTask()
        if (editor.actionManager.selectingText) {
            editor.showToolOptionPanel()
        }
    }

    private fun interceptScroll(e: MotionEvent): Boolean {
        val actionManager = editor.actionManager
        if (!actionManager.selectingText) {
            return false
        }

        val scroller = editor.getScroller()
        val layout = editor.layout
        val line = layout.getCursorOffsetLine(scroller.currY + e.y - TextSelectHandle.radius)
        val selectionRangePosition = actionManager.selectingRange!!

        if (isTouchingTextSelectHandleStart) {
            val row =
                layout.getCursorOffsetRow(line, scroller.currX + e.x + TextSelectHandle.radius)

            if (reachedPosition(
                    TextSelectHandle.LEFT_TEXT_SELECT_HANDLE,
                    selectionRangePosition,
                    line,
                    row
                )
            ) {
                return true
            }

            scrollIfReachedEdge(TextSelectHandle.LEFT_TEXT_SELECT_HANDLE, e)
            return true
        }

        if (isTouchingTextSelectHandleEnd) {
            val row =
                layout.getCursorOffsetRow(line, scroller.currX + e.x - TextSelectHandle.radius)

            if (reachedPosition(
                    TextSelectHandle.RIGHT_TEXT_SELECT_HANDLE,
                    selectionRangePosition,
                    line,
                    row
                )
            ) {
                return true
            }

            scrollIfReachedEdge(TextSelectHandle.RIGHT_TEXT_SELECT_HANDLE, e)
            return true
        }

        return false
    }

    private fun reachedPosition(
        who: String,
        rangePosition: CharRangePosition,
        line: Int,
        row: Int
    ): Boolean {
        val startPos = rangePosition.start
        val endPos = rangePosition.end
        if (who == TextSelectHandle.LEFT_TEXT_SELECT_HANDLE) {
            if (line == endPos.line && row == endPos.row) {
                return true
            }

            if (line > endPos.line || (line == endPos.line && row > endPos.row)) {
                return true
            }

            startPos.line = line
            startPos.row = row
        } else {
            if (line == startPos.line && row == startPos.row) {
                return true
            }

            if (line < startPos.line || (line == startPos.line && row < startPos.row)) {
                return true
            }

            endPos.line = line
            endPos.row = row
        }
        return false
    }

    override fun onScroll(
        e1: MotionEvent,
        e2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        if (interceptScroll(e2)) {
            return true
        }

        val scroller = editor.getScroller()
        scroller.startScroll(
            scroller.currX,
            scroller.currY,
            requireDistanceX(scroller, distanceX),
            requireDistanceY(scroller, distanceY),
            0
        )
        editor.invalidate()
        return true
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun requireDistanceX(scroller: OverScroller, distanceX: Float): Int {
        var endX = scroller.currX + distanceX.toInt()
        endX = max(0, endX)
        endX = min(editor.getScrollMaxX(), endX)

        return endX - scroller.currX
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun requireDistanceY(scroller: OverScroller, distanceY: Float): Int {
        var endY = scroller.currY + distanceY.toInt()
        endY = max(0, endY)
        endY = min(editor.getScrollMaxY(), endY)

        return endY - scroller.currY
    }

    open fun scrollBy(distanceX: Float, distanceY: Float, animation: Boolean = false) {
        MyLog.e(TAG, "scrollBy(offsetX=$distanceX, offsetY=$distanceY)")
        val scroller = editor.getScroller()
        var endX = scroller.currX + distanceX.toInt()
        var endY = scroller.currY + distanceY.toInt()
        endX = max(endX, 0)
        endY = max(endY, 0)
        endX = min(endX, editor.getScrollMaxX())
        endY = min(endY, editor.getScrollMaxY())
        if (animation) {
            scroller.startScroll(
                scroller.currX,
                scroller.currY,
                endX - scroller.currX,
                endY - scroller.currY
            )
        } else {
            scroller.startScroll(
                scroller.currX,
                scroller.currY,
                endX - scroller.currX,
                endY - scroller.currY,
                0
            )
        }
        editor.invalidate()
    }

    private fun scrollIfReachedEdge(who: String, e: MotionEvent) {
        when (who) {
            TextSelectHandle.LEFT_TEXT_SELECT_HANDLE -> {
                scrollingTask.setEvent(MotionEvent.obtain(e))
                scrollingTask.setWho(TextSelectHandle.LEFT_TEXT_SELECT_HANDLE)
                stopScrollingTask()
                editor.post(scrollingTask)
            }

            TextSelectHandle.RIGHT_TEXT_SELECT_HANDLE -> {
                scrollingTask.setEvent(MotionEvent.obtain(e))
                scrollingTask.setWho(TextSelectHandle.RIGHT_TEXT_SELECT_HANDLE)
                stopScrollingTask()
                editor.post(scrollingTask)
            }

        }
    }

    private fun stopScrollingTask() {
        editor.removeCallbacks(scrollingTask)
    }

    override fun onFling(
        e1: MotionEvent,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        val scroller = editor.getScroller()
        scroller.forceFinished(true)
        scroller.fling(
            scroller.currX,
            scroller.currY,
            -velocityX.toInt(),
            -velocityY.toInt(),
            0,
            editor.getScrollMaxX(),
            0,
            editor.getScrollMaxY()
        )
        editor.postInvalidateOnAnimation()
        return false
    }

    override fun onLongPress(e: MotionEvent) {
        val actionManager = editor.actionManager
        if (actionManager.selectingText || !editor.functionManager.isEditable) {
            return
        }

        val layout = editor.layout
        val line = layout.getCursorOffsetLine(e.y + editor.getOffsetY() - TextSelectHandle.radius)
        val row =
            layout.getCursorOffsetRow(line, e.x + editor.getOffsetX() - TextSelectHandle.radius)
        selectWord(line, row)
    }

    override fun onDoubleTap(e: MotionEvent): Boolean {
        val actionManager = editor.actionManager
        if (actionManager.selectingText || !editor.functionManager.isEditable) {
            return true
        }

        val layout = editor.layout
        val line = layout.getCursorOffsetLine(e.y + editor.getOffsetY())
        val row = layout.getCursorOffsetRow(line, e.x + editor.getOffsetX())
        selectWord(line, row)
        return true
    }

    private fun selectWord(line: Int, row: Int) {
        val textModel = editor.getText()
        val textLineModel = textModel.getTextRow(line)
        val lineCount = textModel.lastLine
        if (line == 1 && line == lineCount && textLineModel.isEmpty()) {
            return
        }

        if (textLineModel.isEmpty()) {
            val lastLine = line - 1
            if (lastLine < 1) {
                val nextLine = line + 1
                if (nextLine > lineCount) {
                    return
                }
                editor.setSelection(
                    line, textLineModel.length,
                    nextLine, 0
                )
                return
            }
            val lastTextLineModel = textModel.getTextRow(lastLine)
            editor.setSelection(
                lastLine, lastTextLineModel.length,
                line, row
            )
            return
        }

        val pair = getTextSelectHandlePair(textLineModel, max(0, row - 1))
        val start = pair.first
        val end = pair.second
        if (start == end) {
            return
        }

        editor.setSelection(line, start, line, end)
    }

    /**
     * 从指定的 (line, row) 开始，
     * 向此行的 左 / 右 搜索可选中的最小 row & 最大 row，返回 Pair<StartRow, EndRow>
     *
     * @suppress 在调用此方法前请先检验此行是否有字符，如果没有我们不保证会抛出异常
     * @param textLineModel 此行的文本行模型
     * @param row 列数
     * @see CharTable.WHITE_SPACE
     * @see CharTable.BracketCollection
     * */
    open fun getTextSelectHandlePair(textLineModel: TextRow, row: Int): Pair<Int, Int> {
        var leftPointer = row
        var rightPointer = row
        val len = textLineModel.length
        var leftSearchingEnabled = true
        var rightSearchingEnabled = true
        while (leftSearchingEnabled || rightSearchingEnabled) {
            val lChar = textLineModel[leftPointer]
            if (leftPointer == rightPointer && (lChar == ' ' || lChar in CharTable.BracketCollection)) {
                break
            }

            if (leftSearchingEnabled) {
                val nextLeftPointer = leftPointer - 1
                if (nextLeftPointer >= 0) {
                    val char = textLineModel[nextLeftPointer]
                    if (char == ' ' || char in CharTable.BracketCollection) {
                        leftSearchingEnabled = false
                    }
                }
            }

            if (rightSearchingEnabled) {
                val char = textLineModel[rightPointer]
                if (char == ' ' || char in CharTable.BracketCollection) {
                    --rightPointer
                    rightSearchingEnabled = false
                }
            }

            if (leftPointer - 1 < 0) {
                leftSearchingEnabled = false
            }
            if (rightPointer + 1 == len) {
                rightSearchingEnabled = false
            }

            if (leftSearchingEnabled) {
                --leftPointer
            }
            if (rightSearchingEnabled) {
                ++rightPointer
            }
        }

        ++rightPointer
        return leftPointer to rightPointer
    }

    override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
        return true
    }

    override fun onDoubleTapEvent(e: MotionEvent): Boolean {
        return true
    }

    override fun onScale(detector: ScaleGestureDetector): Boolean {
        if (editor.functionManager.isScalable) {
            val styleManager = editor.styleManager
            val scaleTextSize =
                styleManager.painters.codeTextPainter.textSize * detector.scaleFactor
            if (scaleTextSize < textSizeMin || scaleTextSize > textSizeMax) {
                return true
            }
            val focusX = detector.focusX
            val focusY = detector.focusY
            val lineHeight = styleManager.painters.getLineHeight()
            styleManager.setTextSizePxDirect(scaleTextSize)
            val lineHeightFactor = styleManager.painters.getLineHeight().toFloat() / lineHeight
            val scroller = editor.getScroller()
            var afterScrollY = (scroller.currY + focusY) * lineHeightFactor - focusY
            var afterScrollX = (scroller.currX + focusX) * detector.scaleFactor - focusX
            afterScrollX = max(0f, min(afterScrollX, editor.getScrollMaxX().toFloat()))
            afterScrollY = max(0f, min(afterScrollY, editor.getScrollMaxY().toFloat()))
            scroller.startScroll(afterScrollX.toInt(), afterScrollY.toInt(), 0, 0, 0)
            isScaling = true
            editor.invalidate()
            return true
        }
        return false
    }

    override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
        val scroller = editor.getScroller()
        scroller.forceFinished(true)
        return editor.functionManager.isScalable
    }

    override fun onScaleEnd(detector: ScaleGestureDetector) {
        isScaling = false
        editor.invalidate()
    }

    open fun isScaling(): Boolean {
        return isScaling
    }

    open inner class ScrollingTask : Runnable {

        private lateinit var event: MotionEvent

        private lateinit var who: String

        override fun run() {
            // FIXME 简单实现 AutoScroll，左右滑动有 BUG
            val layout = editor.layout
            val scroller = editor.getScroller()
            val selectionRange = editor.actionManager.selectingRange!!
            val line =
                layout.getCursorOffsetLine(scroller.currY + event.y - TextSelectHandle.radius)
            val row = layout.getCursorOffsetRow(
                line,
                scroller.currX + event.x
            )

            reachedPosition(who, selectionRange, line, row)
            editor.reachToPosition(line, row, true)
            editor.postDelayed(this, 80)
        }

        open fun setEvent(event: MotionEvent) {
            this.event = event
        }

        open fun setWho(who: String) {
            this.who = who
        }

    }

}