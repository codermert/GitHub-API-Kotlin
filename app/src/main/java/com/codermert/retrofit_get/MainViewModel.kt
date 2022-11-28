package com.codermert.retrofit_get

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    val okCode: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var userData: User

    fun requestUserInfo(userId: String) {
        GithubAPI.requestUserInfo(userId).enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                userData = response.body()?.let {
                    User(
                        image = it.image,
                        name = it.name,
                        userId = it.userId,
                        repos = it.repos
                    )
                } ?: User("", "", "", 0)
                okCode.value = true
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                okCode.value = false
                Log.d("minha - MainViewModel", "${t.message}")
            }
        })
    }
}