package com.mucheng.web.devops.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mucheng.web.devops.R
import com.mucheng.web.devops.support.LanguageKeys
import com.mucheng.web.devops.ui.adapter.ComponentInfoAdapter
import com.mucheng.web.devops.util.supportedText
import com.mucheng.webops.plugin.data.CreateInfo
import com.mucheng.webops.plugin.data.CreateInfoResult
import com.mucheng.webops.plugin.data.info.ComponentInfo

class CreateInfoDialog(context: Context) : MaterialAlertDialogBuilder(context) {

    private val createInfo: MutableList<ComponentInfo> = ArrayList()

    private var createInfoImpl: CreateInfo? = null

    private var adapter: ComponentInfoAdapter? = null

    override fun setTitle(title: CharSequence?): CreateInfoDialog {
        super.setTitle(title)
        return this
    }

    override fun setCancelable(cancelable: Boolean): CreateInfoDialog {
        super.setCancelable(cancelable)
        return this
    }

    override fun setNeutralButton(
        text: CharSequence?,
        listener: DialogInterface.OnClickListener?
    ): CreateInfoDialog {
        super.setNeutralButton(text, listener)
        return this
    }

    init {
        setView(R.layout.layout_component_info)
        setPositiveButton(supportedText(LanguageKeys.PermissionRequestOK), null)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCreateInfo(createInfo: CreateInfo): CreateInfoDialog {
        this.createInfoImpl = createInfo
        this.createInfo.clear()
        this.createInfo.addAll(createInfo.getInfo())
        if (this.adapter != null) {
            adapter!!.notifyDataSetChanged()
        }
        return this
    }

    override fun show(): AlertDialog {
        val alertDialog = super.show()
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            try {
                val close = createInfoImpl?.dispatchConfirm(CreateInfoResult(createInfo)) ?: true
                if (close) {
                    alertDialog.dismiss()
                }
            } catch (e: Throwable) {
                e.printStackTrace()
                alertDialog.dismiss()
                MaterialAlertDialogBuilder(context)
                    .setTitle(supportedText(LanguageKeys.CannotDispatchCreateInfo))
                    .setMessage("${supportedText(LanguageKeys.Exception)}: ${e.stackTraceToString()}")
                    .setPositiveButton(supportedText(LanguageKeys.PermissionRequestOK), null)
                    .setCancelable(false)
                    .show()
            }
        }
        alertDialog.window?.clearFlags(
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM
        )
        alertDialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        val recyclerView: RecyclerView =
            alertDialog.findViewById(R.id.recyclerView) ?: return alertDialog
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        this.adapter = ComponentInfoAdapter(context, createInfo)
        recyclerView.adapter = adapter
        return alertDialog
    }

}