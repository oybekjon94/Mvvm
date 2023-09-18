package com.example.mvvm_1.networking

import com.example.mvvm_1.models.UserData
import retrofit2.http.GET

interface ApiServes {

    @GET("users")
    fun getUsers(): kotlinx.coroutines.flow.Flow<List<UserData>>
}