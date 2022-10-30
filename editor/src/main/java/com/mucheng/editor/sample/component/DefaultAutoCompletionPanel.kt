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

package com.mucheng.editor.sample.component

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.view.postDelayed
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.textview.MaterialTextView
import com.mucheng.editor.R
import com.mucheng.editor.base.AbstractTheme
import com.mucheng.editor.base.lang.AbstractLanguage
import com.mucheng.editor.base.panel.AbstractAutoCompletionPanel
import com.mucheng.editor.data.AutoCompletionItem
import com.mucheng.editor.event.ThemeUpdateEvent
import com.mucheng.editor.token.ThemeToken
import com.mucheng.editor.tool.dp
import com.mucheng.editor.view.MuCodeEditor
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@Suppress("LeakingThis")
open class DefaultAutoCompletionPanel(editor: MuCodeEditor) : AbstractAutoCompletionPanel(editor),
    ThemeUpdateEvent {

    private val inflater by lazy { LayoutInflater.from(editor.context) }

    private val adapter by lazy { Adapter() }

    private lateinit var content: View

    private lateinit var indicator: LinearProgressIndicator

    private val baseCoroutine = CoroutineScope(Dispatchers.IO + CoroutineName("BaseCoroutine"))

    private var job: Job? = null

    private var currentCodeBlockLanguage: AbstractLanguage? = null

    init {
        editor.eventManager.subscribeEvent(ThemeUpdateEvent::class.java, this)
        animationStyle = com.google.android.material.R.style.Animation_AppCompat_Dialog
        isFocusable = false
        isTouchable = true

        width = editor.context.resources.displayMetrics.widthPixels
        height = editor.context.dp(140f).toInt()

        createContentView()
        contentView = content
    }

    override fun onCreateBackground(): Drawable? {
        return null
    }

    override fun onThemeUpdate(theme: AbstractTheme) {
        if (!::content.isInitialized) {
            return
        }

        val cardView: CardView = content.findViewById(R.id.cardView)
        cardView.setCardBackgroundColor(theme.getColor(ThemeToken.AUTO_COMPLETION_PANEL_BACKGROUND_COLOR).hexColor)

        indicator = content.findViewById(R.id.indicator)
        indicator.setIndicatorColor(theme.getColor(ThemeToken.AUTO_COMPLETION_PANEL_INDICATOR_COLOR).hexColor)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun notifyAutoCompletionItemChanged() {
        editor.post {
            adapter.notifyDataSetChanged()
        }
    }

    override fun launchRequireAutoCompletionCoroutine() {
        job?.cancel()
        job = baseCoroutine.launch {
            // 等待 lex 的完成, 为了代码块的稳定找想
            editor.languageManager.getLastJob()?.join()
            synchronized(this::class.java) {
                editor.post {
                    indicator.visibility = View.VISIBLE
                }
                clearAutoCompletionItems()

                val publisher = getAutoCompletionPublisher()
                val mainlyLanguage = editor.languageManager.language

                var constAutoCompletionItems: List<AutoCompletionItem>? = null
                var variableAutoCompletionItems: List<AutoCompletionItem>? = null
                var keywords: Set<String>? = null
                var specials: Set<String>? = null

                val cursor = editor.getCursor()
                val codeBlocks = getCodeBlocks()
                var isInCodeBlock = false
                for (codeBlock in codeBlocks) {
                    if (!isActive) {
                        return@synchronized
                    }
                    val currentLine = cursor.line
                    val currentRow = cursor.row
                    if (codeBlock.has(currentLine, currentRow)) {
                        val codeBlockLanguage = codeBlock.language
                        constAutoCompletionItems = codeBlockLanguage.getConstAutoCompletionItems()
                        variableAutoCompletionItems =
                            codeBlockLanguage.getVariableAutoCompletionItems()
                        keywords = codeBlockLanguage.getKeywordTokenMap().keys
                        specials = codeBlockLanguage.getSpecialTokenMap().keys
                        setAutoCompletionHelper(codeBlockLanguage.getAutoCompletionHelper())
                        currentCodeBlockLanguage = codeBlockLanguage
                        isInCodeBlock = true
                        // 已经在此代码块了, 怎么可能还在别的地方呢?
                        // 执行 CodeBlockEvent
                        editor.eventManager.dispatchCodeBlockEvent(codeBlock)
                        break
                    }
                }
                if (!isInCodeBlock) {
                    constAutoCompletionItems = mainlyLanguage.getConstAutoCompletionItems()
                    variableAutoCompletionItems = mainlyLanguage.getVariableAutoCompletionItems()
                    keywords = mainlyLanguage.getKeywordTokenMap().keys
                    specials = mainlyLanguage.getSpecialTokenMap().keys
                    setAutoCompletionHelper(mainlyLanguage.getAutoCompletionHelper())
                    currentCodeBlockLanguage = null
                }

                for (userDefinedItem in variableAutoCompletionItems!!) {
                    addAutoCompletionItem(userDefinedItem)
                    if (!isActive) {
                        return@synchronized
                    }
                }

                for (custom in constAutoCompletionItems!!) {
                    addAutoCompletionItem(custom)
                    if (!isActive) {
                        return@synchronized
                    }
                }

                // 默认调用 AutoCompletionHelper.getKeywordType 方法
                val keywordType = getAutoCompletionHelper().getKeywordType()
                for (keyword in keywords!!) {
                    addAutoCompletionItem(AutoCompletionItem(keyword, keywordType))
                    if (!isActive) {
                        return@synchronized
                    }
                }

                // 默认调用 AutoCompletionHelper.getSpecialType 方法
                val specialType = getAutoCompletionHelper().getSpecialType()
                for (special in specials!!) {
                    addAutoCompletionItem(AutoCompletionItem(special, specialType))
                    if (!isActive) {
                        return@synchronized
                    }
                }

                publisher.publish(editor, this@DefaultAutoCompletionPanel, this)
                editor.postDelayed(100) {
                    indicator.visibility = View.GONE
                }
            }
        }
    }

    override fun show() {
        if (getAutoCompletionItemSize() == 0 || isShowing) {
            return
        }

        super.show()
    }

    override fun getCurrentCodeBlockLanguage(): AbstractLanguage? {
        return this.currentCodeBlockLanguage
    }

    @SuppressLint("InflateParams")
    private fun createContentView() {
        content = inflater.inflate(R.layout.layout_auto_completion_panel_content, null, false)
        val theme = editor.getTheme()
        onThemeUpdate(theme)

        val recyclerView: RecyclerView = content.findViewById(R.id.autoCompletionRecyclerView)
        recyclerView.layoutManager =
            FixedLinearLayoutManager(editor.context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    private inner class FixedLinearLayoutManager(
        context: Context?,
        orientation: Int,
        reverseLayout: Boolean
    ) : LinearLayoutManager(context, orientation, reverseLayout) {

        override fun onLayoutChildren(
            recycler: RecyclerView.Recycler?,
            state: RecyclerView.State?
        ) {
            try {
                super.onLayoutChildren(recycler, state)
            } catch (_: IndexOutOfBoundsException) {
            }
        }

    }

    open inner class Adapter : RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                inflater.inflate(R.layout.layout_auto_completion_panel_item, parent, false)
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val autoCompletionItem = getAutoCompletionItemAt(position)
            val autoCompletionHelper = getAutoCompletionHelper()
            holder.itemView.setOnClickListener {
                autoCompletionHelper.insertedText(
                    autoCompletionItem,
                    editor,
                    this@DefaultAutoCompletionPanel,
                    getAutoCompletionPublisher().fetchInputText(editor)
                )
            }
            holder.icon.setImageDrawable(
                autoCompletionHelper.getIconDrawable(
                    editor,
                    autoCompletionItem.type
                )
            )
            holder.title.text = autoCompletionItem.title
            holder.type.text = autoCompletionItem.type
        }

        override fun getItemCount(): Int {
            return getAutoCompletionItemSize()
        }

    }

    open inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: ShapeableImageView = itemView.findViewById(R.id.item_icon)
        val title: MaterialTextView = itemView.findViewById(R.id.item_title)
        val type: MaterialTextView = itemView.findViewById(R.id.item_type)
    }

}