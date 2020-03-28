package com.patipan.dev.shared.pagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.patipan.dev.model.BaseCoinRankingAdapterData
import com.patipan.dev.model.CoinRankingRequest
import com.patipan.dev.shared.domain.CoinRankingUseCase

class CoinRankingDataSourceFactory(
    private val useCase: CoinRankingUseCase,
    private val coinRankingRequest: CoinRankingRequest
) :
    DataSource.Factory<Long, BaseCoinRankingAdapterData>() {

    val mutableDataSource: MutableLiveData<CoinRankingDataSource> = MutableLiveData()
    override fun create(): DataSource<Long, BaseCoinRankingAdapterData> {
        val coinRankingDataSource = CoinRankingDataSource(useCase, coinRankingRequest)
        mutableDataSource.postValue(coinRankingDataSource)
        return coinRankingDataSource
    }

}