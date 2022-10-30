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

package com.mucheng.editor.language.html

import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.mucheng.editor.base.panel.AbstractAutoCompletionPanel
import com.mucheng.editor.command.DeletedCommand
import com.mucheng.editor.command.InsertedCommand
import com.mucheng.editor.data.AutoCompletionItem
import com.mucheng.editor.language.R
import com.mucheng.editor.sample.helper.DefaultAutoCompletionHelper
import com.mucheng.editor.tool.executeAnimation
import com.mucheng.editor.view.MuCodeEditor

open class HtmlAutoCompletionHelper : DefaultAutoCompletionHelper() {

    companion object {
        const val ELEMENT = "Element"
        const val ATTRIBUTE = "Attribute"
        const val QUICK_GEN = "QuickGenerate"
        const val CODE_BLOCK = "CodeBlock"
    }

    override fun getIconDrawable(editor: MuCodeEditor, type: String): Drawable? {
        val context = editor.context
        return when (type) {
            ELEMENT -> ContextCompat.getDrawable(context, R.drawable.ic_auto_completion_element)
            ATTRIBUTE -> ContextCompat.getDrawable(context, R.drawable.ic_auto_completion_attribute)
            QUICK_GEN -> ContextCompat.getDrawable(
                context,
                com.mucheng.editor.R.drawable.ic_auto_completion_quick_gen
            )

            CODE_BLOCK -> ContextCompat.getDrawable(
                context,
                R.drawable.ic_auto_completion_code_block
            )

            else -> super.getIconDrawable(editor, type)
        }
    }

    override fun isMatchedInsertedText(): Boolean {
        return true
    }

    override fun skipCharIfNeeded(editor: MuCodeEditor, char: Char): Boolean {
        val language = editor.getLanguage()
        return language.isDigit(char) || language.isWhitespace(char) || char == '>'
    }

    override fun insertedText(
        autoCompletionItem: AutoCompletionItem,
        editor: MuCodeEditor,
        autoCompletionPanel: AbstractAutoCompletionPanel,
        inputText: String
    ) {
        val insertedText = autoCompletionItem.insertedText
        if (insertedText.startsWith(inputText)) {
            // 委托给父类
            super.insertedText(autoCompletionItem, editor, autoCompletionPanel, inputText)
        } else {
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
            val index = insertedText.indexOf("</")
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
            } else {
                cursor.executeAnimation(
                    editor.animationManager.cursorAnimation
                ) {
                    cursor.moveToRight(insertedText.length) // 将 Cursor 向右移动
                    editor.eventManager.dispatchContentChangedEvent() // 进行 Rescan
                }
                undoManager.push(
                    InsertedCommand(
                        currentLine,
                        currentRow,
                        cursor.line,
                        cursor.row,
                        insertedText
                    )
                )
            }
            editor.reachToCursor(cursor)
        }
    }

}