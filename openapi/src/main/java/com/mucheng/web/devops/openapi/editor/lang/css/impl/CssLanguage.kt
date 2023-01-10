package com.mucheng.web.devops.openapi.editor.lang.css.impl

import android.os.Bundle
import com.mucheng.web.devops.openapi.editor.lang.css.css3Lexer
import com.mucheng.web.devops.openapi.editor.lang.html.HTMLLexer
import io.github.rosemoe.sora.lang.EmptyLanguage
import io.github.rosemoe.sora.lang.Language
import io.github.rosemoe.sora.lang.Language.INTERRUPTION_LEVEL_STRONG
import io.github.rosemoe.sora.lang.analysis.AnalyzeManager
import io.github.rosemoe.sora.lang.completion.*
import io.github.rosemoe.sora.lang.completion.snippet.CodeSnippet
import io.github.rosemoe.sora.lang.completion.snippet.parser.CodeSnippetParser
import io.github.rosemoe.sora.lang.format.Formatter
import io.github.rosemoe.sora.lang.smartEnter.NewlineHandler
import io.github.rosemoe.sora.text.CharPosition
import io.github.rosemoe.sora.text.ContentReference
import io.github.rosemoe.sora.util.MyCharacter
import io.github.rosemoe.sora.widget.SymbolPairMatch
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.Token


@Suppress("JoinDeclarationAndAssignment")
class CssLanguage : Language {

    private val autoComplete: IdentifierAutoComplete

    private val manager: CssIncrementalAnalyzeManager

    companion object {

        private val snippetMap = mapOf(
            "left" to createSnippet("The element left", "left: $0;"),
            "top" to createSnippet("The element top", "top: $0;"),
            "right" to createSnippet("The element right", "right: $0;"),
            "bottom" to createSnippet("The element bottom", "bottom: $0;"),
        )

        private fun createSnippet(description: String, snippet: String): Array<Any> {
            return arrayOf(description, snippet.parseSnippet())
        }

        private fun String.parseSnippet(): CodeSnippet {
            return CodeSnippetParser.parse(this)
        }
    }

    init {
        autoComplete = IdentifierAutoComplete(emptyArray())
        manager = CssIncrementalAnalyzeManager()
    }

    override fun getAnalyzeManager(): AnalyzeManager {
        return manager
    }

    override fun getInterruptionLevel(): Int {
        return INTERRUPTION_LEVEL_STRONG
    }

    override fun requireAutoComplete(
        content: ContentReference,
        position: CharPosition,
        publisher: CompletionPublisher,
        extraArguments: Bundle
    ) {
        val prefix = CompletionHelper.computePrefix(content, position, MyCharacter::isJavaIdentifierPart)
        if (prefix != "") {
            snippetMap.keys.forEach {
                if (it.startsWith(prefix)) {
                    val value = snippetMap[it]
                    val description = value!![0] as String
                    val snippet = value[1] as CodeSnippet
                    publisher.addItem(SimpleSnippetCompletionItem(it, description, SnippetDescription(
                        prefix.length, snippet, true
                    )
                    ))
                }
            }
        }
        autoComplete.requireAutoComplete(content, position, prefix, publisher, manager.identifiers)
    }


    override fun getIndentAdvance(content: ContentReference, line: Int, column: Int): Int {
        val str = content.getLine(line).substring(0, column)
        val tokenizer = css3Lexer(CharStreams.fromString(str))
        var token: Token
        var advance = 0
        if (str.trim().isEmpty()) {
            --advance
        } else {
            while (tokenizer.nextToken().also { token = it }.type != HTMLLexer.EOF) {
                val type = token.type
                if (type == css3Lexer.T__4) {
                    ++advance
                }
                if (type == css3Lexer.T__5) {
                    --advance
                }
            }
        }
        return advance * 4
    }

    override fun useTab(): Boolean {
        return true
    }

    override fun getFormatter(): Formatter {
        return EmptyLanguage.EmptyFormatter.INSTANCE
    }

    override fun getSymbolPairs(): SymbolPairMatch {
        return SymbolPairMatch.DefaultSymbolPairs()
    }

    override fun getNewlineHandlers(): Array<NewlineHandler>? {
        return null
    }

    override fun destroy() {}

}