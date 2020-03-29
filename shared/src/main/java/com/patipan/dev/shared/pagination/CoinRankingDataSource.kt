package com.patipan.dev.shared.pagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.patipan.dev.model.*
import com.patipan.dev.shared.Either
import com.patipan.dev.shared.domain.CoinRankingUseCase
import kotlinx.coroutines.runBlocking
import kotlin.math.abs

class CoinRankingDataSource(
    private val useCase: CoinRankingUseCase,
    private val coinRankingRequest: CoinRankingRequest,private val errorDataSource: ((error: Failed) -> Unit)
) :
    PageKeyedDataSource<Long, BaseCoinRankingAdapterData>() {

    private val coinRankingListItem = arrayListOf<BaseCoinRankingAdapterData>()

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, BaseCoinRankingAdapterData>
    ) {
        runBlocking {
            return@runBlocking when (val response = useCase.run(coinRankingRequest)) {
                is Either.Right -> {
                    val state = response.right.data?.state

                    response.right.data?.coin?.mapIndexed { index, coinData ->
                        mapperItem(
                            index,
                            coinData
                        )
                    }

                    coinRankingRequest.page = state?.offset?.plus(1)?.toLong()

                    callback.onResult(
                        coinRankingListItem,
                        state?.offset?.minus(1)?.toLong(),
                        coinRankingRequest.page
                    )
                }

                is Either.Left -> {
                    errorDataSource(response.left)
                }
            }
        }
    }

    override fun loadAfter(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, BaseCoinRankingAdapterData>
    ) {
        runBlocking {
            return@runBlocking when (val response = useCase.run(coinRankingRequest)) {
                is Either.Right -> {
                    val state = response.right.data?.state
                    coinRankingRequest.page = state?.offset?.plus(1)?.toLong()

                    if (coinRankingRequest.page?.toInt() ?: 0 >= state?.limit ?: 0) return@runBlocking

                    response.right.data?.coin?.mapIndexed { index, coinData ->
                        mapperItem(
                            index,
                            coinData
                        )
                    }

                    callback.onResult(
                        coinRankingListItem,
                        state?.offset?.plus(1)?.toLong()
                    )
                }

                is Either.Left -> {
                    errorDataSource(response.left)
                }
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, BaseCoinRankingAdapterData>
    ) {
        //Nothing
    }

    private fun mapperItem(index: Int, coinRankingResponseDataCoin: CoinRankingResponseDataCoin) {
        val firstStartIndex = index + 1
        val baseCoinRankingAdapterData =
            if (firstStartIndex <= 5 && abs(firstStartIndex - 5) == 0 || firstStartIndex > 5 && firstStartIndex % 5 == 0) {
                CoinRankingRightData(
                    coinRankingResponseDataCoin.id,
                    coinRankingResponseDataCoin.name,
                    coinRankingResponseDataCoin.description,
                    coinRankingResponseDataCoin.iconUrl
                )

            } else {
                CoinRankingLeftData(
                    coinRankingResponseDataCoin.id,
                    coinRankingResponseDataCoin.name,
                    coinRankingResponseDataCoin.description,
                    coinRankingResponseDataCoin.iconUrl
                )
            }
        coinRankingListItem.add(baseCoinRankingAdapterData)
    }
}