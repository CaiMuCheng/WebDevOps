package com.mucheng.webops.plugin.data

sealed class PluginConfigItem {

    data class TitleItem(var title: String) : PluginConfigItem()

    data class ClickableItem(var title: String, var description: String) : PluginConfigItem()

    data class SwitchItem(var title: String, var description: String, var isChecked: Boolean) :
        PluginConfigItem()

}
