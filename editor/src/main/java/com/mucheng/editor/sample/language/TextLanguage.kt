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
package com.mucheng.editor.sample.language

import com.mucheng.editor.base.IAutoCompletionHelper
import com.mucheng.editor.base.lang.AbstractBasicLanguage
import com.mucheng.editor.base.lexer.AbstractLexer
import com.mucheng.editor.sample.helper.DefaultAutoCompletionHelper
import com.mucheng.editor.token.ThemeToken
import com.mucheng.editor.view.MuCodeEditor

object TextLanguage : AbstractBasicLanguage() {

    private lateinit var editor: MuCodeEditor

    private val autoCompletionHelper = DefaultAutoCompletionHelper()

    override fun getLexer(): AbstractLexer? {
        return null
    }

    override fun doSpan(): Boolean {
        return false
    }

    override fun setEditor(editor: MuCodeEditor) {
        TextLanguage.editor = editor
    }

    override fun getEditor(): MuCodeEditor {
        return editor
    }

    override fun isKeyword(text: String): Boolean {
        return false
    }

    override fun isSpecial(text: String): Boolean {
        return false
    }

    override fun getOperatorTokenMap(): HashMap<Char, ThemeToken> {
        return hashMapOf()
    }

    override fun getKeywordTokenMap(): HashMap<String, ThemeToken> {
        return hashMapOf()
    }

    override fun getSpecialTokenMap(): HashMap<String, ThemeToken> {
        return hashMapOf()
    }

    override fun getAutoCompletionHelper(): IAutoCompletionHelper {
        return autoCompletionHelper
    }

}