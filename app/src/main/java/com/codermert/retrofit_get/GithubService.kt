package com.codermert.retrofit_get

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubService {
    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl("https://api.github.com/users/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    var service: IGithubService = retrofit.create(IGithubService::class.java)
}

