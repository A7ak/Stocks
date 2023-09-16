package com.example.stocks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stocks.ui.theme.StocksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StocksTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    appBar()
                }

            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun appBar() {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(title = { Text(text = "stocks") }, actions = {
                    IconButton(onClick = {
                        // handle click
                    }) {
                        Icon(
                            imageVector = Icons.Default.AddCircle,
                            contentDescription = "add stock"
                        )

                    }

                }, scrollBehavior = scrollBehavior)
            }
        ) { values ->
            stockList(values)
        }

    }

    @Composable
    fun stockList(values: PaddingValues) {
        LazyColumn(modifier = Modifier.padding(values), content = {
            items(25) {
                stockItem()
            }
        })
    }

    @Composable
    fun stockItem() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
            //  .background(Color.Black)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(weight = .5f, fill = true),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "zomato",
                    color = Color.White
                )

                Text(
                    text = "zomato inc",
                    color = Color.White
                )
            }

            Image(
                modifier = Modifier.weight(weight = .25F, fill = true),
                painter = painterResource(id = R.drawable.green_graph),
                contentDescription = "graph"
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(weight = .25f, fill = true),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "123",
                    color = Color.White
                )

                Text(
                    modifier = Modifier.background(Color.Green),
                    text = "-3.50%",
                    color = Color.White
                )
            }
        }
    }

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun stockItemPreview() {
        StocksTheme {
            appBar()
        }
    }
}