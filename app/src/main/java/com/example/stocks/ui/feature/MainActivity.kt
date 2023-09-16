package com.example.stocks.ui.feature

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.example.stocks.ui.composable.bottomSheet
import com.example.stocks.ui.theme.StocksTheme
import com.example.stocks.viewmodel.MyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MyViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StocksTheme {
                bottomSheet(viewModel)
            }
        }
    }
}
@Composable
fun HorizontalSpacer(width: Dp) = Spacer(modifier = Modifier.width(width))

@Composable
fun VerticalSpacer(width: Dp) = Spacer(modifier = Modifier.height(width))

