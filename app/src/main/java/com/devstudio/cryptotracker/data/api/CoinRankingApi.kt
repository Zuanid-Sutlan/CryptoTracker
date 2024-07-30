package com.devstudio.cryptotracker.data.api

import com.devstudio.cryptotracker.data.model.CoinResponse
import com.devstudio.cryptotracker.data.model.CoinsResponse
import com.devstudio.cryptotracker.data.model.SearchResponse
import com.devstudio.cryptotracker.data.model.StatsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinRankingApi {
    @Headers("x-access-token: ${RetrofitClient.API_KEY}")
    @GET("v2/coins")
    fun getCoins(@Query("limit") limit: Int = 100): Call<CoinsResponse>

    @Headers("x-access-token: ${RetrofitClient.API_KEY}")
    @GET("v2/coin/{uuid}")
    fun getCoinByUUid(@Path("uuid") uuid: String): Call<CoinResponse>

    @Headers("x-access-token: ${RetrofitClient.API_KEY}")
    @GET("v2/coins")
    fun searchCoins(@Query("search") query: String): Call<SearchResponse>

    @Headers("x-access-token: ${RetrofitClient.API_KEY}")
    @GET("v2/stats")
    fun getStats(): Call<StatsResponse>

    /*fun getCoinByUUid(symbol: String, callback: (Coin?) -> Unit) {
        getAllCoins().enqueue(object : Callback<CoinRankingResponse> {
            override fun onResponse(
                call: Call<CoinRankingResponse>,
                response: Response<CoinRankingResponse>
            ) {
                if (response.isSuccessful) {
                    val coins = response.body()?.data?.coins
                    val coin = coins?.find { it.symbol.equals(symbol, ignoreCase = true) }
                    callback(coin)
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<CoinRankingResponse>, t: Throwable) {
                callback(null)
            }
        })
    }*/
}