package br.com.mauker.browsr.lib

import android.content.Context
import androidx.startup.AppInitializer
import br.com.mauker.browsr.lib.organizations.entity.Organization
import br.com.mauker.browsr.lib.organizations.repository.GhRepository
import br.com.mauker.browsr.lib.utils.NetworkUtils
import org.koin.core.Koin
import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.createScope
import org.koin.core.component.inject
import org.koin.core.scope.Scope
import timber.log.Timber

class BrowsrSDK(private val context: Context): BrowsrLib, KoinScopeComponent {

    init {
        Timber.plant(Timber.DebugTree())
    }

    private val browsrKoin: Koin by lazy {
        AppInitializer
            .getInstance(context)
            .initializeComponent(BrowsrDiInitializer::class.java)
    }

    override fun getKoin(): Koin = browsrKoin

    override val scope: Scope by lazy {
        createScope(this)
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