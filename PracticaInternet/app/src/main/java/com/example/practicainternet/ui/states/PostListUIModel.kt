package com.example.practicainternet.ui.states

import com.example.practicainternet.data.models.Post

data class PostListUIModel(
    val postList: List<Post>,
    val selectedPostId: Int? = null,
    val selectedPost: Post? = null
)