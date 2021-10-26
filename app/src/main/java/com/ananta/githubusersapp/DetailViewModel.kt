package com.ananta.githubusersapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {

    private val _detail = MutableLiveData<UserResponse?>()
    val detail: MutableLiveData<UserResponse?> = _detail

    private val _followers = MutableLiveData<List<PersonItem>?>()
    val followers: MutableLiveData<List<PersonItem>?> = _followers

    private val _following = MutableLiveData<List<PersonItem>?>()
    val following: MutableLiveData<List<PersonItem>?> = _following

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: MutableLiveData<Boolean> = _isEmpty

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

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
                        if(responseBody.isEmpty()) {
                            _isEmpty.value = true
                            _followers.value = responseBody
                        } else {
                            _followers.value = responseBody
                            _isEmpty.value = false
                        }
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
                        if(responseBody.isEmpty()) {
                            _isEmpty.value = true
                            _following.value = responseBody
                        } else {
                            _isEmpty.value = false
                            _following.value = responseBody
                        }
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

    companion object {
        private const val TAG = "DetailViewModel"
    }

}