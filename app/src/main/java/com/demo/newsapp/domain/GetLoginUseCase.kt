package com.demo.newsapp.domain

import com.demo.newsapp.data.model.LoginModel
import com.demo.newsapp.data.repository.NewsRepo
import com.demo.newsapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class GetLoginUseCase(private val newsRepo: NewsRepo) {

   operator fun invoke() : Flow<Resource<LoginModel>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(newsRepo.getLoginUser()))

        }catch (e : Throwable){
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)
}