package com.ananta.githubusersapp

import retrofit2.Call
import retrofit2.http.*

private const val mySuperSecretKey = BuildConfig.KEY

interface ApiService {

    @GET("search/users")
    @Headers("Authorization: token $mySuperSecretKey")
    fun getUser(
        @Query("q") login: String
    ): Call<UserSearchResponse>

    @GET("users/{login}")
    @Headers("Authorization: token $mySuperSecretKey")
    fun getUserDetail(
        @Path("login") login: String
    ): Call<UserResponse>

    @GET("users/{login}/followers")
    @Headers("Authorization: token $mySuperSecretKey")
    fun getUserFollowers(
        @Path("login") login: String
    ): Call<List<PersonItem>>

    @GET("users/{login}/following")
    @Headers("Authorization: token $mySuperSecretKey")
    fun getUserFollowing(
        @Path("login") login: String
    ): Call<List<PersonItem>>

}