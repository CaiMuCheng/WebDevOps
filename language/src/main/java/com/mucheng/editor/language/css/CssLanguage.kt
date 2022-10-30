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

package com.mucheng.editor.language.css

import androidx.annotation.Keep
import com.mucheng.editor.base.IAutoCompletionHelper
import com.mucheng.editor.base.lang.AbstractBasicLanguage
import com.mucheng.editor.data.AutoCompletionItem
import com.mucheng.editor.language.css.CssAutoCompletionHelper.Companion.SELECTOR
import com.mucheng.editor.language.css.CssAutoCompletionHelper.Companion.STYLE
import com.mucheng.editor.token.ThemeToken
import com.mucheng.editor.view.MuCodeEditor

@Suppress("SpellCheckingInspection")
@Keep
object CssLanguage : AbstractBasicLanguage() {

    private lateinit var editor: MuCodeEditor

    private val lexer = CssLexer(this)

    private val operatorTokenMap: Map<Char, ThemeToken> = hashMapOf(
        '+' to CssToken.PLUS,
        '*' to CssToken.MULTI,
        '/' to CssToken.DIV,
        ':' to CssToken.COLON,
        '!' to CssToken.NOT,
        '%' to CssToken.MOD,
        '^' to CssToken.XOR,
        '&' to CssToken.AND,
        '?' to CssToken.QUESTION,
        '~' to CssToken.COMP,
        '.' to CssToken.DOT,
        ',' to CssToken.COMMA,
        ';' to CssToken.SEMICOLON,
        '=' to CssToken.EQUALS,
        '(' to CssToken.LEFT_PARENTHESIS,
        ')' to CssToken.RIGHT_PARENTHESIS,
        '[' to CssToken.LEFT_BRACKET,
        ']' to CssToken.RIGHT_BRACKET,
        '{' to CssToken.LEFT_BRACE,
        '}' to CssToken.RIGHT_BRACE,
        '|' to CssToken.OR,
        '<' to CssToken.LESS_THAN,
        '>' to CssToken.MORE_THAN
    )

    private val autoCompletionHelper = CssAutoCompletionHelper()

    private val keywordTokenMap: Map<String, ThemeToken> = emptyMap()

    private val specialTokenMap: Map<String, ThemeToken> = emptyMap()

    private val autoCompletionItems: List<AutoCompletionItem> = createAutoCompletionItems()

    override fun getLexer(): CssLexer {
        return lexer
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

    @Suppress("NOTHING_TO_INLINE")
    private inline fun createAutoCompletionItems(): List<AutoCompletionItem> {
        return listOf(
            createSelector("head"),
            createSelector("body"),
            createSelector("a"),
            createSelector("abbr"),
            createSelector("acronym"),
            createSelector("address"),
            createSelector("applet"),
            createSelector("area"),
            createSelector("article"),
            createSelector("aside"),
            createSelector("audio"),
            createSelector("html"),
            createSelector("h1"),
            createSelector("h2"),
            createSelector("h3"),
            createSelector("h4"),
            createSelector("h5"),
            createSelector("h6"),
            createSelector("header"),
            createSelector("hr"),
            createSelector("i"),
            createSelector("iframe"),
            createSelector("img"),
            createSelector("input"),
            createSelector("ins"),
            createSelector("kbd"),
            createSelector("keygen"),
            createSelector("label"),
            createSelector("legend"),
            createSelector("li"),
            createSelector("link"),
            createSelector("meta"),
            createSelector("main"),
            createSelector("map"),
            createSelector("mark"),
            createSelector("menu"),
            createSelector("item"),
            createSelector("meter"),
            createSelector("nav"),
            createSelector("noframes"),
            createSelector("noscript"),
            createSelector("object"),
            createSelector("ol"),
            createSelector("optgroup"),
            createSelector("option"),
            createSelector("p"),
            createSelector("param"),
            createSelector("pre"),
            createSelector("progress"),
            createSelector("q"),
            createSelector("rq"),
            createSelector("rt"),
            createSelector("ruby"),
            createSelector("s"),
            createSelector("samp"),
            createSelector("script"),
            createSelector("section"),
            createSelector("select"),
            createSelector("small"),
            createSelector("source"),
            createSelector("span"),
            createSelector("strike"),
            createSelector("strong"),
            createSelector("sub"),
            createSelector("title"),
            createSelector("table"),
            createSelector("tbody"),
            createSelector("td"),
            createSelector("textarea"),
            createSelector("tfoot"),
            createSelector("th"),
            createSelector("thead"),
            createSelector("time"),
            createSelector("tr"),
            createSelector("track"),
            createSelector("tt"),
            createSelector("u"),
            createSelector("ul"),
            createSelector("var"),
            createSelector("video"),
            createSelector("wbr"),
            createSelector("b"),
            createSelector("base"),
            createSelector("basefont"),
            createSelector("bdi"),
            createSelector("bdo"),
            createSelector("big"),
            createSelector("blockquote"),
            createSelector("br"),
            createSelector("button"),
            createSelector("canvas"),
            createSelector("caption"),
            createSelector("center"),
            createSelector("cite"),
            createSelector("code"),
            createSelector("col"),
            createSelector("colgroup"),
            createSelector("command"),
            createSelector("datalist"),
            createSelector("dd"),
            createSelector("del"),
            createSelector("dfn"),
            createSelector("details"),
            createSelector("dialog"),
            createSelector("dir"),
            createSelector("div"),
            createSelector("dl"),
            createSelector("dt"),
            createSelector("em"),
            createSelector("embed"),
            createSelector("fieldset"),
            createSelector("figcaption"),
            createSelector("figure"),
            createSelector("font"),
            createSelector("footer"),
            createSelector("form"),
            createSelector("frame"),
            createSelector("frameset"),
            createSelector("summary"),
            createSelector("sup"),
            createSelector("style"),
            createStyle("background-color"),
            createStyle("color"),
            createStyle("font-family"),
            createStyle("font-size"),
            createStyle("text-align"),
            createStyle("top"),
            createStyle("left"),
            createStyle("bottom"),
            createStyle("right"),
            createStyle("margin"),
            createStyle("margin-top"),
            createStyle("margin-left"),
            createStyle("margin-bottom"),
            createStyle("margin-right"),
            createStyle("padding"),
            createStyle("padding-left"),
            createStyle("padding-right"),
            createStyle("padding-top"),
            createStyle("padding-bottom"),
            createStyle("display"),
            createStyle("width"),
            createStyle("height"),
            createStyle("position"),
            createStyle("visibility"),
            createStyle("overflow"),
            createStyle("float"),
            createStyle("clear"),
            createStyle("color"),
            createStyle("font-family"),
            createStyle("font-size"),
            createStyle("font-style"),
            createStyle("font-variant"),
            createStyle("letter-spacing"),
            createStyle("line-height"),
            createStyle("font-weight"),
            createStyle("vertical-align"),
            createStyle("text-decoration"),
            createStyle("text-transform"),
            createStyle("text-align"),
            createStyle("list-style-type"),
            createStyle("list-style-image"),
            createStyle("list-style-position"),
            createStyle("baclground-color"),
            createStyle("background"),
            createStyle("background-repeat"),
            createStyle("background-image"),
            createStyle("background-attachment"),
            createStyle("background-position"),
            createStyle("border-top"),
            createStyle("border-left"),
            createStyle("border-bottom"),
            createStyle("border-right"),
            createStyle("border-top-color"),
            createStyle("border-top-width"),
            createStyle("border-top-style")
        )
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun createSelector(
        name: String,
        insertedText: String = name
    ): AutoCompletionItem {
        return AutoCompletionItem(name, SELECTOR, insertedText)
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun createStyle(
        style: String,
        insertedText: String = "$style: "
    ): AutoCompletionItem {
        return AutoCompletionItem(style, STYLE, insertedText)
    }

}