package com.example.myapp

import org.junit.Assert
import org.junit.Test

class Exo1 {

    fun multiplication(x: Int, y: Int): Int {
        var result = 0
        for (i in 1..y) {
            result = result + x
        }
        return result
    }


    fun resteDivision(nombreDivisé: Int, diviseur: Int): Int {
        var result = nombreDivisé
        while (result >= diviseur) {
            var newResult = result - diviseur
            result = newResult
        }
        return result
    }

    fun fibonacci(x: Int): Int {
        if (x <= 1) return 1
        return fibonacci(x-1) + fibonacci(x-2)
    }

    @Test
    fun testMultiplication() {
        Assert.assertEquals(105, multiplication(21, 5))
        Assert.assertEquals(0, multiplication(45, 0))
        Assert.assertEquals(-230, multiplication(-23, 10))
    }

    @Test
    fun testResteDivision() {
        Assert.assertEquals(1, resteDivision(21, 5))
        Assert.assertEquals(0, resteDivision(45, 5))
    }

    @Test
    fun testFibonacci() {
        Assert.assertEquals(3, fibonacci(3))
        Assert.assertEquals(21, fibonacci(7))
    }
}
