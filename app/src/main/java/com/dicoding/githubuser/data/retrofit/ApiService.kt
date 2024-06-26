package com.dicoding.githubuser.data.retrofit

import com.dicoding.githubuser.BuildConfig
import com.dicoding.githubuser.data.response.ResponseDetailUser
import com.dicoding.githubuser.data.response.ResponseUserGithub
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ApiService {

    @JvmSuppressWildcards
    @GET("users")
    suspend fun getUserGithub(
        @Header("Authorization")
        authorization: String = BuildConfig.API_KEY
    ): MutableList<ResponseUserGithub.Item>

    @JvmSuppressWildcards
    @GET("users/{username}")
    suspend fun getDetailUserGithub(
        @Path("username") username: String,
        @Header("Authorization")
        authorization: String = BuildConfig.API_KEY
    ): ResponseDetailUser

    @JvmSuppressWildcards
    @GET("/users/{username}/followers")
    suspend fun getFollowersUserGithub(
        @Path("username") username: String,
        @Header("Authorization")
        authorization: String = BuildConfig.API_KEY
    ): MutableList<ResponseUserGithub.Item>

    @JvmSuppressWildcards
    @GET("/users/{username}/following")
    suspend fun getFollowingUserGithub(
        @Path("username") username: String,
        @Header("Authorization")
        authorization: String = BuildConfig.API_KEY
    ): MutableList<ResponseUserGithub.Item>

    @JvmSuppressWildcards
    @GET("search/users")
    suspend fun searchUserGithub(
        @QueryMap params: Map<String, Any>,
        @Header("Authorization")
        authorization: String = BuildConfig.API_KEY
    ): ResponseUserGithub
}