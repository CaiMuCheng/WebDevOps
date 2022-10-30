package com.mucheng.web.devops.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textview.MaterialTextView
import com.mucheng.web.devops.R
import com.mucheng.web.devops.util.TypefaceUtil
import com.mucheng.webops.plugin.data.PluginConfigItem

class PluginConfigAdapter(
    private val context: Context,
    private val settingItemList: List<PluginConfigItem>
) : RecyclerView.Adapter<PluginConfigAdapter.BaseViewHolder>() {

    companion object {
        private const val TITLE = 0
        private const val CLICKABLE = 1
        private const val SWITCH = 2
    }

    private val inflater by lazy { LayoutInflater.from(context) }

    interface SettingItemCallback {

        fun onSettingItemClick(
            view: View,
            settingItem: PluginConfigItem.ClickableItem,
            position: Int
        )

        fun onSettingItemChecked(
            view: SwitchMaterial,
            settingItem: PluginConfigItem.SwitchItem,
            position: Int,
            isChecked: Boolean
        )

    }

    sealed class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class TitleViewHolder(itemView: View) : BaseViewHolder(itemView) {
        val title: MaterialTextView = itemView.findViewById(R.id.title)
    }

    inner class ClickableViewHolder(itemView: View) : BaseViewHolder(itemView) {
        val title: MaterialTextView = itemView.findViewById(R.id.title)
        val message: MaterialTextView = itemView.findViewById(R.id.message)
    }

    inner class SwitchViewHolder(itemView: View) : BaseViewHolder(itemView) {
        val title: MaterialTextView = itemView.findViewById(R.id.title)
        val message: MaterialTextView = itemView.findViewById(R.id.message)
        val switch: SwitchMaterial = itemView.findViewById(R.id.switchBtn)
    }

    private var settingItemCallback: SettingItemCallback? = null

    fun setSettingItemCallback(callback: SettingItemCallback) {
        this.settingItemCallback = callback
    }

    fun getSettingItemCallback(): SettingItemCallback? {
        return settingItemCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            TITLE -> TitleViewHolder(
                inflater.inflate(
                    R.layout.layout_setting_title_item,
                    parent,
                    false
                )
            )

            CLICKABLE -> ClickableViewHolder(
                inflater.inflate(
                    R.layout.layout_setting_clickable_item,
                    parent,
                    false
                )
            )

            SWITCH -> SwitchViewHolder(
                inflater.inflate(
                    R.layout.layout_setting_switch_item,
                    parent,
                    false
                )
            )

            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        var settingItem = settingItemList[position]
        when (holder) {
            is PluginConfigAdapter.ClickableViewHolder -> {
                settingItem = settingItem as PluginConfigItem.ClickableItem
                holder.itemView.setOnClickListener {
                    settingItemCallback?.onSettingItemClick(
                        it,
                        settingItem as PluginConfigItem.ClickableItem, holder.adapterPosition
                    )
                }
                val typeface = TypefaceUtil.getTypeface()
                holder.title.text = settingItem.title
                holder.message.text = settingItem.description
                holder.title.typeface = typeface
                holder.message.typeface = typeface
                if (settingItem.description.isEmpty()) {
                    holder.message.visibility = View.GONE
                }
            }

            is PluginConfigAdapter.SwitchViewHolder -> {
                settingItem = settingItem as PluginConfigItem.SwitchItem
                val typeface = TypefaceUtil.getTypeface()
                holder.switch.isChecked = settingItem.isChecked
                holder.switch.setOnCheckedChangeListener { _, isChecked ->
                    settingItemCallback?.onSettingItemChecked(
                        holder.switch,
                        settingItem as PluginConfigItem.SwitchItem, holder.adapterPosition,
                        isChecked
                    )
                }
                holder.title.text = settingItem.title
                holder.message.text = settingItem.description
                holder.title.typeface = typeface
                holder.message.typeface = typeface
                holder.switch.typeface = typeface
                holder.itemView.setOnClickListener {
                    holder.switch.isChecked = !holder.switch.isChecked
                }
                if (settingItem.description.isEmpty()) {
                    holder.message.visibility = View.GONE
                }
            }

            is PluginConfigAdapter.TitleViewHolder -> {
                settingItem = settingItem as PluginConfigItem.TitleItem
                holder.title.text = settingItem.title
                holder.title.typeface = TypefaceUtil.getTypeface()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (settingItemList[position]) {
            is PluginConfigItem.TitleItem -> TITLE
            is PluginConfigItem.ClickableItem -> CLICKABLE
            is PluginConfigItem.SwitchItem -> SWITCH
        }
    }

    override fun getItemCount(): Int {
        return settingItemList.size
    }

}