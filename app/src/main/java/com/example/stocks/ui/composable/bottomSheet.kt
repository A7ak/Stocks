package com.example.stocks.ui.composable

import androidx.compose.foundation.background
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.stocks.viewmodel.MyViewModel

@OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun bottomSheet(viewModel: MyViewModel) {
        val scaffoldState = rememberBottomSheetScaffoldState()

        BottomSheetScaffold(
            modifier = Modifier.background(Color.White),
            scaffoldState = scaffoldState,
            sheetContent = {

            },
            sheetPeekHeight = 0.dp,
        ) {
            appBar(viewModel = viewModel)
        }
    }
