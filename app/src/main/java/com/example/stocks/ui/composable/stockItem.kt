package com.example.stocks.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stocks.R
import com.example.stocks.model.Stocks
import com.example.stocks.ui.feature.HorizontalSpacer
import com.example.stocks.ui.feature.VerticalSpacer

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
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = stock.company,
                color = colorResource(id = R.color.grey),
                fontSize = 14.sp
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
                    color = Color.White,
                    fontSize = 16.sp
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
                        .padding(start = 10.dp, top = 6.dp, end = 4.dp, bottom = 6.dp),
                    text = "${stock.change}%",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }
    }
}
