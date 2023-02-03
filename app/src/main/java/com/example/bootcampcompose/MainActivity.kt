package com.example.bootcampcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
                    val all: List<Item> = viewModel.getFlow().collectAsState(initial = listOf()).value
                    Init(all)
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