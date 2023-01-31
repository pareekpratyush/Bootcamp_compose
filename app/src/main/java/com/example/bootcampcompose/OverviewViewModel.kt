package com.example.bootcampcompose

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bootcampcompose.network.Api
import com.example.bootcampcompose.network.DataItem
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {

    var itemList:List<Item> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("No error")

    fun getItemList() {
        viewModelScope.launch {
            try {
                val apiResponse = Api.retrofitService.getItems()
                itemList = apiItemToListItem(apiResponse.data.items)
            }
            catch (e: Exception) {
                errorMessage = e.stackTraceToString()
            }
        }
        Log.d("Error message: ",errorMessage)
    }

    private fun apiItemToListItem(list:List<DataItem>):List<Item>{
        var tList = mutableListOf<Item>()
        for(dataItem in list){
            var t = Item(dataItem.name,R.drawable.img,dataItem.price.drop(2).toDouble(),sds = (dataItem.extra!=null))
            tList.add(t)
        }
        Log.d("OVM",tList.size.toString())
        return tList
    }


}