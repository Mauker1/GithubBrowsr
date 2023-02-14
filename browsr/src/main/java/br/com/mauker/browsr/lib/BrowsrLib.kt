package br.com.mauker.browsr.lib

import br.com.mauker.browsr.lib.organizations.entity.Organization

interface BrowsrLib {
    suspend fun getOrganizations(): List<Organization>
    suspend fun setFavorite(orgId: Int)
    suspend fun removeFavorite(orgId: Int)
    suspend fun removeAllFavorites()
}