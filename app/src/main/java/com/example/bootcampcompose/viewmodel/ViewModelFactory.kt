package com.example.bootcampcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bootcampcompose.db.ItemRepo

class ViewModelFactory(private val repo: ItemRepo) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(ItemViewModel::class.java)){
            return ItemViewModel(repo) as T
        }
        throw IllegalArgumentException("Unkown Viewmodel class")
    }
}