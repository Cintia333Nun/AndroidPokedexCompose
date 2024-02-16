package com.example.androidpokedexcompose.data.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import java.net.HttpURLConnection
import java.net.URL

object Utils {
    fun clearCache(context: Context) {
        try {
            val cacheDir = context.cacheDir
            cacheDir.listFiles()?.forEach { file ->
                file.deleteRecursively()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun closeApp(context: Context) {
        if (context is Activity) {
            context.finish()
        } else {
            //android.os.Process.killProcess(android.os.Process.myPid())
        }
    }

    fun freeMemory() {
        System.runFinalization()
        Runtime.getRuntime().freeMemory()
        Runtime.getRuntime().gc()
        System.gc()
    }

    fun startGooglePing(context: Context): Boolean {
        var isSuccess = false
        try {
            if (isNetworkEnable(context)) {
                try {
                    val url = URL("https://www.google.com:443/")
                    val urlc = url.openConnection() as HttpURLConnection
                    urlc.setRequestProperty(
                        "User-Agent",
                        "Android Application: PingTest"
                    )
                    urlc.setRequestProperty("Connection", "close")
                    urlc.connectTimeout = 5 * 1000
                    urlc.connect()
                    if (urlc.responseCode < 500 || urlc.responseCode > 504) {
                        isSuccess = true
                    }
                    urlc.disconnect()
                } catch (exception: Exception) {
                    exception.printStackTrace()
                }
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }

        freeMemory()

        return isSuccess
    }

    private fun isNetworkEnable(context: Context): Boolean {
        val wifi = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        var isMobileDataEnabled = false

        try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val cmClass = Class.forName(cm.javaClass.name)
            val method = cmClass.getDeclaredMethod("getMobileDataEnabled")
            method.isAccessible = true
            isMobileDataEnabled = method.invoke(cm) as Boolean
        } catch (e: Exception) {
            e.printStackTrace()
        }

        freeMemory()

        return wifi.isWifiEnabled || isMobileDataEnabled
    }

}

