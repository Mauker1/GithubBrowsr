package br.com.mauker.browsr.lib.organizations.repository

import br.com.mauker.browsr.lib.organizations.entity.Organization

interface GhRepository {
    suspend fun getGhOrganizations(): List<Organization>
    suspend fun setFavorite(orgId: Int)
    suspend fun removeFavorite(orgId: Int)
    suspend fun removeAllFavorites()
}