package com.example.bootcampcompose.network

data class ApiItem(
    val `data`: Data,
    val error: Any,
    val status: String
)