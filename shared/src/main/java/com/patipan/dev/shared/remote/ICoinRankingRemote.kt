package com.patipan.dev.shared.remote

import com.patipan.dev.model.CoinRankingRequest
import com.patipan.dev.model.CoinRankingResponse
import com.patipan.dev.model.Failed
import com.patipan.dev.shared.Either

interface ICoinRankingRemote {
   suspend fun getListCoinRanking(coinRangingRequest: CoinRankingRequest): Either<Failed, CoinRankingResponse>
}