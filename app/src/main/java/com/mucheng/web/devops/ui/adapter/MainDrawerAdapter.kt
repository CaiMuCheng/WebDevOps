package com.mucheng.web.devops.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.google.android.material.imageview.ShapeableImageView
import com.mucheng.web.devops.R
import com.mucheng.web.devops.data.model.DrawerItem
import com.mucheng.web.devops.ui.view.MaterialTextViewX
import com.mucheng.web.devops.util.Context

class MainDrawerAdapter(
    private val context: Context,
    private val drawerItemList: List<DrawerItem>
) : RecyclerView.Adapter<MainDrawerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: ShapeableImageView = itemView.findViewById(R.id.icon)
        val text: MaterialTextViewX = itemView.findViewById(R.id.text)
    }

    interface OnItemClickListener {
        fun onItemClick(card: MaterialCardView, drawerItem: DrawerItem, position: Int)
    }

    private val inflater by lazy { LayoutInflater.from(context) }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_main_drawer, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val drawerItem = drawerItemList[position]
        val card = holder.itemView as MaterialCardView
        val icon = holder.icon
        val text = holder.text

        card.setOnClickListener {
            onItemClickListener?.onItemClick(card, drawerItem, position)
        }

        // 设置图片
        Glide.with(Context)
            .load(drawerItem.iconRes)
            .into(icon)

        // 设置文字
        text.text = drawerItem.text
    }

    override fun getItemCount(): Int {
        return drawerItemList.size
    }

}