package com.patipan.dev.coinrankingapp

import com.patipan.dev.coinrankingapp.adapter.CoinRankingListAdapter
import com.patipan.dev.coinrankingapp.view.viewmodel.CoinRankingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object UIModule {
    val coinRankingModule = module {
        viewModel {
            CoinRankingViewModel(
                get()
            )
        }
        factory { CoinRankingListAdapter() }
    }
}