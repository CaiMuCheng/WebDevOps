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

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import com.mucheng.editor.animation.flex.FlexValue
import com.mucheng.editor.base.AbstractComponent
import com.mucheng.editor.base.AbstractTheme
import com.mucheng.editor.base.layout.AbstractLayout
import com.mucheng.editor.token.ThemeToken
import com.mucheng.editor.tool.dp
import com.mucheng.editor.view.MuCodeEditor
import com.mucheng.text.model.base.AbstractTextModel
import kotlin.math.max
import kotlin.math.min

@Suppress("MemberVisibilityCanBePrivate")
open class Renderer(editor: MuCodeEditor) : AbstractComponent(editor) {

    var maxWidth = 0f
        private set

    private val margin = editor.context.dp(5)

    private val dividingLineWidthUnit: Float = editor.context.dp(2)

    private var dividingLineWidth = 0f

    protected lateinit var theme: AbstractTheme

    protected lateinit var painters: Painters

    protected lateinit var textModel: AbstractTextModel

    protected lateinit var layout: AbstractLayout

    private var leftToolbarWidth = 0f

    protected var offsetX = 0f

    protected var offsetY = 0f

    @SuppressLint("AnnotateVersionCheck")
    private val isSupportedDrawTextRun = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

    val textSelectHandleStartParams = FloatArray(4)

    val textSelectHandleEndParams = FloatArray(4)

    val offsetAnimation = FlexValue(editor, 0f, 0.1f)

    fun getLeftToolbarWidth(): Float {
        return leftToolbarWidth
    }

    open fun render(canvas: Canvas) {
        remake()
        renderBackgroundColor(canvas)
        renderBackground(canvas)
        renderTextSelectHandleBackground(canvas)
        renderCursor(canvas)
        renderLineNumberBackground(canvas)
        renderHighlightLine(canvas)
        renderLineNumber(canvas)
        renderCodeText(canvas)
        renderTextSelectHandle(canvas)
    }

    private fun remake() {
        offsetY = 0f
        maxWidth = 0f

        theme = editor.styleManager.theme
        painters = editor.styleManager.painters
        textModel = editor.getText()
        layout = editor.layout

        if (editor.functionManager.isLineNumberEnabled) {
            // 一个 Flex 弹性动画, 控制 LineNumber 的弹性初始值
            offsetAnimation.targetVal = painters.lineNumberPainter.measureText(
                editor.getLastVisibleLine().toString()
            ) + margin * 4 + dividingLineWidth
            offsetAnimation.speed =
                editor.animationManager.cursorAnimation.getDuration() / 1000f
            offsetAnimation.update()
            offsetX = offsetAnimation.currentVal
        } else {
            // 不进行动画更新
            offsetX = 0f
        }

        leftToolbarWidth = offsetX

        dividingLineWidth = if (editor.functionManager.isDividingLineEnabled) {
            dividingLineWidthUnit
        } else {
            0f
        }

        painters.lineNumberPainter.color =
            theme.getColor(ThemeToken.LINE_NUMBER_COLOR_TOKEN).hexColor

        painters.lineNumberBackgroundPainter.color =
            theme.getColor(ThemeToken.LINE_NUMBER_BACKGROUND_COLOR_TOKEN).hexColor

        painters.lineNumberDividingLinePainter.color =
            theme.getColor(ThemeToken.LINE_NUMBER_DIVIDING_LINE_COLOR_TOKEN).hexColor

        painters.lineHighlightPainter.color =
            theme.getColor(ThemeToken.LINE_HIGHLIGHT_COLOR_TOKEN).hexColor

        if (!editor.animationManager.cursorAnimation.isRunning()) {
            painters.cursorPainter.color = theme.getColor(ThemeToken.CURSOR_COLOR_TOKEN).hexColor
        }

        painters.textSelectHandleBackgroundPainter.color =
            theme.getColor(ThemeToken.TEXT_SELECT_HANDLE_BACKGROUND_COLOR_TOKEN).hexColor

        painters.textSelectHandlePainter.color =
            theme.getColor(ThemeToken.TEXT_SELECT_HANDLE_COLOR_TOKEN).hexColor
    }

    protected open fun renderBackgroundColor(canvas: Canvas) {
        val backgroundColor = theme.getColor(ThemeToken.BACKGROUND_COLOR_TOKEN).hexColor
        canvas.drawColor(backgroundColor)
    }

    protected open fun renderBackground(canvas: Canvas) {
        if (!editor.functionManager.isCustomBackgroundEnabled) {
            return
        }

        val destBitmap = editor.styleManager.customBackground ?: return
        if (destBitmap.width != editor.width || destBitmap.height != editor.height) {
            editor.styleManager.setCustomBackground(
                Bitmap.createScaledBitmap(
                    destBitmap,
                    editor.width,
                    editor.height,
                    true
                )
            )
        }
        canvas.drawBitmap(
            editor.styleManager.customBackground!!,
            0f,
            0f,
            painters.customBackgroundPainter
        )
    }

    protected open fun renderTextSelectHandleBackground(canvas: Canvas) {
        val actionManager = editor.actionManager

        if (!actionManager.selectingText) {
            return
        }

        val selectionRange = actionManager.selectingRange!!
        val startPos = selectionRange.start
        val endPos = selectionRange.end
        val startLine = startPos.line
        val endLine = endPos.line
        val visibleLineStart = editor.getFirstVisibleLine()
        val visibleLineEnd = editor.getLastVisibleLine()
        val scrollingOffsetX = editor.getOffsetX()
        val scrollingOffsetY = editor.getOffsetY()
        val painter = painters.textSelectHandleBackgroundPainter
        val fontMetricsInt = painters.codeTextPainter.fontMetricsInt
        val fontMetricsOffset = fontMetricsInt.descent
        val realOffsetX = offsetX - scrollingOffsetX
        val realOffsetY = offsetY - scrollingOffsetY + fontMetricsOffset
        val lineHeight = painters.getLineHeight()

        if (startLine == endLine) {
            val firstVisibleRow = editor.getFirstVisibleRow(startLine)
            val lastVisibleRow = editor.getLastVisibleRow(endLine)

            val startRow = max(firstVisibleRow, startPos.row)
            val endRow = min(lastVisibleRow, endPos.row)

            val startX = layout.measureLineRow(startLine, 0, startRow) + realOffsetX
            val startY = (startLine - 1) * lineHeight + realOffsetY
            val endX = layout.measureLineRow(endLine, 0, endRow) + realOffsetX
            val endY = endLine * lineHeight + realOffsetY
            canvas.drawRect(
                startX,
                startY,
                endX,
                endY,
                painter
            )
            return
        }

        var workLine = if (startLine < visibleLineStart) visibleLineStart else startLine
        val reachLine = if (endLine > visibleLineEnd) visibleLineEnd else endLine
        while (workLine <= reachLine) {
            var endX: Float
            var endY: Float
            val firstVisibleRow = editor.getFirstVisibleRow(workLine)
            val lastVisibleRow = editor.getLastVisibleRow(workLine)

            when (workLine) {
                startLine -> {
                    val startTextRow = textModel.getTextRow(workLine)
                    val startRow = max(firstVisibleRow, startPos.row)
                    val endRow = max(startRow, min(lastVisibleRow, startTextRow.length))
                    val startX = layout.measureLineRow(workLine, 0, startRow) + realOffsetX
                    endX = layout.measureLineRow(
                        workLine,
                        startRow,
                        endRow
                    ) + startX
                    endY = (workLine * lineHeight).toFloat() + realOffsetY
                    canvas.drawRect(
                        startX,
                        endY - lineHeight,
                        endX,
                        endY,
                        painter
                    )
                    ++workLine
                    continue
                }

                endLine -> {
                    val endRow = min(lastVisibleRow, endPos.row)
                    endX = layout.measureLineRow(workLine, 0, endRow) + realOffsetX
                    endY = (workLine * lineHeight).toFloat() + realOffsetY
                }

                else -> {
                    endX = layout.measureLineRow(
                        workLine,
                        editor.getFirstVisibleRow(workLine),
                        editor.getLastVisibleRow(workLine)
                    ) + realOffsetX
                    endY = (workLine * lineHeight).toFloat() + realOffsetY
                }
            }
            endX = max(endX, realOffsetX + 20)
            canvas.drawRect(
                realOffsetX,
                endY - lineHeight,
                endX,
                endY,
                painter
            )
            ++workLine
        }
    }

    protected open fun renderHighlightLine(canvas: Canvas) {
        if (!editor.isEnabled || !editor.functionManager.isEditable || editor.actionManager.selectingText) {
            return
        }
        val cursor = editor.getCursor()
        val lineHeight = painters.getLineHeight()
        val painter = painters.lineHighlightPainter
        val visibleLineStart = editor.getFirstVisibleLine()
        val visibleLineEnd = editor.getLastVisibleLine()
        val scrollingOffsetY = editor.getOffsetY()
        val fontMetrics = painters.lineNumberPainter.fontMetrics
        val fontMetricsOffset = fontMetrics.descent
        val realOffsetY = offsetY - scrollingOffsetY + fontMetricsOffset

        if (cursor.line < visibleLineStart || cursor.line > visibleLineEnd) {
            return
        }

        val cursorAnimation = editor.animationManager.cursorAnimation
        if (cursorAnimation.isRunning()) {
            val startX = 0f
            val endX = editor.width.toFloat()
            val vectorY =
                cursorAnimation.animatedHighlightLineBottomY()
            val startY = vectorY - lineHeight + realOffsetY
            val endY = vectorY + realOffsetY

            canvas.drawRect(
                startX,
                startY,
                endX,
                endY,
                painter
            )
            return
        }

        val startX = 0f
        val endX = editor.width.toFloat()
        val startY = (cursor.line - 1) * lineHeight + realOffsetY
        val endY = cursor.line * lineHeight + realOffsetY

        canvas.drawRect(
            startX,
            startY,
            endX,
            endY,
            painter
        )
    }

    protected open fun renderCursor(canvas: Canvas) {
        if (!editor.isEnabled || !editor.functionManager.isEditable || editor.actionManager.selectingText) {
            return
        }
        val cursor = editor.getCursor()
        val lineHeight = painters.getLineHeight()
        val painter = painters.cursorPainter
        val visibleLineStart = editor.getFirstVisibleLine()
        val visibleLineEnd = editor.getLastVisibleLine()
        val scrollingOffsetX = editor.getOffsetX()
        val scrollingOffsetY = editor.getOffsetY()
        val fontMetricsInt = painters.codeTextPainter.fontMetricsInt
        val fontMetricsOffset = fontMetricsInt.descent
        val realOffsetY = offsetY - scrollingOffsetY + fontMetricsOffset
        if (cursor.line < visibleLineStart || cursor.line > visibleLineEnd) {
            return
        }

        val cursorAnimation = editor.animationManager.cursorAnimation
        if (cursorAnimation.isRunning()) {
            val animatedX = offsetX + cursorAnimation.animatedX() - scrollingOffsetX
            val animatedY = cursorAnimation.animatedY()
            val startY = animatedY - lineHeight + realOffsetY
            val endY = animatedY + realOffsetY

            canvas.drawLine(
                animatedX,
                startY,
                animatedX,
                endY,
                painter
            )
            return
        }

        val cursorVisibleAnimation = editor.animationManager.cursorVisibleAnimation
        if (cursorVisibleAnimation.isRunning()) {
            if (!cursorVisibleAnimation.isVisible()) {
                return
            }
        }

        val cursorOffsetX = offsetX + getCursorOffsetX(cursor) - scrollingOffsetX
        val startY = (cursor.line - 1) * lineHeight + realOffsetY
        val endY = cursor.line * lineHeight + realOffsetY

        canvas.drawLine(
            cursorOffsetX,
            startY,
            cursorOffsetX,
            endY,
            painter
        )
    }

    protected open fun renderCodeText(canvas: Canvas) {
        val lineHeight = painters.getLineHeight()
        var workLine = editor.getFirstVisibleLine()
        val reachLine = editor.getLastVisibleLine()
        val painter = painters.codeTextPainter
        val scrollingOffsetX = editor.getOffsetX()
        val scrollingOffsetY = editor.getOffsetY()
        val languageManager = editor.languageManager
        val styleManager = editor.styleManager
        val language = languageManager.language
        val spans = styleManager.spans
        val x = offsetX - scrollingOffsetX

        if (language.doSpan() && spans.isNotEmpty()) {
            try {
                while (workLine <= reachLine) {
                    val textRow = textModel.getTextRow(workLine)
                    var offsetX = x
                    val y = (workLine * lineHeight).toFloat() - scrollingOffsetY
                    val firstVisibleRow = editor.getFirstVisibleRow(workLine)
                    val lastVisibleRow = editor.getLastVisibleRow(workLine)
                    val lineSpans =
                        spans.getLineSpan(workLine)
                    for (span in lineSpans) {
                        if (span.startRow > lastVisibleRow) {
                            break
                        }

                        painter.color = span.color.hexColor
                        if (span.endRow >= lastVisibleRow) {
                            if (span.startRow >= firstVisibleRow) {
                                drawTextRun(
                                    canvas,
                                    textRow,
                                    span.startRow,
                                    lastVisibleRow,
                                    span.startRow,
                                    lastVisibleRow,
                                    offsetX,
                                    y,
                                    false,
                                    painter
                                )

                                offsetX += layout.measureLineRow(
                                    workLine,
                                    span.startRow,
                                    lastVisibleRow
                                )
                            } else {
                                drawTextRun(
                                    canvas,
                                    textRow,
                                    firstVisibleRow,
                                    lastVisibleRow,
                                    firstVisibleRow,
                                    lastVisibleRow,
                                    offsetX,
                                    y,
                                    false,
                                    painter
                                )
                                offsetX += layout.measureLineRow(
                                    workLine,
                                    firstVisibleRow,
                                    lastVisibleRow
                                )
                            }
                        } else {
                            if (span.startRow >= firstVisibleRow) {
                                drawTextRun(
                                    canvas,
                                    textRow,
                                    span.startRow,
                                    span.endRow,
                                    span.startRow,
                                    span.endRow,
                                    offsetX,
                                    y,
                                    false,
                                    painter
                                )
                                offsetX += layout.measureLineRow(
                                    workLine,
                                    span.startRow,
                                    span.endRow
                                )
                            } else {
                                drawTextRun(
                                    canvas,
                                    textRow,
                                    firstVisibleRow,
                                    span.endRow,
                                    firstVisibleRow,
                                    span.endRow,
                                    offsetX,
                                    y,
                                    false,
                                    painter
                                )
                                offsetX += layout.measureLineRow(
                                    workLine,
                                    firstVisibleRow,
                                    span.endRow
                                )
                            }
                        }
                    }
                    maxWidth = max(maxWidth, offsetX)
                    ++workLine
                }

            } catch (e: IndexOutOfBoundsException) {
                renderCodeTextBasic(canvas)
            } catch (e: ConcurrentModificationException) {
                renderCodeTextBasic(canvas)
            }
        } else {
            renderCodeTextBasic(canvas)
        }
    }

    @SuppressLint("NewApi")
    @Suppress("SameParameterValue", "NOTHING_TO_INLINE")
    private inline fun drawTextRun(
        canvas: Canvas,
        text: CharSequence,
        start: Int,
        end: Int,
        contextStart: Int,
        contextEnd: Int,
        x: Float,
        y: Float,
        isRtl: Boolean,
        paint: Paint
    ) {
        if (isSupportedDrawTextRun) {
            canvas.drawTextRun(text, start, end, contextStart, contextEnd, x, y, isRtl, paint)
        } else {
            canvas.drawText(text, start, end, x, y, paint)
        }
    }

    protected open fun renderCodeTextBasic(canvas: Canvas) {
        val lineHeight = painters.getLineHeight()
        var workLine = editor.getFirstVisibleLine()
        val reachLine = editor.getLastVisibleLine()
        val painter = painters.codeTextPainter
        val scrollingOffsetX = editor.getOffsetX()
        val scrollingOffsetY = editor.getOffsetY()
        val x = offsetX - scrollingOffsetX

        painter.color = theme.getColor(ThemeToken.IDENTIFIER_COLOR_TOKEN).hexColor
        try {
            while (workLine <= reachLine) {
                val firstVisibleRow = editor.getFirstVisibleRow(workLine)
                val lastVisibleRow = editor.getLastVisibleRow(workLine)

                val textRow = textModel.getTextRow(workLine)
                val y = (workLine * lineHeight).toFloat() - scrollingOffsetY

                canvas.drawText(textRow, firstVisibleRow, lastVisibleRow, x, y, painter)
                maxWidth =
                    max(maxWidth, layout.measureLineRow(workLine, firstVisibleRow, lastVisibleRow))
                ++workLine
            }
        } catch (e: IndexOutOfBoundsException) {
            renderCodeTextBasic(canvas)
        }
    }

    protected open fun renderTextSelectHandle(canvas: Canvas) {
        val actionManager = editor.actionManager
        if (!actionManager.selectingText) {
            return
        }

        val selectionRange = actionManager.selectingRange!!
        val startPos = selectionRange.start
        val endPos = selectionRange.end
        val lineHeight = painters.getLineHeight()
        val textSelectHandle = editor.textSelectHandle
        val scrollingOffsetX = editor.getOffsetX()
        val scrollingOffsetY = editor.getOffsetY()
        val painter = painters.textSelectHandlePainter
        val firstVisibleRow = editor.getFirstVisibleRow(startPos.line)
        val startRow = max(firstVisibleRow, startPos.row)
        val lastVisibleRow = editor.getLastVisibleRow(endPos.line)
        val endRow = min(lastVisibleRow, endPos.row)

        val startX =
            offsetX + layout.measureLineRow(startPos.line, 0, startRow) - scrollingOffsetX
        val startY =
            startPos.line * lineHeight.toFloat() - scrollingOffsetY + lineHeight / 6
        textSelectHandle.draw(
            canvas,
            startX,
            startY,
            painter,
            TextSelectHandle.LEFT_TEXT_SELECT_HANDLE
        )
        textSelectHandleStartParams[0] = textSelectHandle.startX
        textSelectHandleStartParams[1] = textSelectHandle.startY
        textSelectHandleStartParams[2] = textSelectHandle.endX
        textSelectHandleStartParams[3] = textSelectHandle.endY

        val endX =
            offsetX + layout.measureLineRow(endPos.line, 0, endRow) - scrollingOffsetX
        val endY = endPos.line * lineHeight.toFloat() - scrollingOffsetY + lineHeight / 6
        textSelectHandle.draw(
            canvas,
            endX,
            endY,
            painter,
            TextSelectHandle.RIGHT_TEXT_SELECT_HANDLE
        )
        textSelectHandleEndParams[0] = textSelectHandle.startX
        textSelectHandleEndParams[1] = textSelectHandle.startY
        textSelectHandleEndParams[2] = textSelectHandle.endX
        textSelectHandleEndParams[3] = textSelectHandle.endY
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun getCursorOffsetX(cursor: Cursor): Float {
        if (cursor.row == 0) {
            return 0f
        }

        return layout.measureLineRow(cursor.line, 0, cursor.row)
    }

    protected open fun renderLineNumberBackground(canvas: Canvas) {
        if (!editor.functionManager.isLineNumberEnabled) return
        val lineNumberBackgroundPainter = painters.lineNumberBackgroundPainter

        var endX = offsetX - margin * 2 - dividingLineWidth
        if (!editor.functionManager.isStickyLineNumberEnabled) {
            endX -= editor.getOffsetX()
        }
        val endY = editor.height.toFloat()

        canvas.drawRect(
            0f,
            0f,
            endX,
            endY,
            lineNumberBackgroundPainter
        )

        if (!editor.functionManager.isDividingLineEnabled) {
            return
        }

        val newLeft = offsetX - margin * 2 - dividingLineWidth
        var start = newLeft
        var end = newLeft + dividingLineWidth
        if (!editor.functionManager.isStickyLineNumberEnabled) {
            start -= editor.getOffsetX()
            end -= editor.getOffsetX()
        }
        val lineNumberDividingLinePainter = painters.lineNumberDividingLinePainter
        canvas.drawRect(
            start,
            0f,
            end,
            endY,
            lineNumberDividingLinePainter
        )
    }

    protected open fun renderLineNumber(canvas: Canvas) {
        if (!editor.functionManager.isLineNumberEnabled) return
        val lineHeight = painters.getLineHeight()
        var workLine = editor.getFirstVisibleLine()
        val reachLine = editor.getLastVisibleLine()
        val scrollingOffsetY = editor.getOffsetY()
        val painter = painters.lineNumberPainter

        var startX = offsetX - margin * 3
        if (!editor.functionManager.isStickyLineNumberEnabled) {
            startX -= editor.getOffsetX()
        }
        while (workLine <= reachLine) {
            canvas.drawText(
                workLine.toString(), startX,
                (lineHeight * workLine).toFloat() - scrollingOffsetY,
                painter
            )
            ++workLine
        }
    }
}