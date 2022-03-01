package com.example.cryptocurrencycompose.presentation.coin_detail

import com.example.cryptocurrencycompose.domain.model.Coin
import com.example.cryptocurrencycompose.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
