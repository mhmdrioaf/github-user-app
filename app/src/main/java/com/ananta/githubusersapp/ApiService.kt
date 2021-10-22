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