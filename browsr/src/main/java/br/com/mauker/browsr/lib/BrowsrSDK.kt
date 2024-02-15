package br.com.mauker.browsr.lib

import android.content.Context
import br.com.mauker.browsr.lib.organizations.entity.Organization
import br.com.mauker.browsr.lib.organizations.repository.GhRepository
import br.com.mauker.browsr.lib.utils.NetworkUtils
import org.koin.core.component.inject
import timber.log.Timber

class BrowsrSDK(private val context: Context): BrowsrLib, IsolatedKoinComponent() {

    init {
//        startKoin {
//            androidContext(context)
//            modules(
//                databaseModule,
//                networkModule,
//                ghOrganizationsModule,
//                utilsModule
//            )
//        }
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