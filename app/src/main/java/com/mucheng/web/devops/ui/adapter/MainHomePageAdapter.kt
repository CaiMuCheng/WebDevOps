package com.mucheng.web.devops.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mucheng.web.devops.R
import com.mucheng.web.devops.ui.view.MaterialTextViewX
import com.mucheng.webops.plugin.data.Workspace

class MainHomePageAdapter(
    private val context: Context,
    private val list: List<Workspace>
) : RecyclerView.Adapter<MainHomePageAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: MaterialTextViewX = itemView.findViewById(R.id.title)
        val time: MaterialTextViewX = itemView.findViewById(R.id.time)
    }

    interface OnActionListener {
        fun onClick(view: View, workspace: Workspace, position: Int)
        fun onLongClick(view: View, workspace: Workspace, position: Int)
    }

    private val inflater by lazy { LayoutInflater.from(context) }

    private var onActionListener: OnActionListener? = null

    fun setOnActionListener(onActionListener: OnActionListener) {
        this.onActionListener = onActionListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_main_home_page, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val workspace = list[position]
        holder.itemView.setOnClickListener {
            onActionListener?.onClick(it, workspace, holder.adapterPosition)
        }
        holder.itemView.setOnLongClickListener {
            if (onActionListener != null) {
                onActionListener!!.onLongClick(it, workspace, holder.adapterPosition)
                true
            } else {
                false
            }
        }
        holder.title.text = workspace.getName()
        holder.time.text = workspace.getCreationTime()
    }

    override fun getItemCount(): Int {
        return list.size
    }

}