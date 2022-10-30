package com.mucheng.web.devops.ui.view

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textview.MaterialTextView
import com.mucheng.web.devops.util.TypefaceUtil

open class MaterialTextViewX @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : MaterialTextView(context, attrs) {

    init {
        typeface = TypefaceUtil.getTypeface()
    }

}