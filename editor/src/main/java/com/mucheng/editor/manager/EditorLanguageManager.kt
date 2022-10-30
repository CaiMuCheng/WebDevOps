/*
 * CN:
 * 作者：SuMuCheng
 * 我的 QQ: 3578557729
 * Github 主页：https://github.com/CaiMuCheng
 * 项目主页: https://github.com/CaiMuCheng/MuCodeEditor
 *
 * 你可以免费使用、商用以下代码，也可以基于以下代码做出修改，但是必须在你的项目中标注出处
 * 例如：在你 APP 的设置中添加 “关于编辑器” 一栏，其中标注作者以及此编辑器的 Github 主页
 *
 * 此代码使用 MPL 2.0 开源许可证，你必须标注作者信息
 * 若你要修改文件，请勿删除此注释
 * 若你违反以上条例我们有权向您提起诉讼!
 *
 * EN:
 * Author: SuMuCheng
 * My QQ-Number: 3578557729
 * Github Homepage: https://github.com/CaiMuCheng
 * Project Homepage: https://github.com/CaiMuCheng/MuCodeEditor
 *
 * You can use the following code for free, commercial use, or make modifications based on the following code, but you must mark the source in your project.
 * For example: add an "About Editor" column in your app's settings, which identifies the author and the Github home page of this editor.
 *
 * This code uses the MPL 2.0 open source license, you must mark the author information.
 * Do not delete this comment if you want to modify the file.
 * If you violate the above regulations we have the right to sue you!
 */

package com.mucheng.editor.manager

import com.mucheng.annotations.mark.InvalidateRequired
import com.mucheng.editor.base.AbstractManager
import com.mucheng.editor.base.lang.AbstractLanguage
import com.mucheng.editor.base.panel.AbstractAutoCompletionPanel
import com.mucheng.editor.base.panel.AbstractOperatorPanel
import com.mucheng.editor.base.panel.AbstractToolOptionPanel
import com.mucheng.editor.data.TokenContainer
import com.mucheng.editor.event.ContentChangedEvent
import com.mucheng.editor.sample.component.DefaultAutoCompletionPanel
import com.mucheng.editor.sample.component.DefaultOperatorPanel
import com.mucheng.editor.sample.component.DefaultToolOptionPanel
import com.mucheng.editor.sample.language.TextLanguage
import com.mucheng.editor.span.Span
import com.mucheng.editor.view.MuCodeEditor
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex

@Suppress("LeakingThis")
open class EditorLanguageManager(editor: MuCodeEditor) : AbstractManager(editor),
    ContentChangedEvent {

    open var language: AbstractLanguage = TextLanguage
        protected set

    open lateinit var autoCompletionPanel: AbstractAutoCompletionPanel
        protected set

    open lateinit var operatorPanel: AbstractOperatorPanel
        protected set

    open lateinit var toolOptionPanel: AbstractToolOptionPanel
        protected set

    private val lexerBaseCoroutine =
        CoroutineScope(Dispatchers.IO + CoroutineName("TokenAnalyzeCoroutine"))

    private var lastJob: Job? = null

    private val mutex = Mutex()

    init {
        language.setEditor(editor)
        autoCompletionPanel = DefaultAutoCompletionPanel(editor)
        operatorPanel = DefaultOperatorPanel(editor)
        toolOptionPanel = DefaultToolOptionPanel(editor)
        editor.eventManager.subscribeEvent(ContentChangedEvent::class.java, this)
    }

    open fun getLastJob(): Job? {
        return lastJob
    }

    open fun analyze(): EditorLanguageManager {
        val lexer = language.getLexer() ?: return this
        val text = editor.getText()
        val spans = editor.styleManager.spans

        lastJob?.cancel()
        lastJob = lexerBaseCoroutine.launch {
            mutex.lock()
            spans.clear()

            lexer.setSources(text)
            lexer.setCoroutine(this)
            try {
                lexer.analyze()
            } catch (e: Throwable) {
                spans.clear()
                lexer.clear()
                mutex.unlock()
                return@launch
            }

            if (!isActive) {
                spans.clear()
                lexer.clear()
                mutex.unlock()
                return@launch
            }

            val tokens = lexer.getTokens()
            pushToSpans(tokens)

            val codeBlocks = lexer.getCodeBlocks()
            autoCompletionPanel.setCodeBlocks(codeBlocks)

            editor.postInvalidate()
            mutex.unlock()
        }
        return this
    }

    @InvalidateRequired
    open fun setLanguage(language: AbstractLanguage): EditorLanguageManager {
        this.language = language
        language.setEditor(editor)
        return this
    }

    open fun setAutoCompletionPanel(autoCompletionPanel: AbstractAutoCompletionPanel): EditorLanguageManager {
        this.autoCompletionPanel = autoCompletionPanel
        return this
    }

    open fun setOperatorPanel(operatorPanel: AbstractOperatorPanel): EditorLanguageManager {
        this.operatorPanel.dismiss()
        this.operatorPanel = operatorPanel
        operatorPanel.show()
        return this
    }

    open fun setToolOptionPanel(toolOptionPanel: AbstractToolOptionPanel): EditorLanguageManager {
        this.toolOptionPanel.dismiss()
        this.toolOptionPanel = toolOptionPanel
        toolOptionPanel.show()
        return this
    }

    private fun pushToSpans(result: List<TokenContainer>) {
        val spans = editor.styleManager.spans
        val theme = editor.styleManager.theme
        val textModel = editor.getText()

        spans.createLineSpans(textModel.lastLine)
        for (container in result) {
            val type = container.token.type!!
            val line = container.line
            val startRow = container.startRow
            val endRow = container.endRow

            val span = Span(startRow, endRow, theme.getColor(type))
            spans.appendSpan(line, span)
        }
    }

    override fun onContentChanged() {
        analyze()
    }

}