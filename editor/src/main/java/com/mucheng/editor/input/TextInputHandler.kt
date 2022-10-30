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

package com.mucheng.editor.input

import com.mucheng.editor.base.IInputHandler
import com.mucheng.editor.command.DeletedCommand
import com.mucheng.editor.command.InsertedCommand
import com.mucheng.editor.component.Cursor
import com.mucheng.editor.tool.executeAnimation
import com.mucheng.editor.view.MuCodeEditor
import com.mucheng.text.model.base.AbstractTextModel

open class TextInputHandler(protected val editor: MuCodeEditor) : IInputHandler {

    override fun commitText(text: CharSequence, requireAutoCompletionPanel: Boolean) {
        val textModel = editor.getText()
        val cursor = editor.getCursor()
        val cursorAnimation = editor.animationManager.cursorAnimation
        val undoManager = editor.undoManager

        val startLine = cursor.line
        val startRow = cursor.row

        cursor.executeAnimation(cursorAnimation) {
            textModel.insert(cursor.line, cursor.row, text)
            cursor.moveToRight(text.length)
            editor.eventManager.dispatchContentChangedEvent()
        }

        undoManager.push(
            InsertedCommand(
                startLine,
                startRow,
                cursor.line,
                cursor.row,
                text
            )
        )
        editor.reachToCursor(cursor)
        if (requireAutoCompletionPanel) {
            editor.requireAutoCompletionPanel()
        }
    }

    override fun deleteText() {
        val textModel = editor.getText()
        val cursor = editor.getCursor()
        val cursorAnimation = editor.animationManager.cursorAnimation
        val undoManager = editor.undoManager

        val startLine = cursor.line
        val startRow = cursor.row
        var builder: StringBuilder? = null

        cursor.executeAnimation(cursorAnimation) {
            builder = deleteTextInternal(textModel, cursor)
        }

        undoManager.push(DeletedCommand(cursor.line, cursor.row, startLine, startRow, builder!!))
        editor.eventManager.dispatchContentChangedEvent()
        editor.reachToCursor(cursor)
        editor.requireAutoCompletionPanel()
    }

    private fun deleteTextInternal(textModel: AbstractTextModel, cursor: Cursor): StringBuilder {
        val line = cursor.line
        val row = cursor.row
        val textLineModel = textModel.getTextRow(line)
        if (row == 0 && line == 1) {
            return StringBuilder()
        }

        if (row == 0 && line > 1) {
            val lastLine = line - 1
            val lastRow = textModel.getTextRowSize(lastLine)
            val char = textModel.get(lastLine, textModel.getTextRowSize(lastLine))
            textModel.deleteCharAt(lastLine, textModel.getTextRowSize(lastLine))
            cursor.moveToPosition(lastLine, lastRow)
            return StringBuilder(1).append(char)
        }

        val char = textLineModel[cursor.row - 1]
        textLineModel.deleteCharAt(cursor.row - 1)
        cursor.moveToLeft()
        return StringBuilder(1).append(char)
    }

    override fun replaceSelectingText(text: CharSequence) {
        val actionManager = editor.actionManager
        val textModel = editor.getText()
        val cursor = editor.getCursor()
        val cursorAnimation = editor.animationManager.cursorAnimation
        val selectingRange = actionManager.selectingRange!!
        val startPos = selectingRange.start
        val endPos = selectingRange.end
        val undoManager = editor.undoManager

        cursor.executeAnimation(cursorAnimation) {
            val delStartLine = startPos.line
            val delStartRow = startPos.row
            val delText =
                textModel.subSequence(startPos.line, startPos.row, endPos.line, endPos.row)
            textModel.delete(startPos.line, startPos.row, endPos.line, endPos.row)
            cursor.moveToPosition(startPos.line, startPos.row)
            undoManager.push(
                DeletedCommand(
                    delStartLine,
                    delStartRow,
                    endPos.line,
                    endPos.row,
                    delText
                )
            )

            val insertedLine = startPos.line
            val insertedRow = startPos.row
            textModel.insert(startPos.line, startPos.row, text)
            cursor.moveToRight(text.length)
            actionManager.cancelSelectingText()
            undoManager.push(
                InsertedCommand(
                    insertedLine,
                    insertedRow,
                    cursor.line,
                    cursor.row,
                    text
                )
            )
        }
        editor.eventManager.dispatchContentChangedEvent()
        editor.reachToCursor(cursor)
        editor.requireAutoCompletionPanel()
    }

    override fun deleteSelectingText() {
        val actionManager = editor.actionManager
        val textModel = editor.getText()
        val cursor = editor.getCursor()
        val cursorAnimation = editor.animationManager.cursorAnimation
        val selectingRange = actionManager.selectingRange!!
        val startPos = selectingRange.start
        val endPos = selectingRange.end
        val undoManager = editor.undoManager

        cursor.executeAnimation(cursorAnimation) {
            val deletedLine = startPos.line
            val deletedRow = startPos.row
            val deletedText =
                textModel.subSequence(startPos.line, startPos.row, endPos.line, endPos.row)
            textModel.delete(startPos.line, startPos.row, endPos.line, endPos.row)
            cursor.moveToPosition(startPos.line, startPos.row)

            actionManager.cancelSelectingText()
            undoManager.push(
                DeletedCommand(
                    deletedLine,
                    deletedRow,
                    endPos.line,
                    endPos.row,
                    deletedText
                )
            )
        }
        editor.eventManager.dispatchContentChangedEvent()
        editor.reachToCursor(cursor)
        editor.requireAutoCompletionPanel()
    }

    override fun moveCursorToLeft() {
        val cursor = editor.getCursor()
        val cursorAnimation = editor.animationManager.cursorAnimation
        editor.closeAutoCompletionPanel()
        cursor.executeAnimation(cursorAnimation) {
            cursor.moveToLeft()
        }
        editor.reachToCursor(cursor)
    }

    override fun moveCursorToTop() {
        val cursor = editor.getCursor()
        val cursorAnimation = editor.animationManager.cursorAnimation
        editor.closeAutoCompletionPanel()
        cursor.executeAnimation(cursorAnimation) {
            cursor.moveToTop()
        }
        editor.reachToCursor(cursor)
    }

    override fun moveCursorToRight() {
        val cursor = editor.getCursor()
        val cursorAnimation = editor.animationManager.cursorAnimation
        editor.closeAutoCompletionPanel()
        cursor.executeAnimation(cursorAnimation) {
            cursor.moveToRight()
        }
        editor.reachToCursor(cursor)
    }

    override fun moveCursorToBottom() {
        val cursor = editor.getCursor()
        val cursorAnimation = editor.animationManager.cursorAnimation
        editor.closeAutoCompletionPanel()
        cursor.executeAnimation(cursorAnimation) {
            cursor.moveToBottom()
        }
        editor.reachToCursor(cursor)
    }

    override fun moveCursorToHome() {
        val cursor = editor.getCursor()
        val cursorAnimation = editor.animationManager.cursorAnimation
        editor.closeAutoCompletionPanel()
        cursor.executeAnimation(cursorAnimation) {
            cursor.moveToHome()
        }
        editor.reachToCursor(cursor)
    }

    override fun moveCursorToEnd() {
        val cursor = editor.getCursor()
        val cursorAnimation = editor.animationManager.cursorAnimation
        editor.closeAutoCompletionPanel()
        cursor.executeAnimation(cursorAnimation) {
            cursor.moveToEnd()
        }
        editor.reachToCursor(cursor)
    }

    override fun performContextMenuAction(id: Int): Boolean {
        when (id) {
            android.R.id.selectAll -> {
                // 选中所有
                editor.selectionAll()
                return true
            }

            android.R.id.cut -> {
                editor.copySelection()
                if (editor.actionManager.selectingText) {
                    editor.deleteText()
                }
                return true
            }

            android.R.id.copy -> {
                editor.copySelection()
                return true
            }

            android.R.id.paste,
            android.R.id.pasteAsPlainText -> {
                editor.paste()
                return true
            }
        }
        return false
    }

}