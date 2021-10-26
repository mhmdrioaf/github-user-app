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

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: MutableLiveData<Boolean> = _isEmpty

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
                        if(responseBody.totalCount < 1) {
                            _isEmpty.value = true
                            _result.value = responseBody.items
                        } else {
                            _isEmpty.value = false
                            _result.value = responseBody.items
                        }
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
}