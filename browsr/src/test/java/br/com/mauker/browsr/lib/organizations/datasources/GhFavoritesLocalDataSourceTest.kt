package br.com.mauker.browsr.lib.organizations.datasources

import br.com.mauker.browsr.lib.coVerifyNever
import br.com.mauker.browsr.lib.coVerifyOnce
import br.com.mauker.browsr.lib.organizations.datasources.local.GhFavoriteOrgsDAO
import br.com.mauker.browsr.lib.organizations.datasources.local.GhFavoritesLocalDataSource
import br.com.mauker.browsr.lib.organizations.datasources.local.GhFavoritesLocalDataSourceImpl
import br.com.mauker.browsr.lib.organizations.entity.FavoriteOrg
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import org.junit.AfterClass
import org.junit.Before
import org.junit.Test

class GhFavoritesLocalDataSourceTest {
    private lateinit var dataSource: GhFavoritesLocalDataSource
    private val dao: GhFavoriteOrgsDAO = mockk(relaxed = true)

    @Before
    fun setup() {
        clearAllMocks()
        dataSource = GhFavoritesLocalDataSourceImpl(dao)
    }

    @Test
    fun `when favorite is called, should call DAO`() {
        coEvery { dao.insert(any()) } returns 1L

        runBlocking {
            dataSource.addFavoriteOrg(1)

            coVerifyOnce { dao.insert(FAVORITE_ORG) }
            coVerifyNever { dao.delete(FAVORITE_ORG) }
            confirmVerified(dao)
        }
    }

    @Test
    fun `when remove favorite is called, should call DAO`() {
        coEvery { dao.delete(any()) } returns 1

        runBlocking {
            dataSource.removeFavoriteOrg(1)

            coVerifyOnce { dao.delete(FAVORITE_ORG) }
            coVerifyNever { dao.insert(FAVORITE_ORG) }
            confirmVerified(dao)
        }
    }

    @Test
    fun `when remove all is called, should call DAO`() {
        runBlocking {
            dataSource.removeAllFavoriteOrgs()

            coVerify { dao.deleteAllFavorites() }
            coVerifyNever { dao.insert(FAVORITE_ORG) }
            coVerifyNever { dao.delete(FAVORITE_ORG) }
            confirmVerified(dao)
        }
    }

    companion object {
        private val FAVORITE_ORG = FavoriteOrg(1)

        @JvmStatic
        @AfterClass
        fun tearDown() {
            unmockkAll()
        }
    }
}