package com.example.myapp.mario

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

private const val GRAVITY = 0.5f

private const val VITESSE_SAUT = 13f
private const val VITESSE_HORIZONTALE = 1

class Jeu {

    data class Monde(
        val mario: Mario = Mario(),
        val obstacles: List<Obstacle> = listOf(
            Obstacle(x = 200, y = 0, largeur = 50, hauteur = 110),
            Obstacle(x = 350, y = 70, largeur = 100, hauteur = 30),
            Obstacle(x = 500, y = 130, largeur = 100, hauteur = 30),
            Obstacle(x = 650, y = 200, largeur = 100, hauteur = 30),
            Obstacle(x = 800, y = 280, largeur = 100, hauteur = 30),
            Obstacle(x = 590, y = 370, largeur = 100, hauteur = 30),
            Obstacle(x = 830, y = 480, largeur = 200, hauteur = 30),
            Obstacle(x = 1040, y = 590, largeur = 50, hauteur = 30),
            Obstacle(x = 800, y = 690, largeur = 100, hauteur = 30),
            Obstacle(x = 620, y = 750, largeur = 20, hauteur = 30),
            Obstacle(x = 500, y = 850, largeur = 40, hauteur = 30),
            Obstacle(x = 300, y = 950, largeur = 100, hauteur = 30),
            Obstacle(x = 490, y = 1050, largeur = 100, hauteur = 30),
            Obstacle(x = 590, y = 1150, largeur = 100, hauteur = 30),

            //Partie a gauche
            Obstacle(x = 100, y = 200, largeur = 20, hauteur = 30),
            Obstacle(x = 250, y = 300, largeur = 20, hauteur = 30),
            Obstacle(x = 100, y = 400, largeur = 20, hauteur = 30),
            Obstacle(x = 250, y = 500, largeur = 20, hauteur = 30),

            Obstacle(x = 100, y = 600, largeur = 20, hauteur = 30),
            Obstacle(x = 250, y = 700, largeur = 20, hauteur = 30),
            Obstacle(x = 100, y = 800, largeur = 20, hauteur = 30),
            Obstacle(x = 210, y = 900, largeur = 20, hauteur = 30),

            )
    )

    var monde by mutableStateOf(Monde())
    var isGoingLeft: Boolean = false
    var isGoingRight: Boolean = false

    suspend fun start() = coroutineScope {
        launch {
            while (isActive) {
                moveMario()
                delay(20)
            }
        }
    }


    fun moveMario() {
        var mario = monde.mario
        if (isGoingLeft) {
            mario = mario.copy(x = mario.x - VITESSE_HORIZONTALE)
        } else if (isGoingRight) {
            mario = mario.copy(x = mario.x + VITESSE_HORIZONTALE)
        }

        mario = mario.copy(y = (mario.y + mario.vy).roundToInt().coerceAtLeast(0))
        if (mario.y > 0) { // Si mario est en l'air
            mario = mario.copy(vy = mario.vy - GRAVITY)
        } else { // sinon, mario est au sol
            mario = mario.copy(vy = 0f)
        }
        monde = monde.copy(mario = mario)
    }


    fun onLeftPressed() {
        isGoingLeft = true
    }

    fun onLeftReleased() {
        isGoingLeft = false
    }

    fun onRightPressed() {
        isGoingRight = true
    }

    fun onRightReleased() {
        isGoingRight = false
    }

    fun onJumpPressed() {
        monde = monde.copy(mario = monde.mario.copy(vy = VITESSE_SAUT))
    }

    fun onJumpReleased() {
    }
}