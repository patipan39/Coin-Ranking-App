package com.patipan.dev.shared.domain

import com.patipan.dev.model.CoinRankingRequest
import com.patipan.dev.model.CoinRankingResponse
import com.patipan.dev.model.Failed
import com.patipan.dev.shared.Either
import com.patipan.dev.shared.data.CoinRankingRemoteImpl
import com.patipan.dev.shared.data.ICoinRankingRepository

class CoinRankingRepositoryImpl(private val remote: CoinRankingRemoteImpl) : ICoinRankingRepository {
    override suspend fun getListCoinRanking(coinRangingRequest: CoinRankingRequest): Either<Failed, CoinRankingResponse> {
        return remote.getListCoinRanking(coinRangingRequest)
    }
}