package com.ananta.githubusersapp

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
<<<<<<< HEAD
=======
    @Headers("Authorization: token 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")
>>>>>>> master
    fun getUser(
        @Query("q") login: String
    ): Call<UserSearchResponse>

    @GET("users/{login}")
<<<<<<< HEAD
=======
    @Headers("Authorization: token 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")
>>>>>>> master
    fun getUserDetail(
        @Path("login") login: String
    ): Call<UserResponse>

    @GET("users/{login}/followers")
<<<<<<< HEAD
=======
    @Headers("Authorization: token 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")
>>>>>>> master
    fun getUserFollowers(
        @Path("login") login: String
    ): Call<List<PersonItem>>

    @GET("users/{login}/following")
<<<<<<< HEAD
=======
    @Headers("Authorization: token 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")
>>>>>>> master
    fun getUserFollowing(
        @Path("login") login: String
    ): Call<List<PersonItem>>

}