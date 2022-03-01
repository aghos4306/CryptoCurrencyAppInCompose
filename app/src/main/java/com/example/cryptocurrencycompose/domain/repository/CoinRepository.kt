package com.example.cryptocurrencycompose.domain.repository

import com.example.cryptocurrencycompose.data.remote.dto.CoinDto
import com.example.cryptocurrencycompose.data.remote.dto.coindetails.CoinDetailDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto

}