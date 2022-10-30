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

package com.mucheng.editor.language.ecmascript

import com.mucheng.editor.token.ThemeToken

@Suppress("SpellCheckingInspection")
object EcmaScriptToken {

    val EOF = ThemeToken(ThemeToken.KEYWORD_COLOR, "EOF")
    val REGEX = ThemeToken(ThemeToken.SPECIAL_COLOR, "REGEX")
    val SINGLE_COMMENT = ThemeToken(ThemeToken.COMMENT_COLOR, "SINGLE_COMMENT")
    val MULTI_COMMENT_START =
        ThemeToken(ThemeToken.COMMENT_COLOR, "MULTI_COMMENT_START")
    val MULTI_COMMENT_PART =
        ThemeToken(ThemeToken.COMMENT_COLOR, "MULTI_COMMENT_PART")
    val MULTI_COMMENT_END =
        ThemeToken(ThemeToken.COMMENT_COLOR, "MULTI_COMMENT_END")

    val SINGLE_STRING = ThemeToken(ThemeToken.STRING_COLOR, "SINGLE_STRING")
    val TEMPLATE_STRING = ThemeToken(ThemeToken.STRING_COLOR, "TEMPLATE_STRING")

    val DIGIT_NUMBER =
        ThemeToken(ThemeToken.NUMERICAL_VALUE_COLOR, "DIGIT_NUMBER")
    val IDENTIFIER = ThemeToken(ThemeToken.IDENTIFIER_COLOR_TOKEN, "IDENTIFIER")

    val WHITESPACE = ThemeToken(ThemeToken.KEYWORD_COLOR, "WHITESPACE")

    val FALSE = ThemeToken(ThemeToken.SPECIAL_COLOR, "FALSE")
    val TRUE = ThemeToken(ThemeToken.SPECIAL_COLOR, "TRUE")
    val NAN = ThemeToken(ThemeToken.SPECIAL_COLOR, "NAN")
    val UNDEFINED = ThemeToken(ThemeToken.SPECIAL_COLOR, "UNDEFINED")
    val NULL = ThemeToken(ThemeToken.SPECIAL_COLOR, "NULL")

    val VAR = ThemeToken(ThemeToken.KEYWORD_COLOR, "VAR")
    val LET = ThemeToken(ThemeToken.KEYWORD_COLOR, "LET")
    val CONST = ThemeToken(ThemeToken.KEYWORD_COLOR, "CONST")

    val IF = ThemeToken(ThemeToken.KEYWORD_COLOR, "IF")
    val ELSE = ThemeToken(ThemeToken.KEYWORD_COLOR, "ELSE")
    val SWITCH = ThemeToken(ThemeToken.KEYWORD_COLOR, "SWITCH")
    val CASE = ThemeToken(ThemeToken.KEYWORD_COLOR, "CASE")
    val DEFAULT = ThemeToken(ThemeToken.KEYWORD_COLOR, "DEFAULT")

    val FOR = ThemeToken(ThemeToken.KEYWORD_COLOR, "FOR")
    val WHILE = ThemeToken(ThemeToken.KEYWORD_COLOR, "WHILE")
    val DO = ThemeToken(ThemeToken.KEYWORD_COLOR, "DO")
    val BREAK = ThemeToken(ThemeToken.KEYWORD_COLOR, "BREAK")
    val CONTINUE = ThemeToken(ThemeToken.KEYWORD_COLOR, "CONTINUE")

    val FUNCTION = ThemeToken(ThemeToken.KEYWORD_COLOR, "FUNCTION")
    val RETURN = ThemeToken(ThemeToken.KEYWORD_COLOR, "RETURN")
    val YIELD = ThemeToken(ThemeToken.KEYWORD_COLOR, "YIELD")
    val ASYNC = ThemeToken(ThemeToken.KEYWORD_COLOR, "ASYNC")
    val AWAIT = ThemeToken(ThemeToken.KEYWORD_COLOR, "AWAIT")

    val THROW = ThemeToken(ThemeToken.KEYWORD_COLOR, "THROW")
    val TRY = ThemeToken(ThemeToken.KEYWORD_COLOR, "TRY")
    val CATCH = ThemeToken(ThemeToken.KEYWORD_COLOR, "CATCH")
    val FINALLY = ThemeToken(ThemeToken.KEYWORD_COLOR, "FINALLY")

    val THIS = ThemeToken(ThemeToken.KEYWORD_COLOR, "THIS")
    val WITH = ThemeToken(ThemeToken.KEYWORD_COLOR, "WITH")
    val IN = ThemeToken(ThemeToken.KEYWORD_COLOR, "IN")
    val OF = ThemeToken(ThemeToken.KEYWORD_COLOR, "OF")
    val DELETE = ThemeToken(ThemeToken.KEYWORD_COLOR, "DELETE")
    val INSTANCEOF = ThemeToken(ThemeToken.KEYWORD_COLOR, "INSTANCEOF")
    val TYPEOF = ThemeToken(ThemeToken.KEYWORD_COLOR, "TYPEOF")

    val NEW = ThemeToken(ThemeToken.KEYWORD_COLOR, "NEW")
    val CLASS = ThemeToken(ThemeToken.KEYWORD_COLOR, "CLASS")
    val EXTENDS = ThemeToken(ThemeToken.KEYWORD_COLOR, "EXTENDS")
    val SET = ThemeToken(ThemeToken.KEYWORD_COLOR, "SET")
    val GET = ThemeToken(ThemeToken.KEYWORD_COLOR, "GET")

    val IMPORT = ThemeToken(ThemeToken.KEYWORD_COLOR, "IMPORT")
    val AS = ThemeToken(ThemeToken.KEYWORD_COLOR, "AS")
    val FROM = ThemeToken(ThemeToken.KEYWORD_COLOR, "FROM")
    val EXPORT = ThemeToken(ThemeToken.KEYWORD_COLOR, "EXPORT")

    val VOID = ThemeToken(ThemeToken.KEYWORD_COLOR, "VOID")
    val DEBUGGER = ThemeToken(ThemeToken.KEYWORD_COLOR, "DEBUGGER")

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