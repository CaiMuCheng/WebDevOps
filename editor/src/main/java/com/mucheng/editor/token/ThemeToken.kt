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

package com.mucheng.editor.token

data class ThemeToken(val type: ThemeToken? = null, val name: String? = "") {

    companion object {

        @JvmStatic
        val BACKGROUND_COLOR_TOKEN = ThemeToken(null, "Background-Color-Token")

        @JvmStatic
        val LINE_NUMBER_COLOR_TOKEN = ThemeToken(null, "Line-Number-Color-Token")

        @JvmStatic
        val LINE_HIGHLIGHT_COLOR_TOKEN = ThemeToken(null, "Line-Highlight-Color-Token")

        @JvmStatic
        val LINE_NUMBER_DIVIDING_LINE_COLOR_TOKEN =
            ThemeToken(null, "Line-Number-Dividing-Line-Color-Token")

        @JvmStatic
        val LINE_NUMBER_BACKGROUND_COLOR_TOKEN =
            ThemeToken(null, "Line-Number-Background-Color-Token")

        @JvmStatic
        val CURSOR_COLOR_TOKEN = ThemeToken(null, "Cursor-Color-Token")

        @JvmStatic
        val TEXT_SELECT_HANDLE_BACKGROUND_COLOR_TOKEN =
            ThemeToken(null, "Text-Select-Handle-Background-Color-Token")

        @JvmStatic
        val TEXT_SELECT_HANDLE_COLOR_TOKEN = ThemeToken(null, "Text-Select-Handle-Color-Token")

        @JvmStatic
        val IDENTIFIER_COLOR_TOKEN = ThemeToken(null, "Identifier-Color-Token")

        @JvmStatic
        val KEYWORD_COLOR = ThemeToken(null, "Keyword-Color-Token")

        @JvmStatic
        val COMMENT_COLOR = ThemeToken(null, "Comment-Color-Token")

        @JvmStatic
        val STRING_COLOR = ThemeToken(null, "String-Color-Token")

        @JvmStatic
        val SYMBOL_COLOR = ThemeToken(null, "Symbol-Color-Token")

        @JvmStatic
        val SPECIAL_COLOR = ThemeToken(null, "Special-Color-Token")

        @JvmStatic
        val NUMERICAL_VALUE_COLOR = ThemeToken(null, "Number-Color-Token")

        @JvmStatic
        val OPERATOR_PANEL_BACKGROUND_COLOR =
            ThemeToken(null, "OPERATOR_PANEL_BACKGROUND_COLOR")

        @JvmStatic
        val AUTO_COMPLETION_PANEL_BACKGROUND_COLOR =
            ThemeToken(null, "AUTO_COMPLETION_PANEL_BACKGROUND_COLOR")

        val AUTO_COMPLETION_PANEL_INDICATOR_COLOR =
            ThemeToken(null, "AUTO_COMPLETION_PANEL_INDICATOR_COLOR")

        @JvmStatic
        val TOOL_OPTION_PANEL_BACKGROUND_COLOR =
            ThemeToken(null, "TOOL_OPTION_PANEL_BACKGROUND_COLOR")
    }

}