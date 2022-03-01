package com.example.cryptocurrencycompose.data.repository

import com.example.cryptocurrencycompose.data.remote.CoinPaprikaApi
import com.example.cryptocurrencycompose.data.remote.dto.CoinDto
import com.example.cryptocurrencycompose.data.remote.dto.coindetails.CoinDetailDto
import com.example.cryptocurrencycompose.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
): CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}