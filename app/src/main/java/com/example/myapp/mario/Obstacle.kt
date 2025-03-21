package com.example.myapp.mario

import androidx.compose.ui.unit.IntRect

data class Obstacle(
    val left: Int,
    val bottom: Int,
    val width: Int,
    val height: Int
) {
    val right = left + width
    val top = bottom + height

    fun rect() = IntRect(left, bottom, right, top)
}