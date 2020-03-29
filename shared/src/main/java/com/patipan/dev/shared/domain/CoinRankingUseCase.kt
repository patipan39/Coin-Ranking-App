package com.patipan.dev.shared.domain

import com.patipan.dev.model.CoinRankingRequest
import com.patipan.dev.model.CoinRankingResponse
import com.patipan.dev.model.Failed
import com.patipan.dev.shared.BaseUseCase
import com.patipan.dev.shared.Either

class CoinRankingUseCase(private val repository: CoinRankingRepositoryImpl) :
    BaseUseCase<CoinRankingRequest, CoinRankingResponse>() {
    override suspend fun run(params: CoinRankingRequest): Either<Failed, CoinRankingResponse> {
        return repository.getListCoinRanking(params)
    }
}