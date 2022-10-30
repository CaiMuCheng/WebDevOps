package com.mucheng.web.devops.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.google.android.material.imageview.ShapeableImageView
import com.mucheng.web.devops.R
import com.mucheng.web.devops.ui.view.MaterialTextViewX
import com.mucheng.webops.plugin.data.Project

class SelectNewProjectAdapter(
    private val context: Context,
    private val projectList: List<Project>
) : RecyclerView.Adapter<SelectNewProjectAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card: MaterialCardView = itemView.findViewById(R.id.card)
        val icon: ShapeableImageView = itemView.findViewById(R.id.icon)
        val title: MaterialTextViewX = itemView.findViewById(R.id.title)
        val description: MaterialTextViewX = itemView.findViewById(R.id.description)
    }

    interface OnSelectNewProjectListener {
        fun onSelectNewProject(view: View, project: Project, position: Int)
    }

    private val inflater by lazy { LayoutInflater.from(context) }

    private var onSelectNewProjectListener: OnSelectNewProjectListener? = null

    fun setOnSelectNewProjectListener(onSelectNewProjectListener: OnSelectNewProjectListener) {
        this.onSelectNewProjectListener = onSelectNewProjectListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_new_project, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val project = projectList[position]
        Glide.with(context)
            .load(project.icon ?: ContextCompat.getDrawable(context, R.mipmap.ic_launcher))
            .into(holder.icon)

        holder.title.text = project.name
        holder.description.text = project.description
        holder.card.setOnClickListener {
            onSelectNewProjectListener?.onSelectNewProject(it, project, position)
        }
    }

    override fun getItemCount(): Int {
        return projectList.size
    }

}