package com.mucheng.web.devops.openapi.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

object TimeUtil {

    @SuppressLint("SimpleDateFormat")
    private val formatter = SimpleDateFormat("yyyy/MM/dd - HH:mm:ss")

    fun getFormattedTime(): String {
        return formatter.format(Date())
    }

}