package com.demo.newsapp.data.network

import com.demo.newsapp.data.model.LoginModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET
    suspend fun loginUser() : LoginModel

    @POST
    @FormUrlEncoded
    suspend fun loginUser(
        @Field("Emailid") emailId : String,
        @Field("Password") password : String
     ) : LoginModel
}

/*
https://api.jsonbin.io/v3/b/6678d97aacd3cb34a85c3cc1/meta/privacy
X-Bin-Private:false
X-Master-Key:$2a$10$mDI9.IE3eZtsYRlrKHfFWe65tHkqzT6VAmGtrPtaJH36Qsrriq5bO
        Put api*/
