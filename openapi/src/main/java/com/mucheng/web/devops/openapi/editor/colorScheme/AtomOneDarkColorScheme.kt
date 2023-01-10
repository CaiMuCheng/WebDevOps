package com.mucheng.web.devops.openapi.editor.colorScheme

import android.graphics.Color
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme

class AtomOneDarkColorScheme : EditorColorScheme() {

    companion object {
        const val STRING = 50
    }

    override fun applyDefault() {
        super.applyDefault()
        setColor(LINE_DIVIDER, Color.TRANSPARENT)
        setColor(HTML_TAG, "#E06C75".parseColor())
        setColor(ATTRIBUTE_NAME, "#D19A66".parseColor())
        setColor(ATTRIBUTE_VALUE, "#98C379".parseColor())
        setColor(STRING, "#98C379".parseColor())

        setColor(ANNOTATION, "#C678DD".parseColor())
        setColor(FUNCTION_NAME, "#61AFEF".parseColor())
        setColor(IDENTIFIER_NAME, "#ABB2BF".parseColor())
        setColor(IDENTIFIER_VAR, "#ABB2BF".parseColor())
        setColor(LITERAL, "#D19A66".parseColor())
        setColor(KEYWORD, "#C678DD".parseColor())
        setColor(COMMENT, "#5C6370".parseColor())
        setColor(OPERATOR, "#ABB2BF".parseColor())
        setColor(WHOLE_BACKGROUND, "#282C34".parseColor())
        setColor(LINE_NUMBER_BACKGROUND, "#282C34".parseColor())
        setColor(SELECTION_HANDLE, "#528BFF".parseColor())
        setColor(SELECTION_INSERT, "#528BFF".parseColor())
        setColor(SELECTED_TEXT_BACKGROUND, "#3E4451".parseColor())
        setColor(MATCHED_TEXT_BACKGROUND, "#3E4451".parseColor())
        setColor(SNIPPET_BACKGROUND_EDITING, "#3E4451".parseColor())
        setColor(SNIPPET_BACKGROUND_INACTIVE, "#3E4451".parseColor())
        setColor(SNIPPET_BACKGROUND_RELATED, "#3E4451".parseColor())
        setColor(NON_PRINTABLE_CHAR, "#ABB2BF".parseColor())
        setColor(CURRENT_LINE, "#2c313c".parseColor())
        setColor(LINE_NUMBER, "#636D83".parseColor())
        setColor(LINE_NUMBER_CURRENT, "#ABB2BF".parseColor())
        setColor(TEXT_NORMAL, "#ABB2BF".parseColor())
        setColor(BLOCK_LINE, "#2c313c".parseColor())
        setColor(BLOCK_LINE_CURRENT, "#626772".parseColor())
        setColor(TEXT_SELECTED, "#ffffff".parseColor())
    }

    private fun String.parseColor(): Int {
        return Color.parseColor(this)
    }

}