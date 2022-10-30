package com.mucheng.web.devops.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.mucheng.web.devops.R
import com.mucheng.web.devops.ui.view.MaterialTextViewX
import com.mucheng.web.devops.ui.view.SelectorView
import com.mucheng.web.devops.ui.view.TextInputEditTextX
import com.mucheng.webops.plugin.data.info.ComponentInfo

class ComponentInfoAdapter(
    private val context: Context,
    private val createInfoList: List<ComponentInfo>
) : RecyclerView.Adapter<ComponentInfoAdapter.ViewHolder>() {

    companion object {
        private const val TitleInfo = 1
        private const val InputInfo = 2
        private const val SelectorInfo = 3
    }

    sealed class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class TitleViewHolder(itemView: View) : ViewHolder(itemView) {
        val title: MaterialTextViewX = itemView.findViewById(R.id.title)
    }

    class InputViewHolder(itemView: View) : ViewHolder(itemView) {
        val input: TextInputEditTextX = itemView.findViewById(R.id.input)
    }

    class SelectorViewHolder(itemView: View) : ViewHolder(itemView) {
        val selector: SelectorView = itemView.findViewById(R.id.selector)
    }

    private val inflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            TitleInfo -> {
                TitleViewHolder(inflater.inflate(R.layout.item_create_info_title, parent, false))
            }

            InputInfo -> {
                InputViewHolder(inflater.inflate(R.layout.item_create_info_input, parent, false))
            }

            SelectorInfo -> {
                SelectorViewHolder(
                    inflater.inflate(
                        R.layout.item_create_info_selector,
                        parent,
                        false
                    )
                )
            }

            else -> throw RuntimeException("Stub!")
        }
    }

    override fun getItemCount(): Int {
        return createInfoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var createInfo = createInfoList[position]
        when (holder) {
            is TitleViewHolder -> {
                createInfo = createInfo as ComponentInfo.TitleInfo
                holder.title.text = createInfo.title
            }

            is InputViewHolder -> {
                createInfo = createInfo as ComponentInfo.InputInfo
                holder.input.setText(createInfo.title)
                holder.input.hint = createInfo.hint
                holder.input.addTextChangedListener(onTextChanged = { charSequence, _, _, _ ->
                    val text = charSequence?.toString() ?: ""
                    (createInfo as ComponentInfo.InputInfo).title = text
                })
                holder.input.isSingleLine = createInfo.isSingleLine
            }

            is SelectorViewHolder -> {
                createInfo = createInfo as ComponentInfo.SelectorInfo
                holder.selector.setData(createInfo.items, createInfo.position)
                holder.selector.setOnMenuItemClickListener(object :
                    SelectorView.OnMenuItemClickListener {

                    override fun onMenuItemClick(currentText: String, position: Int): Boolean {
                        createInfo.position = position
                        return true
                    }

                })
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (createInfoList[position]) {
            is ComponentInfo.TitleInfo -> TitleInfo
            is ComponentInfo.InputInfo -> InputInfo
            is ComponentInfo.SelectorInfo -> SelectorInfo
        }
    }

}