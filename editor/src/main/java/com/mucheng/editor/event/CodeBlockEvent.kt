package com.mucheng.editor.event

import com.mucheng.editor.span.CodeBlock

interface CodeBlockEvent : Event {

    /**
     * 当 Cursor 位于 CodeBlock 中执行
     *
     * @param codeBlock 当前 Cursor 所处的 CodeBlock
     * */
    fun onCodeBlock(codeBlock: CodeBlock)

}