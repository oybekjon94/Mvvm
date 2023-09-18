package com.example.mvvm_1.networking

import me.sianaki.flowretrofitadapter.FlowCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiiClient {

    const val BASE_URL = "https://api.github.com/"
    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(FlowCallAdapterFactory.create())
            .build()
    }
    //shuni ichidan ApiServesni chaqirib olish mumkin
    val apiService = getRetrofit().create(ApiServes::class.java)
}