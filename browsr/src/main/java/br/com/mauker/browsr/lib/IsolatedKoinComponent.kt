package br.com.mauker.browsr.lib

import org.koin.core.Koin
import org.koin.core.component.KoinComponent

abstract class IsolatedKoinComponent: KoinComponent {

    // This will override the default Koin instance
     override fun getKoin(): Koin = DILibContext.instance!!.koin
}