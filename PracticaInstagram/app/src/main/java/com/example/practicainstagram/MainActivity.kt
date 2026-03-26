package com.example.practicainstagram

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practicainstagram.ui.theme.PracticaInstagramTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaInstagramTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Header()
        StoryList()
        PostList()
        AppMenu()
    }
}

@Composable
fun AppMenu() {
    Row {
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.house),
                contentDescription = "Home"
            )
        }
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "Home"
            )
        }
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.reels),
                contentDescription = "Home"
            )
        }
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.bag),
                contentDescription = "Home"
            )
        }
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.user),
                contentDescription = "Home"
            )
        }
    }
}

@Composable
fun PostList() {
    val postList = arrayListOf(
        Post(
            "Alice",
            R.drawable.postimage,
            "Liked by Bob and 10 others",
            "This is a description of the post."
        ),
        Post(
            "Bob",
            R.drawable.postimage,
            "Liked by Alice and 5 others",
            "This is a description of the post."
        ),
        Post(
            "Charlie",
            R.drawable.postimage,
            "Liked by David and 8 others",
            "This is a description of the post."
        ),
        Post(
            "David",
            R.drawable.postimage,
            "Liked by Eve and 12 others",
            "This is a description of the post."
        ),
        Post(
            "Eve",
            R.drawable.postimage,
            "Liked by Frank and 3 others",
            "This is a description of the post."
        ),
        Post(
            "Frank",
            R.drawable.postimage,
            "Liked by Grace and 7 others",
            "This is a description of the post."
        ),
        Post(
            "Grace",
            R.drawable.postimage,
            "Liked by Heidi and 9 others",
            "This is a description of the post."
        ),
        Post(
            "Heidi",
            R.drawable.postimage,
            "Liked by Ivan and 4 others",
            "This is a description of the post."
        ),
        Post(
            "Ivan",
            R.drawable.postimage,
            "Liked by Judy and 6 others",
            "This is a description of the post."
        ),
        Post(
            "Judy",
            R.drawable.postimage,
            "Liked by Alice and 11 others",
            "This is a description of the post."
        )
    )
    LazyColumn() {
        items(postList) { postItem ->
            Post(postItem)
        }
    }
}

@Composable
fun Post(post: Post) {
    Column(
        modifier = Modifier.padding(0.dp, 10.dp)
    ) {
        PostTitle(post)
        PostImages(post)
        PostActions(post)
        PostReactions(post)
        PostDescription(post)
    }
}

@Composable
fun PostDescription(post: Post) {
    Text(
        text = post.description,
        modifier = Modifier.padding(5.dp, 0.dp)
    )
}

@Composable
fun PostReactions(post: Post) {
    Row{
        ReactionImages()
        Text(
            text = post.likeText,
            modifier = Modifier.padding(5.dp, 0.dp)
        )
    }
}

@Composable
fun ReactionImages() {
    Row{
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Reaction 1",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
        )
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Reaction 2",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
        )
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Reaction 3",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
        )
    }
}

@Composable
fun PostActions(post: Post) {
    Row {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            IconButton(
                onClick = { /*TODO*/ },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.heart),
                    contentDescription = "Like",
                    modifier = Modifier.padding(5.dp)
                )
            }
            IconButton(
                onClick = { /*TODO*/ },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.messages),
                    contentDescription = "Comment",
                    modifier = Modifier.padding(5.dp)
                )
            }
            IconButton(
                onClick = { /*TODO*/ },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.share),
                    contentDescription = "Share",
                    modifier = Modifier.padding(5.dp)
                )
            }
        }

        IconButton(
            onClick = { /*TODO*/ },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.save),
                contentDescription = "Save",
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}

@Composable
fun PostImages(post: Post) {
    Image(
        painter = painterResource(id = post.postImage),
        contentDescription = "Post Image",
        contentScale = ContentScale.Inside,
        modifier = Modifier
            .fillMaxSize()
    )
}

@Composable
fun PostTitle(post: Post) {
    Row {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .padding(5.dp)
            )
            Text(
                text = post.username,
                modifier = Modifier.padding(5.dp, 0.dp)
            )
        }
        IconButton(
            onClick = { /*TODO*/ },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.options),
                contentDescription = "Options",
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}

@Composable
fun StoryList() {
    val storyNames = arrayListOf(
        "Alice",
        "Bob",
        "Charlie",
        "David",
        "Eve",
        "Frank",
        "Grace",
        "Heidi",
        "Ivan",
        "Judy"
    )
    LazyRow {
        item {
            Story("Your Story")
        }
        items(storyNames) { storyName ->
            Story(storyName)
        }
    }
}

@Composable
fun Story(username: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(10.dp, 2.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Story",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
        )
        Text(
            text = username
        )
    }
}

@Composable
fun Header() {
    Row {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.instagram_logo),
                contentDescription = "Instagram Logo",
                modifier = Modifier.width(150.dp),
                contentScale = ContentScale.Fit
            )
        }
        IconButton(
            onClick = { /*TODO*/ },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.add),
                contentDescription = "Messenger",
                modifier = Modifier.padding(5.dp)
            )
        }
        IconButton(
            onClick = { /*TODO*/ },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.heart),
                contentDescription = "Messenger",
                modifier = Modifier.padding(5.dp)

            )
        }
        IconButton(
            onClick = { /*TODO*/ },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.messenger),
                contentDescription = "Messenger",
                modifier = Modifier.padding(5.dp)

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PracticaInstagramTheme {
        HomeScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun AppMenuPreview() {
    PracticaInstagramTheme {
        AppMenu()
    }
}