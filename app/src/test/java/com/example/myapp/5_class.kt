package com.example.myapp

data class Rectangle(val longueur: Int, val largeur: Int) {
    fun aire() = longueur * largeur
}

fun main() {
    var rect1: Rectangle = Rectangle(2, 3)
    var aire1 = rect1.aire()
    var rect2 = Rectangle(5, 6)
    var aire2 = rect2.aire()
}