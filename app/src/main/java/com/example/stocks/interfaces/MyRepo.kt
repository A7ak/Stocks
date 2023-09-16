package com.example.stocks.interfaces

import com.example.stocks.model.Stocks

interface MyRepo {
    fun getData(): List<Stocks>
}