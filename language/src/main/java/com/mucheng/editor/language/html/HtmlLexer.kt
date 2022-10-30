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

import com.mucheng.editor.base.lexer.AbstractLexer
import com.mucheng.editor.base.panel.AbstractAutoCompletionPanel
import com.mucheng.editor.language.css.CssLanguage
import com.mucheng.editor.language.css.CssToken
import com.mucheng.editor.language.ecmascript.EcmaScriptLanguage
import com.mucheng.editor.language.ecmascript.EcmaScriptToken
import com.mucheng.text.model.standard.CharTable

@Suppress("NOTHING_TO_INLINE", "MemberVisibilityCanBePrivate", "MemberVisibilityCanBePrivate")
open class HtmlLexer(language: HtmlLanguage) : AbstractLexer(language) {

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

            if (handleEcmaScriptStart()) continue

            if (handleCssStart()) continue

            if (handleHtmlWhitespace()) continue

            if (handleHtmlComments()) continue

            if (handleHtmlString()) continue

            if (handleHtmlDOCTYPE()) continue

            if (handleHtmlOperator()) continue

            if (handleHtmlElement()) continue

            if (handleHtmlAttribute()) continue

            if (handleHtmlDigit()) continue

            if (handleHtmlIdentifier()) continue

            ++row
        }
    }

    protected inline fun handleEcmaScriptStart(): Boolean {
        val startLine = line
        val startRow = row
        if (scannedChar == '<') {
            yyChar()
            val builder = StringBuilder()
            while (isLetter() && isNotRowEOF()) {
                builder.append(scannedChar)
                yyChar()
            }
            val text = builder.toString()
            if (text == "script" && scannedChar == '>') {
                // 为 EcmaScript 脚本
                yyChar()
                addToken(HtmlToken.ECMASCRIPT_CODE_BLOCK_START, startRow, row)
                val endLine = lineSize()
                val endRow = sources.getTextRowSize(endLine)
                addCodeBlock(startLine, startRow, endLine, endRow, EcmaScriptLanguage.getInstance())

                handleEcmaScript(startLine, startRow)
                return true
            }
        }
        row = startRow
        getChar()
        return false
    }

    protected inline fun handleCssStart(): Boolean {
        val startLine = line
        val startRow = row
        if (scannedChar == '<') {
            yyChar()
            val builder = StringBuilder()
            while (isLetter() && isNotRowEOF()) {
                builder.append(scannedChar)
                yyChar()
            }
            val text = builder.toString()
            if (text == "style" && scannedChar == '>') {
                // 为 EcmaScript 脚本
                yyChar()
                addToken(HtmlToken.CSS_CODE_BLOCK_START, startRow, row)

                val endLine = lineSize()
                val endRow = sources.getTextRowSize(endLine)
                addCodeBlock(startLine, startRow, endLine, endRow, CssLanguage)
                handleCss(startLine, startRow)
                return true
            }
        }
        row = startRow
        getChar()
        return false
    }

    protected inline fun handleCss(startLine: Int, startRow: Int) {
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

            if (handleCssEnd(startLine, startRow)) break

            if (handleCssComments()) continue

            if (handleCssOperator()) continue

            if (handleCssString()) continue

            if (handleCssAttribute()) continue

            if (handleCssDigit()) continue

            if (handleCssIdentifier()) continue

            ++row

        }
    }

    protected inline fun handleCssEnd(startLine: Int, startRow: Int): Boolean {
        val start = row
        if (scannedChar == '<') {
            yyChar()
            if (scannedChar == '/') {
                yyChar()
                val builder = StringBuilder()
                while (isLetter() && isNotRowEOF()) {
                    builder.append(scannedChar)
                    yyChar()
                }
                if (builder.indexOf("style") != -1 && scannedChar == '>') {
                    yyChar()
                    // 为 EcmaScript 结束块
                    val endLine = line
                    val endRow = row

                    val codeBlockIndex = getCodeBlockCount() - 1
                    if (codeBlockIndex >= 0) {
                        val lastCodeBlock = getCodeBlock(codeBlockIndex)
                        if (lastCodeBlock.startLine == startLine && lastCodeBlock.startRow == startRow) {
                            addToken(HtmlToken.CSS_CODE_BLOCK_END, start, row)
                            lastCodeBlock.endLine = endLine
                            lastCodeBlock.endRow = endRow
                            return true
                        }
                    }

                }
            }
        }
        row = start
        getChar()
        return false
    }

    protected inline fun handleEcmaScript(startLine: Int, startRow: Int) {
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

            if (handleEcmaScriptEnd(startLine, startRow)) break

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

    protected inline fun handleEcmaScriptEnd(startLine: Int, startRow: Int): Boolean {
        val start = row
        if (scannedChar == '<') {
            yyChar()
            if (scannedChar == '/') {
                yyChar()
                val builder = StringBuilder()
                while (isLetter() && isNotRowEOF()) {
                    builder.append(scannedChar)
                    yyChar()
                }
                if (builder.indexOf("script") != -1 && scannedChar == '>') {
                    yyChar()
                    // 为 EcmaScript 结束块
                    val endLine = line
                    val endRow = row

                    val codeBlockIndex = getCodeBlockCount() - 1
                    if (codeBlockIndex >= 0) {
                        val lastCodeBlock = getCodeBlock(codeBlockIndex)
                        if (lastCodeBlock.startLine == startLine && lastCodeBlock.startRow == startRow) {
                            addToken(HtmlToken.ECMASCRIPT_CODE_BLOCK_END, start, row)
                            lastCodeBlock.endLine = endLine
                            lastCodeBlock.endRow = endRow
                            return true
                        }
                    }
                }
            }
        }
        row = start
        getChar()
        return false
    }

    protected inline fun handleHtmlString(): Boolean {
        if (scannedChar != '"') {
            return false
        }

        val start = row
        yyChar()

        while (isNotRowEOF()) {
            if (scannedChar == '"') {
                yyChar()
                break
            }
            yyChar()
        }

        val end = row
        addToken(
            HtmlToken.STRING,
            start,
            end
        )
        return true
    }

    protected inline fun handleHtmlDOCTYPE(): Boolean {
        if (scannedChar != '<') {
            return false
        }

        val start = row
        yyChar()
        if (scannedChar != '!') {
            row = start
            getChar()
            return false
        }

        val buffer = StringBuilder()
        yyChar()
        while (isLetter() && isNotRowEOF()) {
            buffer.append(scannedChar)
            yyChar()
        }

        val docText = buffer.toString()
        if (docText == "DOCTYPE" || docText == "doctype") {
            yyChar()
            while (isWhitespace() && isNotRowEOF()) {
                yyChar()
            }
            buffer.clear()
            while (isLetter() && isNotRowEOF()) {
                buffer.append(scannedChar)
                yyChar()
            }
            val htmlText = buffer.toString()
            if (htmlText == "html" && scannedChar == '>') {
                val end = ++row
                addToken(
                    HtmlToken.DOCTYPE,
                    start,
                    end
                )
                return true
            }
        }
        row = start
        getChar()
        return false
    }

    protected inline fun handleHtmlOperator(): Boolean {
        if (!isOperator()) {
            return false
        }

        val token = language.getOperatorTokenMap()[scannedChar]!!
        addToken(
            token,
            row,
            row + 1
        )

        ++row
        return true
    }

    protected inline fun handleHtmlElement(): Boolean {
        if (isLetter() && scannedLineSource.getOrNull(row - 1) == '<') {
            val start = row
            while ((isLetter() || isDigit()) && isNotRowEOF()) {
                yyChar()
            }
            val end = row
            addToken(
                HtmlToken.ELEMENT_NAME,
                start,
                end
            )
            return true
        }

        if (isLetter() && scannedLineSource.getOrNull(row - 1) == '/') {
            val start = row
            while ((isLetter() || isDigit()) && isNotRowEOF()) {
                yyChar()
            }
            val end = row
            addToken(
                HtmlToken.ELEMENT_NAME,
                start,
                end
            )
            return true
        }
        return false
    }

    protected inline fun handleHtmlAttribute(): Boolean {
        if (!isLetter()) {
            return false
        }

        val start = row
        while ((isLetter() || isDigit() || scannedChar == '-') && isNotRowEOF()) {
            yyChar()
        }
        if (scannedChar == '=') {
            addToken(
                HtmlToken.ATTRIBUTE,
                start,
                row
            )
            return true
        }
        row = start
        getChar()
        return false
    }

    protected inline fun handleHtmlDigit(): Boolean {
        if (!isDigit()) {
            return false
        }

        val start = row
        while (isDigit() && isNotRowEOF()) {
            yyChar()
        }
        val end = row

        addToken(
            HtmlToken.IDENTIFIER,
            start,
            end
        )
        return true
    }

    protected inline fun handleHtmlIdentifier(): Boolean {
        if (isWhitespace() || isOperator() || isDigit()) {
            return false
        }

        val start = row
        while (!isWhitespace() && !isOperator() && isNotRowEOF()) {
            yyChar()
        }
        val end = row

        addToken(
            HtmlToken.IDENTIFIER,
            start,
            end
        )
        return true
    }

    protected inline fun handleHtmlWhitespace(): Boolean {
        if (!isWhitespace()) {
            return false
        }

        val start = row
        while (isWhitespace() && isNotRowEOF()) {
            yyChar()
        }
        val end = row
        addToken(
            HtmlToken.WHITESPACE,
            start,
            end
        )
        return true
    }

    protected inline fun handleHtmlComments(): Boolean {
        if (scannedChar != '<') {
            return false
        }
        val start = row
        yyChar()
        if (scannedChar == '!') {
            yyChar()
            if (scannedChar == '-') {
                yyChar()
                if (scannedChar == '-') {
                    val currentFindPos = scannedLineSource.indexOf("-->", row + 1)
                    if (currentFindPos != -1) {
                        val end = currentFindPos + 3
                        addToken(
                            HtmlToken.COMMENT,
                            start,
                            end
                        )
                        row = end
                        return true
                    }
                    addToken(
                        HtmlToken.COMMENT,
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

                        val findPos = scannedLineSource.indexOf("-->")
                        if (findPos != -1) {
                            val end = findPos + 3
                            addToken(
                                HtmlToken.COMMENT,
                                0,
                                end
                            )
                            row = end
                            return true
                        }

                        addToken(
                            HtmlToken.COMMENT,
                            0,
                            rowSize()
                        )
                        ++line
                    }
                    return true
                }
            }
        }

        row = start
        getChar()
        return false
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
        if (!EcmaScriptLanguage.getInstance().isOperator(scannedChar)) {
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
        if (EcmaScriptLanguage.getInstance().isSpecial(text)) {
            val token = EcmaScriptLanguage.getInstance().getSpecialTokenMap()[text]!!
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

        if (EcmaScriptLanguage.getInstance().isKeyword(text)) {
            val token = EcmaScriptLanguage.getInstance().getKeywordTokenMap()[text]!!
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
        if (isWhitespace() || EcmaScriptLanguage.getInstance()
                .isOperator(scannedChar) || isDigit()
        ) {
            return false
        }

        val start = row
        val builder = StringBuilder()
        while (!isWhitespace() && !EcmaScriptLanguage.getInstance()
                .isOperator(scannedChar) && isNotRowEOF()
        ) {
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
                        AbstractAutoCompletionPanel.VARIABLE
                    )
                }

                // 为函数
                EcmaScriptToken.FUNCTION -> {
                    addAutoCompletionItem(
                        EcmaScriptLanguage.getInstance(),
                        builder.toString(),
                        AbstractAutoCompletionPanel.FUNCTION
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
        if (!CssLanguage.isOperator(scannedChar) || scannedChar == '"') {
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
        if (isWhitespace() || CssLanguage.isOperator(scannedChar) || isDigit()) {
            return false
        }

        val buffer = StringBuilder()
        val start = row
        while (!isWhitespace() && !CssLanguage.isOperator(scannedChar) && isNotRowEOF()) {
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

    override fun clear() {
        super.clear()
        HtmlLanguage.getInstance().clearVariableAutoCompletionItems()
        EcmaScriptLanguage.getInstance().clearVariableAutoCompletionItems()
        CssLanguage.clearVariableAutoCompletionItems()
    }

}