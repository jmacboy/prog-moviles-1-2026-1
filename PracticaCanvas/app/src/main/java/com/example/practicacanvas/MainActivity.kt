package com.example.practicacanvas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import com.example.practicacanvas.ui.theme.PracticaCanvasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticaCanvasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CanvasPage(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CanvasPage(modifier: Modifier = Modifier) {

    var touchStart by remember { mutableStateOf<Offset?>(null) }
    var touchEnd by remember { mutableStateOf<Offset?>(null) }
    var isDrawing by remember { mutableStateOf(false) }

    Canvas(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {

                detectDragGestures(
                    onDragStart = { offset ->
                        touchStart = offset
                        isDrawing = true
                    },
                    onDragEnd = {
                        isDrawing = false
                    },
                    onDrag = { change, _ ->
                        change.consume()
                        touchEnd = change.position
                    }
                )
                detectTapGestures { offset ->
                    touchStart = offset
                }
            }

    ) {
        drawLine(
            color = Color.Blue,
            start = Offset(100f, 100f),
            end = Offset(300f, 300f),
        )
//        touchPoint?.let {
//            drawCircle(
//                color = Color.Red,
//                radius = 100f,
//                center = it
//            )
//        }
        if (touchStart != null && touchEnd != null) {
            drawLine(
                color = Color.Red,
                start = touchStart!!,
                end = touchEnd!!,
                strokeWidth = 10f
            )
        }
        drawLine(
            color = Color.Blue,
            start = this.center,
            end = Offset(700f, 1500f),
            strokeWidth = 5f
        )
        drawRect(
            color = Color.Green,
            topLeft = Offset(300f, 300f),
            size = Size(300f, 200f)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun CanvasPagePreview() {
    PracticaCanvasTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            CanvasPage(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
