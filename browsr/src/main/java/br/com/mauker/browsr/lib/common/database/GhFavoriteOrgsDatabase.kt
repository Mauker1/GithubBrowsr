package br.com.mauker.browsr.lib.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.mauker.browsr.lib.organizations.datasources.local.GhFavoriteOrgsDAO
import br.com.mauker.browsr.lib.organizations.entity.FavoriteOrg

@Database(
    entities = [
        FavoriteOrg::class
    ],
    exportSchema = false,
    version = 1
)
abstract class GhFavoriteOrgsDatabase: RoomDatabase() {

    abstract fun ghFavoriteOrgsDao(): GhFavoriteOrgsDAO

    companion object {
        const val NAME = "gh_favorite_orgs.db"
    }
}