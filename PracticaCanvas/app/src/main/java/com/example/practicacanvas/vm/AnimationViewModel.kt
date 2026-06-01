package com.example.practicacanvas.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicacanvas.CircleConstants
import com.example.practicacanvas.XDirection
import com.example.practicacanvas.vm.state.AnimationState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnimationViewModel : ViewModel() {

    fun startAnimation() = viewModelScope.launch {

        while (true) {

            _state.value = _state.value.copy(
                x = _state.value.x + (getXDirection()) * 5f,
                y = _state.value.y + (getYDirection()) * 5f
            )
            delay(16L) //16ms de espera
            if (_state.value.x >= _state.value.screenWidth - CircleConstants.RADIUS) {
                _state.value = _state.value.copy(
                    xDirection = XDirection.LEFT
                )
            }
            if (_state.value.x <= CircleConstants.RADIUS) {
                _state.value = _state.value.copy(
                    xDirection = XDirection.RIGHT
                )
            }
            if (_state.value.y >= _state.value.screenHeight - CircleConstants.RADIUS) {
                _state.value = _state.value.copy(
                    yDirection = com.example.practicacanvas.YDirection.UP
                )
            }
            if (_state.value.y <= CircleConstants.RADIUS) {
                _state.value = _state.value.copy(
                    yDirection = com.example.practicacanvas.YDirection.DOWN
                )
            }
        }
    }

    private fun getYDirection(): Float {
        return if (_state.value.yDirection == com.example.practicacanvas.YDirection.DOWN) {
            1f
        } else {
            -1f
        }
    }

    private fun getXDirection(): Float {
        return if (_state.value.xDirection == XDirection.RIGHT) {
            1f
        } else {
            -1f
        }
    }

    fun updateBounds(width: Int, height: Int) {
        _state.value = _state.value.copy(
            screenWidth = width.toFloat(),
            screenHeight = height.toFloat()
        )
    }

    private val _state = MutableStateFlow(AnimationState())
    val state: StateFlow<AnimationState> = _state.asStateFlow()

}