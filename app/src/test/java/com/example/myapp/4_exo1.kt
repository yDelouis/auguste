package com.example.myapp

import org.junit.Assert
import org.junit.Test

class Exo1 {

    fun resteDivision(nombreDivisé: Int, diviseur: Int): Int {
        return diviseur
    }

    @Test
    fun testResteDivision() {
        Assert.assertEquals(1, resteDivision(21, 5))
        Assert.assertEquals(0, resteDivision(45, 5))
        Assert.assertEquals(-1, resteDivision(-23, 11))
    }
}
