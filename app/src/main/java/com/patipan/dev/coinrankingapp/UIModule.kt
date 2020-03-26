package com.patipan.dev.coinrankingapp

import com.patipan.dev.coinrankingapp.adapter.CoinRankingListAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object UIModule {
    val coinRankingModule = module {
        viewModel { CoinRankingViewModel() }
        factory { CoinRankingListAdapter() }
    }
}