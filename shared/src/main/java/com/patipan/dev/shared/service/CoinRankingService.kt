package com.patipan.dev.shared.service

import com.patipan.dev.model.CoinRankingResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinRankingService {

    @GET("v1/public/coins")
    fun getListCoinRanking(
        @Query("prefix") searchPrefix: String? = null,
        @Query("offset") page: Long? = null
    ): Call<CoinRankingResponse>
}