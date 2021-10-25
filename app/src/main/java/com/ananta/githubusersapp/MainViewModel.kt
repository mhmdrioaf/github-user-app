package com.ananta.githubusersapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _result = MutableLiveData<List<Result>>()
    val result: MutableLiveData<List<Result>> = _result

    private val _detail = MutableLiveData<UserResponse>()
    val detail: MutableLiveData<UserResponse> = _detail

    private val _followers = MutableLiveData<List<PersonItem>>()
    val followers: MutableLiveData<List<PersonItem>> = _followers

    private val _following = MutableLiveData<List<PersonItem>>()
    val following: MutableLiveData<List<PersonItem>> = _following

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "MainViewModel"
    }


    /*

    Fungsi untuk mendapatkan data dari Search

     */

    fun findUser(userId: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUser(userId)
        client.enqueue(object : Callback<UserSearchResponse> {
            override fun onResponse(
                call: Call<UserSearchResponse>,
                response: Response<UserSearchResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _result.value = responseBody.items
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<UserSearchResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    /*

    Fungsi untuk mendapatkan user detail

     */

    fun findUserDetail(userId: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUserDetail(userId)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _detail.value = responseBody
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    /*

    Fungsi untuk mendapatkan data followers

     */

    fun findUserFollowers(userId: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUserFollowers(userId)
        client.enqueue(object : Callback<List<PersonItem>> {
            override fun onResponse(
                call: Call<List<PersonItem>>,
                response: Response<List<PersonItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _followers.value = responseBody
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<List<PersonItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun findUserFollowing(userId: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUserFollowing(userId)
        client.enqueue(object : Callback<List<PersonItem>> {
            override fun onResponse(
                call: Call<List<PersonItem>>,
                response: Response<List<PersonItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _following.value = responseBody
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<List<PersonItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}