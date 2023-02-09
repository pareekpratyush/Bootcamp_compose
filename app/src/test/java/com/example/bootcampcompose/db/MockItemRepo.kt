package com.example.bootcampcompose.db

import android.util.Log
import com.example.bootcampcompose.Item
import com.example.bootcampcompose.R
import com.example.bootcampcompose.network.ApiItem
import com.example.bootcampcompose.network.DataItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockItemRepo {

    var items = mutableListOf<Item>()

    suspend fun insertItem(item: Item) {
        if(item!=null)
            items.add(item)
    }

    fun getItemsFlow(): Flow<List<Item>> = flow { emit(items) }

    fun searchItem(query: String): Flow<List<Item>> {
        val filteredList = items.filter { item -> item.name.contains(query) }
        return flow { emit(filteredList) }
    }

    suspend fun updateDB(apiItem: ApiItem?) {
        try {
            val result = apiItem
            if(result!= null && result.data!=null && result?.data?.items!=null){
                val itemList = apiItemToListItems(result.data.items)
                for (item in itemList)
                    insertItem(item)
            }
        } catch (e: Exception) {
            Log.d("ItemRepo updateDB", "Crashed ${e.message}")
        }
    }


    fun apiItemToListItems(list:List<DataItem>):List<Item>{
        val tList = mutableListOf<Item>()
        for(dataItem in list){
            val t = Item(dataItem.name, R.drawable.img,dataItem.price.drop(2).toDouble(),sds = (dataItem.extra.length>0))
            tList.add(t)
        }
        return tList
    }
}