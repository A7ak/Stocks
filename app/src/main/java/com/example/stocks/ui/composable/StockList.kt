package com.example.stocks.ui.composable

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.stocks.R
import com.example.stocks.viewmodel.MyViewModel

@OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun StockList(values: PaddingValues, viewModel: MyViewModel, remove: (Int) -> Unit) {

        val list by viewModel.data.collectAsState()

        LazyColumn(
            state = rememberLazyListState(),
            modifier = Modifier
                .padding(values)
                .padding(horizontal = 6.dp)
        ) {
            itemsIndexed(list) { index, stock ->

                val dismissState = rememberDismissState(
                    initialValue = DismissValue.Default,
                    confirmValueChange = {
                        if (it == DismissValue.DismissedToStart) {
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
                    dismissContent = {
                        Column {
                            stockItem(stock = stock)
                            Divider(
                                color = colorResource(id = R.color.light_black),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    },
                    directions = setOf(DismissDirection.EndToStart)
                )

            }
        }
    }
