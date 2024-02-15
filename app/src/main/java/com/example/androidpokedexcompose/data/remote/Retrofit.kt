package com.example.androidpokedexcompose.data.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import com.example.androidpokedexcompose.data.utils.Utils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.TimeUnit

//@Module
//@InstallIn(SingletonComponent::class)
object Retrofit {
    //@Provides
    //@Singleton
    fun provideApiRest(endPoint: String): ApiPokemonsInterface {
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        val rest = Retrofit.Builder()
            .baseUrl(endPoint)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return rest.create(ApiPokemonsInterface::class.java)
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

        Utils.freeMemory()

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

        Utils.freeMemory()

        return wifi.isWifiEnabled || isMobileDataEnabled
    }
}