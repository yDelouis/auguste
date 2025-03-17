package com.example.myapp

import org.junit.Assert
import org.junit.Test

class Functions {

    /**
     * Une fonction est comme un petit robot qui suit une logique pour,
     * en fonction de variables qu'on lui donne, calculer un résultat.
     *
     * La syntaxe est :
     * fun <nomDeLaFonction>(<parametre1: TypeParametre1>, <parametre2: TypeParamètre2>, ...): <TypeDeRetour> {
     *    <logiqueDeLaFonction>
     *    return résultat
     * }
     */

    fun somme(nombre1: Int, nombre2: Int): Int {
        return nombre1 + nombre2
    }

    fun soustraction(nombre1: Int, nombre2: Int): Int = nombre1 - nombre2

    fun multiplication(number1: Int, number2: Int): Int {
        return number1 * number2
    }

    /**
     * Les tests permettent de tester que notre fonction marche bien sur des cas calculés à la main.
     * Ça permet de s'assurer que ce que l'on fait est correct.
     * Et ça permet surtout de s'assurer qu'on ne casse rien quand on change le code
     * On peut les lancer en cliquant sur le triangle vert à gauche de la fonction.
     */

    @Test
    fun testSomme() {
        Assert.assertEquals(12, somme(7, 5))
        Assert.assertEquals(-6, somme(6, -12))
    }

    @Test
    fun testMultiplication() {
        Assert.assertEquals(12, multiplication(3, 4))
        Assert.assertEquals(-6, multiplication(-2, 3))
        Assert.assertEquals(0, multiplication(23, 0))
    }
}
