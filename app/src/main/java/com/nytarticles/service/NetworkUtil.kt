package com.nytarticles.service

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtil {

    fun isNetworkConnected() : Boolean {
        var cm = MyApp._INSTANCE.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected;
    }

}