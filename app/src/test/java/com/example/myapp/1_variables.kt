package com.example.myapp

class Variables {

    /**
     * Une variable est une boîte dans laquelle on stocke quelque chose
     * Si une boîte peut être vide, on dit que la variable est nullable
     * Dans ce cas, en kotlin, le type finit par ?
     *
     * var <nomDeLaVariable>: <Type>
     * ou
     * var <nomDeLaVariable>: <Type> = <ValeurInitiale>
     * var => pour déclarer une nouvelle variable
     * <nomDeLaVariable> => choisi par le dev pour representer ce que va stocker la boîte
     * <Type> => forme de la boîte. Dans cette boîte, on ne pourra mettre que des choses du même type
     * <ValeurInitiale> => valeur à mettre dans la boîte
     *
     */

    // Nombre entier sur 32 bit (de -2^31 à 2^31)
    var integerVariable: Int = 12
    // Nombre entier sur 64 bit (de -2^63 à 2^63)
    var longVariable: Long = 15
    // Nombre a virgule sur 32 bit
    var floatVariable: Float = -3.4f
    // Nombre a virgule sur 64 bit
    var doubleVariable: Double = -3.4

    // Texte
    var textVariable: String = "Un texte"
    var textVariable2: String = "integerVariable contient ${integerVariable}"

    // Booléen : 2 valeurs : true (vrai) et false (faux)
    var booleanVariable: Boolean = true

    // Variables nullables
    var nullableInteger: Int? = null
    var nullableInteger2: Int? = 1

    var nullableTextVariable: String? = null

    var list: List<Int> = listOf(12, 23, 54)
    var mutableList: MutableList<Int> = mutableListOf(487, 34, 34)

    /**
     * Changer la ce qu'on met dans la boîte s'appelle assigner. La syntaxe est :
     * <nomDeLaVariable> = <nouvelleValeur>
     */

    fun example() {
        integerVariable = 16
        nullableTextVariable = "un nouveau texte"
        list.get(1)
        mutableList.add(23)
    }
}
