package com.example.stocks.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.stocks.interfaces.MyRepo
import com.example.stocks.model.Stocks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val repo: MyRepo) : ViewModel() {

    private val _data = MutableStateFlow<List<Stocks>>(emptyList())

    val data: StateFlow<List<Stocks>> = _data

    fun addData() {
        _data.update {
            var newList = it.toMutableList()
            newList.add(repo.getData().random())
            newList
        }
    }

    fun removeData(index: Int): Boolean {
        val list = _data.value.toMutableList()
        list.removeAt(index)
        _data.update {
            list
        }
        return true
    }
}
