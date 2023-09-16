package com.example.stocks.service

import com.example.stocks.model.Stocks

class MockData {
    fun getStockList(): List<Stocks> {
        var stockList = ArrayList<Stocks>()
        stockList.add(
            Stocks(
                id = Math.random().toString(),
                name = "Zomato",
                company = "Zomato inc",
                price = 123f,
                change = 3.5f
            )
        )
        stockList.add(
            Stocks(
                id = Math.random().toString(),
                name = "Swiggy",
                company = "Swiggy inc",
                price = 85f,
                change = -2.6f
            )
        )
        stockList.add(
            Stocks(
                id = Math.random().toString(),
                name = "Apple",
                company = "Apple inc",
                price = 175f,
                change = -0.42f
            )
        )
        stockList.add(
            Stocks(
                id = Math.random().toString(),
                name = "Google",
                company = "Alphabet inc",
                price = 55f,
                change = 1.25f
            )
        )
        return stockList
    }
}