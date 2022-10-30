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

package com.mucheng.editor.theme

import com.mucheng.editor.base.AbstractTheme
import com.mucheng.editor.color.Color
import com.mucheng.editor.manager.EditorStyleManager
import com.mucheng.editor.token.ThemeToken

//修改后, MuTheme 成为 BaseTheme 的子类
//其实有点多余了，可以考虑把 BaseTheme 并入 AbstractTheme
@Suppress("SpellCheckingInspection")
open class MuTheme(styleManager: EditorStyleManager) : AbstractTheme(styleManager) {

    override fun lightColors() {
        with(lightEditorColors) {
            // 编辑器背景颜色
            put(ThemeToken.BACKGROUND_COLOR_TOKEN, Color("#FFFFFFFF"))

            // 关键字颜色
            put(ThemeToken.KEYWORD_COLOR, Color("#FFc678dd"))

            // 标识符颜色
            put(ThemeToken.IDENTIFIER_COLOR_TOKEN, Color("#FF000000"))

            // 字符串颜色
            put(ThemeToken.STRING_COLOR, Color("#FF009688"))

            // 数字颜色
            put(ThemeToken.NUMERICAL_VALUE_COLOR, Color("#FF497ce3"))

            // 特殊值颜色
            put(ThemeToken.SPECIAL_COLOR, Color("#FF51AAFF"))

            // 光标颜色
            put(ThemeToken.CURSOR_COLOR_TOKEN, Color("#FF000000"))

            // 行号颜色
            put(ThemeToken.LINE_NUMBER_COLOR_TOKEN, Color("#FF000000"))

            //行号分界线颜色
            put(ThemeToken.LINE_NUMBER_DIVIDING_LINE_COLOR_TOKEN, Color("#FF000000"))

            //行号背景颜色
            put(ThemeToken.LINE_NUMBER_BACKGROUND_COLOR_TOKEN, Color("#FFEEEEEE"))

            //编辑行高亮颜色
            put(ThemeToken.LINE_HIGHLIGHT_COLOR_TOKEN, Color("#10000000"))

            // 选中文本的背景色
            put(ThemeToken.TEXT_SELECT_HANDLE_BACKGROUND_COLOR_TOKEN, Color("#D08aa8fe"))

            put(ThemeToken.TEXT_SELECT_HANDLE_COLOR_TOKEN, Color("#FF536DFE"))

            // 符号的颜色
            put(ThemeToken.SYMBOL_COLOR, Color("#FF51AAFF"))

            // 注释的颜色
            put(ThemeToken.COMMENT_COLOR, Color("#FF586694"))

            // 自动补全栏背景色
            put(ThemeToken.AUTO_COMPLETION_PANEL_BACKGROUND_COLOR, Color("#FFFFFFFF"))

            // 自动补全栏指示器颜色
            put(ThemeToken.AUTO_COMPLETION_PANEL_INDICATOR_COLOR, Color("#FF6200EE"))

            // 运算符背景色
            put(ThemeToken.OPERATOR_PANEL_BACKGROUND_COLOR, Color("#FFFFFFFF"))

            // 工具栏背景颜色
            put(ThemeToken.TOOL_OPTION_PANEL_BACKGROUND_COLOR, Color("#FFFFFFFF"))
        }
    }

    override fun darkColors() {
        with(darkEditorColors) {
            // 编辑器背景颜色
            put(ThemeToken.BACKGROUND_COLOR_TOKEN, Color("#FF1E1E1E"))

            // 关键字颜色
            put(ThemeToken.KEYWORD_COLOR, Color("#FFc678dd"))

            // 标识符颜色
            put(ThemeToken.IDENTIFIER_COLOR_TOKEN, Color("#FFA4ABCC"))

            // 字符川颜色
            put(ThemeToken.STRING_COLOR, Color("#FFC3E88D"))

            // 数字颜色
            put(ThemeToken.NUMERICAL_VALUE_COLOR, Color("#FF497CE3"))

            // 特殊值颜色
            put(ThemeToken.SPECIAL_COLOR, Color("#FF51AAFF"))

            // 光标颜色
            put(ThemeToken.CURSOR_COLOR_TOKEN, Color("#FFF5F5F7"))

            // 行号颜色
            put(ThemeToken.LINE_NUMBER_COLOR_TOKEN, Color("#FFA4ABCC"))

            //行号分界线颜色
            put(ThemeToken.LINE_NUMBER_DIVIDING_LINE_COLOR_TOKEN, Color("#FFFFFFFF"))

            //行号背景颜色
            put(ThemeToken.LINE_NUMBER_BACKGROUND_COLOR_TOKEN, Color("#FF222222"))

            //编辑行高亮颜色
            put(ThemeToken.LINE_HIGHLIGHT_COLOR_TOKEN, Color("#33333333"))

            // 选中文本的背景色
            put(ThemeToken.TEXT_SELECT_HANDLE_BACKGROUND_COLOR_TOKEN, Color("#FF515C6A"))

            put(ThemeToken.TEXT_SELECT_HANDLE_COLOR_TOKEN, Color("#FF536DFE"))

            // 符号的颜色
            put(ThemeToken.SYMBOL_COLOR, Color("#FF51AAFF"))

            // 注释的颜色
            put(ThemeToken.COMMENT_COLOR, Color("#FF858C99"))

            // 自动补全栏背景色
            put(ThemeToken.AUTO_COMPLETION_PANEL_BACKGROUND_COLOR, Color("#FF1E1E1E"))

            // 自动补全栏指示器颜色
            put(ThemeToken.AUTO_COMPLETION_PANEL_INDICATOR_COLOR, Color("#FFBB86FC"))

            // 运算符背景色
            put(ThemeToken.OPERATOR_PANEL_BACKGROUND_COLOR, Color("#FF1E1E1E"))

            // 工具栏背景颜色
            put(ThemeToken.TOOL_OPTION_PANEL_BACKGROUND_COLOR, Color("#FF1E1E1E"))
        }
    }

}