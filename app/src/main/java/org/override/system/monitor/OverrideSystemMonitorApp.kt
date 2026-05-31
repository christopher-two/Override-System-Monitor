package org.override.system.monitor

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.override.system.monitor.di.appModule

class OverrideSystemMonitorApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@OverrideSystemMonitorApp)
            modules(appModule)
        }
    }
}