package com.patipan.dev.coinrankingapp.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.patipan.dev.model.BaseCoinRankingAdapterData
import com.patipan.dev.model.CoinRankingRequest
import com.patipan.dev.shared.domain.CoinRankingUseCase
import com.patipan.dev.shared.pagination.CoinRankingDataSourceFactory

class CoinRankingViewModel(
    useCase: CoinRankingUseCase
) : ViewModel() {

    private val pageSize: Int = 10
    private val coinRankingList: LiveData<PagedList<BaseCoinRankingAdapterData>>
    private val coinRankingRequest: CoinRankingRequest
    private val dataSourceFactory: CoinRankingDataSourceFactory

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(true)
            .build()

        coinRankingRequest = CoinRankingRequest(page = 0)
        dataSourceFactory = CoinRankingDataSourceFactory(useCase, coinRankingRequest)
        coinRankingList = LivePagedListBuilder(dataSourceFactory, config).build()
    }

    fun refreshingData() {
        coinRankingRequest.page = 0
        coinRankingList.value?.dataSource?.invalidate()
    }

    fun searchCoinRanking(wording: String) {
        coinRankingRequest.searchInList = if (wording.isEmpty()) null else wording
        coinRankingRequest.page = 0
        coinRankingList.value?.dataSource?.invalidate()
    }

    fun observeError() = dataSourceFactory.mutableDataSource.value?.errorMutable
    fun observeMutableCoinItemList() = coinRankingList
}