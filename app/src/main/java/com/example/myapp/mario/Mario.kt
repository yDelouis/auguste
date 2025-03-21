package com.example.myapp.mario

import androidx.compose.ui.unit.IntRect

data class Mario(
    val left: Int = 50,
    val bottom: Int = 0,
    val vy: Float = 0f,
    val reverse: Boolean = false
){

    val right = left + WIDTH
    val top = bottom + HEIGHT

    fun rect() = IntRect(left, bottom, right, top)

    companion object {
        const val WIDTH = 70
        const val HEIGHT = 85
    }
}
