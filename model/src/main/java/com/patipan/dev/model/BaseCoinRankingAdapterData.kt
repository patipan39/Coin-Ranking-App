package com.patipan.dev.model

interface BaseCoinRankingAdapterData

data class CoinRankingLeftData(
    val itemId: Int? = null,
    val name: String? = null,
    val description: String? = null,
    val imageUrl: String? = null
) :
    BaseCoinRankingAdapterData

data class CoinRankingRightData(
    val itemId: Int? = null,
    val name: String? = null,
    val description: String? = null,
    val imageUrl: String? = null
) : BaseCoinRankingAdapterData