package com.ananta.githubusersapp

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    fun getUser(
        @Query("q") login: String
    ): Call<UserSearchResponse>

    @GET("users/{login}")
    fun getUserDetail(
        @Path("login") login: String
    ): Call<UserResponse>

    @GET("users/{login}/followers")
    fun getUserFollowers(
        @Path("login") login: String
    ): Call<List<PersonItem>>

    @GET("users/{login}/following")
    fun getUserFollowing(
        @Path("login") login: String
    ): Call<List<PersonItem>>

}