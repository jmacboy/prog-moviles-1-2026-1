package com.example.practicalistas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practicalistas.ui.theme.PracticaListasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaListasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ListScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ListScreen(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        item {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .background(Color.LightGray)
            ) {
                Text(
                    text = "Header",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        items(100) { index ->
            Text(
                text = "Item #$index",
                modifier = Modifier.padding(16.dp)
            )
        }
        item {
            ButtonList()
        }
    }
}

@Composable
fun NamesListScreen(modifier: Modifier = Modifier) {
    val names = arrayListOf(
        "Alice", "Bob", "Charlie", "David", "Eve",
        "Frank", "Grace", "Heidi", "Ivan", "Judy",
        "Karl", "Leo", "Mallory", "Nina", "Oscar",
        "Peggy", "Quentin", "Rupert", "Sybil", "Trent",
        "Uma", "Victor", "Walter", "Xavier", "Yvonne",
        "Zara"
    )
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(names) { name ->
            Text(
                text = name,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            )
        }
    }
}

@Composable
fun ButtonList(modifier: Modifier = Modifier) {
    LazyRow {
        items(10) {
            Button(
                onClick = {}
            ) {
                Text(text = "Button #$it")
            }
        }
    }
}

@Composable
fun PersonListScreen(modifier: Modifier = Modifier) {
    val people = arrayListOf<Person>(
        Person("Alice", "Smith", 30, "555-1234"),
        Person("Bob", "Johnson", 25, "555-5678"),
        Person("Charlie", "Williams", 35, "555-8765"),
        Person("David", "Brown", 28, "555-4321"),
        Person("Eve", "Davis", 22, "555-2468"),
        Person("Frank", "Miller", 40, "555-1357"),
        Person("Grace", "Wilson", 27, "555-9876"),
        Person("Heidi", "Moore", 32, "555-2468"),
        Person("Ivan", "Taylor", 29, "555-3698"),
        Person("Judy", "Anderson", 31, "555-7531")
    )

    LazyColumn(
        modifier = modifier.fillMaxSize()

    ) {
        items(people) { person ->
            PersonItem(person)
        }
    }
}

@Composable
fun PersonItem(person: Person) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()

    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .weight(1f)
        ) {
            Text(
                text = "${person.name} ${person.lastName}",
                modifier = Modifier.padding(0.dp)
            )
            Text(
                text = person.phone,
                fontSize = 20.sp,
                modifier = Modifier.padding(0.dp)
            )
        }
        Text(
            text = person.age.toString(),
            fontSize = 30.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NamesListScreenPreview() {
    PracticaListasTheme {
        NamesListScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    PracticaListasTheme {
        ListScreen()
    }
}