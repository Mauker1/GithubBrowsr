package br.com.mauker.browsr.lib.organizations.datasources.local

interface GhFavoritesLocalDataSource {
    suspend fun getFavoriteOrgs(): List<Int>
    suspend fun addFavoriteOrg(orgId: Int)
    suspend fun removeFavoriteOrg(orgId: Int)
    suspend fun removeAllFavoriteOrgs()
}