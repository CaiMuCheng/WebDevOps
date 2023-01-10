package com.mucheng.web.devops.openapi.editor.lang.json

class JSONLexer(init: CharSequence) {

    companion object {
        const val EOF = -1
        const val NEW_LINE = 0
        const val WHITE_SPAFE = 1
        const val BLOCK_OPEN = 2
        const val BLOCK_CLOSE = 3
        const val IS = 5
        const val AND = 6
        const val STRING = 7
        const val NUMBER = 8
        const val LITERAL = 9
        const val IDENTIFIER = 10
    }

    private var char: Char? = null

    private val buffer = StringBuilder()

    var charSequence: CharSequence

    var index: Int

    var length: Int

    init {
        charSequence = init
        index = 0
        length = charSequence.length
    }

    fun nextToken(): Int {
        getChar()
        if (index >= length) {
            return EOF
        }

        if (char == '\r') {
            val start = index
            yyChar()
            if (char == '\n') {
                yyChar()
                return NEW_LINE
            }
            backTo(start)
        } else if (char == '\n') {
            yyChar()
            return NEW_LINE
        }

        if (char == '"') {
            yyChar()
            while (index < length && (char != '\n' && char != '\r')) {
                if (char == '"') {
                    val start = index
                    prevChar()
                    if (char == '\\') {
                        val escapeEnd = index
                        while (index >= 0 && char == '\\') {
                            prevChar()
                        }
                        val escapeStart = index + 1
                        val distance = escapeEnd - escapeStart + 1
                        if (distance % 2 == 0) {
                            // break while
                            backTo(start)
                            yyChar()
                            break
                        } else {
                            // continue while
                            backTo(start)
                            yyChar()
                            continue
                        }
                    } else {
                        backTo(start)
                        yyChar()
                        break
                    }
                }
                yyChar()
            }
            return STRING
        }

        if (char == '\b' || char == '\t' || char == ' ') {
            while (char == '\b' || char == '\t' || char == ' ') {
                yyChar()
            }
            return WHITE_SPAFE
        }

        if (char == '{' || char == '[') {
            yyChar()
            return BLOCK_OPEN
        }

        if (char == '}' || char == '[') {
            yyChar()
            return BLOCK_CLOSE
        }

        if (char == ':') {
            yyChar()
            return IS
        }

        if (char == ',') {
            yyChar()
            return AND
        }

        if (char != null && Character.isDigit(char!!)) {
            val isZero = char == '0'
            val start = index
            if (isZero) {
                yyChar()
                if (char == 'x') {
                    yyChar()
                    while (char != null && (Character.isDigit(char!!) || (((char!! >= 'a') && (char!! <= 'f')) || ((char!! >= 'A') && (char!! <= 'F'))))) {
                        yyChar()
                    }
                    return NUMBER
                }
                backTo(start)
            }
            while (char != null && Character.isDigit(char!!)) {
                yyChar()
            }
            if (char == '.') {
                yyChar()
                while (char != null && Character.isDigit(char!!)) {
                    yyChar()
                }
            }
            return NUMBER
        }

        buffer.clear()
        while (
            char != null &&
            index < length &&
            char != '\r' &&
            char != '\n' &&
            char != '\t' &&
            char != '\n' &&
            char != ':' &&
            char != ',' &&
            char != '[' &&
            char != '{' &&
            char != ']' &&
            char != '}' &&
            char != '"'
        ) {
            buffer.append(char)
            yyChar()
        }
        val text = buffer.toString()
        if (text == "true" || text == "false" || text == "null") {
            return LITERAL
        }
        if (buffer.isEmpty()) {
            yyChar()
        }
        return IDENTIFIER
    }

    private fun getChar() {
        char = if (index in 0 until length) {
            charSequence[index]
        } else {
            null
        }
    }

    private fun yyChar() {
        ++index
        getChar()
    }

    private fun prevChar() {
        --index
        getChar()
    }

    private fun backTo(index: Int) {
        this.index = index
        getChar()
    }

    fun reset(charSequence: CharSequence) {
        this.charSequence = charSequence
        index = 0
        length = charSequence.length
        buffer.clear()
    }

}