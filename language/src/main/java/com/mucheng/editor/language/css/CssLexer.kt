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

package com.mucheng.editor.language.css

import com.mucheng.editor.base.lexer.AbstractLexer
import com.mucheng.text.model.standard.CharTable

@Suppress("NOTHING_TO_INLINE", "MemberVisibilityCanBePrivate")
open class CssLexer(language: CssLanguage) : AbstractLexer(language) {

    override fun analyze() {
        while (isRunning()) {

            if (line > lineSize()) {
                return
            }

            scannedLineSource = sources.getTextRow(line)

            if (row >= rowSize()) {
                ++line
                row = 0
                continue
            }

            scannedLineSource = sources.getTextRow(line)
            getChar()

            if (handleCssWhitespace()) continue

            if (handleCssComments()) continue

            if (handleCssOperator()) continue

            if (handleCssString()) continue

            if (handleCssAttribute()) continue

            if (handleCssDigit()) continue

            if (handleCssIdentifier()) continue

            ++row

        }
    }

    protected inline fun handleCssWhitespace(): Boolean {
        if (!isWhitespace()) {
            return false
        }

        val start = row
        while (isWhitespace() && isNotRowEOF()) {
            yyChar()
        }
        val end = row
        addToken(
            CssToken.WHITESPACE,
            start,
            end
        )
        return true
    }

    protected inline fun handleCssComments(): Boolean {
        if (scannedChar != '/') {
            return false
        }

        val start = row
        yyChar()
        if (scannedChar != '*') {
            row = start
            getChar()
            return false
        }

        val currentFindPos = scannedLineSource.indexOf("*/", row + 1)
        if (currentFindPos != -1) {
            val end = currentFindPos + 2
            addToken(
                CssToken.COMMENT,
                start,
                end
            )
            row = end
            return true
        }

        addToken(
            CssToken.COMMENT,
            start,
            rowSize()
        )

        ++line
        while (line <= lineSize()) {
            row = 0
            scannedLineSource = sources.getTextRow(line)
            if (scannedLineSource.isEmpty()) {
                ++line
                continue
            }

            val findPos = scannedLineSource.indexOf("*/")
            if (findPos != -1) {
                val end = findPos + 2
                addToken(
                    CssToken.COMMENT,
                    0,
                    end
                )
                row = end
                return true
            }

            addToken(
                CssToken.COMMENT,
                0,
                rowSize()
            )
            ++line
        }
        return true
    }

    protected inline fun handleCssOperator(): Boolean {
        if (!isOperator() || scannedChar == '"') {
            return false
        }

        if (!CharTable.BracketCollection.contains(scannedChar!!)) {
            val start = row
            while (isNotRowEOF() && isOperator()) {
                yyChar()
            }
            addToken(CssToken.OPERATOR, start, row)
        } else {
            val token = language.getOperatorTokenMap()[scannedChar]!!
            addToken(
                token,
                row,
                row + 1
            )
            ++row
        }
        return true
    }

    protected inline fun handleCssString(): Boolean {
        if (scannedChar != '"') {
            return false
        }

        val start = row
        yyChar()

        while (isNotRowEOF()) {
            if (scannedChar == '"') {
                if (row - 1 >= 0 && scannedLineSource[row - 1] != '\\') {
                    yyChar()
                    break
                }
            }
            yyChar()
        }

        val end = row
        addToken(
            CssToken.STRING,
            start,
            end
        )
        return true
    }

    protected inline fun handleCssAttribute(): Boolean {
        if (!isLetter()) {
            return false
        }

        val start = row
        while ((isLetter() || scannedChar == '-') && isNotRowEOF()) {
            yyChar()
        }
        val end = row

        while (isWhitespace() && isNotRowEOF()) {
            yyChar()
        }
        val nextEnd = row
        if (scannedChar == ':') {
            addToken(
                CssToken.ATTRIBUTE,
                start,
                nextEnd
            )
            addToken(
                CssToken.COLON,
                nextEnd,
                nextEnd + 1
            )
            row = nextEnd + 1
            return true
        }

        addToken(
            CssToken.IDENTIFIER,
            start,
            end
        )
        row = end
        return true
    }

    protected inline fun handleCssDigit(): Boolean {
        if (!isDigit()) {
            return false
        }

        val start = row
        while ((isDigit() || isLetter()) && isNotRowEOF()) {
            yyChar()
        }
        val end = row

        addToken(
            CssToken.DIGIT,
            start,
            end
        )
        row = end
        return true
    }

    protected inline fun handleCssIdentifier(): Boolean {
        if (isWhitespace() || isOperator() || isDigit()) {
            return false
        }

        val buffer = StringBuilder()
        val start = row
        while (!isWhitespace() && !isOperator() && isNotRowEOF()) {
            buffer.append(scannedChar)
            yyChar()
        }
        val end = row

        addToken(
            CssToken.IDENTIFIER,
            start,
            end
        )
        return true
    }

}