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
import com.mucheng.web.devops.data.model.MainCommonItem
import com.mucheng.web.devops.ui.view.MaterialTextViewX

class MainCommonAdapter(
    private val context: Context,
    private val mainCommonItems: List<MainCommonItem>
) : RecyclerView.Adapter<MainCommonAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card: MaterialCardView = itemView.findViewById(R.id.card)
        val icon: ShapeableImageView = itemView.findViewById(R.id.icon)
        val title: MaterialTextViewX = itemView.findViewById(R.id.title)
        val version: MaterialTextViewX = itemView.findViewById(R.id.version)
        val size: MaterialTextViewX = itemView.findViewById(R.id.size)
        val description: MaterialTextViewX = itemView.findViewById(R.id.description)
    }

    interface MainCommonItemCallback {
        fun onMainCommonItemClick(view: View, mainCommonItem: MainCommonItem, position: Int)
    }

    private val inflater by lazy { LayoutInflater.from(context) }

    private var mainCommonItemCallback: MainCommonItemCallback? = null

    fun setMainCommonItemCallback(mainCommonItemCallback: MainCommonItemCallback) {
        this.mainCommonItemCallback = mainCommonItemCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_main_common, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mainCommonItem = mainCommonItems[position]

        holder.card.setOnClickListener {
            mainCommonItemCallback?.onMainCommonItemClick(
                it,
                mainCommonItem,
                holder.adapterPosition
            )
        }
        Glide.with(context)
            .load(mainCommonItem.icon)
            .into(holder.icon)

        holder.title.text = mainCommonItem.title
        holder.version.text = mainCommonItem.version
        holder.size.text = mainCommonItem.size
        holder.description.text = mainCommonItem.description
    }

    override fun getItemCount(): Int {
        return mainCommonItems.size
    }

}