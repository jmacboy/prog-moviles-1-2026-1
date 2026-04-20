package com.example.practicainternet.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.practicainternet.data.models.Post
import com.example.practicainternet.ui.NavScreens
import com.example.practicainternet.ui.viewmodels.PostListViewModel


@Composable
fun PostListScreen(
    modifier: Modifier = Modifier,
    vm: PostListViewModel = PostListViewModel(),
    navController: NavHostController
) {
    Scaffold(modifier = modifier) { innerPadding ->
        PostList(modifier = Modifier.padding(innerPadding), vm = vm, onNavigate = {
            navController.navigate(NavScreens.DETAIL.name)
        })
    }
}

@Composable
fun PostList(
    modifier: Modifier = Modifier,
    vm: PostListViewModel = PostListViewModel(),
    onNavigate: () -> Unit
) {

    val postListState by vm.state.collectAsState()
    LazyColumn(modifier = modifier) {
        items(postListState.postList) { post ->
            PostItem(post, vm, onNavigate)
        }
    }
}

@Composable
fun PostItem(item: Post, vm: PostListViewModel, onNavigate: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                vm.selectItem(item.id)
                onNavigate()
            }
    ) {
        Text(text = item.id.toString(), modifier = Modifier.padding(8.dp))
        Text(text = item.title, modifier = Modifier.padding(8.dp))
    }
}