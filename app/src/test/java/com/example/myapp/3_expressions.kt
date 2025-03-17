package com.example.myapp

import org.junit.Assert
import org.junit.Test

class Expressions {

    fun egalDouze(nombre: Int): Boolean {
        if (nombre == 12) {
            return true
        } else if (nombre != 24) {
            return true
        } else {
            return false
        }
    }

    fun doubleSiTreize(nombre: Int): Int {
        var resultat = if (nombre == 13) 2 * nombre else nombre
        return resultat
    }

    fun sommeNombresJusque(nombre: Int): Int {
        var resultat: Int = 0
        for (i in 1..nombre) {
            resultat = resultat + i
        }
        return resultat
    }

    fun factoriel(nombre: Int): Int {
        var result :Int = 1
        for (i in 1..nombre) {
            result = result * i
        }
        return result
    }

    @Test
    fun testSommeNombreJusque() {
        Assert.assertEquals(6, sommeNombresJusque(3))
    }

    @Test
    fun testFactoriel() {
        Assert.assertEquals(6, factoriel(3))
        Assert.assertEquals(24, factoriel(4))
    }

}
