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

package com.mucheng.editor.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Canvas
import android.os.Build
import android.os.Bundle
import android.os.TransactionTooLargeException
import android.text.InputType
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.view.WindowManager
import android.view.accessibility.AccessibilityNodeInfo
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import android.view.inputmethod.InputMethodManager
import android.widget.OverScroller
import com.mucheng.annotations.mark.Hide
import com.mucheng.annotations.mark.InvalidateRequired
import com.mucheng.annotations.mark.Model
import com.mucheng.editor.base.AbstractComponent
import com.mucheng.editor.base.AbstractManager
import com.mucheng.editor.base.AbstractTheme
import com.mucheng.editor.base.IInputHandler
import com.mucheng.editor.base.lang.AbstractLanguage
import com.mucheng.editor.base.layout.AbstractLayout
import com.mucheng.editor.component.Cursor
import com.mucheng.editor.component.Painters
import com.mucheng.editor.component.Renderer
import com.mucheng.editor.component.TextSelectHandle
import com.mucheng.editor.debug.MyLog
import com.mucheng.editor.layout.TextModelLayout
import com.mucheng.editor.manager.EditorActionManager
import com.mucheng.editor.manager.EditorAnimationManager
import com.mucheng.editor.manager.EditorEventManager
import com.mucheng.editor.manager.EditorFunctionManager
import com.mucheng.editor.manager.EditorLanguageManager
import com.mucheng.editor.manager.EditorStyleManager
import com.mucheng.editor.manager.EditorUndoManager
import com.mucheng.editor.proxy.InputConnectionProxy
import com.mucheng.editor.render.ExternalRenderer
import com.mucheng.editor.tool.executeAnimation
import com.mucheng.editor.touch.TouchHandler
import com.mucheng.text.model.AndroidTextModel
import com.mucheng.text.model.base.AbstractTextModel
import com.mucheng.text.model.position.CharRangePosition
import kotlin.math.max
import kotlin.math.min

@Suppress("LeakingThis", "unused", "MemberVisibilityCanBePrivate")
open class MuCodeEditor @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {

        private const val TAG = "MuCodeEditor"

        const val ACTION_MANAGER = "ActionManager"

        const val ANIMATION_MANAGER = "AnimationManager"

        const val EVENT_MANAGER = "EventManager"

        const val FUNCTION_MANAGER = "FunctionManager"

        const val LANGUAGE_MANAGER = "LanguageManager"

        const val STYLE_MANAGER = "StyleManager"

        const val UNDO_MANAGER = "UndoManager"

        const val RENDERER = "Renderer"

        const val PAINTERS = "Painters"

        const val LAYOUT = "Layout"

        const val TEXT_SELECT_HANDLE = "TextSelectHandle"

    }

    var actionManager: EditorActionManager
        private set

    var animationManager: EditorAnimationManager
        private set

    var eventManager: EditorEventManager
        private set

    var functionManager: EditorFunctionManager
        private set

    var languageManager: EditorLanguageManager
        private set

    var styleManager: EditorStyleManager
        private set

    var undoManager: EditorUndoManager
        private set

    var layout: AbstractLayout = TextModelLayout(this)
        private set

    private var renderer: Renderer = Renderer(this)

    var textSelectHandle: TextSelectHandle = TextSelectHandle(this)
        private set

    private val scroller = OverScroller(context)

    @Model
    private var textModel: AbstractTextModel = AndroidTextModel()

    private val cursor = Cursor(this)

    private val externalRenderers: MutableList<ExternalRenderer> = ArrayList()

    private val touchHandler = TouchHandler(this)

    private val gestureDetector = createGestureDetector(touchHandler)

    private val scaleGestureDetector = ScaleGestureDetector(context, touchHandler)

    private val inputConnectionProxy = InputConnectionProxy(this)

    private var lastReachingPositionTime: Long = 0

    init {
        eventManager = EditorEventManager(this)
        actionManager = EditorActionManager(this)
        functionManager = EditorFunctionManager(this)
        styleManager = EditorStyleManager(this)
        languageManager = EditorLanguageManager(this)
        animationManager = EditorAnimationManager(this)
        undoManager = EditorUndoManager((this))
        initialize()
        postInvalidate()
    }

    @Suppress("DEPRECATION")
    @Hide
    private fun initialize() {
        val context = context
        if (context is Activity) {
            // 设置弹出软键盘时自动改变大小
            context.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        }
        isFocusable = true
        isFocusableInTouchMode = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            defaultFocusHighlightEnabled = false
        }
    }

    @Hide
    private fun createGestureDetector(touchHandler: TouchHandler): GestureDetector {
        val gestureDetector = GestureDetector(context, touchHandler)
        gestureDetector.setOnDoubleTapListener(touchHandler)
        return gestureDetector
    }

    override fun computeScroll() {
        if (scroller.computeScrollOffset()) {
            postInvalidateOnAnimation()
        }
    }

    override fun onDraw(canvas: Canvas) {
        eventManager.dispatchRenderBeginEvent()
        renderer.render(canvas)
        externalRenderers.forEach {
            it.render(canvas)
        }
        eventManager.dispatchRenderFinishEvent()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val result = gestureDetector.onTouchEvent(event)
        val result2 = scaleGestureDetector.onTouchEvent(event)
        if (event.action == MotionEvent.ACTION_UP) {
            touchHandler.onUp(event)
        }
        return result || result2
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        animationManager.cursorVisibleAnimation.start()
        languageManager.operatorPanel.show()
        MyLog.e(TAG, "Attach CursorVisibleAnimation")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        animationManager.cursorVisibleAnimation.cancel()
        MyLog.e(TAG, "Cancel CursorVisibleAnimation")
    }

    @Suppress("KotlinConstantConditions", "KotlinConstantConditions")
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        val isCtrlPressed = event.isCtrlPressed
        val isAltPressed = event.isAltPressed
        val isShiftPressed = event.isShiftPressed
        when (keyCode) {

            KeyEvent.KEYCODE_ENTER -> {
                inputConnectionProxy.commitText("\n", 0)
            }

            KeyEvent.KEYCODE_DEL, KeyEvent.KEYCODE_FORWARD_DEL -> {
                inputConnectionProxy.deleteSurroundingText(0, 0)
            }

            KeyEvent.KEYCODE_SPACE -> {
                inputConnectionProxy.commitText(" ", 0)
            }

            KeyEvent.KEYCODE_TAB -> {
                inputConnectionProxy.commitText(" ".repeat(3), 0)
            }

            KeyEvent.KEYCODE_DPAD_LEFT -> {
                actionManager.cancelSelectingText()
                inputConnectionProxy.moveCursorToLeft()
            }

            KeyEvent.KEYCODE_DPAD_UP -> {
                actionManager.cancelSelectingText()
                inputConnectionProxy.moveCursorToTop()
            }

            KeyEvent.KEYCODE_DPAD_RIGHT -> {
                actionManager.cancelSelectingText()
                inputConnectionProxy.moveCursorToRight()
            }

            KeyEvent.KEYCODE_DPAD_DOWN -> {
                actionManager.cancelSelectingText()
                inputConnectionProxy.moveCursorToBottom()
            }

            KeyEvent.KEYCODE_PAGE_UP -> {
                actionManager.cancelSelectingText()
                moveCursorToHome()
            }

            KeyEvent.KEYCODE_PAGE_DOWN -> {
                actionManager.cancelSelectingText()
                moveCursorToEnd()
            }

            KeyEvent.KEYCODE_MOVE_HOME -> {
                actionManager.cancelSelectingText()
                moveCursorToLineStart()
            }

            KeyEvent.KEYCODE_MOVE_END -> {
                actionManager.cancelSelectingText()
                moveCursorToLineEnd()
            }

            KeyEvent.KEYCODE_PASTE -> {
                // 粘贴
                paste()
            }

            KeyEvent.KEYCODE_COPY -> {
                // 复制
                copySelection()
            }

            else -> {
                if (isCtrlPressed && !isAltPressed && keyCode == KeyEvent.KEYCODE_A) {
                    selectionAll()
                } else if (isCtrlPressed && !isAltPressed && keyCode == KeyEvent.KEYCODE_C) {
                    copySelection()
                } else if (isCtrlPressed && !isAltPressed && keyCode == KeyEvent.KEYCODE_V) {
                    paste()
                } else if (isCtrlPressed && !isAltPressed && keyCode == KeyEvent.KEYCODE_Z) {
                    undo()
                } else if (isCtrlPressed && !isAltPressed && isShiftPressed && keyCode == KeyEvent.KEYCODE_Z) {
                    redo()
                } else {
                    if (!isCtrlPressed && !isAltPressed && event.isPrintingKey) {
                        inputConnectionProxy.commitText(
                            String(byteArrayOf(event.unicodeChar.toByte())),
                            0
                        )
                    }
                }
            }

        }
        return super.onKeyDown(keyCode, event)
    }

    override fun createAccessibilityNodeInfo(): AccessibilityNodeInfo {
        val node = super.createAccessibilityNodeInfo()
        node.isEditable = functionManager.isEditable && isEnabled
        node.isScrollable = true
        node.inputType = InputType.TYPE_CLASS_TEXT
        node.isMultiLine = true
        node.isLongClickable = true
        node.text = getText().toString()
        node.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_COPY)
        node.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_PASTE)
        node.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD)
        node.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD)
        return node
    }

    override fun performAccessibilityAction(action: Int, arguments: Bundle?): Boolean {
        when (action) {
            AccessibilityNodeInfo.ACTION_COPY -> {
                copySelection()
            }

            AccessibilityNodeInfo.ACTION_PASTE -> {
                paste()
            }

            AccessibilityNodeInfo.ACTION_SCROLL_FORWARD -> {
                moveCursorToHome()
                return true
            }

            AccessibilityNodeInfo.ACTION_SCROLL_BACKWARD -> {
                moveCursorToEnd()
                return true
            }
        }
        return super.performAccessibilityAction(action, arguments)
    }

    open fun getOffsetX(): Int {
        return scroller.currX
    }

    open fun getOffsetY(): Int {
        return scroller.currY
    }

    open fun getLeftToolbarWidth(): Float {
        return renderer.getLeftToolbarWidth()
    }

    open fun getScrollMaxX(): Int {
        return max(
            0f,
            layout.getLayoutWidth() + getLeftToolbarWidth() + getScroller().currX - width / 2f
        ).toInt()
    }

    open fun getScrollMaxY(): Int {
        return max(0, layout.getLayoutHeight() - height / 2)
    }

    open fun getFirstVisibleLine(): Int {
        return layout.getFirstVisibleLine()
    }

    open fun getLastVisibleLine(): Int {
        return layout.getLastVisibleLine()
    }

    open fun getFirstVisibleRow(line: Int): Int {
        return layout.getFirstVisibleRow(line)
    }

    open fun getLastVisibleRow(line: Int): Int {
        return layout.getLastVisibleRow(line)
    }

    open fun getTextSelectHandleStartParams(): FloatArray {
        return renderer.textSelectHandleStartParams
    }

    open fun getTextSelectHandleEndParams(): FloatArray {
        return renderer.textSelectHandleEndParams
    }

    open fun getRenderMaxX(): Float {
        return renderer.maxWidth
    }

    open fun showSoftInput() {
        if (isEnabled && functionManager.isEditable) {
            if (isInTouchMode) {
                requestFocusFromTouch()
            }

            if (!hasFocus()) {
                requestFocus()
            }

            val inputMethodManager =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            requestFocus()
            inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    open fun isSoftInputActive(): Boolean {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.isActive(this)
    }

    open fun hideSoftInput() {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        inputMethodManager.hideSoftInputFromWindow(
            windowToken,
            InputMethodManager.HIDE_IMPLICIT_ONLY
        )
    }

    open fun setSelection(startLine: Int, startRow: Int, endLine: Int, endRow: Int): MuCodeEditor {
        val textLineModel = textModel.getTextRow(startLine)
        if (startLine == 1 && startLine == textModel.lastLine && textLineModel.isEmpty()) {
            return this
        }

        closeAutoCompletionPanel()
        val indexer = textModel.getIndexer()
        val rangePosition = CharRangePosition(
            indexer.lineRowToPosition(startLine, startRow),
            indexer.lineRowToPosition(endLine, endRow)
        )
        actionManager.selectingText(rangePosition)
        postInvalidate()
        return this
    }

    open fun selectionAll(): MuCodeEditor {
        val lastTextLineModel = textModel.getTextRow(textModel.lastLine)
        return setSelection(
            1, 0,
            textModel.lastLine, lastTextLineModel.length
        )
    }

    open fun moveCursorToPosition(line: Int, row: Int): MuCodeEditor {
        val cursorAnimation = animationManager.cursorAnimation
        cursor.executeAnimation(cursorAnimation) {
            cursor.moveToPosition(line, row)
        }
        reachToCursor(cursor)
        return this
    }

    open fun moveCursorToLineStart(): MuCodeEditor {
        return moveCursorToPosition(cursor.line, 0)
    }

    open fun moveCursorToLineEnd(): MuCodeEditor {
        val cursorLine = cursor.line
        return moveCursorToPosition(cursorLine, textModel.getTextRowSize(cursorLine))
    }

    open fun moveCursorToLeft(): MuCodeEditor {
        val cursorAnimation = animationManager.cursorAnimation
        cursor.executeAnimation(cursorAnimation) {
            cursor.moveToLeft()
        }
        reachToCursor(cursor)
        return this
    }

    open fun moveCursorToHome(): MuCodeEditor {
        return moveCursorToPosition(1, 0)
    }

    open fun moveCursorToEnd(): MuCodeEditor {
        return moveCursorToPosition(
            textModel.lastLine,
            textModel.getTextRow(textModel.lastLine).length
        )
    }

    open fun moveCursorToTop(): MuCodeEditor {
        val cursorAnimation = animationManager.cursorAnimation
        cursor.executeAnimation(cursorAnimation) {
            cursor.moveToTop()
        }
        reachToCursor(cursor)
        return this
    }

    open fun moveCursorToRight(): MuCodeEditor {
        val cursorAnimation = animationManager.cursorAnimation
        cursor.executeAnimation(cursorAnimation) {
            cursor.moveToRight()
        }
        reachToCursor(cursor)
        return this
    }

    open fun moveCursorToBottom(): MuCodeEditor {
        val cursorAnimation = animationManager.cursorAnimation
        cursor.executeAnimation(cursorAnimation) {
            cursor.moveToBottom()
        }
        reachToCursor(cursor)
        return this
    }

    open fun insertText(text: CharSequence): MuCodeEditor {
        inputConnectionProxy.commitText(text, 0, false)
        return this
    }

    @InvalidateRequired
    open fun insertText(line: Int, row: Int, text: CharSequence): MuCodeEditor {
        textModel.insert(line, row, text)
        return this
    }

    open fun deleteText(): MuCodeEditor {
        inputConnectionProxy.deleteSurroundingText(0, 0)
        return this
    }

    open fun undo(): MuCodeEditor {
        if (canUndo()) {
            undoManager.undo()
            postInvalidate()
        }
        return this
    }

    open fun redo(): MuCodeEditor {
        if (canRedo()) {
            undoManager.redo()
            postInvalidate()
        }
        return this
    }

    open fun canUndo(): Boolean {
        return undoManager.canUndo()
    }

    open fun canRedo(): Boolean {
        return undoManager.canRedo()
    }

    open fun setTheme(theme: AbstractTheme): MuCodeEditor {
        styleManager.setTheme(theme)
        languageManager.analyze()
        postInvalidate()
        return this
    }

    open fun getTheme(): AbstractTheme {
        return styleManager.theme
    }

    open fun setDarkTheme(darkTheme: Boolean): MuCodeEditor {
        styleManager.theme.setEnabledDarkColors(darkTheme)
        languageManager.analyze()
        postInvalidate()
        return this
    }

    open fun isDarkTheme(): Boolean {
        return styleManager.theme.isDarkTheme()
    }

    open fun setLanguage(language: AbstractLanguage): MuCodeEditor {
        languageManager.setLanguage(language)
        languageManager.analyze()
        postInvalidate()
        return this
    }

    open fun getLanguage(): AbstractLanguage {
        return languageManager.language
    }

    fun getCurrentCodeBlockLanguage(): AbstractLanguage? {
        return languageManager.autoCompletionPanel.getCurrentCodeBlockLanguage()
    }

    open fun requireAutoCompletionPanel(): MuCodeEditor {
        languageManager.autoCompletionPanel.launchRequireAutoCompletionCoroutine()
        return this
    }

    open fun closeAutoCompletionPanel(): MuCodeEditor {
        languageManager.autoCompletionPanel.dismiss()
        return this
    }

    open fun showOperatorPanel(): MuCodeEditor {
        languageManager.operatorPanel.show()
        return this
    }

    open fun closeOperatorPanel(): MuCodeEditor {
        languageManager.operatorPanel.dismiss()
        return this
    }

    open fun showToolOptionPanel(): MuCodeEditor {
        languageManager.toolOptionPanel.show()
        return this
    }

    fun closeToolOptionPanel(): MuCodeEditor {
        languageManager.toolOptionPanel.dismiss()
        return this
    }

    open fun copySelection(): MuCodeEditor {
        val selectionRange = actionManager.selectingRange ?: return this
        val startPos = selectionRange.start
        val endPos = selectionRange.end
        val clipboardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val data = ClipData.newPlainText(
            null, textModel.subSequence(
                startPos.line, startPos.row, endPos.line, endPos.row
            )
        )
        try {
            clipboardManager.setPrimaryClip(data)
        } catch (_: TransactionTooLargeException) {
        }
        return this
    }

    open fun paste(): MuCodeEditor {
        val clipboardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        if (!clipboardManager.hasPrimaryClip()) {
            return this
        }

        val clipData = clipboardManager.primaryClip ?: return this
        val nearestText = clipData.getItemAt(0).text
        insertText(nearestText)
        return this
    }

    open fun setInputHandler(inputHandler: IInputHandler): MuCodeEditor {
        inputConnectionProxy.setInputHandler(inputHandler)
        return this
    }

    open fun getInputHandler(): IInputHandler {
        return inputConnectionProxy.getInputHandler()
    }

    override fun onCheckIsTextEditor(): Boolean {
        return isEnabled && functionManager.isEditable
    }

    override fun onCreateInputConnection(outAttrs: EditorInfo): InputConnection {
        outAttrs.inputType = EditorInfo.TYPE_TEXT_FLAG_MULTI_LINE
        return inputConnectionProxy
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        touchHandler.scrollBy(
            (if (getOffsetX() > getScrollMaxX()) getScrollMaxX() - getOffsetX() else 0).toFloat(),
            (if (getOffsetY() > getScrollMaxY()) getScrollMaxY() - getOffsetY() else 0).toFloat()
        )
        // 如果 Cursor 在合法区间内则进行 reach 操作
        if (cursor.line <= textModel.lastLine && cursor.row >= 0) {
            reachToCursor(cursor)
        }
        eventManager.dispatchSizeChangedEvent(w, h, oldw, oldh)
    }

    protected open fun getComplexHeight(): Int {
        val height = height
        val operatorPanel = languageManager.operatorPanel
        val operatorHeight =
            if (functionManager.isOperatorPanelEnabled && operatorPanel.isShowing) {
                operatorPanel.height
            } else {
                0
            }
        return height - operatorHeight
    }

    open fun reachToCursor(cursor: Cursor): MuCodeEditor {
        return reachToPosition(cursor.line, cursor.row)
    }

    open fun reachToPosition(line: Int, row: Int, useAnimation: Boolean = true): MuCodeEditor {
        val lineHeight = styleManager.painters.getLineHeight()
        val offsetY = line * lineHeight
        val offsetX = getLeftToolbarWidth() + layout.measureLineRow(line, 0, row)

        var targetX: Float = getOffsetX().toFloat()
        var targetY: Float = getOffsetY().toFloat()

        if (offsetY - lineHeight < getOffsetY()) {
            targetY = offsetY - lineHeight * 1.1f
        }

        if (offsetY > getComplexHeight() + getOffsetY()) {
            targetY = (offsetY - getComplexHeight() + lineHeight * 0.1).toFloat()
        }

        var charWidth = 0f
        val textLineModel = textModel.getTextRow(line)
        if (row <= textLineModel.lastIndex) {
            charWidth = layout.measureLineRow(line, row, row + 1)
        }

        if (offsetX < getOffsetX()) {
            targetX = offsetX - getLeftToolbarWidth() - charWidth * 0.2f
        }

        if (offsetX + charWidth > getOffsetX() + width) {
            targetX = offsetX + charWidth * 0.8f - width
        }

        targetX = max(0f, min(getScrollMaxX().toFloat(), targetX))
        targetY = max(0f, min(getScrollMaxY().toFloat(), targetY))

        if (useAnimation) {
            if (System.currentTimeMillis() - lastReachingPositionTime >= 100) {
                scroller.forceFinished(true)
                scroller.startScroll(
                    getOffsetX(),
                    getOffsetY(),
                    (targetX - getOffsetX()).toInt(),
                    (targetY - getOffsetY()).toInt()
                )
            } else {
                scroller.startScroll(
                    getOffsetX(),
                    getOffsetY(),
                    (targetX - getOffsetX()).toInt(),
                    (targetY - getOffsetY()).toInt(),
                    0
                )
            }
        } else {
            scroller.startScroll(
                getOffsetX(),
                getOffsetY(),
                (targetX - getOffsetX()).toInt(),
                (targetY - getOffsetY()).toInt(),
                0
            )
        }
        lastReachingPositionTime = System.currentTimeMillis()
        invalidate()
        return this
    }

    open fun scrollBy(
        distanceX: Float,
        distanceY: Float,
        animation: Boolean = false
    ): MuCodeEditor {
        touchHandler.scrollBy(distanceX, distanceY, animation)
        return this
    }

    @Suppress("CAST_NEVER_SUCCEEDS")
    open fun replaceComponent(componentType: String, component: AbstractComponent): MuCodeEditor {
        when (componentType) {
            RENDERER -> renderer = component as Renderer

            PAINTERS -> styleManager.setPainters(componentType as Painters)

            LAYOUT -> layout = component as AbstractLayout

            TEXT_SELECT_HANDLE -> textSelectHandle = component as TextSelectHandle

            else -> throw IllegalArgumentException("Unknown component type \"$componentType\", cannot successfully replace it")
        }
        postInvalidate()
        return this
    }

    open fun replaceManager(managerType: String, manager: AbstractManager): MuCodeEditor {
        when (managerType) {
            ACTION_MANAGER -> actionManager = manager as EditorActionManager

            ANIMATION_MANAGER -> animationManager = manager as EditorAnimationManager

            EVENT_MANAGER -> eventManager = manager as EditorEventManager

            FUNCTION_MANAGER -> functionManager = manager as EditorFunctionManager

            LANGUAGE_MANAGER -> languageManager = manager as EditorLanguageManager

            STYLE_MANAGER -> styleManager = manager as EditorStyleManager

            UNDO_MANAGER -> undoManager = manager as EditorUndoManager

            else -> throw IllegalArgumentException("Unknown manager type \"$managerType\", cannot successfully replace it")
        }
        postInvalidate()
        return this
    }

    @Synchronized
    open fun setText(text: CharSequence): MuCodeEditor {
        post {
            languageManager.autoCompletionPanel.dismiss()
        }
        functionManager.setUndoStackEnabled(false)
        undoManager.clear()

        if (text is AbstractTextModel) {
            this.textModel = text
        } else {
            textModel.clear()
            textModel.append(text)
        }

        // Reset cursor position
        cursor.moveToPosition(1, 0)

        functionManager.setUndoStackEnabled(true)
        post {
            eventManager.dispatchContentChangedEvent()
        }

        // Cancel selecting the text.
        if (actionManager.selectingText) {
            actionManager.cancelSelectingText()
        }

        post {
            reachToCursor(cursor)
        }
        return this
    }

    open fun getText(): AbstractTextModel {
        return textModel
    }

    open fun addExternalRenderer(renderer: ExternalRenderer): MuCodeEditor {
        externalRenderers.add(renderer)
        return this
    }

    open fun removeExternalRenderer(renderer: ExternalRenderer): MuCodeEditor {
        externalRenderers.remove(renderer)
        return this
    }

    open fun getScroller(): OverScroller {
        return scroller
    }

    open fun getCursor(): Cursor {
        return cursor
    }

    open fun setDebug(isEnabled: Boolean): MuCodeEditor {
        MyLog.setPrint(isEnabled)
        return this
    }

    @Hide
    private inline fun timeUse(block: () -> Unit) {
        val startTime = System.currentTimeMillis()
        block()
        val endTime = System.currentTimeMillis()
        MyLog.e(TAG, "Time cost: ${endTime - startTime} ms.")
    }

}