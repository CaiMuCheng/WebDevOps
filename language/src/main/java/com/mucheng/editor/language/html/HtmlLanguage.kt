/*
 * CN:
 * 作者：SuMuCheng
 * 我的 QQ: 3578557729
 * Github 主页：https://github.com/CaiMuCheng
 * 项目主页: https://github.com/CaiMuCheng/MuCodeEditor
 *
 * 你可以免费使用、商用以下代码，也可以基于以下代码做出修改，但是必须在你的项目中标注出处
 * 例如：在你 APP 的设置中添加 “关于编辑器” 一栏，其中标注作者以及此编辑器的 Github 主页
 *
 * 此代码使用 MPL 2.0 开源许可证，你必须标注作者信息
 * 若你要修改文件，请勿删除此注释
 * 若你违反以上条例我们有权向您提起诉讼!
 *
 * EN:
 * Author: SuMuCheng
 * My QQ-Number: 3578557729
 * Github Homepage: https://github.com/CaiMuCheng
 * Project Homepage: https://github.com/CaiMuCheng/MuCodeEditor
 *
 * You can use the following code for free, commercial use, or make modifications based on the following code, but you must mark the source in your project.
 * For example: add an "About Editor" column in your app's settings, which identifies the author and the Github home page of this editor.
 *
 * This code uses the MPL 2.0 open source license, you must mark the author information.
 * Do not delete this comment if you want to modify the file.
 * If you violate the above regulations we have the right to sue you!
 */

package com.mucheng.editor.language.html

import androidx.annotation.Keep
import com.mucheng.editor.base.IAutoCompletionHelper
import com.mucheng.editor.base.lang.AbstractBasicLanguage
import com.mucheng.editor.base.lexer.AbstractLexer
import com.mucheng.editor.data.AutoCompletionItem
import com.mucheng.editor.token.ThemeToken
import com.mucheng.editor.view.MuCodeEditor

@Keep
@Suppress("LeakingThis")
open class HtmlLanguage : AbstractBasicLanguage() {

    companion object {

        @Volatile
        @JvmStatic
        private var instance: HtmlLanguage? = null

        @JvmStatic
        fun getInstance(): HtmlLanguage {
            if (instance == null) {
                synchronized(HtmlLanguage::class.java) {
                    if (instance == null) {
                        instance = HtmlLanguage()
                    }
                }
            }
            return instance!!
        }

    }

    private val htmlLexer = HtmlLexer(this)

    private lateinit var editor: MuCodeEditor

    private val operatorTokenMap = hashMapOf(
        '+' to HtmlToken.PLUS,
        '*' to HtmlToken.MULTI,
        '/' to HtmlToken.DIV,
        ':' to HtmlToken.COLON,
        '!' to HtmlToken.NOT,
        '%' to HtmlToken.MOD,
        '^' to HtmlToken.XOR,
        '&' to HtmlToken.AND,
        '?' to HtmlToken.QUESTION,
        '~' to HtmlToken.COMP,
        '.' to HtmlToken.DOT,
        ',' to HtmlToken.COMMA,
        ';' to HtmlToken.SEMICOLON,
        '=' to HtmlToken.EQUALS,
        '(' to HtmlToken.LEFT_PARENTHESIS,
        ')' to HtmlToken.RIGHT_PARENTHESIS,
        '[' to HtmlToken.LEFT_BRACKET,
        ']' to HtmlToken.RIGHT_BRACKET,
        '{' to HtmlToken.LEFT_BRACE,
        '}' to HtmlToken.RIGHT_BRACE,
        '|' to HtmlToken.OR,
        '<' to HtmlToken.LESS_THAN,
        '>' to HtmlToken.MORE_THAN
    )

    private val autoCompletionHelper = HtmlAutoCompletionHelper()

    private val keywordTokenMap: Map<String, ThemeToken> = emptyMap()

    private val specialTokenMap: Map<String, ThemeToken> = emptyMap()

    private val autoCompletionItems: List<AutoCompletionItem> =
        createCustomAutoCompletionItems()

    override fun getLexer(): AbstractLexer? {
        return htmlLexer
    }

    override fun doSpan(): Boolean {
        return true
    }

    override fun setEditor(editor: MuCodeEditor) {
        this.editor = editor
    }

    override fun getEditor(): MuCodeEditor {
        return editor
    }

    override fun getOperatorTokenMap(): Map<Char, ThemeToken> {
        return operatorTokenMap
    }

    override fun getKeywordTokenMap(): Map<String, ThemeToken> {
        return keywordTokenMap
    }

    override fun getSpecialTokenMap(): Map<String, ThemeToken> {
        return specialTokenMap
    }

    override fun getAutoCompletionHelper(): IAutoCompletionHelper {
        return autoCompletionHelper
    }

    override fun getConstAutoCompletionItems(): List<AutoCompletionItem> {
        return autoCompletionItems
    }

    @Suppress("NOTHING_TO_INLINE", "SpellCheckingInspection")
    private inline fun createCustomAutoCompletionItems(): MutableList<AutoCompletionItem> {
        return mutableListOf(
            createElement("DOCTYPE", "<!DOCTYPE html>"),
            createElement("doctype", "<!doctype html>"),
            createElement("a"),
            createElement("abbr"),
            createElement("acronym"),
            createElement("address"),
            createElement("applet"),
            createElement("area"),
            createElement("article"),
            createElement("aside"),
            createElement("audio"),
            createElement("html"),
            createElement("head"),
            createElement("body"),
            createElement("h1"),
            createElement("h2"),
            createElement("h3"),
            createElement("h4"),
            createElement("h5"),
            createElement("h6"),
            createElement("header"),
            createElement("hr", "<hr/>"),
            createElement("i"),
            createElement("iframe"),
            createElement("img"),
            createElement("input"),
            createElement("ins"),
            createElement("kbd"),
            createElement("keygen"),
            createElement("label"),
            createElement("legend"),
            createElement("li"),
            createElement("link"),
            createElement("meta"),
            createElement("main"),
            createElement("map"),
            createElement("mark"),
            createElement("menu"),
            createElement("item"),
            createElement("meter"),
            createElement("nav"),
            createElement("noframes"),
            createElement("noscript"),
            createElement("object"),
            createElement("ol"),
            createElement("optgroup"),
            createElement("option"),
            createElement("p"),
            createElement("param"),
            createElement("pre"),
            createElement("progress"),
            createElement("q"),
            createElement("rq"),
            createElement("rt"),
            createElement("ruby"),
            createElement("s"),
            createElement("samp"),
            AutoCompletionItem("script", HtmlAutoCompletionHelper.CODE_BLOCK, "<script></script>"),
            createElement("section"),
            createElement("select"),
            createElement("small"),
            createElement("source"),
            createElement("span"),
            createElement("strike"),
            createElement("strong"),
            createElement("sub"),
            createElement("title"),
            createElement("table"),
            createElement("tbody"),
            createElement("td"),
            createElement("textarea"),
            createElement("tfoot"),
            createElement("th"),
            createElement("thead"),
            createElement("time"),
            createElement("tr"),
            createElement("track"),
            createElement("tt"),
            createElement("u"),
            createElement("ul"),
            createElement("var"),
            createElement("video"),
            createElement("wbr"),
            createElement("b"),
            createElement("base"),
            createElement("basefont"),
            createElement("bdi"),
            createElement("bdo"),
            createElement("big"),
            createElement("blockquote"),
            createElement("br", "<br/>"),
            createElement("button"),
            createElement("canvas"),
            createElement("caption"),
            createElement("center"),
            createElement("cite"),
            createElement("code"),
            createElement("col"),
            createElement("colgroup"),
            createElement("command"),
            createElement("datalist"),
            createElement("dd"),
            createElement("del"),
            createElement("dfn"),
            createElement("details"),
            createElement("dialog"),
            createElement("dir"),
            createElement("div"),
            createElement("dl"),
            createElement("dt"),
            createElement("em"),
            createElement("embed"),
            createElement("fieldset"),
            createElement("figcaption"),
            createElement("figure"),
            createElement("font"),
            createElement("footer"),
            createElement("form"),
            createElement("frame"),
            createElement("frameset"),
            createElement("summary"),
            createElement("sup"),
            AutoCompletionItem("style", HtmlAutoCompletionHelper.CODE_BLOCK, "<style></style>"),
            createAttr("class"),
            createAttr("accesskey"),
            createAttr("dir"),
            createAttr("id"),
            createAttr("lang"),
            createAttr("style"),
            createAttr("tabindex"),
            createAttr("charset"),
            createAttr("content"),
            createAttr("href"),
            createAttr("hreflang"),
            createAttr("rel"),
            createAttr("rev"),
            createAttr("target"),
            createAttr("code"),
            createAttr("object"),
            createAttr("align"),
            createAttr("alt"),
            createAttr("archive"),
            createAttr("codebase"),
            createAttr("height"),
            createAttr("hspace"),
            createAttr("onclick"),
            createAttr("name"),
            createAttr("width"),
            createAttr("type"),
            createAttr("value"),
            createAttr("span"),
            createAttr("accept-charset"),
            createAttr("enctype"),
            createAttr("method"),
            createAttr("src"),
            createAttr("maxlength"),
            createQuickGen(
                "doc", """
                |<!DOCTYPE html>
                |<html lang="zh">
                |<head>
                |	<meta charset="UTF-8">
                |	<meta http-equiv="X-UA-Compatible" content="IE=edge">
                |	<meta name="viewport" content="width=device-width, initial-scale=1.0">
                |	<title>Document</title>
                |</head>
                |<body>
                |	
                |</body>
                |</html>
            """.trimMargin()
            )
        )
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun createElement(
        name: String,
        insertedText: String = "<$name></$name>"
    ): AutoCompletionItem {
        return AutoCompletionItem(name, HtmlAutoCompletionHelper.ELEMENT, insertedText)
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun createAttr(
        name: String,
        insertedText: String = "$name=\"\""
    ): AutoCompletionItem {
        return AutoCompletionItem(name, HtmlAutoCompletionHelper.ATTRIBUTE, insertedText)
    }

    @Suppress("NOTHING_TO_INLINE", "SameParameterValue")
    private inline fun createQuickGen(name: String, insertedText: String): AutoCompletionItem {
        return AutoCompletionItem(name, HtmlAutoCompletionHelper.QUICK_GEN, insertedText)
    }

}