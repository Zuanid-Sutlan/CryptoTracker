package com.devstudio.cryptotracker.ui.navigation

import kotlinx.serialization.Serializable

sealed class Screens {

    @Serializable
    data object AllCoinsScreen: Screens()

    @Serializable
    data object GetCoinByUUid: Screens()

    @Serializable
    data object CoinDetailScreen: Screens()

    @Serializable
    data object MarketStatsScreen: Screens()
}