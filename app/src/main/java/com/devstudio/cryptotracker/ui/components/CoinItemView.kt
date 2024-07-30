package com.devstudio.cryptotracker.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.devstudio.cryptotracker.data.model.Coin

@Composable
fun CoinItemView(coin: Coin, onClick: (Coin) -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable {
                    onClick(coin)
                }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    coin.iconUrl?.let { ImageLoadFromUrl(url = it) }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = coin.symbol)
                }
                Text(text = formatDouble(coin.price))
                Text(text = "${coin.change}%")
            }
        }
    }
}

fun formatDouble(value: Double): String {
    return /*if (value >= 1000){
        String.format("%.2f", value)
    }else if (value >= 100){
        String.format("%.2f", value)
    }else */if (value > 9.99){
        String.format("%.2f", value)
    }else if (value > 0.99){
        String.format("%.3f", value)
    }else if (value > 0.01){
        String.format("%.4f", value)
    } else if (value > 0.001){
        String.format("%.5f", value)
    }else {
        String.format("%.6f", value)
    }
}
