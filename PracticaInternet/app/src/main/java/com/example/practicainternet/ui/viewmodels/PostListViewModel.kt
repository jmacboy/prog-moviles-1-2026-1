package com.example.practicainternet.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicainternet.data.repositories.PostRepository
import com.example.practicainternet.ui.states.PostListUIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostListViewModel : ViewModel() {
    private val _state: MutableStateFlow<PostListUIModel> = MutableStateFlow(
        PostListUIModel(
            postList = emptyList(),
            selectedPostId = null
        )
    )

    val state: StateFlow<PostListUIModel> = _state.asStateFlow()

    val repository = PostRepository()

    init {
        fetchPosts()
    }

    fun fetchPosts() = viewModelScope.launch {
        val result = repository.getPostsList()
        _state.update {
            it.copy(postList = result)
        }
    }

    fun selectItem(id: Int) {
        _state.update {
            it.copy(
                selectedPostId = id,
            )
        }
        fetchPostItem(id)

    }

    private fun fetchPostItem(id: Int) = viewModelScope.launch {
        val postResult = repository.getPostById(id)
        _state.update {
            it.copy(
                selectedPost = postResult
            )
        }
    }

}