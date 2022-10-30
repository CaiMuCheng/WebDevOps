package com.mucheng.editor.language.json

import com.mucheng.editor.base.IAutoCompletionHelper
import com.mucheng.editor.base.lang.AbstractBasicLanguage
import com.mucheng.editor.base.lexer.AbstractLexer
import com.mucheng.editor.language.html.HtmlAutoCompletionHelper
import com.mucheng.editor.token.ThemeToken
import com.mucheng.editor.view.MuCodeEditor

object JsonLanguage : AbstractBasicLanguage() {

    private lateinit var editor: MuCodeEditor

    private val lexer = JsonLexer(this)

    private val autoCompletionHelper = HtmlAutoCompletionHelper()

    private val operatorTokenMap = hashMapOf(
        '[' to JsonToken.LEFT_BRACKET,
        ']' to JsonToken.RIGHT_BRACKET,
        '{' to JsonToken.LEFT_BRACE,
        '}' to JsonToken.RIGHT_BRACE,
        ':' to JsonToken.IS,
        ',' to JsonToken.AND
    )

    private val keywordTokenMap: Map<String, ThemeToken> = emptyMap()

    private val specialTokenMap: Map<String, ThemeToken> = hashMapOf(
        "true" to JsonToken.TRUE,
        "false" to JsonToken.FALSE,
        "null" to JsonToken.NULL
    )

    override fun getLexer(): AbstractLexer {
        return lexer
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

}