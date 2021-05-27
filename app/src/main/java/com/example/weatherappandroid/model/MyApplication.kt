package com.example.weatherappandroid.model

import android.app.Application

class MyApplication : android.app.Application() {

    companion object {
        lateinit var instance: Application
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
    }
}