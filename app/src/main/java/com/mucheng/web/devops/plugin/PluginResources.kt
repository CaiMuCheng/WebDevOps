package com.mucheng.web.devops.plugin

import android.annotation.SuppressLint
import android.content.res.AssetManager
import android.content.res.Configuration
import android.content.res.Resources
import android.content.res.XmlResourceParser
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import androidx.annotation.Keep

@Keep
@Suppress("DEPRECATION")
class PluginResources(assets: AssetManager?, metrics: DisplayMetrics?, config: Configuration?) :
    Resources(assets, metrics, config) {

    private lateinit var injectResources: Resources

    constructor(injectResources: Resources) : this(
        injectResources.assets, injectResources.displayMetrics, injectResources.configuration
    ) {
        this.injectResources = injectResources
    }

    override fun getString(id: Int): String {
        return injectResources.getString(id)
    }

    override fun getLayout(id: Int): XmlResourceParser {
        return injectResources.getLayout(id)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Deprecated(
        "Deprecated in Java",
        ReplaceWith("super.getDrawable(id)", "android.content.res.Resources")
    )
    override fun getDrawable(id: Int): Drawable {
        return injectResources.getDrawable(id)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun getDrawable(id: Int, theme: Theme?): Drawable {
        return injectResources.getDrawable(id, theme)
    }

    @Deprecated("Deprecated in Java")
    override fun getColor(id: Int): Int {
        return injectResources.getColor(id)
    }

}