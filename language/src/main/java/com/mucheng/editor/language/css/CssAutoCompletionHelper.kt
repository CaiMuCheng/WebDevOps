package com.mucheng.editor.language.css

import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.mucheng.editor.language.R
import com.mucheng.editor.sample.helper.DefaultAutoCompletionHelper
import com.mucheng.editor.view.MuCodeEditor

open class CssAutoCompletionHelper : DefaultAutoCompletionHelper() {

    companion object {
        const val SELECTOR = "Selector"
        const val STYLE = "Style"
    }

    override fun getIconDrawable(editor: MuCodeEditor, type: String): Drawable? {
        val context = editor.context
        return when (type) {
            SELECTOR -> ContextCompat.getDrawable(context, R.drawable.ic_auto_completion_selector)
            STYLE -> ContextCompat.getDrawable(context, R.drawable.ic_auto_completion_style)
            else -> super.getIconDrawable(editor, type)
        }
    }

}