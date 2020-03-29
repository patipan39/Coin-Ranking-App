package com.patipan.dev.shared.pagination

import androidx.paging.DataSource
import com.patipan.dev.model.BaseCoinRankingAdapterData
import com.patipan.dev.model.CoinRankingRequest
import com.patipan.dev.model.Failed
import com.patipan.dev.shared.SingleLiveEvent
import com.patipan.dev.shared.domain.CoinRankingUseCase

class CoinRankingDataSourceFactory(
    private val useCase: CoinRankingUseCase,
    private val coinRankingRequest: CoinRankingRequest
) :
    DataSource.Factory<Long, BaseCoinRankingAdapterData>() {

    val singleErrorDataSource: SingleLiveEvent<Failed> = SingleLiveEvent()

    override fun create(): DataSource<Long, BaseCoinRankingAdapterData> {
        return CoinRankingDataSource(useCase, coinRankingRequest) {
            singleErrorDataSource.postValue(it)
        }
    }

}