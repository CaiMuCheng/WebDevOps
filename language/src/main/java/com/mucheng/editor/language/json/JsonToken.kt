package com.mucheng.editor.language.json

import com.mucheng.editor.token.ThemeToken

object JsonToken {
    val IDENTIFIER = ThemeToken(ThemeToken.IDENTIFIER_COLOR_TOKEN, "IDENTIFIER")
    val STRING = ThemeToken(ThemeToken.STRING_COLOR, "STRING")
    val NUMBER = ThemeToken(ThemeToken.NUMERICAL_VALUE_COLOR, "NUMBER")
    val TRUE = ThemeToken(ThemeToken.SPECIAL_COLOR, "TRUE")
    val FALSE = ThemeToken(ThemeToken.SPECIAL_COLOR, "FALSE")
    val NULL = ThemeToken(ThemeToken.SPECIAL_COLOR, "NULL")

    val WHITESPACE = ThemeToken(ThemeToken.IDENTIFIER_COLOR_TOKEN, "WHITESPACE")

    val IS = ThemeToken(ThemeToken.SYMBOL_COLOR, "IS")
    val AND = ThemeToken(ThemeToken.SYMBOL_COLOR, "AND")
    val LEFT_BRACKET = ThemeToken(ThemeToken.SYMBOL_COLOR, "LEFT_BRACKET") // '['
    val RIGHT_BRACKET =
        ThemeToken(ThemeToken.SYMBOL_COLOR, "RIGHT_BRACKET") // ']'
    val LEFT_BRACE = ThemeToken(ThemeToken.SYMBOL_COLOR, "LEFT_BRACE") // '{'
    val RIGHT_BRACE = ThemeToken(ThemeToken.SYMBOL_COLOR, "RIGHT_BRACE") // '}'
}