package com.mucheng.web.devops.openapi.editor.lang.html.impl

import com.mucheng.web.devops.openapi.editor.lang.html.HTMLLexer
import com.mucheng.web.devops.openapi.editor.lang.html.impl.HtmlState.Companion.STATE_INCOMPLETE_COMMENT
import com.mucheng.web.devops.openapi.editor.lang.html.impl.HtmlState.Companion.STATE_NORMAL
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

class HtmlIncrementalAnalyzeManager : AsyncIncrementalAnalyzeManager<HtmlState, Long>() {

    private val tokenizerProvider = ThreadLocal<HTMLLexer>()

    val identifiers = SyncIdentifiers()

    companion object {
        private fun pack(type: Int, column: Int): Long {
            return IntPair.pack(type, column)
        }

        private const val COMPLETE = 25

        private const val INCOMPLETE = 24

    }

    @Synchronized
    private fun obtainTokenizer(): HTMLLexer {
        var res = tokenizerProvider.get()
        if (res == null) {
            res = HTMLLexer(CharStreams.fromString(""))
            tokenizerProvider.set(res)
        }
        return res
    }

    override fun getInitialState(): HtmlState {
        return HtmlState()
    }

    override fun computeBlocks(text: Content?, delegate: CodeBlockAnalyzeDelegate?): MutableList<CodeBlock> {
        return ArrayList(0)
    }

    override fun generateSpansForLine(lineResult: IncrementalAnalyzeManager.LineTokenizeResult<HtmlState, Long>): MutableList<Span> {
        val tokens = lineResult.tokens
        val spans = ArrayList<Span>(tokens.size)
        var prevIsTagName = false
        for (i in 0 until tokens.size) {
            val tokenRecord = tokens[i]
            val type = IntPair.getFirst(tokenRecord)
            val column = IntPair.getSecond(tokenRecord)
            when (type) {
                HTMLLexer.TAG_OPEN,
                HTMLLexer.TAG_CLOSE,
                HTMLLexer.TAG_SLASH,
                HTMLLexer.TAG_SLASH_CLOSE,
                HTMLLexer.TAG_EQUALS -> {
                    spans.add(
                        Span.obtain(
                            column,
                            TextStyle.makeStyle(EditorColorScheme.OPERATOR)
                        )
                    )
                    prevIsTagName = false
                }

                HTMLLexer.CDATA, HTMLLexer.HTML_COMMENT, COMPLETE, INCOMPLETE -> {
                    spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.COMMENT)))
                    prevIsTagName = false
                }

                HTMLLexer.TAG_NAME -> {
                    if (prevIsTagName) {
                        spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.ATTRIBUTE_NAME)))
                    } else {
                        spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.HTML_TAG)))
                    }
                    prevIsTagName = true
                }

                HTMLLexer.ATTVALUE_VALUE -> {
                    spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.ATTRIBUTE_VALUE)))
                    prevIsTagName = true
                }

                HTMLLexer.DTD, HTMLLexer.SCRIPTLET -> {
                    spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.FUNCTION_NAME)))
                    prevIsTagName = false
                }

                else -> {
                    spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.TEXT_NORMAL)))
                    if (type != HTMLLexer.TAG_WHITESPACE) {
                        prevIsTagName = false
                    }
                }
            }
        }
        return spans
    }

    override fun tokenizeLine(
        line: CharSequence,
        state: HtmlState,
        lineIndex: Int
    ): IncrementalAnalyzeManager.LineTokenizeResult<HtmlState, Long> {
        val tokens = ArrayList<Long>()
        val stateObj = HtmlState()
        var newState = STATE_NORMAL
        if (state.state == STATE_NORMAL) {
            newState = tokenizeNormal(line, 0, tokens)
        } else if (state.state == STATE_INCOMPLETE_COMMENT) {
            val result = tryFillIncompleteComment(line, tokens)
            newState = IntPair.getFirst(result)
            newState = if (newState == STATE_NORMAL) {
                tokenizeNormal(line, IntPair.getSecond(result), tokens)
            } else {
                STATE_INCOMPLETE_COMMENT
            }
        }
        if (tokens.isEmpty()) {
            tokens.add(pack(HTMLLexer.EOF, 0))
        }
        stateObj.state = newState
        return IncrementalAnalyzeManager.LineTokenizeResult(stateObj, tokens)
    }

    private fun tokenizeNormal(text: CharSequence, offset: Int, tokens: MutableList<Long>): Int {
        val tokenizer = obtainTokenizer()
        tokenizer.inputStream = CharStreams.fromString(text.substring(offset, text.length))
        var token: Token

        var state = STATE_NORMAL
        while (tokenizer.nextToken().also { token = it }.type != HTMLLexer.EOF) {
            if (token.type == HTMLLexer.HTML_COMMENT_OPEN) {
                state = STATE_INCOMPLETE_COMMENT
                tokens.add(pack(INCOMPLETE, token.charPositionInLine + offset))
                break
            }
            tokens.add(pack(token.type, token.charPositionInLine + offset))
        }
        return state
    }

    private fun tryFillIncompleteComment(text: CharSequence, tokens: MutableList<Long>): Long {
        var index = 0
        while (index < text.length) {
            if (text[index] == '-') {
                if (index + 1 < text.length && text[index + 1] == '-') {
                    if (index + 2 < text.length && text[index + 2] == '>') {
                        tokens.add(pack(COMPLETE, 0))
                        return pack(STATE_NORMAL, index + 3)
                    }
                }
            }
            ++index
        }

        tokens.add(pack(INCOMPLETE, 0))
        return pack(STATE_INCOMPLETE_COMMENT, 0)
    }

    override fun stateEquals(state: HtmlState, another: HtmlState): Boolean {
        return state == another
    }

}