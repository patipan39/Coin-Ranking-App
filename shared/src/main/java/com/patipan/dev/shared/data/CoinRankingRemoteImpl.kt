package com.patipan.dev.shared.data

import com.patipan.dev.model.CoinRankingRequest
import com.patipan.dev.model.CoinRankingResponse
import com.patipan.dev.model.Failed
import com.patipan.dev.shared.Either
import com.patipan.dev.shared.remote.ICoinRankingRemote
import com.patipan.dev.shared.request
import com.patipan.dev.shared.service.CoinRankingService

class CoinRankingRemoteImpl(private val service: CoinRankingService) : ICoinRankingRemote {
    override fun getListCoinRanking(coinRangingRequest: CoinRankingRequest): Either<Failed, CoinRankingResponse> {
        return request(service.getListCoinRanking(), { it }, CoinRankingResponse())
    }
}