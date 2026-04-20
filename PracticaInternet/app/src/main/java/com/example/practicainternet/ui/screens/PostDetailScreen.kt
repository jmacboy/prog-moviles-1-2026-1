package com.example.practicainternet.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.practicainternet.ui.viewmodels.PostListViewModel

@Composable
fun PostDetailScreen(
    vm: PostListViewModel = PostListViewModel()
) {
    Scaffold(modifier = Modifier) { innerPadding ->
        PostDetail(modifier = Modifier.padding(innerPadding), vm = vm)
    }
}


@Composable
fun PostDetail(vm: PostListViewModel, modifier: Modifier = Modifier) {
    val postDetail = vm.state.collectAsState().value.selectedPost
    if (postDetail == null) {
        Text("No se ha seleccionado ningún post", modifier = modifier.padding(16.dp))
        return
    }
    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "ID: ${postDetail.id}", modifier = Modifier.padding(bottom = 8.dp))
        Text(text = "Title: ${postDetail.title}", modifier = Modifier.padding(bottom = 8.dp))
        Text(text = "Body: ${postDetail.body}")
    }
}