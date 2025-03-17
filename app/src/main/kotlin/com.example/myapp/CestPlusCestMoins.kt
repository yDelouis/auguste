package com.example.myapp

import kotlin.random.Random

fun main() {
    // Générer un nombre entier aléatoire entre 0 et 10 000 (10 000 exclus)
    val randomNumber = Random.nextInt(0, 10001) // La borne supérieure (10001) est exclusive
    println("Le nombre aléatoire généré entre 0 et 10 000 est : $randomNumber")

    var usernumber = 10001
    while (usernumber != randomNumber) {
        usernumber = demanderUnNombre()
        direSiCestPlusOuSiCestMoins(usernumber, randomNumber)
    }
    direQuonAGagné()
}

fun demanderUnNombre(): Int {
    // Demander un nombre à l'utilisateur
    print("Veuillez entrer un nombre : ")

    // Lire l'entrée de l'utilisateur
    val input = readLine()

    // Convertir l'entrée en entier et gérer les erreurs possibles
    val number = input?.toIntOrNull()

    if (number != null) {
        println("Vous avez entré le nombre : $number")
        return number
    } else {
        println("Ce n'est pas un nombre valide.")
        return 0
    }
}

fun direSiCestPlusOuSiCestMoins(usernumber:Int, randomnumber:Int) {
    if(usernumber>=randomnumber) {
        println("C'est moins")
    }else{
        println("C'est plus")
    }
}

fun direQuonAGagné() {
println("Tu as gagné")
}

