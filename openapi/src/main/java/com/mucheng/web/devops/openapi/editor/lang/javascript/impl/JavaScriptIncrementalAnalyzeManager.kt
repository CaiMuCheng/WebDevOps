package com.mucheng.web.devops.openapi.editor.lang.javascript.impl

import com.mucheng.web.devops.openapi.editor.lang.javascript.JavaScriptLexer
import com.mucheng.web.devops.openapi.editor.lang.javascript.JavaScriptLexer.*
import com.mucheng.web.devops.openapi.editor.lang.javascript.impl.JavaScriptState.Companion.STATE_INCOMPLETE_COMMENT
import com.mucheng.web.devops.openapi.editor.lang.javascript.impl.JavaScriptState.Companion.STATE_NORMAL
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

class JavaScriptIncrementalAnalyzeManager : AsyncIncrementalAnalyzeManager<JavaScriptState, Long>() {

    private val tokenizerProvider = ThreadLocal<JavaScriptLexer>()

    companion object {
        private fun pack(type: Int, column: Int): Long {
            return IntPair.pack(type, column)
        }

        private const val COMPLETE = 129

        private const val INCOMPLETE = 130

    }

    @Synchronized
    private fun obtainTokenizer(): JavaScriptLexer {
        var res = tokenizerProvider.get()
        if (res == null) {
            res = JavaScriptLexer(CharStreams.fromString(""))
            tokenizerProvider.set(res)
        }
        return res
    }

    override fun getInitialState(): JavaScriptState {
        return JavaScriptState()
    }

    override fun computeBlocks(text: Content?, delegate: CodeBlockAnalyzeDelegate?): MutableList<CodeBlock> {
        return ArrayList(0)
    }

    override fun generateSpansForLine(lineResult: IncrementalAnalyzeManager.LineTokenizeResult<JavaScriptState, Long>): MutableList<Span> {
        val tokens = lineResult.tokens
        val spans = ArrayList<Span>(tokens.size)
        var prevTokenType = -1
        for (i in 0 until tokens.size) {
            val tokenRecord = tokens[i]
            val type = IntPair.getFirst(tokenRecord)
            val column = IntPair.getSecond(tokenRecord)
            when (type) {
                OpenBracket,
                CloseBracket,
                OpenParen,
                CloseParen,
                OpenBrace,
                CloseBrace,
                SemiColon,
                Comma,
                Assign,
                QuestionMark,
                QuestionMarkDot,
                Colon,
                Ellipsis,
                Dot,
                PlusPlus,
                Plus,
                MinusMinus,
                Minus,
                BitNot,
                Not, Multiply,
                Divide,
                Modulus,
                NullCoalesce,
                Hashtag,
                RightShiftArithmetic,
                LeftShiftArithmetic,
                RightShiftLogical,
                LessThan,
                MoreThan,
                LessThanEquals,
                GreaterThanEquals,
                Equals_,
                NotEquals,
                IdentityEquals,
                IdentityNotEquals,
                BitAnd,
                BitXOr,
                BitOr,
                And,
                Or,
                MultiplyAssign,
                DivideAssign,
                ModulusAssign,
                PlusAssign,
                MinusAssign,
                LeftShiftArithmeticAssign,
                RightShiftArithmeticAssign,
                RightShiftLogicalAssign,
                BitAndAssign,
                BitXorAssign,
                BitOrAssign,
                PowerAssign,
                ARROW
                -> {
                    spans.add(
                        Span.obtain(
                            column,
                            TextStyle.makeStyle(EditorColorScheme.OPERATOR)
                        )
                    )
                    prevTokenType = type
                }

                SingleLineComment, MultiLineComment, COMPLETE, INCOMPLETE, HtmlComment, CDataComment -> {
                    spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.COMMENT)))
                    prevTokenType = type
                }

                Var, NonStrictLet, StrictLet, Const,
                Break, Do, Instanceof, Typeof, Case, Else, New, Catch, Finally,
                Return, Void, Continue, For, Switch, While, Debugger, Function_,
                This, With, Default, If, Throw, Delete, In, Try, As, From,
                Class, JavaScriptLexer.Enum, Extends, Super, Export, Import,
                Async, Await, Yield, Implements, Private, Public, Interface, Package,
                Protected, Static
                -> {
                    spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.KEYWORD)))
                    prevTokenType = type
                }

                NullLiteral,
                BooleanLiteral,
                DecimalLiteral,
                HexIntegerLiteral,
                BigHexIntegerLiteral,
                OctalIntegerLiteral,
                OctalIntegerLiteral2,
                BinaryIntegerLiteral,
                BigOctalIntegerLiteral,
                BigBinaryIntegerLiteral,
                BigDecimalIntegerLiteral
                -> {
                    spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.LITERAL)))
                    prevTokenType = type
                }

                StringLiteral, BackTick -> {
                    spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.ATTRIBUTE_VALUE)))
                    prevTokenType = type
                }

                Identifier -> {
                    when (prevTokenType) {
                        Dot, Function_ -> {
                            spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.FUNCTION_NAME)))
                        }
                        Var, StrictLet, NonStrictLet, Const -> {
                            spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.IDENTIFIER_VAR)))
                        }
                        else -> {
                            spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.TEXT_NORMAL)))
                        }
                    }
                    prevTokenType = type
                }

                else -> {
                    spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.TEXT_NORMAL)))
                    if (type != LineTerminator && type != WhiteSpaces) {
                        prevTokenType = type
                    }
                }
            }
        }
        return spans
    }

    override fun tokenizeLine(
        line: CharSequence,
        state: JavaScriptState,
        lineIndex: Int
    ): IncrementalAnalyzeManager.LineTokenizeResult<JavaScriptState, Long> {
        val tokens = ArrayList<Long>()
        val stateObj = JavaScriptState()
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
            tokens.add(pack(EOF, 0))
        }
        stateObj.state = newState
        return IncrementalAnalyzeManager.LineTokenizeResult(stateObj, tokens)
    }

    private fun tokenizeNormal(text: CharSequence, offset: Int, tokens: MutableList<Long>): Int {
        val tokenizer = obtainTokenizer()
        tokenizer.inputStream = CharStreams.fromString(text.substring(offset, text.length))
        var token: Token

        var state = STATE_NORMAL
        while (tokenizer.nextToken().also { token = it }.type != EOF) {
            if (token.type == MultiLineCommentOpen) {
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
            if (text[index] == '*') {
                if (index + 1 < text.length && text[index + 1] == '/') {
                    tokens.add(pack(COMPLETE, 0))
                    return pack(STATE_NORMAL, index + 2)
                }
            }
            ++index
        }

        tokens.add(pack(INCOMPLETE, 0))
        return pack(STATE_INCOMPLETE_COMMENT, 0)
    }

    override fun stateEquals(state: JavaScriptState, another: JavaScriptState): Boolean {
        return state == another
    }

}