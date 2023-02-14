package br.com.mauker.browsr.lib.organizations.datasources.remote

import br.com.mauker.browsr.lib.organizations.entity.Organization

interface GhRemoteDataSource {
    suspend fun getOrganizations(): List<Organization>
}