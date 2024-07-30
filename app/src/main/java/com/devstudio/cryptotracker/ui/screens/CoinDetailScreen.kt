package com.devstudio.cryptotracker.ui.screens

import android.util.Log
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.devstudio.cryptotracker.data.model.Coin

@Composable
fun CoinDetailScreen(coin: Coin) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        coin.let {
            Text(modifier = Modifier.padding(8.dp), text = it.symbol)
            Text(modifier = Modifier.padding(8.dp), text = it.name)
            Text(modifier = Modifier.padding(8.dp), text = it.description ?: "N/A")
            TextField(
                modifier = Modifier.padding(8.dp),
                value = it.iconUrl ?: "N/A",
                onValueChange = {},
                readOnly = true,
                colors = TextFieldDefaults.colors(
                    cursorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            Text(modifier = Modifier.padding(8.dp), text = it.websiteUrl ?: "N/A")
            Text(modifier = Modifier.padding(8.dp), text = it.price.toString())
            TextField(
                modifier = Modifier.padding(8.dp),
                value = it.uuid ?: "N/A",
                readOnly = true,
                onValueChange = {},
                colors = TextFieldDefaults.colors(
                    cursorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }
        Log.i("Icon: ", coin.iconUrl.toString())
    }
}