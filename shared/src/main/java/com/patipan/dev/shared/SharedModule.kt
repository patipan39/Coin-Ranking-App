package com.patipan.dev.shared

import com.patipan.dev.shared.data.CoinRankingRemoteImpl
import com.patipan.dev.shared.data.ICoinRankingRepository
import com.patipan.dev.shared.domain.CoinRankingRepositoryImpl
import com.patipan.dev.shared.domain.CoinRankingUseCase
import com.patipan.dev.shared.remote.ICoinRankingRemote
import org.koin.dsl.bind
import org.koin.dsl.module

object SharedModule {

    val dataModule = module {
        factory { CoinRankingRemoteImpl(get()) } bind ICoinRankingRemote::class
    }

    val domainModule = module {
        factory { CoinRankingUseCase(get()) }
        factory { CoinRankingRepositoryImpl(get()) } bind ICoinRankingRepository::class
    }

    val serviceModule = module {
        single { ServiceFactory.createCoinRankingService() }
    }
}