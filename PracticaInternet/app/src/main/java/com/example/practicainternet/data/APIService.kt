package com.example.practicainternet.data

import com.example.practicainternet.data.models.Post
import retrofit2.http.GET

interface APIService {
    @GET("posts")
    suspend fun listPosts(): List<Post>
}
