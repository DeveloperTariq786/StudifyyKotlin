package com.example.studifyy

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
    fun isOnline(context: Context):Boolean{
        val connectivityManager=context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo=connectivityManager.activeNetwork
        val capabilities=connectivityManager.getNetworkCapabilities(networkInfo)
        return capabilities!=null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)

}