package br.com.mauker.browsr.lib.organizations.datasources.remote

import br.com.mauker.browsr.lib.organizations.entity.Organization
import br.com.mauker.browsr.lib.organizations.service.GhOrganizationService

class GhRemoteDataSourceImpl(
    private val ghOrganizationService: GhOrganizationService
): GhRemoteDataSource {

    override suspend fun getOrganizations(): List<Organization> {
        return ghOrganizationService.getOrganizations()
    }
}