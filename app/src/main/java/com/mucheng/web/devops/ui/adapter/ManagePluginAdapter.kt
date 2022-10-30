package com.mucheng.web.devops.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.mucheng.web.devops.R
import com.mucheng.web.devops.plugin.Plugin
import com.mucheng.web.devops.ui.view.MaterialTextViewX

class ManagePluginAdapter(
    private val context: Context,
    private val plugins: List<Plugin>
) : RecyclerView.Adapter<ManagePluginAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card: MaterialCardView = itemView.findViewById(R.id.card)
        val title: MaterialTextViewX = itemView.findViewById(R.id.title)
        val packageName: MaterialTextViewX = itemView.findViewById(R.id.packageName)
    }

    interface ManagePluginCallback {
        fun onManagePlugin(view: View, plugin: Plugin, position: Int)
    }

    private val inflater by lazy { LayoutInflater.from(context) }

    private var managePluginCallback: ManagePluginCallback? = null

    fun setManagePluginCallback(managePluginCallback: ManagePluginCallback) {
        this.managePluginCallback = managePluginCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.item_manage_plugin, parent, false))
    }

    override fun getItemCount(): Int {
        return plugins.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plugin = plugins[position]

        holder.card.setOnClickListener {
            managePluginCallback?.onManagePlugin(it, plugin, holder.adapterPosition)
        }
        holder.title.text = plugin.pluginName
        holder.packageName.text = plugin.packageName
    }

}