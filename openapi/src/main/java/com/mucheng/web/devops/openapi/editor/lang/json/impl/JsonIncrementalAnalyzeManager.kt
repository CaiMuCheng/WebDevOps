package com.mucheng.web.devops.openapi.editor.lang.json.impl

import com.mucheng.web.devops.openapi.editor.lang.json.JSONLexer
import io.github.rosemoe.sora.lang.analysis.AsyncIncrementalAnalyzeManager
import io.github.rosemoe.sora.lang.analysis.IncrementalAnalyzeManager
import io.github.rosemoe.sora.lang.styling.CodeBlock
import io.github.rosemoe.sora.lang.styling.Span
import io.github.rosemoe.sora.lang.styling.TextStyle
import io.github.rosemoe.sora.text.Content
import io.github.rosemoe.sora.util.IntPair
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme
import org.antlr.v4.runtime.Token.EOF

class JsonIncrementalAnalyzeManager : AsyncIncrementalAnalyzeManager<JsonState, Long>() {

    private val tokenizerProvider = ThreadLocal<JSONLexer>()

    companion object {
        private fun pack(type: Int, column: Int): Long {
            return IntPair.pack(type, column)
        }

    }

    @Synchronized
    private fun obtainTokenizer(): JSONLexer {
        var res = tokenizerProvider.get()
        if (res == null) {
            res = JSONLexer("")
            tokenizerProvider.set(res)
        }
        return res
    }

    override fun getInitialState(): JsonState {
        return JsonState()
    }

    override fun computeBlocks(text: Content?, delegate: CodeBlockAnalyzeDelegate?): MutableList<CodeBlock> {
        return ArrayList(0)
    }

    override fun generateSpansForLine(lineResult: IncrementalAnalyzeManager.LineTokenizeResult<JsonState, Long>): MutableList<Span> {
        val tokens = lineResult.tokens
        val spans = ArrayList<Span>(tokens.size)
        for (i in 0 until tokens.size) {
            val tokenRecord = tokens[i]
            val type = IntPair.getFirst(tokenRecord)
            val column = IntPair.getSecond(tokenRecord)
            when (type) {

                JSONLexer.STRING -> {
                    spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.ATTRIBUTE_VALUE)))
                }

                JSONLexer.NUMBER -> {
                    spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.LITERAL)))
                }

                JSONLexer.LITERAL -> {
                    spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.FUNCTION_NAME)))
                }

                JSONLexer.BLOCK_OPEN, JSONLexer.BLOCK_CLOSE, JSONLexer.IS, JSONLexer.AND -> {
                    spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.OPERATOR)))
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
        state: JsonState,
        lineIndex: Int
    ): IncrementalAnalyzeManager.LineTokenizeResult<JsonState, Long> {
        val tokens = ArrayList<Long>()
        val stateObj = JsonState()
        tokenizeNormal(line, tokens)
        if (tokens.isEmpty()) {
            tokens.add(pack(EOF, 0))
        }
        return IncrementalAnalyzeManager.LineTokenizeResult(stateObj, tokens)
    }

    private fun tokenizeNormal(text: CharSequence, tokens: MutableList<Long>) {
        val tokenizer = obtainTokenizer()
        tokenizer.reset(text)

        var type: Int
        var lastIndex = 0
        while (tokenizer.nextToken().also { type = it } != JSONLexer.EOF) {
            tokens.add(pack(type, lastIndex))
            lastIndex = tokenizer.index
        }
    }

    override fun stateEquals(state: JsonState, another: JsonState): Boolean {
        return state == another
    }

}