package br.com.mauker.browsr.lib

import android.content.Context
import br.com.mauker.browsr.lib.di.databaseModule
import br.com.mauker.browsr.lib.di.networkModule
import br.com.mauker.browsr.lib.organizations.di.ghOrganizationsModule
import br.com.mauker.browsr.lib.utils.di.utilsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.dsl.koinApplication

internal class DILibContext(context: Context) {

    companion object {
        var instance: DILibContext? = null
            get() {
                if (field == null) {
                    throw IllegalStateException("DILibContext has not been initialized")
                }
                return field!!
            }

        fun init(context: Context) {
            instance = DILibContext(context)
        }
    }

    val koinApp: KoinApplication by lazy {
        koinApplication {
            androidContext(context)
            modules(
                databaseModule,
                networkModule,
                ghOrganizationsModule,
                utilsModule
            )
        }
    }

    val koin = koinApp.koin
}