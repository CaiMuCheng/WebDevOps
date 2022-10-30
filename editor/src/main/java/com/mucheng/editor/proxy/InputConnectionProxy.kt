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

package com.mucheng.editor.proxy

import android.view.inputmethod.BaseInputConnection
import com.mucheng.editor.base.IInputHandler
import com.mucheng.editor.input.TextInputHandler
import com.mucheng.editor.view.MuCodeEditor

@Suppress("MemberVisibilityCanBePrivate")
open class InputConnectionProxy(
    protected val editor: MuCodeEditor,
    fullEditor: Boolean = true
) :
    BaseInputConnection(editor, fullEditor) {

    private var inputHandler: IInputHandler = TextInputHandler(editor)

    open fun setInputHandler(inputHandler: IInputHandler): InputConnectionProxy {
        this.inputHandler = inputHandler
        return this
    }

    open fun getInputHandler(): IInputHandler {
        return inputHandler
    }

    override fun commitText(text: CharSequence, newCursorPosition: Int): Boolean {
        return commitText(text, newCursorPosition, true)
    }

    open fun commitText(
        text: CharSequence,
        newCursorPosition: Int,
        requireAutoCompletionPanel: Boolean
    ): Boolean {
        val isSelectingText = editor.actionManager.selectingText
        if (editor.isEnabled && editor.functionManager.isEditable) {
            if (isSelectingText) {
                inputHandler.replaceSelectingText(text)
            } else {
                inputHandler.commitText(text, requireAutoCompletionPanel)
            }
        }
        return true
    }

    override fun deleteSurroundingText(beforeLength: Int, afterLength: Int): Boolean {
        val isSelectingText = editor.actionManager.selectingText
        if (editor.isEnabled && editor.functionManager.isEditable) {
            if (isSelectingText) {
                inputHandler.deleteSelectingText()
            } else {
                inputHandler.deleteText()
            }
        }
        return true
    }

    /**
     * 不支持按码点删除文本，按照文档返回 false
     * */
    override fun deleteSurroundingTextInCodePoints(beforeLength: Int, afterLength: Int): Boolean {
        return false
    }

    /**
     * 不支持组合文本的输入，按照文档结束 ComposingText 以及返回 false
     * */
    override fun setComposingText(text: CharSequence?, newCursorPosition: Int): Boolean {
        finishComposingText()
        return false
    }

    /**
     * 不支持开始批量编辑，按照文档返回 false
     * */
    override fun beginBatchEdit(): Boolean {
        return false
    }

    /**
     * 不支持结束批量编辑，按照文档返回 false
     * */
    override fun endBatchEdit(): Boolean {
        return false
    }

    open fun moveCursorToLeft(): InputConnectionProxy {
        inputHandler.moveCursorToLeft()
        return this
    }

    open fun moveCursorToTop(): InputConnectionProxy {
        inputHandler.moveCursorToTop()
        return this
    }

    open fun moveCursorToRight(): InputConnectionProxy {
        inputHandler.moveCursorToRight()
        return this
    }

    open fun moveCursorToBottom(): InputConnectionProxy {
        inputHandler.moveCursorToBottom()
        return this
    }

    open fun moveCursorToHome(): InputConnectionProxy {
        inputHandler.moveCursorToHome()
        return this
    }

    open fun moveCursorToEnd(): InputConnectionProxy {
        inputHandler.moveCursorToEnd()
        return this
    }

    override fun performContextMenuAction(id: Int): Boolean {
        return inputHandler.performContextMenuAction(id)
    }

}