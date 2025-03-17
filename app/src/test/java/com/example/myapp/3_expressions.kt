package com.example.myapp

class Expressions {

    fun egalDouze(nombre: Int): Boolean {
        if (nombre == 12) return true
        else return false
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

        return nombre
    }

    @Test
    fun testFactoriel() {

    }

}
