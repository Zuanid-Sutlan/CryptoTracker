package com.devstudio.cryptotracker.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devstudio.cryptotracker.data.api.CoinRankingApi
import com.devstudio.cryptotracker.data.api.RetrofitClient
import com.devstudio.cryptotracker.data.model.Coin
import com.devstudio.cryptotracker.data.model.CoinResponse
import com.devstudio.cryptotracker.data.model.CoinsResponse
import com.devstudio.cryptotracker.data.model.SearchResponse
import com.devstudio.cryptotracker.data.model.StatsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoinRepository {
    private val api: CoinRankingApi by lazy {
        RetrofitClient.instance.create(CoinRankingApi::class.java)
    }

    private val _coins = MutableLiveData<List<Coin>>()
    val coins: LiveData<List<Coin>> get() = _coins


    fun fetchAllCoins(onResult: (List<Coin>?) -> Unit) {
        api.getCoins().enqueue(object : Callback<CoinsResponse> {
            override fun onResponse(call: Call<CoinsResponse>, response: Response<CoinsResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    onResult(response.body()?.data?.coins)
                    Log.i(
                        "CoinRepository",
                        "Fetched ${response.body()?.data?.coins?.size} coins successfully"
                    )
                } else {
                    onResult(null)
                    Log.e("CoinRepository", "Failed to fetch coins: ${response.message()}")
                    Log.e("CoinRepository", "Response: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<CoinsResponse>, t: Throwable) {
                onResult(null)
                Log.e("CoinRepository", "Error fetching coins: ${t.message}", t)
            }
        })
    }

    fun fetchCoinByUUid(symbol: String, onResult: (Coin?) -> Unit) {
        api.getCoinByUUid(symbol).enqueue(object : Callback<CoinResponse> {
            override fun onResponse(call: Call<CoinResponse>, response: Response<CoinResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val coin = response.body()?.data?.coin
                    onResult(coin)
                    Log.i(
                        "CoinRepository",
                        "Fetched coin ${response.body()?.data?.coin} successfully"
                    )
                    Log.i("CoinRepository", "Fetched coin ${response} successfully")
                } else {
                    onResult(null)
                    Log.e("CoinRepository", "Failed to fetch coin: ${response.message()}")
                    Log.e("CoinRepository", "Response: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<CoinResponse>, t: Throwable) {
                onResult(null)
                Log.e("CoinRepository", "Error fetching coin: ${t.message}", t)
            }
        })
    }

    fun searchCoin(query: String, onResult: (List<Coin>?) -> Unit) {
        api.searchCoins(query).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    onResult(response.body()?.data?.coins)
                    Log.i(
                        "CoinRepository",
                        "Fetched ${response.body()?.data?.coins?.size} coins successfully"
                    )
                } else {
                    onResult(null)
                    Log.e("CoinRepository", "Failed to fetch coins: ${response.message()}")
                    Log.e("CoinRepository", "Response: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                onResult(null)
                Log.e("CoinRepository", "Error fetching coins: ${t.message}", t)
            }
        })
    }

    fun fetchStats(onResult: (StatsResponse.Data) -> Unit){
        api.getStats().enqueue(object : Callback<StatsResponse> {
            override fun onResponse(call: Call<StatsResponse>, response: Response<StatsResponse>) {
                if (response.isSuccessful && response.body() != null){
                    onResult(response.body()!!.data)
                    Log.i("CoinRepository", "Fetched stats successfully")
                } else {
                    Log.e("CoinRepository", "Failed to fetch stats: ${response.message()}")
                    Log.e("CoinRepository", "Response: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<StatsResponse>, t: Throwable) {
                Log.e("CoinRepository", "Error fetching stats: ${t.message}", t)
            }

        })
    }
}
