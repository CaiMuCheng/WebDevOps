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

package com.mucheng.editor.manager

import com.mucheng.annotations.mark.InvalidateRequired
import com.mucheng.annotations.mark.Model
import com.mucheng.editor.base.AbstractManager
import com.mucheng.editor.view.MuCodeEditor

@Suppress("LeakingThis", "unused")
@Model
open class EditorFunctionManager(editor: MuCodeEditor) :
    AbstractManager(editor) {

    open var isEditable = true
        protected set

    open var isScalable = true
        protected set

    open var isAutoCompletionPanelEnabled = true
        protected set

    open var isOperatorPanelEnabled = true
        protected set

    open var isToolOptionPanelEnabled = true
        protected set

    open var isUndoStackEnabled = true
        protected set

    open var isCursorAnimationEnabled = true
        protected set

    open var isCursorVisibleAnimationEnabled = true
        protected set

    open var isLineNumberEnabled = true
        protected set

    open var isFlexAnimationEnabled = false
        protected set

    open var isDividingLineEnabled = false
        protected set

    open var isCustomBackgroundEnabled = true
        protected set

    open var isStickyLineNumberEnabled = false
        protected set

    @InvalidateRequired
    fun setEditEnabled(isEditable: Boolean): EditorFunctionManager {
        this.isEditable = isEditable
        editor.actionManager.cancelSelectingText()
        return this
    }

    @InvalidateRequired
    open fun setScalable(isScalable: Boolean): EditorFunctionManager {
        this.isScalable = isScalable
        return this
    }

    @InvalidateRequired
    open fun setAutoCompletionPanelEnabled(isAutoCompletionPanelEnabled: Boolean): EditorFunctionManager {
        this.isAutoCompletionPanelEnabled = isAutoCompletionPanelEnabled
        if (!isAutoCompletionPanelEnabled) {
            editor.languageManager.autoCompletionPanel.dismiss()
        } else {
            editor.requireAutoCompletionPanel()
        }
        return this
    }

    @InvalidateRequired
    open fun setOperatorPanelEnabled(isOperatorPanelEnabled: Boolean): EditorFunctionManager {
        this.isOperatorPanelEnabled = isOperatorPanelEnabled
        if (!isOperatorPanelEnabled) {
            editor.languageManager.operatorPanel.dismiss()
        } else {
            editor.languageManager.operatorPanel.show()
        }
        return this
    }

    open fun setToolOptionPanelEnabled(isToolOptionPanelEnabled: Boolean): EditorFunctionManager {
        this.isToolOptionPanelEnabled = isToolOptionPanelEnabled
        if (!isToolOptionPanelEnabled) {
            editor.languageManager.toolOptionPanel.dismiss()
        } else {
            editor.languageManager.toolOptionPanel.show()
        }
        return this
    }

    @InvalidateRequired
    open fun setUndoStackEnabled(isUndoStackEnabled: Boolean): EditorFunctionManager {
        this.isUndoStackEnabled = isUndoStackEnabled
        return this
    }

    @InvalidateRequired
    fun setCursorAnimationEnabled(cursorAnimationEnabled: Boolean): EditorFunctionManager {
        isCursorAnimationEnabled = cursorAnimationEnabled
        return this
    }

    @InvalidateRequired
    fun setCursorVisibleAnimationEnabled(cursorVisibleAnimationEnabled: Boolean): EditorFunctionManager {
        isCursorVisibleAnimationEnabled = cursorVisibleAnimationEnabled
        if (isCursorVisibleAnimationEnabled) {
            editor.animationManager.cursorVisibleAnimation.start()
        } else {
            editor.animationManager.cursorVisibleAnimation.cancel()
        }
        return this
    }

    @InvalidateRequired
    fun setLineNumberEnabled(
        lineNumberEnabled: Boolean
    ): EditorFunctionManager {
        isLineNumberEnabled = lineNumberEnabled
        return this
    }

    @InvalidateRequired
    fun setDividingLineEnabled(
        dividingLineEnabled: Boolean
    ): EditorFunctionManager {
        isDividingLineEnabled = dividingLineEnabled
        return this
    }

    @InvalidateRequired
    fun setStickyLineNumberEnabled(
        stickyLineNumberEnabled: Boolean
    ): EditorFunctionManager {
        isStickyLineNumberEnabled = stickyLineNumberEnabled
        return this
    }

    @InvalidateRequired
    fun setCustomBackgroundEnabled(customBackgroundEnabled: Boolean): EditorFunctionManager {
        isCustomBackgroundEnabled = customBackgroundEnabled
        return this
    }

}