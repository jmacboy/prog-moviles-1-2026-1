package com.example.practicacanvas.vm.state

import androidx.compose.ui.geometry.Offset
import com.example.practicacanvas.Shape

data class CanvasPageState(
    var touchStart: Offset? = null,
    var touchEnd: Offset? = null,
    var isDrawing: Boolean = false,
    var currentShape: Shape = Shape.LINE
)