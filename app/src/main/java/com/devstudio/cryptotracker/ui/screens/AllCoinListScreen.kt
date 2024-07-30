package com.devstudio.cryptotracker.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.devstudio.cryptotracker.MainViewModel
import com.devstudio.cryptotracker.data.model.Coin
import com.devstudio.cryptotracker.ui.components.CoinItemView
import com.devstudio.cryptotracker.ui.components.PullToRefreshLazyColumn
import com.devstudio.cryptotracker.ui.components.SearchBarInputField
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllCoinListScreen(
    onRefresh: () -> Unit,
    coins: SnapshotStateList<Coin>,
    isRefreshing: Boolean,
    onClick: (Coin) -> Unit,
    viewModel: MainViewModel
) {

    var searchText by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp),
    ) {
        SearchBarInputField(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 8.dp)
                .border(
                    if (searchText.isEmpty()) 1.dp else 2.dp,
                    if (searchText.isEmpty()) MaterialTheme.colorScheme.outline else MaterialTheme.colorScheme.primary,
                    SearchBarDefaults.inputFieldShape
                ),
            query = searchText,
            onQueryChange = {
                searchText = it
            },
            onSearch = {
                viewModel.getCoinBySearch(it)
                Log.i("AllCoinListScreen: ", "search click value: $it")
                active = false
            },
            active = active,
            onActiveChange = {
                active = it
            },
            placeholder = {
                Text(text = "Search")
            }
        )

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
            onRefresh = { onRefresh() }
        ) {
            LazyColumn {
                items(coins) { coin ->
                    CoinItemView(
                        coin = coin,
                        onClick = {
                            viewModel.setCoin(coin)
                            onClick(coin)
                        }
                    )
                }
            }
        }

        /*PullToRefreshLazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            items = coins,
            content = { coin ->
                CoinItemView(coin = coin, onClick = {
                    viewModel.setCoin(it)
                    onClick(it)
                })
            },
            isRefreshing = isRefreshing,
            onRefresh = {
                onRefresh()
            }
        )*/
    }
}
