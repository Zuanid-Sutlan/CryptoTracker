package com.devstudio.cryptotracker.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.devstudio.cryptotracker.MainViewModel
import com.devstudio.cryptotracker.data.model.StatsResponse
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun MarketStats(viewModel: MainViewModel, onRefresh: () -> Unit) {
//    viewModel.getMarketStats()
    val data = viewModel.stats

    val isRefreshing by remember { mutableStateOf(false) }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = {
            onRefresh()
        }
    ) {
        LazyVerticalStaggeredGrid(modifier = Modifier.fillMaxSize(), columns = StaggeredGridCells.Fixed(2)) {
            items(data) {
                val value = when(it.itemName){
                    "Total 24h Volume" -> "${it.itemValue}$"
                    "Total Market Cap" -> "${it.itemValue}$"
                    else -> it.itemValue
                }
                MarketStatsItemView(itemText = it.itemName, itemData = value)
            }
        }
    }
}

@Composable
fun MarketStatsItemView(modifier: Modifier = Modifier, itemText: String, itemData: String) {
    Card(
        modifier = modifier.padding(8.dp)
    ) {
        Column(
            Modifier.padding(6.dp)
        ) {
            Text(modifier = Modifier.fillMaxWidth(), text = itemText, textAlign = TextAlign.Start)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = itemData)
        }
    }
}