package com.mucheng.web.devops.ui.view

import android.content.Context
import android.content.DialogInterface
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mucheng.web.devops.R
import com.mucheng.web.devops.ui.adapter.ComponentInfoAdapter
import com.mucheng.webops.plugin.data.info.ComponentInfo

class ComposableDialog(context: Context) : MaterialAlertDialogBuilder(context) {

    private val components: MutableList<ComponentInfo> = ArrayList()

    private var adapter: ComponentInfoAdapter? = null

    private var onComplete: ((components: List<ComponentInfo>) -> Boolean)? = null

    init {
        setView(R.layout.layout_component_info)
        setPositiveButton("确定", null)
    }

    override fun setTitle(title: CharSequence?): ComposableDialog {
        super.setTitle(title)
        return this
    }

    override fun setMessage(message: CharSequence?): ComposableDialog {
        super.setMessage(message)
        return this
    }

    override fun setNeutralButton(
        text: CharSequence?,
        listener: DialogInterface.OnClickListener?
    ): ComposableDialog {
        super.setNeutralButton(text, listener)
        return this
    }

    override fun setNegativeButton(
        text: CharSequence?,
        listener: DialogInterface.OnClickListener?
    ): ComposableDialog {
        super.setNegativeButton(text, listener)
        return this
    }

    override fun setPositiveButton(
        text: CharSequence?,
        listener: DialogInterface.OnClickListener?
    ): ComposableDialog {
        super.setPositiveButton(text, listener)
        return this
    }

    override fun setCancelable(cancelable: Boolean): ComposableDialog {
        super.setCancelable(cancelable)
        return this
    }


    fun setComponents(components: List<ComponentInfo>): ComposableDialog {
        this.components.clear()
        this.components.addAll(components)
        return this
    }

    fun onComplete(onComplete: (components: List<ComponentInfo>) -> Boolean): ComposableDialog {
        this.onComplete = onComplete
        return this
    }

    override fun show(): AlertDialog {
        val alertDialog = super.show()
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            try {
                val close = onComplete?.invoke(components) ?: true
                if (close) {
                    alertDialog.dismiss()
                }
            } catch (e: Throwable) {
                e.printStackTrace()
                alertDialog.dismiss()
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
        this.adapter = ComponentInfoAdapter(context, components)
        recyclerView.adapter = adapter
        return alertDialog
    }

}