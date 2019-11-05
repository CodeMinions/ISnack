package me.codeminions.isnack

import android.app.Application
import android.content.Context

class MyApplication : Application() {

//    lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        lateinit var context: Context
    }
}