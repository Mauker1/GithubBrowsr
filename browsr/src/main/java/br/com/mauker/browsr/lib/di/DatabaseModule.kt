package br.com.mauker.browsr.lib.di

import androidx.room.Room
import br.com.mauker.browsr.lib.common.database.GhFavoriteOrgsDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            GhFavoriteOrgsDatabase::class.java,
            GhFavoriteOrgsDatabase.NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<GhFavoriteOrgsDatabase>().ghFavoriteOrgsDao() }
}