package com.example.practicacanvas.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.IntSize
import com.example.practicacanvas.CircleConstants
import com.example.practicacanvas.vm.AnimationViewModel

@Composable
fun CanvasAnimationPage() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        AnimationContent(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun AnimationContent(modifier: Modifier, vm: AnimationViewModel = AnimationViewModel()) {
    val state = vm.state.collectAsState()
    var size by remember { mutableStateOf(IntSize.Zero) }
    LaunchedEffect(Unit) {
        vm.startAnimation()
    }
    Canvas(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Gray)
            .clipToBounds()
            .onGloballyPositioned { coordinates ->
                size = coordinates.size
                vm.updateBounds(size.width, size.height)
            }
    ) {
        drawCircle(
            color = Color.Red,
            radius = CircleConstants.RADIUS,
            center = Offset(state.value.x, state.value.y)
        )
    }

}