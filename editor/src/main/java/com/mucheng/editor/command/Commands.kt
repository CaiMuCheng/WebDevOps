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

package com.mucheng.editor.command

import com.mucheng.editor.tool.executeAnimation
import com.mucheng.editor.view.MuCodeEditor

sealed class AbstractUndoableCommand {

    abstract fun undo(editor: MuCodeEditor)

    abstract fun redo(editor: MuCodeEditor)

    abstract fun merge(command: AbstractUndoableCommand): Boolean

}

data class InsertedCommand(
    var startLine: Int,
    var startRow: Int,
    var endLine: Int,
    var endRow: Int,
    val text: StringBuilder = StringBuilder()
) : AbstractUndoableCommand() {

    constructor(
        startLine: Int,
        startRow: Int,
        endLine: Int,
        endRow: Int,
        text: CharSequence
    ) : this(startLine, startRow, endLine, endRow, StringBuilder(text))

    override fun undo(editor: MuCodeEditor) {
        val textModel = editor.getText()
        val cursor = editor.getCursor()
        val cursorAnimation = editor.animationManager.cursorAnimation
        cursor.executeAnimation(cursorAnimation) {
            cursor.moveToPosition(startLine, startRow)
            textModel.delete(
                startLine,
                startRow,
                endLine,
                endRow
            )
            editor.eventManager.dispatchContentChangedEvent()
        }
        editor.reachToCursor(cursor)
    }

    override fun redo(editor: MuCodeEditor) {
        val textModel = editor.getText()
        val cursor = editor.getCursor()
        val cursorAnimation = editor.animationManager.cursorAnimation
        cursor.executeAnimation(cursorAnimation) {
            cursor.moveToPosition(startLine, startRow)
            textModel.insert(startLine, startRow, text)
            cursor.moveToRight(text.length)
            editor.eventManager.dispatchContentChangedEvent()
        }
        editor.reachToCursor(cursor)
    }

    override fun merge(command: AbstractUndoableCommand): Boolean {
        if (command !is InsertedCommand) {
            return false
        }

        val nextStartLine = command.startLine
        val nextStartRow = command.startRow
        if (nextStartLine != endLine || nextStartRow != endRow) {
            return false
        }

        text.append(command.text)
        endLine = command.endLine
        endRow = command.endRow
        return true
    }

}

data class DeletedCommand(
    var startLine: Int,
    var startRow: Int,
    var endLine: Int,
    var endRow: Int,
    val text: StringBuilder = StringBuilder()
) : AbstractUndoableCommand() {

    constructor(
        startLine: Int,
        startRow: Int,
        endLine: Int,
        endRow: Int,
        text: CharSequence
    ) : this(startLine, startRow, endLine, endRow, StringBuilder(text))

    override fun undo(editor: MuCodeEditor) {
        val textModel = editor.getText()
        val cursor = editor.getCursor()
        val cursorAnimation = editor.animationManager.cursorAnimation
        cursor.executeAnimation(cursorAnimation) {
            cursor.moveToPosition(startLine, startRow)
            textModel.insert(startLine, startRow, text)
            cursor.moveToRight(text.length)
            editor.eventManager.dispatchContentChangedEvent()
        }
        editor.reachToCursor(cursor)
    }

    override fun redo(editor: MuCodeEditor) {
        val textModel = editor.getText()
        val cursor = editor.getCursor()
        val cursorAnimation = editor.animationManager.cursorAnimation
        cursor.executeAnimation(cursorAnimation) {
            cursor.moveToPosition(startLine, startRow)
            textModel.delete(
                startLine,
                startRow,
                endLine,
                endRow
            )
            editor.eventManager.dispatchContentChangedEvent()
        }
        editor.reachToCursor(cursor)
    }

    override fun merge(command: AbstractUndoableCommand): Boolean {
        if (command !is DeletedCommand) {
            return false
        }

        val beforeEndLine = command.endLine
        val beforeEndRow = command.endRow

        if (beforeEndLine != startLine || beforeEndRow != startRow) {
            return false
        }

        text.insert(0, command.text)
        startLine = command.startLine
        startRow = command.startRow
        return true
    }

}