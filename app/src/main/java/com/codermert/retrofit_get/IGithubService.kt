package com.codermert.retrofit_get

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IGithubService {
    @GET("{userId}")
    fun requestUserInfo(@Path("userId") userId: String): Call<User>
}