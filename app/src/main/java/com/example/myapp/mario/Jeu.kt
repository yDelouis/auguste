package com.example.myapp.mario

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
private const val VITESSE_HORIZONTALE = 3

class Jeu {

    data class Monde(
        val mario: Mario = Mario(),
        val obstacles: List<Obstacle> = listOf(
            Obstacle(left = 200, bottom = 0, width = 50, height = 110),
            Obstacle(left = 350, bottom = 70, width = 100, height = 30),
            Obstacle(left = 500, bottom = 130, width = 100, height = 30),
            Obstacle(left = 650, bottom = 200, width = 100, height = 30),
            Obstacle(left = 800, bottom = 280, width = 100, height = 30),
            Obstacle(left = 590, bottom = 370, width = 100, height = 30),
            Obstacle(left = 830, bottom = 480, width = 200, height = 30),
            Obstacle(left = 1040, bottom = 590, width = 50, height = 30),
            Obstacle(left = 800, bottom = 690, width = 100, height = 30),
            Obstacle(left = 620, bottom = 750, width = 20, height = 30),
            Obstacle(left = 500, bottom = 850, width = 40, height = 30),
            Obstacle(left = 300, bottom = 950, width = 100, height = 30),
            Obstacle(left = 490, bottom = 1050, width = 100, height = 30),
            Obstacle(left = 590, bottom = 1150, width = 100, height = 30),

            //Partie a gauche
            Obstacle(left = 100, bottom = 200, width = 20, height = 30),
            Obstacle(left = 250, bottom = 300, width = 20, height = 30),
            Obstacle(left = 100, bottom = 400, width = 20, height = 30),
            Obstacle(left = 250, bottom = 500, width = 20, height = 30),

            Obstacle(left = 100, bottom = 600, width = 20, height = 30),
            Obstacle(left = 250, bottom = 700, width = 20, height = 30),
            Obstacle(left = 100, bottom = 800, width = 20, height = 30),
            Obstacle(left = 210, bottom = 900, width = 20, height = 30),

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
        mario = moveHorizontal(mario)
        mario = moveVertical(mario)

        monde = monde.copy(mario = mario)
    }

    private fun moveHorizontal(mario: Mario): Mario {
        var newMario = mario
        if (isGoingLeft) {
            newMario = newMario.copy(left = newMario.left - VITESSE_HORIZONTALE)
        } else if (isGoingRight) {
            newMario = newMario.copy(left = newMario.left + VITESSE_HORIZONTALE)
        }
        if (marioTouche(newMario, monde.obstacles)) {
            newMario = mario
        }
        return newMario
    }

    private fun moveVertical(mario: Mario): Mario {
        var newMario = mario
        newMario = newMario.copy(bottom = (newMario.bottom + newMario.vy).roundToInt().coerceAtLeast(0))
        if (marioTouche(newMario, monde.obstacles)) {
            newMario = newMario.copy(vy = 0f, bottom = mario.bottom)
        }
        if (newMario.bottom > 0) { // Si mario est en l'air
            newMario = newMario.copy(vy = newMario.vy - GRAVITY)
        } else { // sinon, mario est au sol
            newMario = newMario.copy(vy = 0f)
        }
        return newMario
    }

    fun marioTouche(mario: Mario, obstacles: List<Obstacle>): Boolean{
        for(obstacle in obstacles){
            if (marioTouche(mario, obstacle)){
                return true
            }
        }
        return false
    }

    fun marioTouche(mario: Mario, obstacle: Obstacle):Boolean{
       return mario.rect().overlaps(obstacle.rect())
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
        var mario = monde.mario
        mario = mario.copy(bottom = mario.bottom - 1) // hack pour detecter qu'on est sur un obstacle
        if(marioTouche(mario, monde.obstacles) || mario.bottom <= 0) {
            monde = monde.copy(mario = monde.mario.copy(vy = VITESSE_SAUT))
        }
    }

    fun onJumpReleased() {
    }
}