package com.mucheng.web.devops.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.mucheng.web.devops.R
import com.mucheng.web.devops.data.model.FileItem
import com.mucheng.web.devops.ui.view.MaterialTextViewX

class FileSelectorAdapter(
    private val context: Context,
    private val fileList: List<FileItem>
) : RecyclerView.Adapter<FileSelectorAdapter.ViewHolder>() {

    interface FileSelectorCallback {
        fun onFileItemClick(view: View, fileItem: FileItem, position: Int)
        fun onFileItemLongClick(view: View, fileItem: FileItem, position: Int)
    }

    private val inflater by lazy { LayoutInflater.from(context) }

    private var fileSelectorCallback: FileSelectorCallback? = null

    fun setFileSelectorCallback(fileSelectorCallback: FileSelectorCallback) {
        this.fileSelectorCallback = fileSelectorCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.layout_file_selector, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val file = fileList[position]
        val itemView = holder.itemView
        val icon = holder.icon
        val title = holder.title

        itemView.setOnClickListener {
            fileSelectorCallback?.onFileItemClick(it, file, holder.adapterPosition)
        }
        itemView.setOnLongClickListener {
            if (fileSelectorCallback != null) {
                fileSelectorCallback!!.onFileItemLongClick(it, file, holder.adapterPosition)
                true
            } else {
                false
            }
        }

        Glide
            .with(context)
            .load(file.icon)
            .into(icon)

        title.text = file.name
    }

    override fun getItemCount(): Int {
        return fileList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon: ShapeableImageView = itemView.findViewById(R.id.icon)
        val title: MaterialTextViewX = itemView.findViewById(R.id.title)
    }

}