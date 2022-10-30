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

package com.mucheng.editor.base.lang

import com.mucheng.editor.base.IAutoCompletionHelper
import com.mucheng.editor.base.lexer.AbstractLexer
import com.mucheng.editor.data.AutoCompletionItem
import com.mucheng.editor.token.ThemeToken
import com.mucheng.editor.view.MuCodeEditor

abstract class AbstractLanguage {

    abstract fun getLexer(): AbstractLexer?

    abstract fun doSpan(): Boolean

    abstract fun setEditor(editor: MuCodeEditor)

    abstract fun getEditor(): MuCodeEditor

    abstract fun isWhitespace(char: Char?): Boolean

    abstract fun isLetter(char: Char?): Boolean

    abstract fun isDigit(char: Char?): Boolean

    abstract fun isOperator(char: Char?): Boolean

    abstract fun isKeyword(text: String): Boolean

    abstract fun isSpecial(text: String): Boolean

    abstract fun getOperatorTokenMap(): Map<Char, ThemeToken>

    abstract fun getKeywordTokenMap(): Map<String, ThemeToken>

    abstract fun getSpecialTokenMap(): Map<String, ThemeToken>

    abstract fun getConstAutoCompletionItems(): List<AutoCompletionItem>

    abstract fun addConstAutoCompletionItem(autoCompletionItem: AutoCompletionItem)

    abstract fun addAllConstAutoCompletionItem(autoCompletionItems: List<AutoCompletionItem>)

    abstract fun removeConstAutoCompletionItem(autoCompletionItem: AutoCompletionItem)

    abstract fun removeAllConstAutoCompletionItem(autoCompletionItems: List<AutoCompletionItem>)

    abstract fun clearConstAutoCompletionItems()

    abstract fun getVariableAutoCompletionItems(): List<AutoCompletionItem>

    abstract fun addVariableAutoCompletionItem(autoCompletionItem: AutoCompletionItem)

    abstract fun addAllVariableAutoCompletionItem(autoCompletionItems: List<AutoCompletionItem>)

    abstract fun removeVariableAutoCompletionItem(autoCompletionItem: AutoCompletionItem)

    abstract fun removeAllVariableAutoCompletionItem(autoCompletionItems: List<AutoCompletionItem>)

    abstract fun clearVariableAutoCompletionItems()

    abstract fun sortAutoCompletionItem(p1: AutoCompletionItem, p2: AutoCompletionItem): Int

    abstract fun getAutoCompletionHelper(): IAutoCompletionHelper

}