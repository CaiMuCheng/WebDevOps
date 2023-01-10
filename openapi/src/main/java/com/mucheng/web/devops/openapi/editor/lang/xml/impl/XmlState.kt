package com.mucheng.web.devops.openapi.editor.lang.xml.impl

import java.util.*

class XmlState {

    companion object {
        const val STATE_NORMAL = 0
        const val STATE_INCOMPLETE_COMMENT = 1
    }

    var state = STATE_NORMAL

    override fun equals(other: Any?): Boolean {
        return false
    }

    override fun hashCode(): Int {
        return Objects.hash(state)
    }

    override fun toString(): String {
        return "XmlState()"
    }

}