package com.mucheng.web.devops.openapi.editor.colorScheme

import android.graphics.Color
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme

class QuietLightColorScheme : EditorColorScheme() {

    companion object {
        const val STRING = 50
    }

    override fun applyDefault() {
        super.applyDefault()
        setColor(LINE_DIVIDER, Color.TRANSPARENT)
        setColor(HTML_TAG, "#4B69C6".parseColor())
        setColor(ATTRIBUTE_NAME, "#8190A0".parseColor())
        setColor(ATTRIBUTE_VALUE, "#448C27".parseColor())
        setColor(STRING, "#448C27".parseColor())

        setColor(ANNOTATION, "#7A3E9D".parseColor())
        setColor(FUNCTION_NAME, "#AA3731".parseColor())
        setColor(IDENTIFIER_NAME, "#7A3E9D".parseColor())
        setColor(IDENTIFIER_VAR, "#7A3E9D".parseColor())
        setColor(LITERAL, "#9C5D27".parseColor())
        setColor(OPERATOR, "#777777".parseColor())
        setColor(COMMENT, "#AAAAAA".parseColor())
        setColor(KEYWORD, "#7A3E9D".parseColor())
        setColor(WHOLE_BACKGROUND, "#fffbfb".parseColor())
        setColor(TEXT_NORMAL, "#333333".parseColor())
        setColor(LINE_NUMBER_BACKGROUND, "#fffbfb".parseColor())
        setColor(LINE_NUMBER, "#6D705B".parseColor())
        setColor(LINE_NUMBER_CURRENT, "#9769dc".parseColor())
        setColor(SELECTED_TEXT_BACKGROUND, "#C9D0D9".parseColor())
        setColor(MATCHED_TEXT_BACKGROUND, "#C9D0D9".parseColor())
        setColor(SNIPPET_BACKGROUND_EDITING, "#C9D0D9".parseColor())
        setColor(SNIPPET_BACKGROUND_INACTIVE, "#C9D0D9".parseColor())
        setColor(SNIPPET_BACKGROUND_RELATED, "#C9D0D9".parseColor())
        setColor(CURRENT_LINE, "#E4F6D4".parseColor())
        setColor(SELECTION_INSERT, "#54494B".parseColor())
        setColor(SELECTION_HANDLE, "#536cfc".parseColor())
        setColor(BLOCK_LINE, "#aaaaaa60".parseColor())
        setColor(BLOCK_LINE_CURRENT, "#777777b0".parseColor())
        setColor(TEXT_SELECTED, "#ffffff".parseColor())
        setColor(NON_PRINTABLE_CHAR, "#AAAAAA".parseColor())
    }

    private fun String.parseColor(): Int {
        return Color.parseColor(this)
    }

}