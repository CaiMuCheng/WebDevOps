package com.mucheng.web.devops.openapi.editor.lang.php.impl

import java.util.*

class PhpState {

    companion object {
        const val STATE_NORMAL = 0
        const val STATE_INCOMPLETE_HTML_COMMENT = 1
        const val STATE_INCOMPLETE_PHP = 2
        const val STATE_INCOMPLETE_PHP_COMMENT = 3
    }

    var state = STATE_NORMAL

    override fun equals(other: Any?): Boolean {
        return false
    }

    override fun hashCode(): Int {
        return Objects.hash(state)
    }

    override fun toString(): String {
        return "PhpState()"
    }

}