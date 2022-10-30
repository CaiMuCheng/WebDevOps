package com.mucheng.web.devops.ui.view

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.core.view.children
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView
import com.mucheng.web.devops.R
import com.mucheng.web.devops.util.isSystemInDarkTheme

@Suppress("LeakingThis")
open class SelectorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : MaterialCardView(context, attrs) {

    private var onMenuItemClickListener: OnMenuItemClickListener? = null

    private lateinit var textView: MaterialTextView

    private var data: Array<String> = emptyArray()

    private var position: Int = 0

    interface OnMenuItemClickListener {
        fun onMenuItemClick(currentText: String, position: Int): Boolean
    }

    init {
        background = if (context.isSystemInDarkTheme()) {
            ContextCompat.getDrawable(context, R.drawable.drawable_input_style_dark)
        } else {
            ContextCompat.getDrawable(context, R.drawable.drawable_input_style_light)
        }
        cardElevation = 0f
        addChild()
        setOnClickListener {
            val popupMenu = PopupMenu(context, it)
            popupMenu.gravity = Gravity.BOTTOM
            val menu = popupMenu.menu
            for (title in data) {
                menu.add(title)
            }
            popupMenu.setOnMenuItemClickListener { menuItem ->
                val index = menu.children.indexOf(menuItem)
                val text = menuItem.title.toString()
                textView.text = text
                position = index
                onMenuItemClickListener?.onMenuItemClick(text, position) ?: false
            }
            popupMenu.show()
        }
    }

    private fun addChild() {
        textView = MaterialTextViewX(context)
        textView.isSingleLine = true
        textView.setTextColor(
            if (context.isSystemInDarkTheme()) {
                Color.parseColor("#FFFFFFFF")
            } else {
                Color.parseColor("#DE000000")
            }
        )
        textView.textSize = 14f
        textView.layoutParams = LayoutParams(-2, -2).apply {
            gravity = Gravity.CENTER or Gravity.START
        }
        addView(textView)
    }

    open fun getTextView(): MaterialTextView {
        return textView
    }

    open fun setData(data: Array<String>, position: Int) {
        if (data.isEmpty()) {
            throw IllegalArgumentException("The data size must be > 0")
        }
        this.data = data
        this.position = position
        textView.text = data[position]
    }

    open fun getData(): Array<String> {
        return data
    }

    open fun getCurrentText(): CharSequence? {
        return textView.text
    }

    open fun getPosition(): Int {
        return position
    }

    open fun setOnMenuItemClickListener(onMenuItemClickListener: OnMenuItemClickListener) {
        this.onMenuItemClickListener = onMenuItemClickListener
    }

    open fun getOnMenuItemClickListener(): OnMenuItemClickListener? {
        return onMenuItemClickListener
    }

    open fun setTypeface(typeface: Typeface?) {
        textView.typeface = typeface
    }

    open fun getTypeface(): Typeface? {
        return textView.typeface
    }

}