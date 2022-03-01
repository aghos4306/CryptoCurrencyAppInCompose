package com.example.cryptocurrencycompose.presentation.coin_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencycompose.common.Constants.PARAM_COIN_ID
import com.example.cryptocurrencycompose.common.Resource
import com.example.cryptocurrencycompose.presentation.coin_list.CoinListState
import com.example.cryptocurrencycompose.domain.use_case.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state //exposed to composable

    init {
        savedStateHandle.get<String>(PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId: String) {
        getCoinUseCase(coinId).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = CoinDetailState(coin = result.data)
                }
                is Resource.Error -> {
                    _state.value = CoinDetailState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}

//SavedStateHandle comes handy to use to pass coinId into getCoinUseCase in line 28. Create private
//val SavedStateHandle. SavedStateHandle is basically a bundle and it contains information about the
//saved state. We can use it to restore our app. It contains navigation parameter. In the list of
//all our crypto, when we e.g click on BTC, we will pass the id of BTC as a parameter to our BTC Detail
//screen. In the Detail Screen, we will load BTC from the api using the ID. Because we passed the ID as
//a navigation parameter, the ID is contained in SavedStateHandler already. We can just use savedStateHaandler
//to access the parameter in our ViewModel. Instead of passing getCoin in init block, we pass
//savedStateHandle and pass in the proper date type. Create a constant for coinId.
//Note in both viewModels, val state contains the data from our api, we can now use this data in our
//compose UI.