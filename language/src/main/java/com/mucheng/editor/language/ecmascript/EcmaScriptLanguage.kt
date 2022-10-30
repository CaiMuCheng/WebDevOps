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

import androidx.annotation.Keep
import com.mucheng.editor.base.IAutoCompletionHelper
import com.mucheng.editor.base.lang.AbstractBasicLanguage
import com.mucheng.editor.data.AutoCompletionItem
import com.mucheng.editor.sample.helper.DefaultAutoCompletionHelper
import com.mucheng.editor.token.ThemeToken
import com.mucheng.editor.view.MuCodeEditor

@Keep
@Suppress("LeakingThis")
open class EcmaScriptLanguage : AbstractBasicLanguage() {

    companion object {

        @Volatile
        @JvmStatic
        private var instance: EcmaScriptLanguage? = null

        @JvmStatic
        fun getInstance(): EcmaScriptLanguage {
            if (instance == null) {
                synchronized(EcmaScriptLanguage::class.java) {
                    if (instance == null) {
                        instance = EcmaScriptLanguage()
                    }
                }
            }
            return instance!!
        }

    }

    private lateinit var editor: MuCodeEditor

    private val ecmaScriptLexer = EcmaScriptLexer(this)

    private val userDefinedAutoCompletionItems: MutableList<AutoCompletionItem> = ArrayList()

    private val autoCompletionHelper = DefaultAutoCompletionHelper()

    private val operatorTokenMap = hashMapOf(
        '+' to EcmaScriptToken.PLUS,
        '-' to EcmaScriptToken.MINUS,
        '*' to EcmaScriptToken.MULTI,
        '/' to EcmaScriptToken.DIV,
        ':' to EcmaScriptToken.COLON,
        '!' to EcmaScriptToken.NOT,
        '%' to EcmaScriptToken.MOD,
        '^' to EcmaScriptToken.XOR,
        '&' to EcmaScriptToken.AND,
        '?' to EcmaScriptToken.QUESTION,
        '~' to EcmaScriptToken.COMP,
        '.' to EcmaScriptToken.DOT,
        ',' to EcmaScriptToken.COMMA,
        ';' to EcmaScriptToken.SEMICOLON,
        '=' to EcmaScriptToken.EQUALS,
        '(' to EcmaScriptToken.LEFT_PARENTHESIS,
        ')' to EcmaScriptToken.RIGHT_PARENTHESIS,
        '[' to EcmaScriptToken.LEFT_BRACKET,
        ']' to EcmaScriptToken.RIGHT_BRACKET,
        '{' to EcmaScriptToken.LEFT_BRACE,
        '}' to EcmaScriptToken.RIGHT_BRACE,
        '|' to EcmaScriptToken.OR,
        '<' to EcmaScriptToken.LESS_THAN,
        '>' to EcmaScriptToken.MORE_THAN
    )

    private val keywordTokenMap = hashMapOf(
        "var" to EcmaScriptToken.VAR,
        "let" to EcmaScriptToken.LET,
        "const" to EcmaScriptToken.CONST,
        "if" to EcmaScriptToken.IF,
        "else" to EcmaScriptToken.ELSE,
        "switch" to EcmaScriptToken.SWITCH,
        "case" to EcmaScriptToken.CASE,
        "default" to EcmaScriptToken.DEFAULT,
        "for" to EcmaScriptToken.FOR,
        "while" to EcmaScriptToken.WHILE,
        "do" to EcmaScriptToken.DO,
        "break" to EcmaScriptToken.BREAK,
        "continue" to EcmaScriptToken.CONTINUE,
        "function" to EcmaScriptToken.FUNCTION,
        "return" to EcmaScriptToken.RETURN,
        "yield" to EcmaScriptToken.YIELD,
        "async" to EcmaScriptToken.ASYNC,
        "await" to EcmaScriptToken.AWAIT,
        "throw" to EcmaScriptToken.THROW,
        "try" to EcmaScriptToken.TRY,
        "catch" to EcmaScriptToken.CATCH,
        "finally" to EcmaScriptToken.FINALLY,
        "this" to EcmaScriptToken.THIS,
        "with" to EcmaScriptToken.WITH,
        "in" to EcmaScriptToken.IN,
        "of" to EcmaScriptToken.OF,
        "delete" to EcmaScriptToken.DELETE,
        "instanceof" to EcmaScriptToken.INSTANCEOF,
        "typeof" to EcmaScriptToken.TYPEOF,
        "new" to EcmaScriptToken.NEW,
        "class" to EcmaScriptToken.CLASS,
        "extends" to EcmaScriptToken.EXTENDS,
        "set" to EcmaScriptToken.SET,
        "get" to EcmaScriptToken.GET,
        "import" to EcmaScriptToken.IMPORT,
        "as" to EcmaScriptToken.AS,
        "from" to EcmaScriptToken.FROM,
        "export" to EcmaScriptToken.EXPORT,
        "void" to EcmaScriptToken.VOID,
        "debugger" to EcmaScriptToken.DEBUGGER
    )

    private val specialTokenMap = hashMapOf(
        "false" to EcmaScriptToken.FALSE,
        "true" to EcmaScriptToken.TRUE,
        "NaN" to EcmaScriptToken.NAN,
        "undefined" to EcmaScriptToken.UNDEFINED,
        "null" to EcmaScriptToken.NULL
    )

    override fun getLexer(): EcmaScriptLexer {
        return ecmaScriptLexer
    }

    override fun doSpan(): Boolean {
        return true
    }

    override fun setEditor(editor: MuCodeEditor) {
        this.editor = editor
    }

    override fun getEditor(): MuCodeEditor {
        return editor
    }

    override fun isKeyword(text: String): Boolean {
        return text in keywordTokenMap.keys
    }

    override fun getOperatorTokenMap(): Map<Char, ThemeToken> {
        return operatorTokenMap
    }

    override fun getKeywordTokenMap(): Map<String, ThemeToken> {
        return keywordTokenMap
    }

    override fun getSpecialTokenMap(): Map<String, ThemeToken> {
        return specialTokenMap
    }

    override fun getAutoCompletionHelper(): IAutoCompletionHelper {
        return autoCompletionHelper
    }

    override fun getVariableAutoCompletionItems(): List<AutoCompletionItem> {
        return userDefinedAutoCompletionItems
    }

    override fun addVariableAutoCompletionItem(autoCompletionItem: AutoCompletionItem) {
        userDefinedAutoCompletionItems.add(autoCompletionItem)
    }

    override fun addAllVariableAutoCompletionItem(autoCompletionItems: List<AutoCompletionItem>) {
        userDefinedAutoCompletionItems.addAll(autoCompletionItems)
    }

    override fun clearVariableAutoCompletionItems() {
        userDefinedAutoCompletionItems.clear()
    }

}