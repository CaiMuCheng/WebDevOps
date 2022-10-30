package com.mucheng.web.devops.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textview.MaterialTextView
import com.mucheng.web.devops.R
import com.mucheng.web.devops.data.model.ClickableSettingItem
import com.mucheng.web.devops.data.model.ColorSettingItem
import com.mucheng.web.devops.data.model.SettingItem
import com.mucheng.web.devops.data.model.SwitchSettingItem
import com.mucheng.web.devops.data.model.TitleSettingItem
import com.mucheng.web.devops.util.TypefaceUtil

class SettingAdapter(
    private val context: Context,
    private val settingItemList: List<SettingItem>
) : RecyclerView.Adapter<SettingAdapter.BaseViewHolder>() {

    companion object {
        private const val TITLE = 0
        private const val CLICKABLE = 1
        private const val SWITCH = 2
        private const val COLOR = 3
    }

    private val inflater by lazy { LayoutInflater.from(context) }

    interface SettingItemCallback {

        fun onSettingItemClick(view: View, settingItem: ClickableSettingItem, position: Int)

        fun onColorSettingItemClick(view: View, colorSettingItem: ColorSettingItem, position: Int)

        fun onSettingItemChecked(
            view: SwitchMaterial,
            settingItem: SwitchSettingItem,
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

    inner class ColorViewHolder(itemView: View) : BaseViewHolder(itemView) {
        val title: MaterialTextView = itemView.findViewById(R.id.title)
        val message: MaterialTextView = itemView.findViewById(R.id.message)
        val color: MaterialCardView = itemView.findViewById(R.id.color)
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

            COLOR -> {
                ColorViewHolder(
                    inflater.inflate(
                        R.layout.layout_setting_color_item,
                        parent,
                        false
                    )
                )
            }

            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        var settingItem = settingItemList[position]
        when (holder) {
            is ClickableViewHolder -> {
                settingItem = settingItem as ClickableSettingItem
                holder.itemView.setOnClickListener {
                    settingItemCallback?.onSettingItemClick(
                        it,
                        settingItem as ClickableSettingItem, holder.adapterPosition
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

            is SwitchViewHolder -> {
                settingItem = settingItem as SwitchSettingItem
                val typeface = TypefaceUtil.getTypeface()
                holder.switch.isChecked = settingItem.isChecked
                holder.switch.setOnCheckedChangeListener { _, isChecked ->
                    settingItemCallback?.onSettingItemChecked(
                        holder.switch,
                        settingItem as SwitchSettingItem, holder.adapterPosition,
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

            is ColorViewHolder -> {
                settingItem = settingItem as ColorSettingItem

                val typeface = TypefaceUtil.getTypeface()
                holder.title.text = settingItem.title
                holder.message.text = settingItem.description
                holder.title.typeface = typeface
                holder.message.typeface = typeface
                holder.color.setCardBackgroundColor(Color.parseColor(settingItem.color))

                holder.itemView.setOnClickListener {
                    settingItemCallback?.onColorSettingItemClick(
                        it,
                        settingItem as ColorSettingItem, holder.adapterPosition
                    )
                }
            }

            is TitleViewHolder -> {
                settingItem = settingItem as TitleSettingItem
                holder.title.text = settingItem.title
                holder.title.typeface = TypefaceUtil.getTypeface()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (settingItemList[position]) {
            is TitleSettingItem -> TITLE
            is ClickableSettingItem -> CLICKABLE
            is SwitchSettingItem -> SWITCH
            is ColorSettingItem -> COLOR
        }
    }

    override fun getItemCount(): Int {
        return settingItemList.size
    }

}