package com.mucheng.editor.event

import com.mucheng.editor.base.AbstractTheme

interface ThemeUpdateEvent : Event {

    fun onThemeUpdate(theme: AbstractTheme)

}