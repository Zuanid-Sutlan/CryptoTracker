package com.devstudio.cryptotracker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.devstudio.cryptotracker.data.repository.CoinRepository
import com.devstudio.cryptotracker.ui.navigation.AppGraph
import com.devstudio.cryptotracker.ui.navigation.Screens
import com.devstudio.cryptotracker.ui.theme.CryptoTrackerTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            CryptoTrackerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomAppBar(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(4.dp),
                        ) {
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {

                                Button(onClick = { navController.navigate(Screens.AllCoinsScreen) }) {
                                    Text(text = "All")
                                }
                                Button(onClick = { navController.navigate(Screens.GetCoinByUUid) }) {
                                    Text(text = "By UUid")
                                }
                                Button(onClick = { navController.navigate(Screens.MarketStatsScreen) }) {
                                    Text(text = "Stats")
                                }
                            }
                        }
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        AppGraph(navController = navController, viewModel = viewModel)
                    }


                    /*LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        Log.i("mainActivity", "onCreate: ${coins.size}")
                        items(coins) {
                            CoinItemView(coin = it)
                        }
                    }*/
                }
            }
        }
    }


    private fun getCoinBySymbol(symbol: String) {
        val repository = CoinRepository()
        repository.fetchCoinByUUid(symbol) {
            it?.let {

                Log.i("getCoinByUUid: ", it.name)
            }
        }
    }
}

