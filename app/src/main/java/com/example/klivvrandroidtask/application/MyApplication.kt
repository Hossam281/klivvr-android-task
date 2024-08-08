package com.example.klivvrandroidtask.application

import android.app.Application
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Check the current theme configuration and set the night mode accordingly

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }
}
