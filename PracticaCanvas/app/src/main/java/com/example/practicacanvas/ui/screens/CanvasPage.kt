package com.example.practicacanvas.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.practicacanvas.Shape
import com.example.practicacanvas.vm.CanvasPageViewModel
import kotlinx.coroutines.launch
import kotlin.math.pow
import kotlin.math.sqrt

@Composable
fun CanvasPage() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        CanvasPageContent(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun CanvasPageContent(
    modifier: Modifier = Modifier,
    vm: CanvasPageViewModel = CanvasPageViewModel()
) {

    val state = vm.state.collectAsState()
    val scope = rememberCoroutineScope()
    val graphicsLayer = rememberGraphicsLayer()
    var savedBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    Column(
        modifier = modifier
            .fillMaxSize()

            .background(
                Color.White
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    vm.setShape(Shape.RECTANGLE)
                }
            ) {
                Text("Rectangulo")
            }
            Button(
                onClick = {
                    vm.setShape(Shape.LINE)
                }
            ) {
                Text("Línea")
            }
            Button(
                onClick = {
                    vm.setShape(Shape.CIRCLE)
                }
            ) {
                Text("Círculo")
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(0.dp)
                .background(Color.Black)
                .drawWithContent {
                    // Redirect all drawing commands to the graphics layer
                    graphicsLayer.record {
                        this@drawWithContent.drawContent()
                    }
                    // Draw the layer to the screen
                    drawLayer(graphicsLayer)
                }
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp)

                    .clipToBounds()
                    .background(
                        Color.LightGray
                    )
                    .pointerInput(Unit) {
                        // Para detectar el arrastre sobre el componente
                        detectDragGestures(
                            onDragStart = { offset ->
                                vm.onDragStart(offset)
                            },
                            onDragEnd = {
                                vm.onDragEnd()
                                // Al finalizar el dibujo, guardamos el contenido del graphicsLayer en un bitmap
                                scope.launch {
                                    savedBitmap = graphicsLayer.toImageBitmap()
                                }
                            },
                            onDrag = { change, _ ->
                                change.consume()
                                vm.onDrag(change.position)
                            }
                        )
                        // Solo para detectar el toque
//                    detectTapGestures { offset ->
//                        touchStart = offset
//                    }
                    }

            ) {
                // Dibuja el bitmap guardado (si existe) antes de dibujar la forma actual
                savedBitmap?.let { bitmap ->
                    drawImage(image = bitmap)
                }
                if (state.value.touchStart != null && state.value.touchEnd != null) {
                    if (state.value.currentShape == Shape.RECTANGLE) {
                        drawRect(
                            color = Color.Red,
                            topLeft = state.value.touchStart!!,
                            size = Size(
                                width = state.value.touchEnd!!.x - state.value.touchStart!!.x,
                                height = state.value.touchEnd!!.y - state.value.touchStart!!.y
                            )
                        )
                    } else if (state.value.currentShape == Shape.LINE) {
                        drawLine(
                            color = Color.Red,
                            start = state.value.touchStart!!,
                            end = state.value.touchEnd!!,
                            strokeWidth = 10f
                        )
                    } else if (state.value.currentShape == Shape.CIRCLE) {
                        val radius = sqrt(
                            (state.value.touchEnd!!.x - state.value.touchStart!!.x).toDouble()
                                .pow(2.0) +
                                    (state.value.touchEnd!!.y - state.value.touchStart!!.y).toDouble()
                                        .pow(2.0)
                        )
                        drawCircle(
                            color = Color.Red,
                            center = state.value.touchStart!!,
                            radius = radius.toFloat()
                        )
                    }
                }
            }
        }
    }
}

fun createBitmapFromCanvas(): ImageBitmap {
    val width = 500
    val height = 500

    // 1. Create the blank ImageBitmap
    val imageBitmap = ImageBitmap(width, height)

    // 2. Create a Canvas targeting this bitmap
    val canvas = Canvas(imageBitmap)

    // 3. Use CanvasDrawScope to execute drawing commands
    val drawScope = CanvasDrawScope()
    drawScope.draw(
        density = Density(1f),
        layoutDirection = LayoutDirection.Ltr,
        canvas = canvas,
        size = Size(width.toFloat(), height.toFloat())
    ) {
        // Standard Compose drawing calls
        drawRect(color = Color.Blue)
        drawCircle(color = Color.Red, radius = 100f, center = center)
    }

    return imageBitmap
}

