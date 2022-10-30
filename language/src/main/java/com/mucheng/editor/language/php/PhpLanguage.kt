package com.mucheng.editor.language.php

import androidx.annotation.Keep
import com.mucheng.editor.base.IAutoCompletionHelper
import com.mucheng.editor.data.AutoCompletionItem
import com.mucheng.editor.language.html.HtmlAutoCompletionHelper
import com.mucheng.editor.language.html.HtmlLanguage

@Keep
@Suppress("LeakingThis")
open class PhpLanguage : HtmlLanguage() {

    companion object {

        @Volatile
        @JvmStatic
        private var instance: PhpLanguage? = null

        @JvmStatic
        fun getInstance(): HtmlLanguage {
            if (instance == null) {
                synchronized(PhpLanguage::class.java) {
                    if (instance == null) {
                        instance = PhpLanguage()
                    }
                }
            }
            return instance!!
        }

    }

    private val constAutoCompletionItems: MutableList<AutoCompletionItem> = ArrayList()

    private val phpAutoCompletionHelper =
        PhpAutoCompletionHelper(super.getAutoCompletionHelper() as HtmlAutoCompletionHelper)

    init {
        constAutoCompletionItems.addAll(super.getConstAutoCompletionItems())
        constAutoCompletionItems.add(
            AutoCompletionItem(
                "php", HtmlAutoCompletionHelper.CODE_BLOCK, "<?php ?>"
            )
        )
    }

    override fun getConstAutoCompletionItems(): List<AutoCompletionItem> {
        return constAutoCompletionItems
    }

    override fun getAutoCompletionHelper(): IAutoCompletionHelper {
        return phpAutoCompletionHelper
    }

    private val phpLexer = PhpLexer(this)

    override fun getLexer(): PhpLexer {
        return phpLexer
    }

}