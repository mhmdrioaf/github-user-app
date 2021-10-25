package com.ananta.githubusersapp

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getUser(
        @Query("q") login: String
    ): Call<UserSearchResponse>

    @GET("users/{login}")
    @Headers("Authorization: token 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")
    fun getUserDetail(
        @Path("login") login: String
    ): Call<UserResponse>

    @GET("users/{login}/followers")
    @Headers("Authorization: token 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")
    fun getUserFollowers(
        @Path("login") login: String
    ): Call<List<PersonItem>>

    @GET("users/{login}/following")
    @Headers("Authorization: token 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")
    fun getUserFollowing(
        @Path("login") login: String
    ): Call<List<PersonItem>>

}