package com.patipan.dev.shared.data

import com.patipan.dev.model.CoinRankingRequest
import com.patipan.dev.model.CoinRankingResponse
import com.patipan.dev.model.Failed
import com.patipan.dev.shared.Either

interface ICoinRankingRepository {
    suspend fun getListCoinRanking(coinRangingRequest: CoinRankingRequest): Either<Failed, CoinRankingResponse>
}