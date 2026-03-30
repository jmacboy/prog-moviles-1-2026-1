package com.example.practicainstagram.models

data class Post(
    val id: Int,
    val username: String,
    val postImage: Int,
    val likeText: String,
    val description: String,
    var showCommentInput: Boolean = false
) {
}