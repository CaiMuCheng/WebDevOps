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

import com.mucheng.editor.base.AbstractComponent
import com.mucheng.editor.view.MuCodeEditor

@Suppress("MemberVisibilityCanBePrivate", "unused")
open class Cursor(
    override val editor: MuCodeEditor,
    var line: Int = 1,
    var row: Int = 0
) : AbstractComponent(editor) {

    open fun moveToLeft(): Cursor {
        val textModel = editor.getText()
        if (0 < row) {
            --row
        } else if (line - 1 >= 1) {
            --line
            row = textModel.getTextRowSize(line)
        }
        return this
    }

    open fun moveToLeft(count: Int): Cursor {
        var times = 0
        while (times < count) {
            moveToLeft()
            ++times
        }
        return this
    }

    open fun moveToRight(): Cursor {
        val textModel = editor.getText()
        val lineCount = textModel.lastLine
        val textLineModel = textModel.getTextRow(line)
        if (row < textLineModel.length) {
            ++row
        } else if (line + 1 <= lineCount) {
            ++line
            row = 0
        }
        return this
    }

    open fun moveToRight(count: Int): Cursor {
        var times = 0
        while (times < count) {
            moveToRight()
            ++times
        }
        return this
    }

    open fun moveToTop(): Cursor {
        val textModel = editor.getText()

        if (line > 1 && row == 0) {
            --line
        } else if (line > 1) {
            val topTextLineModel = textModel.getTextRow(line - 1)
            if (row > topTextLineModel.length) {
                row = topTextLineModel.length
            }
            --line
        }
        return this
    }

    open fun moveToBottom(): Cursor {
        val textModel = editor.getText()
        val lineCount = textModel.lastLine

        if (line < lineCount && row == 0) {
            ++line
        } else if (line < lineCount) {
            val bottomTextLineModel = textModel.getTextRow(line + 1)
            if (row > bottomTextLineModel.length) {
                row = bottomTextLineModel.length
            }
            ++line
        }
        return this
    }

    open fun moveToHome(): Cursor {
        line = 1
        row = 0
        return this
    }

    open fun moveToEnd(): Cursor {
        val textModel = editor.getText()
        val lineCount = textModel.lastLine
        val rowCount = textModel.getTextRow(lineCount).length
        line = lineCount
        row = rowCount
        return this
    }

    fun moveToPosition(line: Int, row: Int): Cursor {
        this.line = line
        this.row = row
        return this
    }

    open fun getIndex(): Int {
        val textModel = editor.getText()
        val indexer = textModel.getIndexer()
        return indexer.lineRowToIndex(line, row)
    }

    override fun toString(): String {
        return """
            Cursor(line=$line, row=$row, index=${getIndex()})
        """.trimIndent()
    }

}