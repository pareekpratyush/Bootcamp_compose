package com.example.bootcampcompose.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bootcampcompose.Item

@Database(
    entities = [Item::class],
    version = 1
)
abstract class ItemDB : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var instance: ItemDB? = null

        fun getInstance(context: Context): ItemDB? {
            if (instance == null) {
                synchronized(ItemDB::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ItemDB::class.java,
                        "item-db.db"
                    ).build()
                }
            }
            return instance!!
        }
    }
}
