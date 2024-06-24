package com.demo.newsapp.data.repository

import com.demo.newsapp.data.model.LoginModel
import com.demo.newsapp.data.network.ApiService

class NewsRepImpl(val apiService: ApiService) : NewsRepo {
    override suspend fun getLoginUser(): LoginModel {
       return apiService.loginUser()
    }
}