package com.mucheng.web.devops.openapi.editor.lang.xml.impl


import com.mucheng.web.devops.openapi.editor.lang.xml.XMLLexer
import com.mucheng.web.devops.openapi.editor.lang.xml.impl.XmlState.Companion.STATE_INCOMPLETE_COMMENT
import com.mucheng.web.devops.openapi.editor.lang.xml.impl.XmlState.Companion.STATE_NORMAL
import io.github.rosemoe.sora.lang.analysis.AsyncIncrementalAnalyzeManager
import io.github.rosemoe.sora.lang.analysis.IncrementalAnalyzeManager
import io.github.rosemoe.sora.lang.styling.CodeBlock
import io.github.rosemoe.sora.lang.styling.Span
import io.github.rosemoe.sora.lang.styling.TextStyle
import io.github.rosemoe.sora.text.Content
import io.github.rosemoe.sora.util.IntPair
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.Token.EOF

class XmlIncrementalAnalyzeManager : AsyncIncrementalAnalyzeManager<XmlState, Long>() {

    private val tokenizerProvider = ThreadLocal<XMLLexer>()

    companion object {
        private fun pack(type: Int, column: Int): Long {
            return IntPair.pack(type, column)
        }

        private const val COMPLETE_COMMENT = 129

        private const val INCOMPLETE_COMMENT = 130

    }

    @Synchronized
    private fun obtainTokenizer(): XMLLexer {
        var res = tokenizerProvider.get()
        if (res == null) {
            res = XMLLexer(CharStreams.fromString(""))
            tokenizerProvider.set(res)
        }
        return res
    }

    override fun getInitialState(): XmlState {
        return XmlState()
    }

    override fun computeBlocks(text: Content?, delegate: CodeBlockAnalyzeDelegate?): MutableList<CodeBlock> {
        return ArrayList(0)
    }

    override fun generateSpansForLine(lineResult: IncrementalAnalyzeManager.LineTokenizeResult<XmlState, Long>): MutableList<Span> {
        val tokens = lineResult.tokens
        val spans = ArrayList<Span>(tokens.size)
        var prevIsTagName = false
        for (i in 0 until tokens.size) {
            val tokenRecord = tokens[i]
            val type = IntPair.getFirst(tokenRecord)
            val column = IntPair.getSecond(tokenRecord)
            when (type) {

                XMLLexer.DTD, XMLLexer.XMLDeclOpen, XMLLexer.SPECIAL_CLOSE -> {
                    spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.FUNCTION_NAME)))
                    prevIsTagName = false
                }

                XMLLexer.OPEN,
                XMLLexer.CLOSE,
                XMLLexer.SLASH,
                XMLLexer.SLASH_CLOSE,
                XMLLexer.EQUALS -> {
                    spans.add(
                        Span.obtain(
                            column,
                            TextStyle.makeStyle(EditorColorScheme.OPERATOR)
                        )
                    )
                    prevIsTagName = false
                }

                XMLLexer.Name -> {
                    if (prevIsTagName) {
                        spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.ATTRIBUTE_NAME)))
                    } else {
                        spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.HTML_TAG)))
                    }
                    prevIsTagName = true
                }

                XMLLexer.CDATA, XMLLexer.COMMENT, COMPLETE_COMMENT, INCOMPLETE_COMMENT -> {
                    spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.COMMENT)))
                    prevIsTagName = false
                }

                XMLLexer.STRING -> {
                    spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.ATTRIBUTE_VALUE)))
                    prevIsTagName = false
                }

                else -> {
                    spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.TEXT_NORMAL)))
                    if (type != XMLLexer.SEA_WS) {
                        prevIsTagName = false
                    }
                }
            }
        }
        return spans
    }

    override fun tokenizeLine(
        line: CharSequence,
        state: XmlState,
        lineIndex: Int
    ): IncrementalAnalyzeManager.LineTokenizeResult<XmlState, Long> {
        val tokens = ArrayList<Long>()
        val stateObj = XmlState()
        var newState = STATE_NORMAL
        when (state.state) {
            STATE_NORMAL -> {
                newState = tokenizeNormal(line, 0, tokens)
            }

            STATE_INCOMPLETE_COMMENT -> {
                val result = tryFillIncompleteComment(line, tokens)
                newState = IntPair.getFirst(result)
                newState = if (newState == STATE_NORMAL) {
                    tokenizeNormal(line, IntPair.getSecond(result), tokens)
                } else {
                    STATE_INCOMPLETE_COMMENT
                }
            }

        }
        if (tokens.isEmpty()) {
            tokens.add(pack(EOF, 0))
        }
        stateObj.state = newState
        return IncrementalAnalyzeManager.LineTokenizeResult(stateObj, tokens)
    }

    private fun tokenizeNormal(text: CharSequence, offset: Int, tokens: MutableList<Long>): Int {
        val tokenizer = obtainTokenizer()
        val substring = text.substring(offset, text.length)
        tokenizer.inputStream = CharStreams.fromString(substring)

        var token: Token
        var state = STATE_NORMAL
        while (tokenizer.nextToken().also { token = it }.type != EOF) {
            if (token.type == XMLLexer.COMMENT_OPEN) {
                state = STATE_INCOMPLETE_COMMENT
                tokens.add(pack(INCOMPLETE_COMMENT, token.charPositionInLine + offset))
                break
            }
            tokens.add(pack(token.type, token.charPositionInLine + offset))
        }
        return state
    }

    private fun tryFillIncompleteComment(text: CharSequence, tokens: MutableList<Long>): Long {
        val tokenizer = obtainTokenizer()
        tokenizer.inputStream = CharStreams.fromString(text.toString())

        var index = 0
        while (index < text.length) {
            if (text[index] == '-') {
                if (index + 1 < text.length && text[index + 1] == '-') {
                    if (index + 2 < text.length && text[index + 2] == '>') {
                        tokens.add(pack(COMPLETE_COMMENT, 0))
                        return pack(STATE_NORMAL, index + 3)
                    }
                }
            }
            ++index
        }

        tokens.add(pack(INCOMPLETE_COMMENT, 0))
        return pack(STATE_INCOMPLETE_COMMENT, 0)
    }

    override fun stateEquals(state: XmlState, another: XmlState): Boolean {
        return state == another
    }

}