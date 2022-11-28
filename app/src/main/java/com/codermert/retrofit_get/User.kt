package com.codermert.retrofit_get

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("avatar_url")
    var image: String = "",
    var name: String = "",
    @SerializedName("login")
    var userId: String = "",
    @SerializedName("public_repos")
    var repos: Int = 0)