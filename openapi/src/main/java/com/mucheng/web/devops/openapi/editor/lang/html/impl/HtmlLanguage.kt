package com.mucheng.web.devops.openapi.editor.lang.html.impl

import android.os.Bundle
import com.mucheng.web.devops.openapi.editor.lang.html.HTMLLexer
import io.github.rosemoe.sora.lang.EmptyLanguage
import io.github.rosemoe.sora.lang.Language
import io.github.rosemoe.sora.lang.Language.INTERRUPTION_LEVEL_STRONG
import io.github.rosemoe.sora.lang.analysis.AnalyzeManager
import io.github.rosemoe.sora.lang.completion.*
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
class HtmlLanguage : Language {

    companion object {
        private val DOCUMENT_SNIPPET = CodeSnippetParser.parse(
            """
            |<!DOCTYPE html>
            |<html lang="en">
            |<head>
            |    <meta charset="UTF-8" />
            |    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
            |    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
            |    <title>Document</title>
            |</head>
            |<body>
            |    $0
            |</body>
            |</html>
        """.trimMargin()
        )
    }

    private val autoComplete: IdentifierAutoComplete

    private val manager: HtmlIncrementalAnalyzeManager

    init {
        autoComplete = IdentifierAutoComplete(emptyArray())
        manager = HtmlIncrementalAnalyzeManager()
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
            if ("doc".startsWith(prefix)) {
                publisher.addItem(
                    SimpleSnippetCompletionItem(
                        "doc",
                        "Snippet - Generate document tree",
                        SnippetDescription(prefix.length, DOCUMENT_SNIPPET, true)
                    )
                )
            }
        }
        autoComplete.requireAutoComplete(content, position, prefix, publisher, manager.identifiers)
    }


    override fun getIndentAdvance(content: ContentReference, line: Int, column: Int): Int {
        val str = content.getLine(line).substring(0, column)
        val tokenizer = HTMLLexer(CharStreams.fromString(str))
        var token: Token
        var advance = 0
        if (str.trim().isEmpty()) {
            --advance
        } else {
            var prevToken: Token? = null
            while (tokenizer.nextToken().also { token = it }.type != HTMLLexer.EOF) {
                val type = token.type
                if (prevToken != null) {
                    if (prevToken.type == HTMLLexer.TAG_NAME && type == HTMLLexer.TAG_CLOSE) {
                        ++advance
                    }
                    if (prevToken.type == HTMLLexer.TAG_NAME && type == HTMLLexer.TAG_SLASH_CLOSE) {
                        --advance
                    }
                }
                prevToken = token
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