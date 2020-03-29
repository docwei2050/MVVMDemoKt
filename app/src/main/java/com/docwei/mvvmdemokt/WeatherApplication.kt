package com.docwei.mvvmdemokt

import android.app.Application

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this;
    }

    companion object {
        lateinit var context: Application;
    }

}