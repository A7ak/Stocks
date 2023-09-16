package com.example.stocks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stocks.model.Stocks
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
                    bottomSheet()
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

            stockList(values, getStockList())
        }

    }

    @Composable
    fun stockList(values: PaddingValues, stockList: List<Stocks>) {
        LazyColumn(modifier = Modifier.padding(values), content = {
            items(stockList) { stock ->
                stockItem(stock)
            }
        })
    }

    @Composable
    fun stockItem(stock: Stocks) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Black)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(weight = .5f, fill = true),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stock.name,
                    color = Color.White
                )

                Text(
                    text = stock.company,
                    color = colorResource(id = R.color.grey)
                )
            }

            Image(
                modifier = Modifier.weight(weight = .25F, fill = true).wrapContentHeight(Alignment.CenterVertically),
                painter = if (stock.change > 0F) {
                    painterResource(id = R.drawable.green_graph)
                } else {
                    painterResource(id = R.drawable.red_graph)
                },
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
                    text = stock.price.toString(),
                    color = Color.White
                )

                Text(
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                        .clip(shape = RoundedCornerShape(4.dp))
                        .background(
                            if (stock.change > 0F) {
                                colorResource(id = R.color.green)
                            } else {
                                Color.Red
                            }
                        ),
                    text = "${stock.change}%",
                    color = Color.White
                )
            }
        }
        Divider(color = colorResource(id = R.color.light_black), modifier = Modifier.fillMaxWidth())
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun bottomSheet() {
        val scaffoldState = rememberBottomSheetScaffoldState()

        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetContent = {

            }
        ) {
        }
    }

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun stockItemPreview() {
        StocksTheme {
            appBar()
        }
    }

    fun getStockList(): List<Stocks> {
        var stockList = ArrayList<Stocks>()

        stockList.add(
            Stocks(
                name = "Zomato",
                company = "Zomato inc",
                price = 123f,
                change = 3.5f
            )
        )
        stockList.add(
            Stocks(
                name = "Swiggy",
                company = "Swiggy inc",
                price = 85f,
                change = -2.6f
            )
        )
        stockList.add(
            Stocks(
                name = "Apple",
                company = "Apple inc",
                price = 175f,
                change = -0.42f
            )
        )
        stockList.add(
            Stocks(
                name = "Google",
                company = "Alphabet inc",
                price = 55f,
                change = 1.25f
            )
        )
        return stockList
    }
}