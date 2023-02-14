package br.com.mauker.browsr.lib.organizations.datasources.local

import androidx.room.Dao
import androidx.room.Query
import br.com.mauker.browsr.lib.common.database.BaseDAO
import br.com.mauker.browsr.lib.organizations.entity.FavoriteOrg

@Dao
interface GhFavoriteOrgsDAO: BaseDAO<FavoriteOrg> {
    @Query("SELECT * FROM FavoriteOrgs")
    suspend fun fetchAllFavorites(): List<Int>

    @Query("DELETE FROM FavoriteOrgs")
    suspend fun deleteAllFavorites()
}