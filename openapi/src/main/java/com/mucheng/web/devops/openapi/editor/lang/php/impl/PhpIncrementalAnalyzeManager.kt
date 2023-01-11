package com.mucheng.web.devops.openapi.editor.lang.php.impl

import android.util.Log
import com.mucheng.web.devops.openapi.editor.lang.php.PhpLexer
import com.mucheng.web.devops.openapi.editor.lang.php.PhpLexer.*
import com.mucheng.web.devops.openapi.editor.lang.php.impl.PhpState.Companion.STATE_INCOMPLETE_HTML_COMMENT
import com.mucheng.web.devops.openapi.editor.lang.php.impl.PhpState.Companion.STATE_INCOMPLETE_PHP
import com.mucheng.web.devops.openapi.editor.lang.php.impl.PhpState.Companion.STATE_INCOMPLETE_PHP_COMMENT
import com.mucheng.web.devops.openapi.editor.lang.php.impl.PhpState.Companion.STATE_NORMAL
import com.mucheng.web.devops.openapi.reader.CharSequenceReader
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

class PhpIncrementalAnalyzeManager : AsyncIncrementalAnalyzeManager<PhpState, Long>() {

    private val tokenizerProvider = ThreadLocal<PhpLexer>()

    private val identifiers = SyncIdentifiers()

    companion object {
        private fun pack(type: Int, column: Int): Long {
            return IntPair.pack(type, column)
        }

        private const val INCOMPLETE_HTML_COMMENT = 237

        private const val COMPLETE_HTML_COMMENT = 238

        private const val INCOMPLETE_PHP_COMMENT = 239

        private const val COMPLETE_PHP_COMMENT = 240

        private const val INCOMPLETE_PHP_OPEN = 241

        private const val INCOMPLETE_PHP_CLOSE = 242

    }

    @Synchronized
    private fun obtainTokenizer(): PhpLexer {
        var tokenizer = tokenizerProvider.get()
        if (tokenizer == null) {
            tokenizer = PhpLexer(CharStreams.fromString(""))
        }
        tokenizerProvider.set(tokenizer)
        return tokenizer
    }

    override fun getInitialState(): PhpState {
        return PhpState()
    }

    override fun stateEquals(state: PhpState, another: PhpState): Boolean {
        return state == another
    }

    override fun tokenizeLine(
        line: CharSequence,
        state: PhpState,
        lineIndex: Int
    ): IncrementalAnalyzeManager.LineTokenizeResult<PhpState, Long> {
        val tokens = ArrayList<Long>()
        val stateObj = PhpState()
        var newState = STATE_NORMAL

        when (state.state) {
            STATE_NORMAL -> {
                newState = tokenizeNormal(line, 0, tokens)
            }

            STATE_INCOMPLETE_HTML_COMMENT -> {
                newState = tokenizeHtmlComment(line, tokens)
            }

            STATE_INCOMPLETE_PHP -> {
                newState = tokenizePhp(line, 0, tokens)
            }

            STATE_INCOMPLETE_PHP_COMMENT -> {
                newState = tokenizePhpComment(line, tokens)
            }
        }

        stateObj.state = newState
        if (tokens.isEmpty()) {
            tokens.add(pack(EOF, 0))
        }
        return IncrementalAnalyzeManager.LineTokenizeResult(stateObj, tokens)
    }

    private fun tokenizeNormal(text: CharSequence, offset: Int, tokens: MutableList<Long>): Int {
        val charSequenceReader = CharSequenceReader(text)
        charSequenceReader.skip(offset.toLong())

        val tokenizer = obtainTokenizer()
        tokenizer.inputStream = CharStreams.fromReader(charSequenceReader)

        var token: Token
        var state = STATE_NORMAL
        while (tokenizer.nextToken().also { token = it }.type != EOF) {
            if (token.type == HtmlCommentOpen) {
                state = STATE_INCOMPLETE_HTML_COMMENT
                tokens.add(
                    pack(
                        INCOMPLETE_HTML_COMMENT,
                        token.charPositionInLine + offset
                    )
                )
                break
            }
            if (token.type == PHPStart) {
                tokens.add(
                    pack(
                        INCOMPLETE_PHP_OPEN,
                        token.charPositionInLine + offset
                    )
                )
                return tokenizePhp(text, token.stopIndex + 1 + offset, tokens)
            }
            tokens.add(pack(token.type, token.charPositionInLine + offset))
        }
        return state
    }

    private fun tokenizeHtmlComment(text: CharSequence, tokens: MutableList<Long>): Int {
        val state = STATE_INCOMPLETE_HTML_COMMENT
        val suffix = "-->"
        val endIndex = text.indexOf(suffix, 0)
        if (endIndex >= 0) {
            tokens.add(pack(INCOMPLETE_HTML_COMMENT, 0))
            return tokenizeNormal(text, endIndex + suffix.length, tokens)
        } else {
            tokens.add(pack(INCOMPLETE_HTML_COMMENT, 0))
        }
        return state
    }

    private fun tokenizePhp(text: CharSequence, offset: Int, tokens: MutableList<Long>): Int {
        var state = STATE_INCOMPLETE_PHP
        val suffix = "?>"
        val endIndex = text.indexOf(suffix, offset)
        if (endIndex >= 0) {
            val charSequenceReader = CharSequenceReader(text)
            charSequenceReader.skip(offset.toLong())

            val tokenizer = obtainTokenizer()
            tokenizer.inputStream = CharStreams.fromReader(charSequenceReader)
            tokenizer.mode(PHP)

            var token: Token
            while (tokenizer.nextToken().also { token = it }.type != EOF) {
                if (token.type == MultiLineCommentOpen) {
                    tokens.add(
                        pack(
                            INCOMPLETE_PHP_COMMENT,
                            token.charPositionInLine + offset
                        )
                    )
                    break
                }
                Log.e("Content", """
                    type: ${token.type}
                    text: $text
                """.trimIndent())
                tokens.add(pack(token.type, token.charPositionInLine + offset))
            }

            tokens.add(pack(INCOMPLETE_PHP_CLOSE, endIndex))
            return tokenizeNormal(text, endIndex + suffix.length, tokens)
        } else {
            val charSequenceReader = CharSequenceReader(text)
            charSequenceReader.skip(offset.toLong())

            val tokenizer = obtainTokenizer()
            tokenizer.inputStream = CharStreams.fromReader(charSequenceReader)
            tokenizer.mode(PHP)

            var token: Token
            while (tokenizer.nextToken().also { token = it }.type != EOF) {
                if (token.type == MultiLineCommentOpen) {
                    state = STATE_INCOMPLETE_PHP_COMMENT
                    tokens.add(
                        pack(
                            INCOMPLETE_PHP_COMMENT,
                            token.charPositionInLine + offset
                        )
                    )
                    break
                }
                tokens.add(pack(token.type, token.charPositionInLine + offset))
            }
        }
        return state
    }

    private fun tokenizePhpComment(text: CharSequence, tokens: MutableList<Long>): Int {
        val state = STATE_INCOMPLETE_PHP_COMMENT
        val suffix = "*/"
        val endIndex = text.indexOf(suffix, 0)
        if (endIndex >= 0) {
            tokens.add(pack(INCOMPLETE_PHP_COMMENT, 0))
            return tokenizePhp(text, endIndex + suffix.length, tokens)
        } else {
            tokens.add(pack(INCOMPLETE_PHP_COMMENT, 0))
        }
        return state
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

                XmlStart, XML, XmlClose, HtmlDtd, INCOMPLETE_PHP_OPEN, INCOMPLETE_PHP_CLOSE -> {
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
                MultiLineComment, HtmlComment,
                INCOMPLETE_HTML_COMMENT, INCOMPLETE_PHP_COMMENT,
                SingleLineComment, ShellStyleComment,
                Comment, Shebang-> {
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

    override fun computeBlocks(text: Content?, delegate: CodeBlockAnalyzeDelegate?): MutableList<CodeBlock> {
        return ArrayList(0)
    }
}