package com.tribal.tribalphotos

import android.app.Application
import com.tribal.tribalphotos.ui.di.TribalPhotosModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TribalApplication() : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
        TribalPhotosModule.init()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@TribalApplication)
        }
    }
}
