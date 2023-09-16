package com.example.stocks.repository

import com.example.stocks.service.MockData
import com.example.stocks.interfaces.MyRepo
import com.example.stocks.model.Stocks
import javax.inject.Inject

class MyRepoImpl @Inject constructor(
    private val mockData: MockData
) : MyRepo {
    override fun getData(): List<Stocks> {
        return mockData.getStockList()

    }

}