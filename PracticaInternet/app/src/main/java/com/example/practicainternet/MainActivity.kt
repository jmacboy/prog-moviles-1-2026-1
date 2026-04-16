package com.example.practicainternet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicainternet.ui.theme.PracticaInternetTheme
import com.example.practicainternet.ui.viewmodels.PostListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaInternetTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PostList(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    @Composable
    fun PostList(modifier: Modifier = Modifier, vm: PostListViewModel = PostListViewModel()) {
        val postList by vm.list.collectAsState()
        LazyColumn(modifier = modifier) {
            items(postList) { post ->
                Row {
                    Text(text = post.id.toString(), modifier = Modifier.padding(8.dp))
                    Text(text = post.title, modifier = Modifier.padding(8.dp))
                }
            }
        }
    }
}
