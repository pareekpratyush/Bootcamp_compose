package com.example.bootcampcompose.db

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.asLiveData
import com.example.bootcampcompose.Item
import com.example.bootcampcompose.R
import com.example.bootcampcompose.network.Api
import com.example.bootcampcompose.network.ApiItem
import com.example.bootcampcompose.network.Data
import com.example.bootcampcompose.network.DataItem
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test

class ItemRepoTest {

    private lateinit var testRepo: MockItemRepo

    @Before
    fun setUp() {
        testRepo = MockItemRepo()
    }

    @Test
    fun test_insertItem_Normal() = runBlocking {
        testRepo.items = mutableListOf<Item>()
        testRepo.insertItem(Item("A", R.drawable.img, 5.00, true))

        assertThat(testRepo.items.size).isEqualTo(1)
        assertThat(testRepo.items[0]).isEqualTo(Item("A", R.drawable.img, 5.00, true))
    }

    @Test
    fun test_insertItem_MultipleItems() = runBlocking {
        testRepo.items = mutableListOf<Item>()
        val A = Item("A", R.drawable.img, 5.00, true)
        val B = Item("B", R.drawable.img, 15.00, false)
        testRepo.insertItem(A)
        testRepo.insertItem(B)

        assertThat(testRepo.items.size).isEqualTo(2)
        assertThat(testRepo.items).isEqualTo(mutableListOf(A, B))
    }

    @Test
    fun test_getItemsFlow_Empty() = runBlocking {
        testRepo.items = mutableListOf<Item>()
        val op = testRepo.getItemsFlow().toList()[0]
        assertThat(op).isEqualTo(listOf<Item>())
    }

    @Test
    fun test_getItemsFlow_withItems() = runBlocking {
        val A = Item("A", R.drawable.img, 5.00, true)
        val B = Item("B", R.drawable.img, 15.00, false)
        testRepo.items = mutableListOf<Item>(A, B)

        val op = testRepo.getItemsFlow().toList()[0]
        assertThat(op).isEqualTo(listOf<Item>(A, B))
    }

    @Test
    fun test_searchItem_itemExists() = runBlocking {
        val A = Item("A", R.drawable.img, 5.00, true)
        val B = Item("B", R.drawable.img, 15.00, false)
        testRepo.items = mutableListOf<Item>(A, B)

        val op = testRepo.searchItem("A").toList()[0]
        assertThat(op).isEqualTo(listOf(A))
    }

    @Test
    fun test_searchItem_itemDoesntExist() = runBlocking{
        val A = Item("A", R.drawable.img, 5.00, true)
        val B = Item("B", R.drawable.img, 15.00, false)
        testRepo.items = mutableListOf<Item>(A, B)

        val op = testRepo.searchItem("C").toList()[0]
        assertThat(op).isEqualTo(listOf<Item>())
    }

    @Test
    fun test_searchItem_multipleExist() = runBlocking {
        val A = Item("A", R.drawable.img, 5.00, true)
        val B = Item("B", R.drawable.img, 15.00, false)
        val C = Item("AC", R.drawable.img, 25.00, true)
        testRepo.items = mutableListOf<Item>(A, B, C)

        val op = testRepo.searchItem("A").toList()[0]
        assertThat(op).isEqualTo(listOf(A,C))
    }

    @Test
    fun test_apiItemToListItems_oneItem() {
        val apiItem = ApiItem(Data(listOf(DataItem("Same Day Shipping","Item 1","R 100"))),"null","success")
        val list = testRepo.apiItemToListItems(apiItem.data.items)
        assertThat(list).isEqualTo(listOf(Item("Item 1",R.drawable.img,100.00,true)))
    }

    @Test
    fun test_apiItemsToListItems_multipleItems() {
        val apiItem = ApiItem(Data(listOf(DataItem("Same Day Shipping","Item 1","R 100"),DataItem("","Item 2","R 50"))),"null","success")
        val list = testRepo.apiItemToListItems(apiItem.data.items)
        assertThat(list).isEqualTo(listOf(Item("Item 1",R.drawable.img,100.00,true),Item("Item 2",R.drawable.img,50.00,false)))
    }

    @Test
    fun test_apiItemsToListItems_empty() {
        val apiItem:ApiItem = ApiItem(Data(listOf<DataItem>()),"null","success")
        val list = testRepo.apiItemToListItems(apiItem.data.items)
        assertThat(list).isEqualTo(listOf<Item>())
    }

    @Test
    fun test_updateDB_normal() = runBlocking{
        testRepo.items = mutableListOf()
        val apiItem = ApiItem(Data(listOf(DataItem("Same Day Shipping","Item 1","R 100"),DataItem("","Item 2","R 50"))),"null","success")
        testRepo.updateDB(apiItem)
        val list = testRepo.items
        assertThat(list).isEqualTo(listOf(Item("Item 1",R.drawable.img,100.00,true),Item("Item 2",R.drawable.img,50.00,false)))
    }

    @Test
    fun test_updateDB_nullFromApi() = runBlocking{
        testRepo.items = mutableListOf()
        val apiItem = null
        testRepo.updateDB(apiItem)
        val list = testRepo.items
        assertThat(list).isEqualTo(listOf<Item>())

    }


}