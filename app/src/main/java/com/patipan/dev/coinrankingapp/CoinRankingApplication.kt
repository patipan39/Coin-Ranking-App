package com.patipan.dev.coinrankingapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CoinRankingApplication : Application() {
    private val listInjection = arrayListOf(UIModule.coinRankingModule)

    override fun onCreate() {
        super.onCreate()

        setUpInjection()
    }

    private fun setUpInjection() {
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@CoinRankingApplication)
            modules(listInjection)
        }
    }
}