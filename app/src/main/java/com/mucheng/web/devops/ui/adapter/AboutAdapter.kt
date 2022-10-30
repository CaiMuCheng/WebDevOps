package com.mucheng.web.devops.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView
import com.mucheng.web.devops.R
import com.mucheng.web.devops.data.model.AboutItem
import com.mucheng.web.devops.data.model.ClickableAboutItem
import com.mucheng.web.devops.data.model.TitleAboutItem

class AboutAdapter(
    private val context: Context,
    private val aboutItemList: List<AboutItem>
) : RecyclerView.Adapter<AboutAdapter.BaseViewHolder>() {

    companion object {
        private const val TITLE = 0
        private const val CLICKABLE = 1
    }

    interface AboutItemCallback {
        fun onAboutItemClick(view: View, aboutItem: ClickableAboutItem, position: Int)
    }

    sealed class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class TitleViewHolder(itemView: View) : BaseViewHolder(itemView) {
        val title: MaterialTextView = itemView.findViewById(R.id.title)
    }

    inner class ClickableViewHolder(itemView: View) : BaseViewHolder(itemView) {
        val icon: ShapeableImageView = itemView.findViewById(R.id.icon)
        val title: MaterialTextView = itemView.findViewById(R.id.title)
    }

    private val inflater by lazy { LayoutInflater.from(context) }

    private var aboutItemCallback: AboutItemCallback? = null

    fun setAboutItemCallback(aboutItemCallback: AboutItemCallback) {
        this.aboutItemCallback = aboutItemCallback
    }

    fun getAboutItemCallback(): AboutItemCallback? {
        return aboutItemCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            TITLE -> TitleViewHolder(
                inflater.inflate(
                    R.layout.layout_about_title_item,
                    parent,
                    false
                )
            )

            CLICKABLE -> ClickableViewHolder(
                inflater.inflate(
                    R.layout.layout_about_clickable_item,
                    parent,
                    false
                )
            )

            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        var aboutItem = aboutItemList[position]
        when (holder) {
            is ClickableViewHolder -> {
                aboutItem = aboutItem as ClickableAboutItem
                holder.itemView.setOnClickListener {
                    aboutItemCallback?.onAboutItemClick(
                        it,
                        aboutItem as ClickableAboutItem, position
                    )
                }

                holder.icon.setImageResource(aboutItem.iconRes)
                holder.title.text = aboutItem.title
            }

            is TitleViewHolder -> {
                aboutItem = aboutItem as TitleAboutItem
                holder.title.text = aboutItem.title
            }
        }
    }

    override fun getItemCount(): Int {
        return aboutItemList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (aboutItemList[position]) {
            is ClickableAboutItem -> CLICKABLE
            is TitleAboutItem -> TITLE
        }
    }

}