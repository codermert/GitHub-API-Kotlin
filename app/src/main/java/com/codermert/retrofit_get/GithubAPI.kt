package com.codermert.retrofit_get


import retrofit2.Call

object GithubAPI {
    fun requestUserInfo(userId: String): Call<User> {
        return GithubService.service.requestUserInfo(userId)
    }
}