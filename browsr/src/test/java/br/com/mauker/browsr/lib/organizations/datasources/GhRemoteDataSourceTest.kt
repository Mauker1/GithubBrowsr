package br.com.mauker.browsr.lib.organizations.datasources

import br.com.mauker.browsr.lib.EMPTY_STRING
import br.com.mauker.browsr.lib.coVerifyOnce
import br.com.mauker.browsr.lib.organizations.datasources.remote.GhRemoteDataSource
import br.com.mauker.browsr.lib.organizations.datasources.remote.GhRemoteDataSourceImpl
import br.com.mauker.browsr.lib.organizations.entity.Organization
import br.com.mauker.browsr.lib.organizations.service.GhOrganizationService
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import org.junit.AfterClass
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GhRemoteDataSourceTest {
    private lateinit var remoteDataSource: GhRemoteDataSource
    private val service: GhOrganizationService = mockk(relaxed = true)

    @Before
    fun setup() {
        clearAllMocks()

        remoteDataSource = GhRemoteDataSourceImpl(ghOrganizationService = service)
    }

    @Test
    fun `when request is made, should call service`() {
        coEvery { service.getOrganizations() } returns emptyList()

        runBlocking {
            remoteDataSource.getOrganizations()

            coVerifyOnce { service.getOrganizations() }
            confirmVerified(service)
        }
    }

    @Test
    fun `when request is made, should return list`() {
        coEvery { service.getOrganizations() } returns orgList

        runBlocking {
            val ret = remoteDataSource.getOrganizations()

            assertEquals(orgList, ret)
        }
    }

    companion object {

        private val orgList = listOf(
            Organization(
                login = "Organization",
                id = 1,
                nodeId = EMPTY_STRING,
                url = EMPTY_STRING,
                reposUrl = EMPTY_STRING,
                eventsUrl = EMPTY_STRING,
                hooksUrl = EMPTY_STRING,
                issuesUrl = EMPTY_STRING,
                membersUrl = EMPTY_STRING,
                publicMembersUrl = EMPTY_STRING,
                avatarUrl = EMPTY_STRING,
                description = EMPTY_STRING
            )
        )

        @JvmStatic
        @AfterClass
        fun tearDown() {
            unmockkAll()
        }
    }
}