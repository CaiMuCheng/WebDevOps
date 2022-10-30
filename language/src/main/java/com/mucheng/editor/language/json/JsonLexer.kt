package com.mucheng.editor.language.json

import com.mucheng.editor.base.lexer.AbstractLexer

@Suppress("NOTHING_TO_INLINE")
open class JsonLexer(language: JsonLanguage) : AbstractLexer(language) {

    override fun analyze() {
        super.analyze()
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

            if (handleJsonWhitespace()) continue

            if (handleJsonString()) continue

            if (handleJsonOperator()) continue

            if (handleJsonSpecial()) continue

            if (handleJsonIdentifier()) continue

            if (handleJsonDigit()) continue

            ++row
        }
    }

    protected inline fun handleJsonWhitespace(): Boolean {
        if (!isWhitespace()) {
            return false
        }

        val start = row
        while (isWhitespace() && isNotRowEOF()) {
            yyChar()
        }
        val end = row
        addToken(
            JsonToken.WHITESPACE,
            line,
            start,
            end
        )
        return true
    }

    protected inline fun handleJsonString(): Boolean {
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
                JsonToken.STRING,
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
                JsonToken.STRING,
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

    protected inline fun handleJsonOperator(): Boolean {
        if (!isOperator()) {
            return false
        }

        val token = language.getOperatorTokenMap()[scannedChar]!!
        addToken(
            token,
            line,
            row,
            row + 1
        )
        ++row
        return true
    }

    protected inline fun handleJsonSpecial(): Boolean {
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

    protected inline fun handleJsonIdentifier(): Boolean {
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

        addToken(
            JsonToken.IDENTIFIER,
            line,
            start,
            end
        )
        return true
    }

    protected inline fun handleJsonDigit(): Boolean {
        if (!isDigit()) {
            return false
        }

        val start = row
        while (isDigit() && isNotRowEOF()) {
            yyChar()
        }
        if (scannedChar == '.') {
            yyChar()
            while (isDigit() && isNotRowEOF()) {
                yyChar()
            }
        }
        val end = row

        addToken(
            JsonToken.NUMBER,
            start,
            end
        )
        return true
    }

}