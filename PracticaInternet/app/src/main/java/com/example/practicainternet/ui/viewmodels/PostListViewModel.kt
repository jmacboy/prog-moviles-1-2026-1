package com.example.practicainternet.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicainternet.data.models.Post
import com.example.practicainternet.data.repositories.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostListViewModel : ViewModel() {
    private val _list: MutableStateFlow<List<Post>> = MutableStateFlow(emptyList())

    val list: StateFlow<List<Post>> = _list.asStateFlow()

    val repository = PostRepository()

    init {
        fetchPosts()
    }

    fun fetchPosts() = viewModelScope.launch {
        val result = repository.getPostsList()
        _list.value = result
    }
}