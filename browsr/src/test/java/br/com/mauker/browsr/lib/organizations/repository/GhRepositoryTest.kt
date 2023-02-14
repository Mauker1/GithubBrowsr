package br.com.mauker.browsr.lib.organizations.repository

import br.com.mauker.browsr.lib.coVerifyOnce
import br.com.mauker.browsr.lib.organizations.datasources.local.GhFavoritesLocalDataSource
import br.com.mauker.browsr.lib.organizations.datasources.remote.GhRemoteDataSource
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import org.junit.AfterClass
import org.junit.Before
import org.junit.Test

class GhRepositoryTest {
    private lateinit var repository: GhRepository
    private val remoteDataSource: GhRemoteDataSource = mockk(relaxed = true)
    private val ghFavoritesLocalDataSource: GhFavoritesLocalDataSource = mockk(relaxed = true)

    @Before
    fun setup() {
        clearAllMocks()
        repository = GhRepositoryImpl(ghRemoteDataSource = remoteDataSource, ghFavoritesLocalDataSource)
    }

    @Test
    fun `when fetching orgs, data sources should be called`() {
        coEvery { remoteDataSource.getOrganizations() } returns emptyList()
        coEvery { ghFavoritesLocalDataSource.getFavoriteOrgs() } returns emptyList()

        runBlocking {
            repository.getGhOrganizations()

            coVerifyOnce { remoteDataSource.getOrganizations() }
            coVerifyOnce { ghFavoritesLocalDataSource.getFavoriteOrgs() }
            confirmVerified(remoteDataSource)
            confirmVerified(ghFavoritesLocalDataSource)
        }
    }

    companion object {
        @JvmStatic
        @AfterClass
        fun tearDown() {
            unmockkAll()
        }
    }
}