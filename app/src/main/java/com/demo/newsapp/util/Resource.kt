package com.demo.newsapp.util

sealed class Resource<T>(val datas : T? = null, val errorMessage : String?= null){

     class Loading<T>() : Resource<T>()

    data class Success<T>(val _datas: T?=null) : Resource<T>(datas=_datas)

    data class Error<T>(val _errorMessage : String? = null) : Resource<T>(errorMessage=_errorMessage)
}