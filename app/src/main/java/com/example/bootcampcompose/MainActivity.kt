package com.example.bootcampcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bootcampcompose.db.ItemDB
import com.example.bootcampcompose.db.ItemRepo
import com.example.bootcampcompose.ui.theme.BootcampComposeTheme
import com.example.bootcampcompose.layouts.Init
import com.example.bootcampcompose.network.Api
import com.example.bootcampcompose.network.ApiService
import com.example.bootcampcompose.viewmodel.ItemViewModel
import com.example.bootcampcompose.viewmodel.ViewModelFactory
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue


class MainActivity : ComponentActivity() {

    private val viewModel : ItemViewModel by lazy {
        ViewModelProvider(this,
        ViewModelFactory(ItemRepo(Api.retrofitService,ItemDB.getInstance(this)!!))
        ).get(ItemViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BootcampComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var query = remember{mutableStateOf(TextFieldValue(""))}
                    val all: List<Item> = viewModel.searchItem(query).collectAsState(initial = listOf()).value
                    Init(all, query)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BootcampComposeTheme {

    }
}