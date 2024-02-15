package br.com.mauker.browsr

import android.app.Application
import br.com.mauker.browsr.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BrowsrApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@BrowsrApplication)
            modules(appModule)
        }
    }
}