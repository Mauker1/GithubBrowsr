package br.com.mauker.browsr.lib.utils.di

import br.com.mauker.browsr.lib.utils.NetworkUtils
import br.com.mauker.browsr.lib.utils.NetworkUtilsImpl
import org.koin.dsl.module

val utilsModule = module {
    factory<NetworkUtils> { NetworkUtilsImpl(context = get()) }
}