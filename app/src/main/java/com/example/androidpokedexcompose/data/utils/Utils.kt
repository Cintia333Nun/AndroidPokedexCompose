package com.example.androidpokedexcompose.data.utils

import android.app.Activity
import android.content.Context
import kotlinx.coroutines.launch

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
}

