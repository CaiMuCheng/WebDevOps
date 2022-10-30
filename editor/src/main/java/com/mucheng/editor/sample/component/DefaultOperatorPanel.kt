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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.mucheng.editor.R
import com.mucheng.editor.base.AbstractTheme
import com.mucheng.editor.base.panel.AbstractOperatorPanel
import com.mucheng.editor.data.OperatorItem
import com.mucheng.editor.event.ThemeUpdateEvent
import com.mucheng.editor.token.ThemeToken
import com.mucheng.editor.tool.dp
import com.mucheng.editor.view.MuCodeEditor

@Suppress("LeakingThis")
open class DefaultOperatorPanel(editor: MuCodeEditor) : AbstractOperatorPanel(editor),
    ThemeUpdateEvent {

    private val inflater by lazy { LayoutInflater.from(editor.context) }

    private lateinit var content: View

    private val adapter by lazy { Adapter() }

    init {
        editor.eventManager.subscribeEvent(ThemeUpdateEvent::class.java, this)
        animationStyle = androidx.appcompat.R.style.Animation_AppCompat_Dialog
        isTouchable = true
        isFocusable = false

        width = editor.context.resources.displayMetrics.widthPixels
        height = editor.context.dp(55f).toInt()

        setBackgroundDrawable(null)

        inputMethodMode = INPUT_METHOD_NEEDED
        softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN

        addOperatorItem(OperatorItem("→", "   "))
        addOperatorItem(OperatorItem(","))
        addOperatorItem(OperatorItem("."))
        addOperatorItem(OperatorItem("{"))
        addOperatorItem(OperatorItem("}"))
        addOperatorItem(OperatorItem("("))
        addOperatorItem(OperatorItem(")"))
        addOperatorItem(OperatorItem(";"))
        addOperatorItem(OperatorItem("="))
        addOperatorItem(OperatorItem("\""))
        addOperatorItem(OperatorItem("|"))
        addOperatorItem(OperatorItem("&"))
        addOperatorItem(OperatorItem("!"))
        addOperatorItem(OperatorItem("["))
        addOperatorItem(OperatorItem("]"))
        addOperatorItem(OperatorItem("<"))
        addOperatorItem(OperatorItem(">"))
        addOperatorItem(OperatorItem("+"))
        addOperatorItem(OperatorItem("-"))
        addOperatorItem(OperatorItem("/"))
        addOperatorItem(OperatorItem("*"))
        addOperatorItem(OperatorItem("?"))
        addOperatorItem(OperatorItem(":"))
        addOperatorItem(OperatorItem("_"))

        createContentView()
        contentView = content
    }

    override fun onThemeUpdate(theme: AbstractTheme) {
        if (!::content.isInitialized) {
            return
        }

        val cardView: CardView = content.findViewById(R.id.cardView)
        cardView.setCardBackgroundColor(theme.getColor(ThemeToken.OPERATOR_PANEL_BACKGROUND_COLOR).hexColor)
    }

    @SuppressLint("InflateParams")
    private fun createContentView() {
        content = inflater.inflate(R.layout.layout_operator_panel_content, null)
        val theme = editor.getTheme()
        onThemeUpdate(theme)

        val recyclerView: RecyclerView = content.findViewById(R.id.operatorRecyclerView)
        recyclerView.layoutManager =
            LinearLayoutManager(editor.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun notifyOperatorItemChanged() {
        editor.post {
            adapter.notifyDataSetChanged()
        }
    }

    open inner class Adapter : RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(inflater.inflate(R.layout.layout_operator_panel_item, parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val operatorItem = getOperatorItem(position)
            holder.itemView.setOnClickListener {
                editor.insertText(operatorItem.insertedText)
            }
            holder.title.text = operatorItem.title
        }

        override fun getItemCount(): Int {
            return getOperatorItemSize()
        }

    }

    open inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: MaterialTextView = itemView.findViewById(R.id.operatorTitle)
    }

}