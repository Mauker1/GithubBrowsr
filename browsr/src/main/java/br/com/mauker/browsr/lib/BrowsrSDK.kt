package br.com.mauker.browsr.lib

import android.content.Context
import br.com.mauker.browsr.lib.di.databaseModule
import br.com.mauker.browsr.lib.di.networkModule
import br.com.mauker.browsr.lib.organizations.di.ghOrganizationsModule
import br.com.mauker.browsr.lib.organizations.entity.Organization
import br.com.mauker.browsr.lib.organizations.repository.GhRepository
import br.com.mauker.browsr.lib.utils.NetworkUtils
import br.com.mauker.browsr.lib.utils.di.utilsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import timber.log.Timber

class BrowsrSDK(private val context: Context): BrowsrLib, KoinComponent {

    init {
        startKoin {
            androidContext(context)
            modules(
                databaseModule,
                networkModule,
                ghOrganizationsModule,
                utilsModule
            )
        }
        Timber.plant(Timber.DebugTree())
    }

    private val networkUtils: NetworkUtils by inject()
    private val ghRepository: GhRepository by inject()

    override suspend fun getOrganizations(): List<Organization> {
        return if (networkUtils.isConnected()) {
            return try {
                ghRepository.getGhOrganizations()
            } catch (e: Exception) {
                Timber.e(e)
                emptyList()
            }
        } else {
            emptyList()
        }
    }

    override suspend fun setFavorite(orgId: Int) {
        ghRepository.setFavorite(orgId)
    }

    override suspend fun removeFavorite(orgId: Int) {
        ghRepository.removeFavorite(orgId)
    }

    override suspend fun removeAllFavorites() {
        ghRepository.removeAllFavorites()
    }
}