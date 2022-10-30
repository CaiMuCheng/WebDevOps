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

package com.mucheng.editor.base.panel

import android.graphics.drawable.Drawable
import android.view.Gravity
import com.mucheng.editor.base.AbstractPanel
import com.mucheng.editor.base.IAutoCompletionHelper
import com.mucheng.editor.base.IAutoCompletionPublisher
import com.mucheng.editor.base.lang.AbstractLanguage
import com.mucheng.editor.data.AutoCompletionItem
import com.mucheng.editor.sample.helper.DefaultAutoCompletionHelper
import com.mucheng.editor.sample.publisher.DefaultAutoCompletionPublisher
import com.mucheng.editor.span.CodeBlock
import com.mucheng.editor.view.MuCodeEditor
import kotlin.math.max

@Suppress("LeakingThis", "MemberVisibilityCanBePrivate", "unused")
abstract class AbstractAutoCompletionPanel(editor: MuCodeEditor) : AbstractPanel(editor) {

    companion object {
        const val KEYWORD = "Keyword"
        const val VARIABLE = "Variable"
        const val FUNCTION = "Function"
        const val SPECIAL = "Special"
    }

    private var autoCompletionPublisher: IAutoCompletionPublisher = DefaultAutoCompletionPublisher()

    private var autoCompletionHelper: IAutoCompletionHelper = DefaultAutoCompletionHelper()

    private val constAutoCompletionItems: MutableList<AutoCompletionItem> = ArrayList()

    private var codeBlocks: List<CodeBlock> = emptyList()

    init {
        setBackgroundDrawable(onCreateBackground())
    }

    abstract fun onCreateBackground(): Drawable?

    open fun setCodeBlocks(codeBlocks: List<CodeBlock>) {
        this.codeBlocks = codeBlocks
    }

    open fun getCodeBlocks(): List<CodeBlock> {
        return codeBlocks
    }

    open fun setAutoCompletionPublisher(publisher: IAutoCompletionPublisher) {
        this.autoCompletionPublisher = publisher
    }

    open fun getAutoCompletionPublisher(): IAutoCompletionPublisher {
        return autoCompletionPublisher
    }

    open fun addAutoCompletionItem(item: AutoCompletionItem) {
        constAutoCompletionItems.add(item)
    }

    open fun removeAutoCompletionItems(item: AutoCompletionItem) {
        constAutoCompletionItems.remove(item)
    }

    open fun clearAutoCompletionItems() {
        constAutoCompletionItems.clear()
    }

    open fun getAutoCompletionItemAt(index: Int): AutoCompletionItem {
        return constAutoCompletionItems[index]
    }

    open fun getAutoCompletionItems(): List<AutoCompletionItem> {
        return constAutoCompletionItems
    }

    open fun getAutoCompletionItemSize(): Int {
        return constAutoCompletionItems.size
    }

    abstract fun notifyAutoCompletionItemChanged()

    abstract fun launchRequireAutoCompletionCoroutine()

    open fun show() {
        editor.post {
            if (!editor.functionManager.isAutoCompletionPanelEnabled) {
                return@post
            }
            val showAsDropStartY = editor.height - height
            val distanceLine = max(1, editor.getCursor().line - editor.getFirstVisibleLine())
            val functionManager = editor.functionManager
            val operatorPanel = editor.languageManager.operatorPanel
            val yOffset = if (functionManager.isOperatorPanelEnabled && operatorPanel.isShowing) {
                operatorPanel.height
            } else {
                0
            }
            val cursorOffsetY =
                (distanceLine + 0.5f) * editor.styleManager.painters.getLineHeight()
                    .toFloat() + yOffset

            if (cursorOffsetY > showAsDropStartY) {
                editor.scrollBy(0f, cursorOffsetY - showAsDropStartY, true)
            }

            showAsDropDown(editor, 0, editor.height - yOffset, Gravity.TOP)

        }
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        if (!isShowing) {
            return
        }
        val showAsDropStartY = editor.height - height
        val distanceLine = max(1, editor.getCursor().line - editor.getFirstVisibleLine())
        val functionManager = editor.functionManager
        val operatorPanel = editor.languageManager.operatorPanel
        val yOffset = if (functionManager.isOperatorPanelEnabled && operatorPanel.isShowing) {
            operatorPanel.height
        } else {
            0
        }
        val cursorOffsetY =
            (distanceLine + 0.5f) * editor.styleManager.painters.getLineHeight()
                .toFloat() + yOffset

        if (cursorOffsetY > showAsDropStartY) {
            editor.scrollBy(0f, cursorOffsetY - showAsDropStartY, true)
        }
    }

    override fun dismiss() {
        editor.post {
            super.dismiss()
        }
    }

    open fun getAutoCompletionHelper(): IAutoCompletionHelper {
        return autoCompletionHelper
    }

    open fun setAutoCompletionHelper(autoCompletionHelper: IAutoCompletionHelper) {
        this.autoCompletionHelper = autoCompletionHelper
    }

    abstract fun getCurrentCodeBlockLanguage(): AbstractLanguage?
}