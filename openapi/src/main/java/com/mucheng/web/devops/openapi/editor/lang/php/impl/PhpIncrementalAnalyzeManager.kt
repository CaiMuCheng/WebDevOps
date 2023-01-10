package com.mucheng.web.devops.openapi.editor.lang.php.impl

import com.mucheng.web.devops.openapi.editor.lang.php.PhpLexer
import com.mucheng.web.devops.openapi.editor.lang.php.PhpLexer.*
import com.mucheng.web.devops.openapi.editor.lang.php.impl.PhpState.Companion.STATE_INCOMPLETE_HTML_COMMENT
import com.mucheng.web.devops.openapi.editor.lang.php.impl.PhpState.Companion.STATE_INCOMPLETE_PHP
import com.mucheng.web.devops.openapi.editor.lang.php.impl.PhpState.Companion.STATE_NORMAL
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

// FIXME: Now php comment doestn't work....
class PhpIncrementalAnalyzeManager : AsyncIncrementalAnalyzeManager<PhpState, Long>() {

    private val tokenizerProvider = ThreadLocal<PhpLexer>()

    companion object {
        private fun pack(type: Int, column: Int): Long {
            return IntPair.pack(type, column)
        }

        private const val COMPLETE = 129

        private const val INCOMPLETE = 130

    }

    @Synchronized
    private fun obtainTokenizer(): PhpLexer {
        var res = tokenizerProvider.get()
        if (res == null) {
            res = PhpLexer(CharStreams.fromString(""))
            tokenizerProvider.set(res)
        }
        return res
    }

    override fun getInitialState(): PhpState {
        return PhpState()
    }

    override fun computeBlocks(text: Content?, delegate: CodeBlockAnalyzeDelegate?): MutableList<CodeBlock> {
        return ArrayList(0)
    }

    override fun generateSpansForLine(lineResult: IncrementalAnalyzeManager.LineTokenizeResult<PhpState, Long>): MutableList<Span> {
        val tokens = lineResult.tokens
        val spans = ArrayList<Span>(tokens.size)
        var prevIsTagName = false
        for (i in 0 until tokens.size) {
            val tokenRecord = tokens[i]
            val type = IntPair.getFirst(tokenRecord)
            val column = IntPair.getSecond(tokenRecord)

            when (type) {

                XmlStart, XML, XmlClose, HtmlDtd -> {
                    spans.add(
                        Span.obtain(
                            column,
                            TextStyle.makeStyle(EditorColorScheme.FUNCTION_NAME)
                        )
                    )
                    prevIsTagName = false
                }

                Abstract, Array, As, BinaryCast, BoolType, Break, Callable, Case, Catch, Class, Clone, Const,
                Continue, Declare, Default, Do, DoubleCast, DoubleType, Echo, Else, ElseIf,
                Empty, EndDeclare, EndFor, EndForeach, EndIf, EndSwitch, EndWhile, Eval,
                Exit, Extends, Final, Finally, FloatCast, For, Foreach, Function_, Global,
                Goto, If, Implements, Import, Include, IncludeOnce, InstanceOf, InsteadOf, Int8Cast,
                Int16Cast, Int64Type, IntType, Interface, IsSet, List, LogicalAnd, LogicalOr,
                LogicalXor, Match_, Namespace, New, ObjectType, Parent_, Partial, Print, Private,
                Protected, Public, Require, RequireOnce, Resource, Return, Static, StringType,
                Switch, Throw, Trait, Try, Typeof, UintCast, UnicodeCast, Unset, Use, Var,
                While, Yield, From, LambdaFn -> {
                    spans.add(
                        Span.obtain(
                            column,
                            TextStyle.makeStyle(EditorColorScheme.KEYWORD)
                        )
                    )
                    prevIsTagName = false
                }


                Get, Set, Call, CallStatic, Constructor, Destruct, Wakeup,
                Sleep, Autoload, IsSet__, Unset__, ToString__, Invoke, SetState,
                Clone__, DebugInfo, Namespace__, Class__, Traic__, Function__, Method__,
                Line__, File__, Dir__ -> {
                    spans.add(
                        Span.obtain(
                            column,
                            TextStyle.makeStyle(EditorColorScheme.IDENTIFIER_NAME)
                        )
                    )
                    prevIsTagName = false
                }

                Spaceship, Lgeneric, Rgeneric, DoubleArrow, Inc, Dec, IsIdentical,
                IsNoidentical, IsEqual, IsNotEq, IsSmallerOrEqual, IsGreaterOrEqual,
                PlusEqual, MinusEqual, MulEqual, Pow, PowEqual, DivEqual, Concaequal,
                ModEqual, ShiftLeftEqual, ShiftRightEqual, AndEqual, OrEqual, XorEqual,
                BooleanOr, BooleanAnd, NullCoalescing, NullCoalescingEqual, ShiftLeft,
                ShiftRight, DoubleColon, ObjectOperator, NamespaceSeparator, Ellipsis,
                Less, Greater, Ampersand, Pipe, Bang, Caret, Plus, Minus, Asterisk,
                Percent, Divide, Tilde, SuppressWarnings, Dollar, Dot, QuestionMark,
                OpenRoundBracket, CloseRoundBracket, OpenSquareBracket, CloseSquareBracket,
                OpenCurlyBracket, CloseCurlyBracket, Comma, Colon, SemiColon, Eq, Quote,
                BackQuote -> {
                    spans.add(
                        Span.obtain(
                            column,
                            TextStyle.makeStyle(EditorColorScheme.OPERATOR)
                        )
                    )
                    prevIsTagName = false
                }


                VarName -> {
                    spans.add(
                        Span.obtain(
                            column,
                            TextStyle.makeStyle(EditorColorScheme.IDENTIFIER_VAR)
                        )
                    )
                    prevIsTagName = false
                }

                BooleanConstant -> {
                    spans.add(
                        Span.obtain(
                            column,
                            TextStyle.makeStyle(EditorColorScheme.FUNCTION_NAME)
                        )
                    )
                    prevIsTagName = false
                }

                Octal, Decimal, Real, Hex, Binary -> {
                    spans.add(
                        Span.obtain(
                            column,
                            TextStyle.makeStyle(EditorColorScheme.LITERAL)
                        )
                    )
                    prevIsTagName = false
                }

                BackQuoteString,
                SingleQuoteString,
                DoubleQuote,
                StringPart -> {
                    spans.add(
                        Span.obtain(
                            column,
                            TextStyle.makeStyle(EditorColorScheme.ATTRIBUTE_VALUE)
                        )
                    )
                    prevIsTagName = false
                }

                HtmlOpen, HtmlClose,
                HtmlSlash, HtmlSlashClose, HtmlEquals -> {
                    spans.add(
                        Span.obtain(
                            column,
                            TextStyle.makeStyle(EditorColorScheme.OPERATOR)
                        )
                    )
                    prevIsTagName = false
                }

                PHPStart, PHPEnd -> {
                    spans.add(
                        Span.obtain(
                            column,
                            TextStyle.makeStyle(EditorColorScheme.FUNCTION_NAME)
                        )
                    )
                    prevIsTagName = false
                }


                HtmlCommentOpen, CommentEnd,
                MultiLineComment, SingleLineComment,
                HtmlComment -> {
                    spans.add(
                        Span.obtain(
                            column,
                            TextStyle.makeStyle(EditorColorScheme.COMMENT)
                        )
                    )
                    prevIsTagName = false
                }

                HtmlName -> {
                    if (prevIsTagName) {
                        spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.ATTRIBUTE_NAME)))
                    } else {
                        spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.HTML_TAG)))
                    }
                    prevIsTagName = true
                }

                HtmlStartQuoteString, HtmlStartDoubleQuoteString,
                HtmlQuoteString, HtmlDoubleQuoteString,
                HtmlEndQuoteString, HtmlEndDoubleQuoteString -> {
                    spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.ATTRIBUTE_VALUE)))
                    prevIsTagName = false
                }

                else -> {
                    spans.add(Span.obtain(column, TextStyle.makeStyle(EditorColorScheme.TEXT_NORMAL)))
                    if (type != HtmlSpace && type != Whitespace) {
                        prevIsTagName = false
                    }
                }
            }
        }
        return spans
    }

    override fun tokenizeLine(
        line: CharSequence,
        state: PhpState,
        lineIndex: Int
    ): IncrementalAnalyzeManager.LineTokenizeResult<PhpState, Long> {
        val tokens = ArrayList<Long>()
        val stateObj = PhpState()
        var newState = STATE_NORMAL
        if (state.state == STATE_NORMAL) {
            newState = tokenizeNormal(line, 0, tokens)
        } else if (state.state == STATE_INCOMPLETE_HTML_COMMENT) {
            val result = tryFillHtmlIncompleteComment(line, tokens)
            newState = IntPair.getFirst(result)
            newState = if (newState == STATE_NORMAL) {
                tokenizeNormal(line, IntPair.getSecond(result), tokens)
            } else {
                STATE_INCOMPLETE_HTML_COMMENT
            }
        } else if (state.state == STATE_INCOMPLETE_PHP) {
            val result = tryFillPhpIncomplete(line, tokens)
            newState = IntPair.getFirst(result)
            newState = if (newState == STATE_NORMAL) {
                tokenizeNormal(line, IntPair.getSecond(result), tokens)
            } else {
                STATE_INCOMPLETE_PHP
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
            if (token.type == HtmlCommentOpen) {
                state = STATE_INCOMPLETE_HTML_COMMENT
                tokens.add(pack(INCOMPLETE, token.charPositionInLine + offset))
                break
            }
            if (token.type == PHPStart) {
                state = STATE_INCOMPLETE_PHP
                tokens.add(pack(INCOMPLETE, token.charPositionInLine + offset))
                break
            }
            tokens.add(pack(token.type, token.charPositionInLine + offset))
        }
        return state
    }

    private fun tryFillHtmlIncompleteComment(text: CharSequence, tokens: MutableList<Long>): Long {
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
        return pack(STATE_INCOMPLETE_HTML_COMMENT, 0)
    }

    private fun tryFillPhpIncomplete(text: CharSequence, tokens: MutableList<Long>): Long {
        val endIndex = text.indexOf("?>")
        if (endIndex < 0) {
            val tokenizer = obtainTokenizer()
            tokenizer.inputStream = CharStreams.fromString(text.toString())
            tokenizer.mode(PHP)

            var token: Token
            while (tokenizer.nextToken().also { token = it }.type != EOF) {
                tokens.add(pack(token.type, token.charPositionInLine))
            }

            tokens.add(pack(INCOMPLETE, 0))
            return pack(STATE_INCOMPLETE_PHP, text.length)
        } else {
            val substring = text.substring(0, endIndex)
            val tokenizer = obtainTokenizer()
            tokenizer.inputStream = CharStreams.fromString(substring)
            tokenizer.mode(PHP)

            var token: Token
            while (tokenizer.nextToken().also { token = it }.type != EOF) {
                tokens.add(pack(token.type, token.charPositionInLine))
            }

            tokens.add(pack(COMPLETE, 0))
            return pack(STATE_NORMAL, endIndex)
        }
    }

    override fun stateEquals(state: PhpState, another: PhpState): Boolean {
        return state == another
    }

}