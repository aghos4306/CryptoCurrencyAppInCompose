package com.example.cryptocurrencycompose.domain.use_case.get_coin

import com.example.cryptocurrencycompose.common.Resource
import com.example.cryptocurrencycompose.data.remote.dto.coindetails.toCoinDetail
import com.example.cryptocurrencycompose.data.remote.dto.toCoin
import com.example.cryptocurrencycompose.domain.model.Coin
import com.example.cryptocurrencycompose.domain.model.CoinDetail
import com.example.cryptocurrencycompose.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coin))
        } catch (e: HttpException) {
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<CoinDetail>("Couldn't reach server, check net connection"))
        }
    }
}