package com.mucheng.web.devops.dialog

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mucheng.web.devops.support.LanguageKeys
import com.mucheng.web.devops.util.supportedText

class PrivacyPolicyDialog(private val activity: AppCompatActivity) :
    MaterialAlertDialogBuilder(activity) {

    init {
        setTitle(supportedText(LanguageKeys.PrivacyPolicy))
        setMessage(activity.assets.open("protocol/Privacy-policy.txt").bufferedReader().readText())
        setCancelable(false)
        setPositiveButton(supportedText(LanguageKeys.Agree), null)
    }

    override fun setNegativeButton(
        text: CharSequence?,
        listener: DialogInterface.OnClickListener?
    ): PrivacyPolicyDialog {
        super.setNegativeButton(text, listener)
        return this
    }

    override fun setNeutralButton(
        text: CharSequence?,
        listener: DialogInterface.OnClickListener?
    ): PrivacyPolicyDialog {
        super.setNeutralButton(text, listener)
        return this
    }

    override fun setPositiveButton(
        text: CharSequence?,
        listener: DialogInterface.OnClickListener?
    ): PrivacyPolicyDialog {
        super.setPositiveButton(text, listener)
        return this
    }

    override fun show(): AlertDialog {
        val alertDialog = super.show()
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            val sharedPreferences =
                activity.getSharedPreferences("PrivacyPolicy", Context.MODE_PRIVATE)
            sharedPreferences.edit {
                putBoolean("read", true)
            }
            alertDialog.dismiss()
        }
        return alertDialog
    }

    fun require(): PrivacyPolicyDialog {
        val sharedPreferences = activity.getSharedPreferences("PrivacyPolicy", Context.MODE_PRIVATE)
        if (!sharedPreferences.getBoolean("read", false)) {
            show()
        }
        return this
    }

}