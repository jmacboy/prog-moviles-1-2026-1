package com.example.practicainternet.data

import com.example.practicainternet.data.models.Post
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("posts")
    suspend fun listPosts(): List<Post>

    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): Post
}
