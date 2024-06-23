package com.demo.newsapp.presentation.login.state

data class ErrorState(
    val emailStatus : Boolean = false,
    val passwordStatus : Boolean = false
)