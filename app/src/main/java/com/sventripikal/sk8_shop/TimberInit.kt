package com.sventripikal.sk8_shop

import android.app.Application
import timber.log.Timber

// Timber initializer
class TimberInit: Application() {
    override fun onCreate() {
        super.onCreate()
        // enable Timber logging
        Timber.plant(Timber.DebugTree())
    }
}