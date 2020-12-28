package com.nytarticles.service

import android.app.Application

class MyApp : Application() {

    companion object {
        lateinit var _INSTANCE: MyApp
    }

    override fun onCreate() {
        super.onCreate()
        _INSTANCE = this
    }
}