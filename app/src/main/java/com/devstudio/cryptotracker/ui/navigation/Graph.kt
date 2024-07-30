package com.devstudio.cryptotracker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.devstudio.cryptotracker.MainViewModel
import com.devstudio.cryptotracker.ui.screens.AllCoinListScreen
import com.devstudio.cryptotracker.ui.screens.CoinDetailScreen
import com.devstudio.cryptotracker.ui.screens.GetCoinByUUidScreen
import com.devstudio.cryptotracker.ui.screens.MarketStats
import kotlinx.coroutines.launch

@Composable
fun AppGraph(navController: NavHostController, viewModel: MainViewModel) {



    val scope = rememberCoroutineScope()

    NavHost(navController = navController, startDestination = Screens.AllCoinsScreen){
        composable<Screens.AllCoinsScreen> {
            AllCoinListScreen(
                coins = viewModel.coins,
                isRefreshing = viewModel.isRefreshing,
                onRefresh = {
                    scope.launch {
                        viewModel.isRefreshing = true
                        viewModel.getCoins(
                            onDismiss = {
                                viewModel.isRefreshing = false
                            }
                        )
                    }
                },
                onClick = {
                    viewModel.setCoin(it)
                    navController.navigate(Screens.CoinDetailScreen)
                },
                viewModel = viewModel
            )
        }

        composable<Screens.CoinDetailScreen> {
//            val coin = it.
            CoinDetailScreen(coin = viewModel.coin.value)
        }

        composable<Screens.GetCoinByUUid> {
            GetCoinByUUidScreen(viewModel = viewModel)
        }

        composable<Screens.MarketStatsScreen> {
            MarketStats(viewModel = viewModel, onRefresh = {
                scope.launch {
                    viewModel.getMarketStats()
                }
            })
        }

    }
}