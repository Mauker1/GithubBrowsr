package br.com.mauker.browsr.lib

import br.com.mauker.browsr.lib.di.databaseModule
import br.com.mauker.browsr.lib.di.networkModule
import br.com.mauker.browsr.lib.organizations.di.ghOrganizationsModule
import br.com.mauker.browsr.lib.utils.di.utilsModule
import org.koin.dsl.koinApplication

object DILibContext {

    val koinApp = koinApplication {
        modules(
            databaseModule,
            networkModule,
            ghOrganizationsModule,
            utilsModule
        )
    }

    val koin = koinApp.koin
}