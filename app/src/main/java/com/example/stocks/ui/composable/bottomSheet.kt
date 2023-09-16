package com.example.stocks.ui.composable

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.stocks.viewmodel.MyViewModel

@OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun bottomSheet(viewModel: MyViewModel) {
        val scaffoldState = rememberBottomSheetScaffoldState()

        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetContent = {
                Text(
                    modifier = Modifier.fillMaxHeight().align(Alignment.CenterHorizontally),
                    text = "No News Found",
                    color = Color.White
                )
            },
            sheetPeekHeight = 100.dp
        ) {
            appBar(viewModel = viewModel)
        }
    }
