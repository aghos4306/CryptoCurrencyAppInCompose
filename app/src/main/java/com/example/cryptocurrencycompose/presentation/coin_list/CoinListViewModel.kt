package com.example.cryptocurrencycompose.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencycompose.common.Resource
import com.example.cryptocurrencycompose.domain.model.Coin
import com.example.cryptocurrencycompose.domain.model.CoinListState
import com.example.cryptocurrencycompose.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
): ViewModel() {
    //we have moved most of the business logic from viewModel to use case, but why do we still
    //have our viewModel ? The main purpose of the viewModel is to maintain our state.
    //Execute usecase, put the result into state object that we will use in composable. Since onEach is
    //a flow it needs to be launched in a coroutine.
    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state //exposed to composable

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = CoinListState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CoinListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}