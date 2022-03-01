package com.example.cryptocurrencycompose.presentation.coin_list

import com.example.cryptocurrencycompose.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
