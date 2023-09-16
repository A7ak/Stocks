package com.example.stocks

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.stocks.model.Stocks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor() : ViewModel() {

    private val _data = MutableStateFlow<List<Stocks>>(emptyList())

    val data: StateFlow<List<Stocks>> = _data

    fun addData() {
        _data.update {
           var newList =  it.toMutableList()
            newList.add(getStockList().random())
            newList
        }
    }

    fun removeData(index: Int) : Boolean {
      //  _data.update {
            var list = _data.value.toMutableList()
        list.removeAt(index)
        Log.d("TAG", "removeData: $index $list")
        _data.update {
            list
        }
        //_data.value = list
        //}
        return true
        }


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
