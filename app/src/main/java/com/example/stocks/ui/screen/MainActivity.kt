package com.example.stocks.ui.screen

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.stocks.R
import com.example.stocks.model.Stocks
import com.example.stocks.ui.theme.StocksTheme
import com.example.stocks.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel by viewModels<MyViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StocksTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    bottomSheet()
                }

            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun appBar(viewModel: MyViewModel) {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(title = { Text(text = "stocks") }, actions = {
                    IconButton(onClick = {
                        viewModel.addData()
                    }) {
                        Icon(
                            imageVector = Icons.Default.AddCircle,
                            contentDescription = "add stock"
                        )

                    }

                }, scrollBehavior = scrollBehavior)
            }
        ) { values ->
            StockList(values, viewModel) {
                viewModel.removeData(it)
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun StockList(values: PaddingValues, viewModel: MyViewModel, remove: (Int) -> Unit) {

        val list by viewModel.data.collectAsState()

        LazyColumn(
            state = rememberLazyListState(),
            modifier = Modifier.padding(values)
        ) {
            itemsIndexed(list) { index, stock ->

                val dismissState = rememberDismissState(
                    initialValue = DismissValue.Default,
                    confirmValueChange = {
                        if (it == DismissValue.DismissedToStart) {
                            Log.d("composa77", "StockList: data removed")
                            viewModel.removeData(index = index)
                            false
                        } else {
                            true
                        }
                    }
                )
                SwipeToDismiss(
                    state = dismissState,

                    background = {

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Red)
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "delete",
                                tint = Color.White,
                                modifier = Modifier.align(
                                    Alignment.CenterEnd
                                )
                            )
                        }
                    },
                    dismissContent = { stockItem(stock = stock) },
                    directions = setOf(DismissDirection.EndToStart)
                )

            }
        }
    }

    @Composable
    fun stockItem(stock: Stocks) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(horizontal = 8.dp)
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column() {
                Text(
                    text = stock.name,
                    color = Color.White
                )

                Text(
                    text = stock.company,
                    color = colorResource(id = R.color.grey)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(80.dp, 40.dp)
                        .wrapContentHeight(Alignment.CenterVertically),
                    painter = if (stock.change > 0F) {
                        painterResource(id = R.drawable.green_graph)
                    } else {
                        painterResource(id = R.drawable.red_graph)
                    },
                    contentDescription = "graph"
                )

                HorizontalSpacer(width = 16.dp)

                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = stock.price.toString(),
                        color = Color.White
                    )

                    VerticalSpacer(width = 8.dp)
                    Text(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(4.dp))
                            .background(
                                if (stock.change > 0F) {
                                    colorResource(id = R.color.green)
                                } else {
                                    Color.Red
                                }
                            )
                            .padding(vertical = 4.dp, horizontal = 8.dp),
                        text = "${stock.change}%",
                        color = Color.White
                    )
                }
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

            },
            sheetPeekHeight = 0.dp,
        ) {
            appBar(viewModel = viewModel)
        }
    }

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun stockItemPreview() {
        StocksTheme {
            appBar(viewModel)
        }
    }
}

@Composable
fun HorizontalSpacer(width: Dp) = Spacer(modifier = Modifier.width(width))

@Composable
fun VerticalSpacer(width: Dp) = Spacer(modifier = Modifier.height(width))