package br.com.mauker.browsr.lib

import android.content.Context
import androidx.startup.Initializer
import br.com.mauker.browsr.lib.di.databaseModule
import br.com.mauker.browsr.lib.di.networkModule
import br.com.mauker.browsr.lib.organizations.di.ghOrganizationsModule
import br.com.mauker.browsr.lib.utils.di.utilsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.Koin
import org.koin.dsl.koinApplication

class BrowsrDiInitializer: Initializer<Koin> {
    override fun create(context: Context): Koin {
        return koinApplication {
            androidContext(context)
            androidLogger()
            modules(
                databaseModule,
                networkModule,
                ghOrganizationsModule,
                utilsModule
            )
        }.koin
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> =
        mutableListOf()
}