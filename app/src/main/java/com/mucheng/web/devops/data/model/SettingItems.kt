package com.mucheng.web.devops.data.model

sealed class SettingItem

data class TitleSettingItem(var title: String) : SettingItem()

data class ClickableSettingItem(var title: String, var description: String) : SettingItem()

data class SwitchSettingItem(
    var title: String,
    var description: String,
    var isChecked: Boolean = false
) : SettingItem()

data class ColorSettingItem(var title: String, var description: String, var color: String) :
    SettingItem()