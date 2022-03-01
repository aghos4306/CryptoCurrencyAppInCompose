package com.example.cryptocurrencycompose.domain.model

import com.example.cryptocurrencycompose.data.remote.dto.coindetails.TeamMember

data class CoinDetail(
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: List<String>,
    val team: List<TeamMember>
)
