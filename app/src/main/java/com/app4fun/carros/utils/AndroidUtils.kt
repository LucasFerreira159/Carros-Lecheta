package com.app4fun.carros.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo

object AndroidUtils {

    //Verifica se existe conexão com internet
    fun isNetworkAvailable(context: Context): Boolean{
        val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networks = connectivity.allNetworks
        return networks
            .map { connectivity.getNetworkInfo(it) }
            .any { it.state == NetworkInfo.State.CONNECTED}
    }
}