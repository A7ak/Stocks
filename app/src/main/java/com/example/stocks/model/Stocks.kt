package com.example.stocks.model

data class Stocks(
    val id: String,
    val name: String,
    val company: String,
    val price: Float,
    val change: Float
)
