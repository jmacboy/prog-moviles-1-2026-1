package com.example.practicacanvas.vm.state

import com.example.practicacanvas.XDirection
import com.example.practicacanvas.YDirection

data class AnimationState(
    var x: Float = 100f,
    var y: Float = 100f,
    var screenWidth: Float = 0f,
    var screenHeight: Float = 0f,
    var xDirection: XDirection = XDirection.RIGHT,
    var yDirection: YDirection = YDirection.DOWN
)