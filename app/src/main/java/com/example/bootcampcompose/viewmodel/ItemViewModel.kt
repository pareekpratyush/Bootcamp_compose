package com.example.bootcampcompose.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bootcampcompose.db.ItemRepo
import kotlinx.coroutines.launch
import androidx.lifecycle.asLiveData
import com.example.bootcampcompose.Item
import com.example.bootcampcompose.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow

class ItemViewModel(private val itemRepo: ItemRepo) : ViewModel() {

    private val itemList: MutableLiveData<List<Item>> by lazy{
        MutableLiveData<List<Item>>(listOf())
    }

    private val filteredList: MutableLiveData<List<Item>> by lazy{
        MutableLiveData<List<Item>>(listOf())
    }

    init {
        updateDB()
    }

    private fun updateDB() {
        viewModelScope.launch(Dispatchers.IO) {
            itemRepo.updateDB()
        }
    }

    fun addItem(item: Item) = viewModelScope.launch {
        itemRepo.insertItem(item)
    }

    fun getData():LiveData<List<Item>>{
        return itemList
    }

    fun getAll() = itemRepo.getItemsFlow()

    fun searchItem(query: MutableState<TextFieldValue>): Flow<List<Item>>{
        if(query.value.text=="") {
            return getAll()
        }
        return itemRepo.searchItem(query.value.text)
    }
}