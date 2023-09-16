package com.example.stocks.ui.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.example.stocks.viewmodel.MyViewModel

@OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun appBar(viewModel: MyViewModel) {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        Scaffold(
            containerColor = Color.Black,
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    title = { Text(text = "Stocks") },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black),
                    actions = {
                        IconButton(onClick = {
                            viewModel.addData()
                        }) {
                            Icon(
                                imageVector = Icons.Default.AddCircle,
                                tint = Color.White,
                                contentDescription = "add stock"
                            )

                        }

                    },
                    scrollBehavior = scrollBehavior
                )
            }
        ) { values ->
            StockList(values, viewModel) {
                viewModel.removeData(it)
            }
        }
    }
