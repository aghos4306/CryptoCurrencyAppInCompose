package com.example.cryptocurrencycompose.presentation.coin_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cryptocurrencycompose.presentation.Screen
import com.example.cryptocurrencycompose.presentation.coin_list.components.CoinListItem

@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.coins) { coin ->
                CoinListItem(
                    coin = coin,
                    onItemClick = {
                        navController.navigate(Screen.CoinDetailScreen.route + "/${coin.id}")
                    }
                )
            }
        }
        if(state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if(state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

//Takes navController for navigation, CoinListViewModel to get an instance of state. Use hiltViewModel.
//to automatically give us viewModel instance. In this screen we have a big box, if the api call is
//successful, then show the list of all crypto, if it is not, show an error message at the center
//of the screen. When you click on a coin, show progress bar at the center of the screen.
//get the reference for the state from viewModel.state.value
//Then get a Box with modifier. And a LazyColumn which will display List of Items. Within the
//LazyColumn, get the items that will give us the List. Pass state.coins, and for each coin,
//Map that to a Composable CoinListItem. CoinListItem takes coin and onItemClick as parameter.
//When we click on a coin, we want to navigate to CoinDetailScreen. use navController.navigate()
//For the navigate we will create a route to handle that. In Presentation package create a Screen
//sealed class and define route for CoinListScreen and CoinDetailScreen. Back in CoinListScreen,
//pass into the navigate the route by appending path paramter to have access to the coin we want
//to load in CoinDetailScreen. Below the LazyColumn check if it contains an error, show a Text
//that will contain the error.