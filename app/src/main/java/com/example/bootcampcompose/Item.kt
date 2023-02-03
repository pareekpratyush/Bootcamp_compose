package com.example.bootcampcompose

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="items")
data class Item(
    @PrimaryKey
    val name:String,
    val image:Int,
    val price: Double,
    val sds:Boolean
    )
