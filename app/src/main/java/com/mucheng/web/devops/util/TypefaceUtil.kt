package com.mucheng.web.devops.util

import android.graphics.Typeface
import com.mucheng.web.devops.config.GlobalConfig

object TypefaceUtil {

    private var typeface: Typeface

    init {
        val applicationContext = Context
        val assets = applicationContext.assets
        val globalConfig = GlobalConfig.getInstance()
        val typefacePath = globalConfig.getAppTypefacePath()

        typeface = try {
            if (typefacePath == "null") {
                Typeface.createFromAsset(assets, "font/MiSans-Regular.ttf")
            } else {
                try {
                    val checkedTypeface = Typeface.createFromFile(typefacePath)
                    if (checkedTypeface == Typeface.DEFAULT) {
                        Typeface.createFromAsset(assets, "font/MiSans-Regular.ttf")
                    } else {
                        checkedTypeface
                    }
                } catch (e: Throwable) {
                    Typeface.createFromAsset(assets, "font/MiSans-Regular.ttf")
                }
            }
        } catch (e: Throwable) {
            Typeface.DEFAULT
        }
    }

    fun setTypeface(typeface: Typeface) {
        this.typeface = typeface
    }

    fun getTypeface(): Typeface {
        return typeface
    }

}