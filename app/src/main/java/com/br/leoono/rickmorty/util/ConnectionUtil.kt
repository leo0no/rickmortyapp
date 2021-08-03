package com.br.leoono.rickmorty.util

import android.content.Context
import android.net.ConnectivityManager

object ConnectionUtil {
    fun isConnected(context: Context): Boolean {
        // Local variables
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        if (activeNetwork != null) {
            when (activeNetwork.type) {
                ConnectivityManager.TYPE_WIFI, ConnectivityManager.TYPE_MOBILE -> return true
                else -> {
                }
            }
        } else {
            return false
        }
        return false
    }
}