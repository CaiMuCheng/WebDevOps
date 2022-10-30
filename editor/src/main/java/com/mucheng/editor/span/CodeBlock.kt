package com.mucheng.editor.span

import com.mucheng.editor.base.lang.AbstractLanguage

open class CodeBlock(
    var startLine: Int,
    var startRow: Int,
    var endLine: Int,
    var endRow: Int,
    var language: AbstractLanguage
) {

    open fun has(line: Int, row: Int): Boolean {
        if (line == startLine) {
            return row > startRow
        }

        if (line == endLine) {
            return row <= endRow
        }

        return line in startLine..endLine
    }

}