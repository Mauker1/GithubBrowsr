package br.com.mauker.browsr.lib.organizations.di

import br.com.mauker.browsr.lib.organizations.datasources.local.GhFavoritesLocalDataSource
import br.com.mauker.browsr.lib.organizations.datasources.local.GhFavoritesLocalDataSourceImpl
import br.com.mauker.browsr.lib.organizations.datasources.remote.GhRemoteDataSource
import br.com.mauker.browsr.lib.organizations.datasources.remote.GhRemoteDataSourceImpl
import br.com.mauker.browsr.lib.organizations.repository.GhRepository
import br.com.mauker.browsr.lib.organizations.repository.GhRepositoryImpl
import br.com.mauker.browsr.lib.organizations.service.GhOrganizationService
import org.koin.dsl.module
import retrofit2.Retrofit

val ghOrganizationsModule = module {

    factory<GhRepository> {
        GhRepositoryImpl(ghRemoteDataSource = get(), ghFavoriteLocalDataSource = get())
    }

    factory<GhRemoteDataSource> { GhRemoteDataSourceImpl(ghOrganizationService = get()) }

    factory<GhFavoritesLocalDataSource> { GhFavoritesLocalDataSourceImpl(dao = get()) }

    single<GhOrganizationService> { get<Retrofit>().create(GhOrganizationService::class.java) }
}