package com.demo.newsapp.presentation.login.state

sealed class LoginEvent{

    data class EmailChanged(var emailId : String) : LoginEvent()
    data class  PasswordChanged(var password : String) : LoginEvent()

    object Submit : LoginEvent()
}