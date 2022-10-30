package com.mucheng.editor.language.php

import com.mucheng.editor.base.panel.AbstractAutoCompletionPanel.Companion.FUNCTION
import com.mucheng.editor.base.panel.AbstractAutoCompletionPanel.Companion.VARIABLE
import com.mucheng.editor.language.html.HtmlLanguage
import com.mucheng.editor.language.html.HtmlLexer
import com.mucheng.editor.language.html.HtmlToken
import com.mucheng.text.model.standard.CharTable

@Suppress("NOTHING_TO_INLINE", "MemberVisibilityCanBePrivate")
open class PhpLexer(language: HtmlLanguage) : HtmlLexer(language) {

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

            if (handlePhpStart()) continue

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

    protected inline fun handlePhpStart(): Boolean {
        val startLine = line
        val startRow = row
        if (scannedChar == '<') {
            yyChar()
            if (scannedChar == '?') {
                yyChar()
                val builder = StringBuilder()
                while (isLetter() && isNotRowEOF()) {
                    builder.append(scannedChar)
                    yyChar()
                }
                val text = builder.toString()
                if (text == "php") {
                    addToken(PhpToken.PHP_CODE_BLOCK_START, startRow, row)
                    // 直接添加 CodeBlock
                    val endLine = lineSize()
                    val endRow = sources.getTextRowSize(endLine)
                    addCodeBlock(startLine, startRow, endLine, endRow, PhpLanguageWrapper)

                    // 为 PHP 块
                    handlePhp(startLine, startRow)
                    return true
                }
            }
        }
        row = startRow
        getChar()
        return false
    }

    protected inline fun handlePhp(startLine: Int, startRow: Int) {
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

            if (handlePhpWhitespace()) continue

            // 遇到结束符直接跳出
            if (handlePhpEnd(startLine, startRow)) break

            // 处理注释
            if (handlePhpComments()) continue

            if (handlePhpString()) continue

            if (handlePhpOperator()) continue

            if (handlePhpKeyword()) continue

            if (handlePhpDigit()) continue

            if (handlePhpIdentifier()) continue

            ++row
        }
    }

    protected inline fun handlePhpWhitespace(): Boolean {
        if (!isWhitespace()) {
            return false
        }

        val start = row
        while (isWhitespace() && isNotRowEOF()) {
            yyChar()
        }
        addToken(PhpToken.WHITE_SPACE, start, row)
        return true
    }

    protected fun handlePhpEnd(startLine: Int, startRow: Int): Boolean {
        val start = row
        if (scannedChar == '?') {
            yyChar()
            if (scannedChar == '>') {
                yyChar()
                // PHP 结束
                val endLine = line
                val endRow = row
                // 在这里应该去结束块

                val codeBlockIndex = getCodeBlockCount() - 1
                if (codeBlockIndex >= 0) {
                    val lastCodeBlock = getCodeBlock(codeBlockIndex)
                    if (lastCodeBlock.startLine == startLine && lastCodeBlock.startRow == startRow) {
                        addToken(PhpToken.PHP_CODE_BLOCK_END, start, row)
                        lastCodeBlock.endLine = endLine
                        lastCodeBlock.endRow = endRow
                        return true
                    }
                }
            }
        }
        row = start
        getChar()
        return false
    }

    protected inline fun handlePhpComments(): Boolean {
        if (scannedChar != '#' && scannedChar != '/') {
            return false
        }

        val start = row
        if (scannedChar == '#') {
            val end = rowSize()
            addToken(
                PhpToken.SINGLE_COMMENT,
                start,
                end
            )
            row = end
            return true
        }

        if (scannedChar == '/') {
            yyChar()
            if (scannedChar == '/') {
                val end = rowSize()
                addToken(
                    HtmlToken.COMMENT,
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
                        PhpToken.SINGLE_COMMENT,
                        start,
                        end
                    )
                    row = end
                    return true
                }

                addToken(
                    PhpToken.MULTI_COMMENT_START,
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
                            PhpToken.MULTI_COMMENT_END,
                            0,
                            end
                        )
                        row = end
                        return true
                    }

                    addToken(
                        PhpToken.MULTI_COMMENT_PART,
                        0,
                        rowSize()
                    )
                    ++line
                }
                return true
            }
        }

        row = start
        getChar()
        return false
    }

    protected inline fun handlePhpString(): Boolean {
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
                PhpToken.STRING,
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
                PhpToken.STRING,
                start,
                end
            )
            return true
        }
        row = start
        getChar()
        return false
    }

    protected inline fun handlePhpOperator(): Boolean {
        if (!isPhpOperator()) {
            return false
        }

        val token = PhpLanguageWrapper.getOperatorTokenMap()[scannedChar]!!
        if (token == PhpToken.EQUALS) {
            val lastTokenContainer = lastNonWhitespaceToken(PhpToken.WHITE_SPACE)
            val lastToken = lastTokenContainer?.token
            if (lastToken == PhpToken.IDENTIFIER) {
                val lastTokenStart = lastTokenContainer.startRow
                val lastTokenEnd = lastTokenContainer.endRow
                val lastTokenText = sources.getTextRow(lastTokenContainer.line)
                    .subSequence(lastTokenStart, lastTokenEnd).toString()
                if (lastTokenText[0] == '$') {
                    // 为变量
                    addAutoCompletionItem(
                        PhpLanguageWrapper,
                        lastTokenText,
                        VARIABLE
                    )
                }
            }
        }

        if (!CharTable.BracketCollection.contains(scannedChar!!)) {
            val start = row
            while (isNotRowEOF() && isOperator()) {
                yyChar()
            }
            addToken(PhpToken.OPERATOR, start, row)
        } else {
            val end = row + 1
            addToken(token, row, end)
            row = end
        }
        return true
    }

    protected inline fun handlePhpKeyword(): Boolean {
        if (!isLetter() && scannedChar != '_') {
            return false
        }

        val buffer = StringBuilder()
        val start = row
        while ((isLetter() || scannedChar == '_') && isNotRowEOF()) {
            buffer.append(scannedChar)
            yyChar()
        }
        val end = row
        val text = buffer.toString()

        if (isPhpKeyword(text)) {
            val token = PhpLanguageWrapper.getKeywordTokenMap()[text]!!
            addToken(
                token,
                start,
                end
            )
            return true
        }

        row = start
        getChar()
        return false
    }

    protected inline fun handlePhpDigit(): Boolean {
        if (!isDigit()) {
            return false
        }

        val start = row
        while (isDigit() && isNotRowEOF()) {
            yyChar()
        }
        val end = row

        addToken(
            PhpToken.DIGIT,
            start,
            end
        )
        return true
    }

    protected inline fun handlePhpIdentifier(): Boolean {
        if (isWhitespace() || isPhpOperator() || isDigit()) {
            return false
        }

        val start = row
        val builder = StringBuilder()
        while (!isWhitespace() && !isPhpOperator() && isNotRowEOF()) {
            builder.append(scannedChar)
            yyChar()
        }
        val end = row

        val lastTokenContainer = lastNonWhitespaceToken(PhpToken.WHITE_SPACE)
        val lastToken = lastTokenContainer?.token
        if (lastToken == PhpToken.FUNCTION) {
            addAutoCompletionItem(
                PhpLanguageWrapper,
                builder.toString(),
                FUNCTION
            )
        }

        addToken(
            PhpToken.IDENTIFIER,
            start,
            end
        )
        return true
    }

    open fun isPhpOperator(): Boolean {
        return scannedChar in PhpLanguageWrapper.getOperatorTokenMap()
    }

    open fun isPhpKeyword(charSequence: CharSequence): Boolean {
        return charSequence in PhpLanguageWrapper.getKeywordTokenMap()
    }

    override fun clear() {
        super.clear()
        PhpLanguageWrapper.clearVariableAutoCompletionItems()
    }

}