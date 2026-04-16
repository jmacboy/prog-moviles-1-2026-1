package com.example.practicainternet.data.repositories

import com.example.practicainternet.data.RetrofitInstance
import com.example.practicainternet.data.models.Post

class PostRepository {
    suspend fun getPostsList(): List<Post> {
        try {
            val retrofitInstance = RetrofitInstance.api
            return retrofitInstance.listPosts()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return emptyList()
    }
}