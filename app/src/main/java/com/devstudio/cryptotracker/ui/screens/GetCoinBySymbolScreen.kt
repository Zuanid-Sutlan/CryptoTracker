package com.devstudio.cryptotracker.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.devstudio.cryptotracker.MainViewModel
import com.devstudio.cryptotracker.data.model.Coin
import com.devstudio.cryptotracker.data.repository.CoinRepository

@Composable
fun GetCoinByUUidScreen(viewModel: MainViewModel) {

//    val repository = CoinRepository()
    val coin = viewModel.coin.collectAsState()
    val symbol = remember { mutableStateOf("") }

    Column(modifier = Modifier) {
        OutlinedTextField(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(8.dp),
            value = symbol.value,
            onValueChange = { symbol.value = it })
        Button(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .fillMaxWidth()
            .padding(16.dp)
            .clip(
                RoundedCornerShape(10.dp)
            ),
            onClick = {
                viewModel.getCoinByUUid(symbol.value)
            }
        ) {
            Text(text = "Get Coin")
        }
        coin.value.let {
            Text(modifier = Modifier.padding(8.dp), text = it.symbol)
            Text(modifier = Modifier.padding(8.dp), text = it.name)
            Text(modifier = Modifier.padding(8.dp), text = it.description ?: "")
            Text(modifier = Modifier.padding(8.dp), text = it.iconUrl ?: "")
            Text(modifier = Modifier.padding(8.dp), text = it.websiteUrl ?: "")
            Text(modifier = Modifier.padding(8.dp), text = it.price.toString())
    //            Text(modifier = Modifier.padding(8.dp), text = it.id)
        }
    }
}