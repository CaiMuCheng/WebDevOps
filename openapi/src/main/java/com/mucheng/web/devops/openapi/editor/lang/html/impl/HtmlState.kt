package com.mucheng.web.devops.openapi.editor.lang.html.impl

import java.util.*

class HtmlState {

    companion object {
        const val STATE_NORMAL = 0
        const val STATE_INCOMPLETE_COMMENT = 1
    }

    var state = STATE_NORMAL

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other !is HtmlState) return false
        if (other === this) return true

        return other.state == state
    }

    override fun hashCode(): Int {
        return Objects.hash(state)
    }

    override fun toString(): String {
        return "HtmlState()"
    }

}