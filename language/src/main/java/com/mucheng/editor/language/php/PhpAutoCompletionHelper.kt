package com.mucheng.editor.language.php

import com.mucheng.editor.base.IAutoCompletionHelper
import com.mucheng.editor.base.panel.AbstractAutoCompletionPanel
import com.mucheng.editor.command.DeletedCommand
import com.mucheng.editor.command.InsertedCommand
import com.mucheng.editor.data.AutoCompletionItem
import com.mucheng.editor.language.html.HtmlAutoCompletionHelper
import com.mucheng.editor.tool.executeAnimation
import com.mucheng.editor.view.MuCodeEditor

class PhpAutoCompletionHelper(private val autoCompletionHelper: HtmlAutoCompletionHelper) :
    IAutoCompletionHelper by autoCompletionHelper {

    override fun insertedText(
        autoCompletionItem: AutoCompletionItem,
        editor: MuCodeEditor,
        autoCompletionPanel: AbstractAutoCompletionPanel,
        inputText: String
    ) {
        if (autoCompletionItem.title == "php" &&
            autoCompletionItem.type == HtmlAutoCompletionHelper.CODE_BLOCK
        ) {
            val insertedText = autoCompletionItem.insertedText
            val cursor = editor.getCursor()
            val textModel = editor.getText()
            val line = cursor.line
            val start = cursor.row - inputText.length
            val end = cursor.row
            val undoManager = editor.undoManager
            val builder = StringBuilder(end - start)
            cursor.row = start
            autoCompletionPanel.dismiss()
            builder.append(textModel.subSequence(line, start, line, end))
            textModel.delete(line, start, line, end) // 进行删除

            // 推送 Delete Command
            undoManager.push(DeletedCommand(line, start, line, end, builder))

            val currentLine = cursor.line
            val currentRow = cursor.row
            // 执行插入
            editor.insertText(currentLine, currentRow, insertedText)

            // 向前移动 Cursor 直到遇到标签结束符 "</"
            val index = insertedText.indexOf("?>")
            if ('\n' !in insertedText && index != -1 && index != 0) {
                cursor.executeAnimation(
                    editor.animationManager.cursorAnimation
                ) {
                    cursor.moveToRight(index) // 将 Cursor 向右移动
                    editor.eventManager.dispatchContentChangedEvent() // 进行 rescan
                }
                undoManager.push(
                    InsertedCommand(
                        currentLine,
                        currentRow,
                        currentLine,
                        currentRow + insertedText.length,
                        insertedText
                    )
                )
            }
            editor.reachToCursor(cursor)
        } else {
            autoCompletionHelper.insertedText(
                autoCompletionItem,
                editor,
                autoCompletionPanel,
                inputText
            )
        }
    }

}