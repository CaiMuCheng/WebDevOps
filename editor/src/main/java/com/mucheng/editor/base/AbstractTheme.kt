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

package com.mucheng.editor.base

import com.mucheng.annotations.mark.InvalidateRequired
import com.mucheng.editor.color.Color
import com.mucheng.editor.manager.EditorStyleManager
import com.mucheng.editor.token.ThemeToken

@Suppress("LeakingThis", "MemberVisibilityCanBePrivate")
abstract class AbstractTheme(val styleManager: EditorStyleManager) {

    protected val lightEditorColors: MutableMap<ThemeToken, Color> = HashMap()

    protected val darkEditorColors: MutableMap<ThemeToken, Color> = HashMap()

    protected var isEnabledDarkColors = false
        private set

    protected var insteadColor: Color? = null

    init {
        lightColors()
        darkColors()
    }

    protected abstract fun lightColors()

    protected abstract fun darkColors()

    @InvalidateRequired
    fun setEnabledDarkColors(enabledDarkColors: Boolean) {
        isEnabledDarkColors = enabledDarkColors
        styleManager.editor.eventManager.dispatchThemeUpdateEvent(this)
    }

    open fun setInsteadColor(color: Color): AbstractTheme {
        this.insteadColor = color
        return this
    }

    open fun getColor(token: ThemeToken): Color {
        val currentColors = getCurrentColors()
        val color = currentColors[token]
        if (color == null) {
            if (insteadColor != null) {
                return insteadColor as Color
            }
            throw IllegalAccessException("In this theme, the theme token \"${token.name}\" doesn't have a mapped color")
        }
        return color
    }

    private fun getCurrentColors(): MutableMap<ThemeToken, Color> {
        return if (isEnabledDarkColors) darkEditorColors else lightEditorColors
    }

    fun isDarkTheme(): Boolean {
        return isEnabledDarkColors
    }

}