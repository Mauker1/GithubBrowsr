package br.com.mauker.browsr.lib.organizations.service

import br.com.mauker.browsr.lib.ACCEPT_JSON_GH_V3
import br.com.mauker.browsr.lib.HEADER_ACCEPT
import br.com.mauker.browsr.lib.organizations.entity.Organization
import retrofit2.http.GET
import retrofit2.http.Header

interface GhOrganizationService {

    @GET(PATH_ORGANIZATIONS)
    suspend fun getOrganizations(
        @Header(HEADER_ACCEPT) accept: String = ACCEPT_JSON_GH_V3
    ): List<Organization>

    companion object {
        private const val PATH_ORGANIZATIONS = "organizations"
    }
}