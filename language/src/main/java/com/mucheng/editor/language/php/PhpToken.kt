package com.mucheng.editor.language.php

import com.mucheng.editor.token.ThemeToken

@Suppress("SpellCheckingInspection", "unused", "unused")
object PhpToken {

    val PHP_CODE_BLOCK_START = ThemeToken(ThemeToken.KEYWORD_COLOR, "PHP_CODE_BLOCK_START")
    val PHP_CODE_BLOCK_END = ThemeToken(ThemeToken.KEYWORD_COLOR, "PHP_CODE_BLOCK_END")
    val WHITE_SPACE = ThemeToken(ThemeToken.IDENTIFIER_COLOR_TOKEN, "WHITE_SPACE")

    val SINGLE_COMMENT = ThemeToken(ThemeToken.COMMENT_COLOR, "SINGLE_COMMENT")
    val MULTI_COMMENT_START = ThemeToken(ThemeToken.COMMENT_COLOR, "MULTI_COMMENT_START")
    val MULTI_COMMENT_PART = ThemeToken(ThemeToken.COMMENT_COLOR, "MULTI_COMMENT_PART")
    val MULTI_COMMENT_END = ThemeToken(ThemeToken.COMMENT_COLOR, "MULTI_COMMENT_END")

    val IDENTIFIER = ThemeToken(ThemeToken.IDENTIFIER_COLOR_TOKEN, "IDENTIFIER")
    val DIGIT = ThemeToken(ThemeToken.NUMERICAL_VALUE_COLOR, "DIGIT")
    val STRING = ThemeToken(ThemeToken.STRING_COLOR, "STRING")

    // 以下为关键字
    val HALT_COMPILER = ThemeToken(ThemeToken.KEYWORD_COLOR, "HALT_COMPILER")
    val ABSTRACT = ThemeToken(ThemeToken.KEYWORD_COLOR, "ABSTRACT")
    val AND_KEYWORD = ThemeToken(ThemeToken.KEYWORD_COLOR, "AND_KEYWORD")
    val ARRAY = ThemeToken(ThemeToken.KEYWORD_COLOR, "ARRAY")
    val AS = ThemeToken(ThemeToken.KEYWORD_COLOR, "AS")
    val BREAK = ThemeToken(ThemeToken.KEYWORD_COLOR, "BREAK")
    val CALLABLE = ThemeToken(ThemeToken.KEYWORD_COLOR, "CALLABLE")
    val CASE = ThemeToken(ThemeToken.KEYWORD_COLOR, "CASE")
    val CATCH = ThemeToken(ThemeToken.KEYWORD_COLOR, "CATCH")
    val CLASS = ThemeToken(ThemeToken.KEYWORD_COLOR, "CLASS")
    val CLONE = ThemeToken(ThemeToken.KEYWORD_COLOR, "CLONE")
    val CONST = ThemeToken(ThemeToken.KEYWORD_COLOR, "CONST")
    val CONTINUE = ThemeToken(ThemeToken.KEYWORD_COLOR, "CONTINUE")
    val DECLARE = ThemeToken(ThemeToken.KEYWORD_COLOR, "DECLARE")
    val DEFAULT = ThemeToken(ThemeToken.KEYWORD_COLOR, "DEFAULT")
    val DIE = ThemeToken(ThemeToken.KEYWORD_COLOR, "DIE")
    val DO = ThemeToken(ThemeToken.KEYWORD_COLOR, "DO")
    val ECHO = ThemeToken(ThemeToken.KEYWORD_COLOR, "ECHO")
    val ELSE = ThemeToken(ThemeToken.KEYWORD_COLOR, "ELSE")
    val ELSEIF = ThemeToken(ThemeToken.KEYWORD_COLOR, "ELSEIF")
    val EMPTY = ThemeToken(ThemeToken.KEYWORD_COLOR, "EMPTY")
    val ENDDECLARE = ThemeToken(ThemeToken.KEYWORD_COLOR, "ENDDECLARE")
    val ENDFOR = ThemeToken(ThemeToken.KEYWORD_COLOR, "ENDFOR")
    val ENDFOREACH = ThemeToken(ThemeToken.KEYWORD_COLOR, "ENDFOREACH")
    val ENDIF = ThemeToken(ThemeToken.KEYWORD_COLOR, "ENDIF")
    val ENDSWITCH = ThemeToken(ThemeToken.KEYWORD_COLOR, "ENDSWITCH")
    val ENDWHILE = ThemeToken(ThemeToken.KEYWORD_COLOR, "ENDWHILE")
    val EVAL = ThemeToken(ThemeToken.KEYWORD_COLOR, "EVAL")
    val EXIT = ThemeToken(ThemeToken.KEYWORD_COLOR, "EXIT")
    val EXTENDS = ThemeToken(ThemeToken.KEYWORD_COLOR, "EXTENDS")
    val FINAL = ThemeToken(ThemeToken.KEYWORD_COLOR, "FINAL")
    val FINALLY = ThemeToken(ThemeToken.KEYWORD_COLOR, "FINALLY")
    val FN = ThemeToken(ThemeToken.KEYWORD_COLOR, "FN")
    val FOR = ThemeToken(ThemeToken.KEYWORD_COLOR, "FOR")
    val FOREACH = ThemeToken(ThemeToken.KEYWORD_COLOR, "FOREACH")
    val FUNCTION = ThemeToken(ThemeToken.KEYWORD_COLOR, "FUNCTION")
    val GLOBAL = ThemeToken(ThemeToken.KEYWORD_COLOR, "GLOBAL")
    val GOTO = ThemeToken(ThemeToken.KEYWORD_COLOR, "GOTO")
    val IF = ThemeToken(ThemeToken.KEYWORD_COLOR, "IF")
    val IMPLEMENTS = ThemeToken(ThemeToken.KEYWORD_COLOR, "IMPLEMENTS")
    val INCLUDE = ThemeToken(ThemeToken.KEYWORD_COLOR, "INCLUDE")
    val INCLUDE_ONCE = ThemeToken(ThemeToken.KEYWORD_COLOR, "INCLUDE_ONCE")
    val INSTANCEOF = ThemeToken(ThemeToken.KEYWORD_COLOR, "INSTANCEOF")
    val INSTEADOF = ThemeToken(ThemeToken.KEYWORD_COLOR, "INSTEADOF")
    val INTERFACE = ThemeToken(ThemeToken.KEYWORD_COLOR, "INTERFACE")
    val ISSET = ThemeToken(ThemeToken.KEYWORD_COLOR, "ISSET")
    val LIST = ThemeToken(ThemeToken.KEYWORD_COLOR, "LIST")
    val MATCH = ThemeToken(ThemeToken.KEYWORD_COLOR, "MATCH")
    val NAMESPACE = ThemeToken(ThemeToken.KEYWORD_COLOR, "NAMESPACE")
    val NEW = ThemeToken(ThemeToken.KEYWORD_COLOR, "NEW")
    val OR_KEYWORD = ThemeToken(ThemeToken.KEYWORD_COLOR, "OR_KEYWORD")
    val PRINT = ThemeToken(ThemeToken.KEYWORD_COLOR, "PRINT")
    val PRIVATE = ThemeToken(ThemeToken.KEYWORD_COLOR, "PRIVATE")
    val PROTECTED = ThemeToken(ThemeToken.KEYWORD_COLOR, "PROTECTED")
    val PUBLIC = ThemeToken(ThemeToken.KEYWORD_COLOR, "PUBLIC")
    val READONLY = ThemeToken(ThemeToken.KEYWORD_COLOR, "READONLY")
    val REQUIRE = ThemeToken(ThemeToken.KEYWORD_COLOR, "REQUIRE")
    val REQUIRE_ONCE = ThemeToken(ThemeToken.KEYWORD_COLOR, "REQUIRE_ONCE")
    val RETURN = ThemeToken(ThemeToken.KEYWORD_COLOR, "RETURN")
    val STATIC = ThemeToken(ThemeToken.KEYWORD_COLOR, "STATIC")
    val SWITCH = ThemeToken(ThemeToken.KEYWORD_COLOR, "SWITCH")
    val THROW = ThemeToken(ThemeToken.KEYWORD_COLOR, "THROW")
    val TRAIT = ThemeToken(ThemeToken.KEYWORD_COLOR, "TRAIT")
    val TRY = ThemeToken(ThemeToken.KEYWORD_COLOR, "TRY")
    val UNSET = ThemeToken(ThemeToken.KEYWORD_COLOR, "UNSET")
    val USE = ThemeToken(ThemeToken.KEYWORD_COLOR, "USE")
    val VAR = ThemeToken(ThemeToken.KEYWORD_COLOR, "VAR")
    val WHILE = ThemeToken(ThemeToken.KEYWORD_COLOR, "WHILE")
    val XOR_KEYWORD = ThemeToken(ThemeToken.KEYWORD_COLOR, "XOR_KEYWORD")
    val YIELD = ThemeToken(ThemeToken.KEYWORD_COLOR, "YIELD")
    val FROM = ThemeToken(ThemeToken.KEYWORD_COLOR, "FROM")

    val OPERATOR = ThemeToken(ThemeToken.SYMBOL_COLOR, "OPERATOR") // 所有运算符的总集（不包含括号）
    val PLUS = ThemeToken(ThemeToken.SYMBOL_COLOR, "PLUS") // '+'
    val MINUS = ThemeToken(ThemeToken.SYMBOL_COLOR, "MINUS") // '-'
    val MULTI = ThemeToken(ThemeToken.SYMBOL_COLOR, "MULTI") // '*'
    val DIV = ThemeToken(ThemeToken.SYMBOL_COLOR, "DIV") // '/'
    val COLON = ThemeToken(ThemeToken.SYMBOL_COLOR, "COLON") // ':'
    val NOT = ThemeToken(ThemeToken.SYMBOL_COLOR, "NOT") // '!'
    val MOD = ThemeToken(ThemeToken.SYMBOL_COLOR, "MOD") // '%'
    val XOR = ThemeToken(ThemeToken.SYMBOL_COLOR, "XOR") // '^'
    val AND = ThemeToken(ThemeToken.SYMBOL_COLOR, "AND") // '&'
    val QUESTION = ThemeToken(ThemeToken.SYMBOL_COLOR, "QUESTION") // '?'
    val COMP = ThemeToken(ThemeToken.SYMBOL_COLOR, "COMP") // '~'
    val DOT = ThemeToken(ThemeToken.SYMBOL_COLOR, "DOT") // '.'
    val COMMA = ThemeToken(ThemeToken.SYMBOL_COLOR, "COMMA") // ','
    val SEMICOLON = ThemeToken(ThemeToken.SYMBOL_COLOR, "SEMICOLON") // ';'
    val EQUALS = ThemeToken(ThemeToken.SYMBOL_COLOR, "EQUALS") // '='
    val LEFT_PARENTHESIS =
        ThemeToken(ThemeToken.SYMBOL_COLOR, "LEFT_PARENTHESIS") // '('
    val RIGHT_PARENTHESIS =
        ThemeToken(ThemeToken.SYMBOL_COLOR, "RIGHT_PARENTHESIS") // ')'
    val LEFT_BRACKET = ThemeToken(ThemeToken.SYMBOL_COLOR, "LEFT_BRACKET") // '['
    val RIGHT_BRACKET =
        ThemeToken(ThemeToken.SYMBOL_COLOR, "RIGHT_BRACKET") // ']'
    val LEFT_BRACE = ThemeToken(ThemeToken.SYMBOL_COLOR, "LEFT_BRACE") // '{'
    val RIGHT_BRACE = ThemeToken(ThemeToken.SYMBOL_COLOR, "RIGHT_BRACE") // '}'
    val OR = ThemeToken(ThemeToken.SYMBOL_COLOR, "OR") // '|'
    val LESS_THAN = ThemeToken(ThemeToken.SYMBOL_COLOR, "LESS_THAN") // '<'
    val MORE_THAN = ThemeToken(ThemeToken.SYMBOL_COLOR, "MORE_THAN") // '>'
}