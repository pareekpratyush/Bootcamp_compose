package com.example.bootcampcompose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bootcampcompose.db.ItemRepo
import kotlinx.coroutines.launch
import androidx.lifecycle.asLiveData
import com.example.bootcampcompose.Item
import com.example.bootcampcompose.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class ItemViewModel(private val itemRepo: ItemRepo) : ViewModel() {

    var itemList: List<Item> by mutableStateOf(listOf())

    init {
        updateDB()
    }

    private fun updateDB() {
        viewModelScope.launch(Dispatchers.IO) {
            itemRepo.updateDB()
        }
    }

    fun getFlow() = itemRepo.getItemsFlow()

    fun addItem(item: Item) = viewModelScope.launch {
        itemRepo.insertItem(item)
    }
}