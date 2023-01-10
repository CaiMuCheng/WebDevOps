package com.mucheng.web.devops.openapi.editor.lang.json.impl

import android.os.Bundle
import com.mucheng.web.devops.openapi.editor.lang.javascript.JavaScriptParser.*
import com.mucheng.web.devops.openapi.editor.lang.json.JSONLexer
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
class JsonLanguage : Language {

    private val autoComplete: IdentifierAutoComplete

    private val manager: JsonIncrementalAnalyzeManager

    init {
        autoComplete = IdentifierAutoComplete()
        manager = JsonIncrementalAnalyzeManager()
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
        val tokenizer = JSONLexer(str)
        var type: Int
        var advance = 0
        if (str.trim().isEmpty()) {
            --advance
        } else {
            while (tokenizer.nextToken().also { type = it } != JSONLexer.EOF) {
                if (type == JSONLexer.BLOCK_OPEN) {
                    ++advance
                }
                if (type == JSONLexer.BLOCK_CLOSE) {
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