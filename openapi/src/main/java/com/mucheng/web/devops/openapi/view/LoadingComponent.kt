package com.mucheng.web.devops.openapi.view

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textview.MaterialTextView
import com.mucheng.web.devops.openapi.R

open class LoadingComponent(context: Context) {

    private val alertDialogBuilder: MaterialAlertDialogBuilder

    private var alertDialog: AlertDialog? = null

    private var cachedMessage: CharSequence? = ""

    init {
        alertDialogBuilder = MaterialAlertDialogBuilder(context)
        alertDialogBuilder.setView(R.layout.layout_loading_component)
        alertDialogBuilder.setCancelable(false)
    }

    fun setContent(content: CharSequence?): LoadingComponent {
        if (alertDialog != null) {
            val title: MaterialTextView? = alertDialog!!.findViewById(R.id.title)
            title?.text = content
            return this
        }
        cachedMessage = content
        return this
    }

    fun setNeutralButton(
        text: CharSequence?,
        dialogInterface: DialogInterface.OnClickListener?
    ): LoadingComponent {
        alertDialogBuilder.setNeutralButton(text, dialogInterface)
        return this
    }

    fun setPositiveButton(
        text: CharSequence?,
        dialogInterface: DialogInterface.OnClickListener?
    ): LoadingComponent {
        alertDialogBuilder.setPositiveButton(text, dialogInterface)
        return this
    }

    fun show(): AlertDialog {
        if (alertDialog != null) {
            alertDialog!!.show()
            return alertDialog as AlertDialog
        }
        alertDialog = alertDialogBuilder.show()
        val title: MaterialTextView? = alertDialog!!.findViewById(R.id.title)
        title?.text = cachedMessage
        return alertDialog!!
    }

    open fun dismiss() {
        alertDialog?.dismiss()
    }

}