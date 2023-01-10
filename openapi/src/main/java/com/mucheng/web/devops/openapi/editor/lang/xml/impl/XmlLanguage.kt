package com.mucheng.web.devops.openapi.editor.lang.xml.impl

import android.os.Bundle
import com.mucheng.web.devops.openapi.editor.lang.html.HTMLLexer
import com.mucheng.web.devops.openapi.editor.lang.javascript.JavaScriptParser.*
import com.mucheng.web.devops.openapi.editor.lang.xml.XMLLexer
import io.github.rosemoe.sora.lang.EmptyLanguage
import io.github.rosemoe.sora.lang.Language
import io.github.rosemoe.sora.lang.analysis.AnalyzeManager
import io.github.rosemoe.sora.lang.completion.*
import io.github.rosemoe.sora.lang.format.Formatter
import io.github.rosemoe.sora.lang.smartEnter.NewlineHandler
import io.github.rosemoe.sora.text.CharPosition
import io.github.rosemoe.sora.text.ContentReference
import io.github.rosemoe.sora.widget.SymbolPairMatch
import org.antlr.v4.runtime.*
import java.util.*

@Suppress("JoinDeclarationAndAssignment")
class XmlLanguage : Language {

    private val autoComplete: IdentifierAutoComplete

    private val manager: XmlIncrementalAnalyzeManager

    init {
        autoComplete = IdentifierAutoComplete()
        manager = XmlIncrementalAnalyzeManager()
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
    }


    override fun getIndentAdvance(content: ContentReference, line: Int, column: Int): Int {
        val str = content.getLine(line).substring(0, column)
        val tokenizer = XMLLexer(CharStreams.fromString(str))
        var token: Token
        var advance = 0
        if (str.trim().isEmpty()) {
            --advance
        } else {
            var prevToken: Token? = null
            while (tokenizer.nextToken().also { token = it }.type != HTMLLexer.EOF) {
                val type = token.type
                if (prevToken != null) {
                    if (prevToken.type == XMLLexer.Name && type == XMLLexer.CLOSE) {
                        ++advance
                    }
                    if (prevToken.type == XMLLexer.Name && type == XMLLexer.SLASH_CLOSE) {
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