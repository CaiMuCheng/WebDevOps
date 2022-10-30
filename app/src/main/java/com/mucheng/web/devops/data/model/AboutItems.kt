package com.mucheng.web.devops.data.model

sealed class AboutItem

data class TitleAboutItem(var title: String) : AboutItem()

data class ClickableAboutItem(var iconRes: Int, var title: String, var tint: Boolean = true) :
    AboutItem()