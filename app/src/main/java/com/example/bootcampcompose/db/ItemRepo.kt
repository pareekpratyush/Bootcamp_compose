package com.example.bootcampcompose.db

import android.util.Log
import com.example.bootcampcompose.Item
import com.example.bootcampcompose.R
import com.example.bootcampcompose.network.ApiItem
import com.example.bootcampcompose.network.ApiService
import com.example.bootcampcompose.network.DataItem
import kotlinx.coroutines.flow.Flow

class ItemRepo(
    private val apiInterface:ApiService,
    private val itemDB: ItemDB
) {

    suspend fun insertItem(item:Item){
        if(item!=null)
            itemDB.itemDao().upsert(item)
    }

    fun getItemsFlow() = itemDB.itemDao().getAll()

    fun searchItem(query:String) = itemDB.itemDao().searchItem(query)

    suspend fun updateDB(){
        try {
            val result:ApiItem? = apiInterface.getItems()
            if(result!= null && result.data!=null && result.data.items!=null){
                Log.d("ItemRepo updateDB", result.data.items.size.toString())
                val itemList = apiItemToListItems(result.data.items)
                for(item in itemList)
                    insertItem(item)
            }
        } catch (e: Exception) {
            Log.d("ItemRepo updateDB","Crashed ${e.message}")
        }
    }

    private fun apiItemToListItems(list:List<DataItem>):List<Item>{
        val tList = mutableListOf<Item>()
        for(dataItem in list){
            val t = Item(dataItem.name, R.drawable.img,dataItem.price.drop(2).toDouble(),sds = (dataItem.extra.length>0))
            tList.add(t)
        }
        return tList
    }
}