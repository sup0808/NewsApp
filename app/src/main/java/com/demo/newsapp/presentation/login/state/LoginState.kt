package com.demo.newsapp.presentation.login.state

import com.demo.newsapp.data.model.LoginModel

data class LoginState(
    var isLoading: Boolean = false,
    var error: String? = "",
    var success: LoginModel? = LoginModel(),
    var status : Int = -1

    )