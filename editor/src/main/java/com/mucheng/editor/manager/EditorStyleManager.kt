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

package com.mucheng.editor.manager

import android.graphics.Bitmap
import android.graphics.Typeface
import com.mucheng.annotations.mark.InvalidateRequired
import com.mucheng.annotations.mark.Model
import com.mucheng.editor.base.AbstractManager
import com.mucheng.editor.base.AbstractTheme
import com.mucheng.editor.component.Painters
import com.mucheng.editor.span.Spans
import com.mucheng.editor.theme.MuTheme
import com.mucheng.editor.tool.sp
import com.mucheng.editor.view.MuCodeEditor

@Suppress("LeakingThis", "unused")
@Model
open class EditorStyleManager(editor: MuCodeEditor) : AbstractManager(editor) {

    open var theme: AbstractTheme = MuTheme(this)
        protected set

    open var painters = Painters(editor)
        protected set

    open var customBackground: Bitmap? = null
        protected set

    open var spans = Spans(editor)
        protected set

    @InvalidateRequired
    open fun setTheme(theme: AbstractTheme): EditorStyleManager {
        this.theme = theme
        editor.eventManager.dispatchThemeUpdateEvent(theme)
        return this
    }

    @InvalidateRequired
    open fun setPainters(painters: Painters): EditorStyleManager {
        this.painters = painters
        return this
    }

    @InvalidateRequired
    open fun setCustomBackground(customBackground: Bitmap?): EditorStyleManager {
        this.customBackground = customBackground
        return this
    }

    @InvalidateRequired
    open fun setTypeface(typeface: Typeface?): EditorStyleManager {
        this.painters.lineNumberPainter.typeface = typeface
        this.painters.codeTextPainter.typeface = typeface
        return this
    }

    @InvalidateRequired
    open fun setTextSize(textSize: Float): EditorStyleManager {
        val textSizeSp = editor.context.sp(textSize)
        setTextSizePxDirect(textSizeSp)
        return this
    }

    @InvalidateRequired
    open fun setTextSizePxDirect(px: Float): EditorStyleManager {
        this.painters.lineNumberPainter.textSize = px
        this.painters.codeTextPainter.textSize = px
        return this
    }

    @InvalidateRequired
    open fun setLetterSpacing(letterSpacing: Float): EditorStyleManager {
        this.painters.lineNumberPainter.letterSpacing = letterSpacing
        this.painters.codeTextPainter.letterSpacing = letterSpacing
        return this
    }

}