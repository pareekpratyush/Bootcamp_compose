package com.example.bootcampcompose.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bootcampcompose.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun upsert(item:Item)

    @Query("SELECT * FROM items")
    fun getAll():Flow<List<Item>>

    @Query("SELECT * FROM items WHERE name LIKE '%' || :query || '%' ")
    fun searchItem(query:String?):Flow<List<Item>>
}