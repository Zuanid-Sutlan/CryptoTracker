package com.devstudio.cryptotracker.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Coin(
    val uuid: String,
    val symbol: String,
    val name: String,
    val description: String?,
    val iconUrl: String?,
    val websiteUrl: String?,
    val price: Double,
    val change: Double,
//    val rank: Int,
)

data class CoinsResponse(
    val status: String,
    val data: Data
) {
    data class Data(
        val coins: List<Coin>
    )
}

data class SearchResponse(
    val status: String,
    val data: Data
) {
    data class Data(
        val coins: List<Coin>
    )
}

data class CoinResponse(
    val status: String,
    val data: CoinData
) {
    data class CoinData(
        val coin: Coin
    )
}

data class StatsResponse(
    val status: String,
    val data: Data
) {
    data class Data(
        /*@SerializedName("totalCoins") */val totalCoins: Int,
        /*@SerializedName("totalMarkets")*/ val totalMarkets: Int,
        /*@SerializedName("totalExchanges") */val totalExchanges: Int,
        /*@SerializedName("totalMarketCap")*/ val totalMarketCap: String,
        /* @SerializedName("total24hVolume") */val total24hVolume: String
        // Add other fields as needed
    )
}

data class ConcriteMarketStats(
    val itemName: String,
    val itemValue: String
)
