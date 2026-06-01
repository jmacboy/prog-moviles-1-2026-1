package com.example.practicacanvas.vm

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import com.example.practicacanvas.Shape
import com.example.practicacanvas.vm.state.CanvasPageState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CanvasPageViewModel : ViewModel() {
    private val _state = MutableStateFlow(CanvasPageState())
    val state: StateFlow<CanvasPageState> = _state.asStateFlow()

    fun setShape(newShape: Shape) {
        _state.value = _state.value.copy(
            currentShape = newShape,
            touchStart = null,
            touchEnd = null,
            isDrawing = false
        )
    }

    fun onDragStart(offset: Offset) {
        _state.value = _state.value.copy(
            touchStart = offset,
            isDrawing = true
        )
    }

    fun onDragEnd() {
        _state.value = _state.value.copy(
            isDrawing = false
        )
    }

    fun onDrag(position: Offset) {
        _state.value = _state.value.copy(
            touchEnd = position
        )
    }

}