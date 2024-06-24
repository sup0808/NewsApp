package com.demo.newsapp.data.repository

import com.demo.newsapp.data.model.LoginModel

interface NewsRepo {

   suspend fun getLoginUser() : LoginModel
}