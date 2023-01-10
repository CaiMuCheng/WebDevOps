package com.mucheng.web.devops.openapi.editor.lang.css.impl

import com.mucheng.web.devops.openapi.editor.lang.css.css3Lexer
import com.mucheng.web.devops.openapi.editor.lang.html.HTMLLexer
import io.github.rosemoe.sora.lang.analysis.AsyncIncrementalAnalyzeManager
import io.github.rosemoe.sora.lang.analysis.IncrementalAnalyzeManager
import io.github.rosemoe.sora.lang.completion.IdentifierAutoComplete.SyncIdentifiers
import io.github.rosemoe.sora.lang.styling.CodeBlock
import io.github.rosemoe.sora.lang.styling.Span
import io.github.rosemoe.sora.lang.styling.TextStyle
import io.github.rosemoe.sora.text.Content
import io.github.rosemoe.sora.util.IntPair
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.Token

class CssIncrementalAnalyzeManager : AsyncIncrementalAnalyzeManager<CssState, Long>() {

    private val tokenizerProvider = ThreadLocal<css3Lexer>()

    val identifiers = SyncIdentifiers()

    companion object {
        private fun pack(type: Int, column: Int): Long {
            return IntPair.pack(type, column)
        }

    }

    @Synchronized
    private fun obtainTokenizer(): css3Lexer {
        var res = tokenizerProvider.get()
        if (res == null) {
            res = css3Lexer(CharStreams.fromString(""))
            tokenizerProvider.set(res)
        }
        return res
    }

    override fun getInitialState(): CssState {
        return CssState()
    }

    override fun computeBlocks(text: Content?, delegate: CodeBlockAnalyzeDelegate?): MutableList<CodeBlock> {
        return ArrayList(0)
    }

    override fun generateSpansForLine(lineResult: IncrementalAnalyzeManager.LineTokenizeResult<CssState, Long>): MutableList<Span> {
        val tokens = lineResult.tokens
        val spans = ArrayList<Span>(tokens.size)
        var prevIsFunction = false
        for (i in 0 until tokens.size) {
            val tokenRecord = tokens[i]
            val type = IntPair.getFirst(tokenRecord)
            val column = IntPair.getSecond(tokenRecord)
            when (type) {

                css3Lexer.Comment -> {
                    spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.COMMENT)))
                }

                css3Lexer.Plus, css3Lexer.Minus, css3Lexer.Greater, css3Lexer.Comma,
                css3Lexer.Tilde, css3Lexer.T__3, css3Lexer.T__4, css3Lexer.T__5 -> {
                    if (type == css3Lexer.T__3 && prevIsFunction) {
                        spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.FUNCTION_NAME)))
                        prevIsFunction = false
                    } else {
                        spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.OPERATOR)))
                    }
                }

                css3Lexer.Function_, css3Lexer.Var -> {
                    spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.FUNCTION_NAME)))
                    prevIsFunction = true
                }

                css3Lexer.Variable -> {
                    spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.FUNCTION_NAME)))
                }

                css3Lexer.Ident -> {
                    spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.TEXT_NORMAL)))
                }

                css3Lexer.Number, css3Lexer.Dimension, css3Lexer.UnknownDimension -> {
                    spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.LITERAL)))
                }

                css3Lexer.String_ -> {
                    spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.ATTRIBUTE_VALUE)))
                }

                else -> {
                    spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.TEXT_NORMAL)))
                }
            }
        }
        return spans
    }

    override fun tokenizeLine(
        line: CharSequence,
        state: CssState,
        lineIndex: Int
    ): IncrementalAnalyzeManager.LineTokenizeResult<CssState, Long> {
        val tokens = ArrayList<Long>()
        val stateObj = CssState()
        tokenizeNormal(line, tokens)
        if (tokens.isEmpty()) {
            tokens.add(pack(HTMLLexer.EOF, 0))
        }
        return IncrementalAnalyzeManager.LineTokenizeResult(stateObj, tokens)
    }

    private fun tokenizeNormal(text: CharSequence, tokens: MutableList<Long>) {
        val tokenizer = obtainTokenizer()
        tokenizer.inputStream = CharStreams.fromString(text.substring(0, text.length))

        var token: Token
        while (tokenizer.nextToken().also { token = it }.type != css3Lexer.EOF) {
            tokens.add(pack(token.type, token.charPositionInLine))
        }
    }

    override fun stateEquals(state: CssState, another: CssState): Boolean {
        return state == another
    }

}