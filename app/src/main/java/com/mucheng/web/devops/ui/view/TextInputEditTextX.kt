package com.mucheng.web.devops.ui.view

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText
import com.mucheng.web.devops.util.TypefaceUtil

open class TextInputEditTextX @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : TextInputEditText(context, attrs) {

    init {
        typeface = TypefaceUtil.getTypeface()
    }

}