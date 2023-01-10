package com.mucheng.web.devops.openapi.editor.lang.php.impl

import android.os.Bundle
import com.mucheng.web.devops.openapi.editor.lang.javascript.JavaScriptParser.*
import com.mucheng.web.devops.openapi.editor.lang.php.PhpLexer
import io.github.rosemoe.sora.lang.EmptyLanguage
import io.github.rosemoe.sora.lang.Language
import io.github.rosemoe.sora.lang.analysis.AnalyzeManager
import io.github.rosemoe.sora.lang.completion.*
import io.github.rosemoe.sora.lang.completion.IdentifierAutoComplete.SyncIdentifiers
import io.github.rosemoe.sora.lang.completion.snippet.parser.CodeSnippetParser
import io.github.rosemoe.sora.lang.format.Formatter
import io.github.rosemoe.sora.lang.smartEnter.NewlineHandler
import io.github.rosemoe.sora.text.CharPosition
import io.github.rosemoe.sora.text.ContentReference
import io.github.rosemoe.sora.util.MyCharacter
import io.github.rosemoe.sora.widget.SymbolPairMatch
import org.antlr.v4.runtime.*
import java.util.*

@Suppress("JoinDeclarationAndAssignment")
class PhpLanguage : Language {

    private val autoComplete: IdentifierAutoComplete

    private val manager: PhpIncrementalAnalyzeManager

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

        private val PHP_BLOCK_SNIPPET = CodeSnippetParser.parse(
            """
            |<?php
            |    $0
            |?>
        """.trimMargin()
        )
    }

    init {
        autoComplete = IdentifierAutoComplete(
            arrayOf(
                "abstract",
                "array",
                "as",
                "binary",
                "break",
                "callable",
                "case",
                "catch",
                "class",
                "clone",
                "const",
                "continue",
                "declare",
                "default",
                "do",
                "real",
                "double",
                "echo",
                "else",
                "elseif",
                "empty",
                "enddeclare",
                "endfor",
                "endforeach",
                "endif",
                "endswitch",
                "endwhile",
                "eval",
                "die",
                "extends",
                "final",
                "finally",
                "float",
                "for",
                "foreach",
                "function",
                "global",
                "goto",
                "if",
                "implements",
                "import",
                "include",
                "include_once",
                "instanceof",
                "insteadof",
                "int8",
                "int16",
                "int64",
                "int",
                "interface",
                "isset",
                "list",
                "and",
                "or",
                "xor",
                "match",
                "namespace",
                "new",
                "null",
                "object",
                "parent",
                "partial",
                "print",
                "private",
                "protected",
                "public",
                "require",
                "require_once",
                "resource",
                "return",
                "static",
                "string",
                "switch",
                "throw",
                "trait",
                "try",
                "clrtypeof",
                "unicode",
                "unset",
                "use",
                "var",
                "while",
                "yield",
                "from",
                "fn"
            )
        )
        manager = PhpIncrementalAnalyzeManager()
    }

    override fun getAnalyzeManager(): AnalyzeManager {
        return manager
    }

    override fun getInterruptionLevel(): Int {
        return Language.INTERRUPTION_LEVEL_STRONG
    }

    override fun requireAutoComplete(
        content: ContentReference,
        position: CharPosition,
        publisher: CompletionPublisher,
        extraArguments: Bundle
    ) {
        val prefix = CompletionHelper.computePrefix(content, position, MyCharacter::isJavaIdentifierPart)
        if (prefix != "") {
            if ("true".startsWith(prefix)) {
                publisher.addItem(
                    SimpleCompletionItem(
                        "true", "The true constant", prefix.length, "true"
                    )
                )
            }
            if ("false".startsWith(prefix)) {
                publisher.addItem(
                    SimpleCompletionItem(
                        "false", "The false constant", prefix.length, "false"
                    )
                )
            }
            if ("null".startsWith(prefix)) {
                publisher.addItem(
                    SimpleCompletionItem(
                        "null", "The null constant", prefix.length, "null"
                    )
                )
            }
            if ("doc".startsWith(prefix)) {
                publisher.addItem(
                    SimpleSnippetCompletionItem(
                        "doc",
                        "Snippet - Generate document tree",
                        SnippetDescription(prefix.length, DOCUMENT_SNIPPET, true)
                    )
                )
            }
            if ("php".startsWith(prefix)) {
                publisher.addItem(
                    SimpleSnippetCompletionItem(
                        "php",
                        "Snippet - Generate php block",
                        SnippetDescription(prefix.length, PHP_BLOCK_SNIPPET, true)
                    )
                )
            }
        }
        autoComplete.requireAutoComplete(content, position, prefix, publisher, SyncIdentifiers())
    }


    override fun getIndentAdvance(content: ContentReference, line: Int, column: Int): Int {
        val str = content.getLine(line).substring(0, column)
        val tokenizer = PhpLexer(CharStreams.fromString(str))
        tokenizer.mode(PhpLexer.PHP)
        var token: Token
        var advance = 0
        if (str.trim().isEmpty()) {
            --advance
        } else {
            while (tokenizer.nextToken().also { token = it }.type != PhpLexer.EOF) {
                val type = token.type
                if (type == PhpLexer.OpenCurlyBracket) {
                    ++advance
                }
                if (type == PhpLexer.CloseCurlyBracket) {
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