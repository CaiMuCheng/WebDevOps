package com.mucheng.web.devops.openapi.util

import java.net.Inet4Address
import java.net.InetAddress
import java.net.NetworkInterface
import java.util.Enumeration

object NetworkUtil {

    val localIPAddress: String
        get() {
            val en: Enumeration<NetworkInterface> = NetworkInterface.getNetworkInterfaces()
            while (en.hasMoreElements()) {
                val networkInterface: NetworkInterface = en.nextElement()
                val enumIpAddress: Enumeration<InetAddress> = networkInterface.inetAddresses
                while (enumIpAddress.hasMoreElements()) {
                    val inetAddress: InetAddress = enumIpAddress.nextElement()
                    if (!inetAddress.isLoopbackAddress && inetAddress is Inet4Address) {
                        return inetAddress.hostAddress?.toString() ?: "127.0.0.1"
                    }
                }
            }
            return "127.0.0.1"
        }

}