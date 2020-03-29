package com.patipan.dev.model

import com.google.gson.annotations.SerializedName

data class CoinRankingResponse(
    @SerializedName("status") val status: String? = null
    , @SerializedName("data") val data: CoinRankingResponseData? = null
)

data class CoinRankingResponseData(
    @SerializedName("stats") val state: CoinRankingResponseDataState? = null,
    @SerializedName("base") val base: CoinRankingResponseDataBase? = null,
    @SerializedName("coins") val coin: ArrayList<CoinRankingResponseDataCoin>? = null
)

data class CoinRankingResponseDataState(
    @SerializedName("total") val total: Double? = null,
    @SerializedName("offset") val offset: Int? = null,
    @SerializedName("limit") val limit: Int? = null,
    @SerializedName("order") val order: String? = null,
    @SerializedName("base") val base: String? = null,
    @SerializedName("totalMarkets") val totalMarkets: Double? = null,
    @SerializedName("totalExchanges") val totalExchanges: Double? = null,
    @SerializedName("totalMarketCap") val totalMarketCap: Double? = null,
    @SerializedName("total24hVolume") val total24hVolume: Double? = null
)

data class CoinRankingResponseDataBase(
    @SerializedName("symbol") val symbol: String? = null,
    @SerializedName("sign") val sign: String? = null
)

data class CoinRankingResponseDataCoin(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("slug") val slug: String? = null,
    @SerializedName("symbol") val symbol: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("color") val color: String? = null,
    @SerializedName("iconType") val iconType: String? = null,
    @SerializedName("iconUrl") val iconUrl: String? = null,
    @SerializedName("websiteUrl") val websiteUrl: String? = null,
    @SerializedName("socials") val socials: ArrayList<CoinRankingResponseDataSocialsAndLinks>? = null,
    @SerializedName("links") val links: ArrayList<CoinRankingResponseDataSocialsAndLinks>? = null,
    @SerializedName("confirmedSupply") val confirmedSupply: Boolean? = null,
    @SerializedName("numberOfMarkets") val numberOfMarkets: Double? = null,
    @SerializedName("numberOfExchanges") val numberOfExchanges: Double? = null,
    @SerializedName("type") val type: String? = null,
    @SerializedName("volume") val volume: Double? = null,
    @SerializedName("marketCap") val marketCap: Double? = null,
    @SerializedName("price") val price: String? = null,
    @SerializedName("circulatingSupply") val circulatingSupply: Double? = null,
    @SerializedName("totalSupply") val totalSupply: Double? = null,
    @SerializedName("approvedSupply") val approveSupply: Boolean? = null,
    @SerializedName("firstSeen") val firstSeen: Double? = null,
    @SerializedName("change") val change: Double? = null,
    @SerializedName("rank") val ranking: Int? = null,
    @SerializedName("history") val history: ArrayList<String>? = null,
    @SerializedName("allTimeHigh") val allTimeHigh: CoinRankingResponseDataAllTimeHigh? = null,
    @SerializedName("penalty") val penalty: Boolean? = null
)

data class CoinRankingResponseDataSocialsAndLinks(
    @SerializedName("name") val name: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("type") val type: String? = null
)

data class CoinRankingResponseDataAllTimeHigh(
    @SerializedName("price") val price: String? = null,
    @SerializedName("timestamp") val timestamp: Long? = null
)