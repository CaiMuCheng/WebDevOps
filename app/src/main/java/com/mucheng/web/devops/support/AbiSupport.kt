package com.mucheng.web.devops.support

import android.os.Build

object AbiSupport {

    @Suppress("NOTHING_TO_INLINE")
    inline fun getCPUAbi(): String {
        // 这个是设备支持的 ABI
        return Build.SUPPORTED_ABIS[0]
    }

}