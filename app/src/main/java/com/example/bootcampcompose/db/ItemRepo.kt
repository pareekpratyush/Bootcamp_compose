package com.example.bootcampcompose.db

import android.util.Log
import com.example.bootcampcompose.Item
import com.example.bootcampcompose.R
import com.example.bootcampcompose.network.ApiService
import com.example.bootcampcompose.network.DataItem
import kotlinx.coroutines.flow.Flow

class ItemRepo(
    private val apiInterface:ApiService,
    private val itemDB: ItemDB
) {

    suspend fun insertItem(item:Item){
        itemDB.itemDao().upsert(item)
    }

    fun getItemsFlow() = itemDB.itemDao().getAll()

    fun searchItem(query:String?) = itemDB.itemDao().searchItem(query)

    suspend fun updateDB(){
        try {
            val result = apiInterface.getItems()
            Log.d("ItemRepo GetAll", (result==null).toString())
            if(result.data.items!=null){
                Log.d("ItemRepo updateDB", result.data.items!!.size.toString())
                val itemList = apiItemsToListItems(result.data.items)
                for(item in itemList)
                    insertItem(item!!)
            }
            else Log.d("ItemRepo updateDB","NULL")
        } catch (e: Exception) {
            Log.d("ItemRepo updateDB","Crashed ${e.message}")
        }

    }

    private fun apiItemsToListItems(list:List<DataItem>):List<Item>{
        var tList = mutableListOf<Item>()
        for(dataItem in list){
            var t = Item(dataItem.name, R.drawable.img,dataItem.price.drop(2).toDouble(),sds = (dataItem.extra!=null))
            tList.add(t)
        }
        return tList
    }
}