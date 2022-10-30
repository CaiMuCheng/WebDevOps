package com.mucheng.webops.plugin.data

import androidx.appcompat.app.AppCompatActivity
import com.mucheng.webops.plugin.data.info.ComponentInfo

class CreateInfo(
    val projectId: String,
    val activity: AppCompatActivity
) {

    private val info: MutableList<ComponentInfo> = ArrayList()

    private var onConfirmListener: ((result: CreateInfoResult) -> Boolean)? = null

    fun addTitleInfo(title: String): CreateInfo {
        info.add(ComponentInfo.TitleInfo(title))
        return this
    }

    fun addInputInfo(
        title: String? = null,
        hint: String? = null,
        isSingleLine: Boolean = true
    ): CreateInfo {
        info.add(ComponentInfo.InputInfo(title, hint, isSingleLine))
        return this
    }

    fun addSelectorInfo(
        items: Array<String>,
        position: Int = 0
    ): CreateInfo {
        if (items.isEmpty()) {
            throw IllegalArgumentException("The SelectorInfo items size must be > 0")
        }
        info.add(ComponentInfo.SelectorInfo(items, position))
        return this
    }

    fun onConfirm(onConfirmListener: ((result: CreateInfoResult) -> Boolean)) {
        this.onConfirmListener = onConfirmListener
    }

    fun dispatchConfirm(createInfoResult: CreateInfoResult): Boolean {
        return onConfirmListener?.invoke(createInfoResult) ?: true
    }

    fun getInfo(): List<ComponentInfo> {
        return info
    }

}