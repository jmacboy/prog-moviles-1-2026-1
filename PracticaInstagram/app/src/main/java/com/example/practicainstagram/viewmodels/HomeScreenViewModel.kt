package com.example.practicainstagram.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.practicainstagram.R
import com.example.practicainstagram.models.Post
import com.example.practicainstagram.models.Story

class HomeScreenViewModel : ViewModel() {
    fun showComment(post: Post) {
        val index = _postList.indexOfFirst { it.id == post.id }
        if (index != -1) {
            _postList[index] = _postList[index].copy(showCommentInput = !_postList[index].showCommentInput)
        }
    }

    private val _postList = mutableStateListOf(
        Post(
            1,
            "Alice",
            R.drawable.postimage,
            "Liked by Bob and 10 others",
            "This is a description of the post."
        ),
        Post(
            2,
            "Bob",
            R.drawable.postimage,
            "Liked by Alice and 5 others",
            "This is a description of the post."
        ),
        Post(
            3,
            "Charlie",
            R.drawable.postimage,
            "Liked by David and 8 others",
            "This is a description of the post."
        ),
        Post(
            4,
            "David",
            R.drawable.postimage,
            "Liked by Eve and 12 others",
            "This is a description of the post."
        ),
        Post(
            5,
            "Eve",
            R.drawable.postimage,
            "Liked by Frank and 3 others",
            "This is a description of the post."
        ),
        Post(
            6,
            "Frank",
            R.drawable.postimage,
            "Liked by Grace and 7 others",
            "This is a description of the post."
        ),
        Post(
            7,
            "Grace",
            R.drawable.postimage,
            "Liked by Heidi and 9 others",
            "This is a description of the post."
        ),
        Post(
            8,
            "Heidi",
            R.drawable.postimage,
            "Liked by Ivan and 4 others",
            "This is a description of the post."
        ),
        Post(
            9,
            "Ivan",
            R.drawable.postimage,
            "Liked by Judy and 6 others",
            "This is a description of the post."
        ),
        Post(
            10,
            "Judy",
            R.drawable.postimage,
            "Liked by Alice and 11 others",
            "This is a description of the post."
        )
    )
    val postList: SnapshotStateList<Post> = _postList
    val storyList = arrayListOf(
        Story("Alice", R.drawable.profile),
        Story("Bob", R.drawable.profile),
        Story("Charlie", R.drawable.profile),
        Story("David", R.drawable.profile),
        Story("Eve", R.drawable.profile),
        Story("Frank", R.drawable.profile),
        Story("Grace", R.drawable.profile),
        Story("Heidi", R.drawable.profile),
        Story("Ivan", R.drawable.profile),
        Story("Judy", R.drawable.profile)
    )
}