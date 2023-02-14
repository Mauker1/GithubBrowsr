package br.com.mauker.browsr.lib.organizations.repository

import br.com.mauker.browsr.lib.organizations.datasources.local.GhFavoritesLocalDataSource
import br.com.mauker.browsr.lib.organizations.datasources.remote.GhRemoteDataSource
import br.com.mauker.browsr.lib.organizations.entity.Organization

class GhRepositoryImpl(
    private val ghRemoteDataSource: GhRemoteDataSource,
    private val ghFavoriteLocalDataSource: GhFavoritesLocalDataSource
): GhRepository {

    override suspend fun getGhOrganizations(): List<Organization> {
        val favorites = ghFavoriteLocalDataSource.getFavoriteOrgs()
        return ghRemoteDataSource.getOrganizations().map { org ->
            org.isFav = favorites.contains(org.id)
            org
        }
    }

    override suspend fun setFavorite(orgId: Int) {
        ghFavoriteLocalDataSource.addFavoriteOrg(orgId)
    }

    override suspend fun removeFavorite(orgId: Int) {
        ghFavoriteLocalDataSource.removeFavoriteOrg(orgId)
    }

    override suspend fun removeAllFavorites() {
        ghFavoriteLocalDataSource.removeAllFavoriteOrgs()
    }
}