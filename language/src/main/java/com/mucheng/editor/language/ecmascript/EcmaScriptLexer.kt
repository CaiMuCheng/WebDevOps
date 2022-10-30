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
 * For example: add an "About Editor" line in your app's settings, which identifies the author and the Github home page of this editor.
 *
 * This code uses the MPL 2.0 open source license, you must mark the author information.
 * Do not delete this comment if you want to modify the file.
 * If you violate the above regulations we have the right to sue you!
 */

package com.mucheng.editor.language.ecmascript

import com.mucheng.editor.base.lexer.AbstractLexer
import com.mucheng.editor.base.panel.AbstractAutoCompletionPanel.Companion.FUNCTION
import com.mucheng.editor.base.panel.AbstractAutoCompletionPanel.Companion.VARIABLE
import com.mucheng.text.model.standard.CharTable

@Suppress("unused", "NOTHING_TO_INLINE", "MemberVisibilityCanBePrivate")
open class EcmaScriptLexer(override val language: EcmaScriptLanguage) : AbstractLexer(language) {

    companion object {

        protected const val TAG = "EcmaScriptLexer"

    }

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

            if (handleEcmaScriptWhitespace()) continue

            if (handleEcmaScriptComments()) continue

            if (handleEcmaScriptString()) continue

            if (handleEcmaScriptRegex()) continue

            if (handleEcmaScriptOperator()) continue

            if (handleEcmaScriptSpecial()) continue

            if (handleEcmaScriptKeyword()) continue

            if (handleEcmaScriptIdentifier()) continue

            if (handleEcmaScriptDigit()) continue

            ++row

        }

    }

    protected inline fun handleEcmaScriptWhitespace(): Boolean {
        if (!isWhitespace()) {
            return false
        }

        val start = row
        while (isWhitespace() && isNotRowEOF()) {
            yyChar()
        }
        val end = row
        addToken(
            EcmaScriptToken.WHITESPACE,
            line,
            start,
            end
        )
        return true
    }

    protected inline fun handleEcmaScriptComments(): Boolean {
        if (scannedChar != '/') {
            return false
        }

        val start = row
        yyChar()
        if (scannedChar == '/') {
            val end = rowSize()
            addToken(
                EcmaScriptToken.SINGLE_COMMENT,
                line,
                start,
                end
            )
            row = end
            return true
        }

        if (scannedChar == '*') {
            val currentFindPos = scannedLineSource.indexOf("*/", row + 1)
            if (currentFindPos != -1) {
                val end = currentFindPos + 2
                addToken(
                    EcmaScriptToken.SINGLE_COMMENT,
                    line,
                    start,
                    end
                )
                row = end
                return true
            }

            addToken(
                EcmaScriptToken.MULTI_COMMENT_START,
                line,
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
                        EcmaScriptToken.MULTI_COMMENT_END,
                        line,
                        0,
                        end
                    )
                    row = end
                    return true
                }

                addToken(
                    EcmaScriptToken.MULTI_COMMENT_PART,
                    line,
                    0,
                    rowSize()
                )
                ++line
            }
            return true
        }

        row = start
        getChar()
        return false
    }

    protected inline fun handleEcmaScriptOperator(): Boolean {
        if (!isOperator()) {
            return false
        }

        if (!CharTable.BracketCollection.contains(scannedChar!!)) {
            val start = row
            while (isNotRowEOF() && isOperator()) {
                yyChar()
            }
            addToken(EcmaScriptToken.OPERATOR, start, row)
        } else {
            val token = language.getOperatorTokenMap()[scannedChar]!!
            addToken(
                token,
                line,
                row,
                row + 1
            )
            ++row
        }
        return true
    }

    protected inline fun handleEcmaScriptSpecial(): Boolean {
        if (!isLetter()) {
            return false
        }

        val start = row
        val buffer = StringBuilder()
        while (isLetter() && isNotRowEOF()) {
            buffer.append(scannedChar)
            yyChar()
        }
        val end = row
        val text = buffer.toString()
        if (isSpecial(text)) {
            val token = language.getSpecialTokenMap()[text]!!
            addToken(
                token,
                line,
                start,
                end
            )
            return true
        }

        row = start
        getChar()
        return false
    }


    protected inline fun handleEcmaScriptKeyword(): Boolean {
        if (!isLetter()) {
            return false
        }

        val buffer = StringBuilder()
        val start = row
        while (isLetter() && isNotRowEOF()) {
            buffer.append(scannedChar)
            yyChar()
        }
        val end = row
        val text = buffer.toString()

        if (isKeyword(text)) {
            val token = language.getKeywordTokenMap()[text]!!
            addToken(
                token,
                line,
                start,
                end
            )
            return true
        }

        row = start
        getChar()
        return false
    }

    protected inline fun handleEcmaScriptString(): Boolean {
        if (scannedChar != '\'' && scannedChar != '"' && scannedChar != '`') {
            return false
        }

        val start = row
        if (scannedChar == '"') {
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
                EcmaScriptToken.SINGLE_STRING,
                line,
                start,
                end
            )
            return true
        }

        if (scannedChar == '\'') {
            yyChar()

            while (isNotRowEOF()) {
                if (scannedChar == '\'') {
                    if (row - 1 >= 0 && scannedLineSource[row - 1] != '\\') {
                        yyChar()
                        break
                    }
                }
                yyChar()
            }

            val end = row
            addToken(
                EcmaScriptToken.SINGLE_STRING,
                line,
                start,
                end
            )
            return true
        }

        if (scannedChar == '`') {
            val currentFindPos = scannedLineSource.indexOf('`', row + 1)

            if (currentFindPos != -1) {
                val end = currentFindPos + 1
                addToken(
                    EcmaScriptToken.TEMPLATE_STRING,
                    line,
                    start,
                    end
                )
                row = end
                return true
            }

            addToken(
                EcmaScriptToken.TEMPLATE_STRING,
                line,
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

                val findPos = scannedLineSource.indexOf('`')
                if (findPos != -1) {
                    val end = findPos + 1
                    addToken(
                        EcmaScriptToken.TEMPLATE_STRING,
                        line,
                        0,
                        end
                    )
                    row = end
                    return true
                }

                addToken(
                    EcmaScriptToken.TEMPLATE_STRING,
                    line,
                    0,
                    rowSize()
                )
                ++line
            }

            return true
        }

        row = start
        getChar()
        return false
    }

    protected inline fun handleEcmaScriptRegex(): Boolean {
        if (scannedChar != '/') {
            return false
        }

        val start = row
        val currentFindPos = scannedLineSource.indexOf('/', row + 1)
        if (currentFindPos != -1) {
            var end = currentFindPos
            row = end
            yyChar()
            if (isLetter()) {
                while (isLetter() && isNotRowEOF()) {
                    yyChar()
                }
                end = row
                addToken(
                    EcmaScriptToken.REGEX,
                    line,
                    start,
                    end
                )
                return true
            }
            ++end
            addToken(
                EcmaScriptToken.REGEX,
                line,
                start,
                end
            )
            row = end
            return true
        }

        row = start
        getChar()
        return false
    }

    protected inline fun handleEcmaScriptIdentifier(): Boolean {
        if (isWhitespace() || isOperator() || isDigit()) {
            return false
        }

        val start = row
        val builder = StringBuilder()
        while (!isWhitespace() && !isOperator() && isNotRowEOF()) {
            builder.append(scannedChar)
            yyChar()
        }
        val end = row

        val lastToken = lastNonWhitespaceToken(EcmaScriptToken.WHITESPACE)
        if (lastToken != null) {
            when (lastToken.token) {
                // 为变量
                EcmaScriptToken.VAR,
                EcmaScriptToken.LET,
                EcmaScriptToken.CONST -> {
                    addAutoCompletionItem(
                        EcmaScriptLanguage.getInstance(),
                        builder.toString(),
                        VARIABLE
                    )
                }

                // 为函数
                EcmaScriptToken.FUNCTION -> {
                    addAutoCompletionItem(
                        EcmaScriptLanguage.getInstance(),
                        builder.toString(),
                        FUNCTION
                    )
                }
            }
        }

        addToken(
            EcmaScriptToken.IDENTIFIER,
            line,
            start,
            end
        )
        return true
    }

    protected inline fun handleEcmaScriptDigit(): Boolean {
        if (!isDigit()) {
            return false
        }

        val start = row
        if (scannedChar == '0') {
            yyChar()
            if (scannedChar == 'x') {
                while ((isDigit() || isLetter()) && isNotRowEOF()) {
                    yyChar()
                }
                val end = row
                addToken(
                    EcmaScriptToken.DIGIT_NUMBER,
                    line,
                    start,
                    end
                )
                return true
            }
            row = start
            getChar()
        }

        while (isDigit() && isNotRowEOF()) {
            yyChar()
        }

        val end = row

        addToken(
            EcmaScriptToken.DIGIT_NUMBER,
            line,
            start,
            end
        )
        return true
    }

    override fun clear() {
        super.clear()
        EcmaScriptLanguage.getInstance().clearVariableAutoCompletionItems()
    }

}