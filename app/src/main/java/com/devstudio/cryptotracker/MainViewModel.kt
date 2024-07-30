package com.devstudio.cryptotracker

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.devstudio.cryptotracker.data.model.Coin
import com.devstudio.cryptotracker.data.model.ConcriteMarketStats
import com.devstudio.cryptotracker.data.model.StatsResponse
import com.devstudio.cryptotracker.data.repository.CoinRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {


    private val repository = CoinRepository()

    var isRefreshing by mutableStateOf(false)

    private val _coins = mutableStateListOf<Coin>()
    val coins: SnapshotStateList<Coin> = _coins

    private val _stats = mutableStateListOf<ConcriteMarketStats>()
    val stats: SnapshotStateList<ConcriteMarketStats> = _stats

    private val _marketStats = MutableStateFlow(StatsResponse.Data(0, 0, 0, "", ""))
    val marketStats: StateFlow<StatsResponse.Data> = _marketStats

    private val _coin = MutableStateFlow(Coin("", "", "", "", "", "", 0.0, 0.0))
    val coin: StateFlow<Coin> = _coin


    init {
        getCoins()
        getMarketStats()
    }

    fun getCoins(onDismiss: () -> Unit = {}) {
        _coins.clear()
        repository.fetchAllCoins(
            onResult = {
                it?.let {
                    _coins.addAll(it)
                }
                onDismiss()
            }
        )
        Log.i("getCoins: ", "${repository.coins.value?.size}")
        _coins.addAll(repository.coins.value ?: emptyList())

    }

    // set coin
    fun setCoin(coin: Coin) {
        Log.i("setCoin: ", "${coin.name} ${coin.price}")
        _coin.value = coin
    }

    // get coin by UUid
    fun getCoinByUUid(uuid: String) {
        repository.fetchCoinByUUid(uuid, onResult = {
            if (it != null) {
                _coin.value = it
            }
        })
    }

    // get coin by search
    fun getCoinBySearch(query: String) {
        _coins.clear()
        repository.searchCoin(
            query,
            onResult = {
                if (it != null) {
                    _coins.addAll(it)
                }
            }
        )
    }

    // get market stats
    fun getMarketStats() {
        _stats.clear()
        repository.fetchStats {
            _stats.add(ConcriteMarketStats("Total Coins", it.totalCoins.toString()))
            _stats.add(ConcriteMarketStats("Total Markets", it.totalMarkets.toString()))
            _stats.add(ConcriteMarketStats("Total Exchanges", it.totalExchanges.toString()))
            _stats.add(ConcriteMarketStats("Total Market Cap", it.totalMarketCap))
            _stats.add(ConcriteMarketStats("Total 24h Volume", it.total24hVolume))

        }
    }



}