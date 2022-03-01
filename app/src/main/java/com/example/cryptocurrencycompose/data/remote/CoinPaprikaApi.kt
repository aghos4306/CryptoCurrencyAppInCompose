package com.example.cryptocurrencycompose.data.remote

import com.example.cryptocurrencycompose.data.remote.dto.CoinDto
import com.example.cryptocurrencycompose.data.remote.dto.coindetails.CoinDetailDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

    @GET("/v1/coins") //everything that comes after base url
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}") //Path parameter takes specific coin id
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto

}