package br.com.mauker.browsr.lib.organizations.datasources.local

import br.com.mauker.browsr.lib.organizations.entity.FavoriteOrg

class GhFavoritesLocalDataSourceImpl(private val dao: GhFavoriteOrgsDAO): GhFavoritesLocalDataSource {

    override suspend fun getFavoriteOrgs(): List<Int> {
        return dao.fetchAllFavorites()
    }

    override suspend fun addFavoriteOrg(orgId: Int) {
        dao.insert(FavoriteOrg(orgId))
    }

    override suspend fun removeFavoriteOrg(orgId: Int) {
        dao.delete(FavoriteOrg(orgId))
    }

    override suspend fun removeAllFavoriteOrgs() {
        dao.deleteAllFavorites()
    }
}