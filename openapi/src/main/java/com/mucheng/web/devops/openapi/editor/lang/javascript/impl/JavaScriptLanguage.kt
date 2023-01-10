package com.mucheng.web.devops.openapi.editor.lang.javascript.impl

import android.os.Bundle
import com.mucheng.web.devops.openapi.editor.lang.javascript.JavaScriptLexer
import com.mucheng.web.devops.openapi.editor.lang.javascript.JavaScriptParser.*
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
class JavaScriptLanguage : Language {

    private val autoComplete: IdentifierAutoComplete

    private val manager: JavaScriptIncrementalAnalyzeManager

    companion object {
        private val FORIN_SNIPPET = CodeSnippetParser.parse(
            "for (const \${1:key} in object) {\n" +
                    "    if (Object.hasOwnProperty.call(object, $1)) {\n" +
                    "        const element = object[$1];\n" +
                    "        $0\n" +
                    "    }\n" +
                    "}"
        )

        private val FOROF_SNIPPET = CodeSnippetParser.parse(
            "for (const \${1:iterator} of object) {\n" +
                    "    $0\n" +
                    "}"
        )

        private val FOREACH_SNIPPET = CodeSnippetParser.parse(
            "array.forEach(\${1:element} => {\n" +
                    "    $0\n" +
                    "});"
        )
    }

    init {
        autoComplete = IdentifierAutoComplete(
            arrayOf(
                "break",
                "do",
                "instanceof",
                "typeof",
                "case",
                "else",
                "new",
                "var",
                "catch",
                "finally",
                "return",
                "void",
                "continue",
                "for",
                "switch",
                "while",
                "debugger",
                "function",
                "this",
                "with",
                "default",
                "if",
                "throw",
                "delete",
                "in",
                "try",
                "as",
                "from",
                "class",
                "enum",
                "extends",
                "super",
                "const",
                "export",
                "import",
                "async",
                "await",
                "yield",
                "implements",
                "let",
                "private",
                "public",
                "interface",
                "package",
                "protected",
                "static"
            )
        )
        manager = JavaScriptIncrementalAnalyzeManager()
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
            if ("forin".startsWith(prefix)) {
                publisher.addItem(
                    SimpleSnippetCompletionItem(
                        "forin",
                        "Snippet - For in loop",
                        SnippetDescription(prefix.length, FORIN_SNIPPET, true)
                    )
                )
            }
            if ("forof".startsWith(prefix)) {
                publisher.addItem(
                    SimpleSnippetCompletionItem(
                        "forof",
                        "Snippet - For of loop",
                        SnippetDescription(prefix.length, FOROF_SNIPPET, true)
                    )
                )
            }
            if ("foreach".startsWith(prefix)) {
                publisher.addItem(
                    SimpleSnippetCompletionItem(
                        "foreach",
                        "Snippet - For each loop",
                        SnippetDescription(prefix.length, FOREACH_SNIPPET, true)
                    )
                )
            }
        }
        autoComplete.requireAutoComplete(content, position, prefix, publisher, SyncIdentifiers())
    }


    override fun getIndentAdvance(content: ContentReference, line: Int, column: Int): Int {
        val str = content.getLine(line).substring(0, column)
        val tokenizer = JavaScriptLexer(CharStreams.fromString(str))
        var token: Token
        var advance = 0
        if (str.trim().isEmpty()) {
            --advance
        } else {
            while (tokenizer.nextToken().also { token = it }.type != JavaScriptLexer.EOF) {
                val type = token.type
                if (type == JavaScriptLexer.OpenBracket || type == JavaScriptLexer.OpenBrace || type == JavaScriptLexer.OpenParen) {
                    ++advance
                }
                if (type == JavaScriptLexer.CloseBracket || type == JavaScriptLexer.CloseBrace || type == JavaScriptLexer.CloseParen) {
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