package com.example.myapp.mario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.myapp.ui.theme.MyAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

class MarioActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var jeu = Jeu()
        lifecycleScope.launch(Dispatchers.Default) { jeu.start() }
        setContent {
            MyAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Interface(jeu, Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Interface(jeu: Jeu, modifier: Modifier = Modifier) {
    Column(modifier) {
        Monde(jeu.monde, modifier.fillMaxWidth().weight(1f))
        BarreDesBoutons(jeu)
    }

}

@Composable
fun BarreDesBoutons(jeu: Jeu, modifier: Modifier = Modifier) {
    Row(modifier) {
        Bouton("<", jeu::onLeftPressed, jeu::onLeftReleased)
        Bouton(">", jeu::onRightPressed, jeu::onRightReleased)
        Bouton("^", jeu::onJumpPressed, jeu::onJumpReleased)
    }
}

@Composable
private fun Bouton(text: String, onPressed: () -> Unit, onReleased: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> onPressed()
                is PressInteraction.Release,
                 is PressInteraction.Cancel
                     -> onReleased()
            }
        }
    }
    Button(onClick = { }, interactionSource = interactionSource) {
        Text(text)
    }
}

@Composable
private fun Monde(monde: Jeu.Monde, modifier: Modifier = Modifier) {
    Layout(modifier = modifier, content = {
        monde.obstacles.forEach { obstacle: Obstacle ->
            ObstacleImage(obstacle)
        }
        MarioImage(monde.mario)
    }) { measurables, constraints ->
        var obstacleMesurables = measurables.dropLast(1)
        var marioMesurable = measurables.last()
        val obstaclePlaceables = obstacleMesurables.mapIndexed { index, measurable ->
            val obstacle = monde.obstacles.get(index)
            measurable.measure(Constraints.fixed(obstacle.largeur, obstacle.hauteur))
        }
        val marioPlaceable = marioMesurable.measure(Constraints.fixed(40, 80))

        layout(constraints.maxWidth, constraints.maxHeight) {
            obstaclePlaceables.mapIndexed { index, placeable ->
                val obstacle = monde.obstacles.get(index)
                placeable.placeRelative(
                    x = obstacle.x,
                    y = constraints.maxHeight - obstacle.y - placeable.height
                )
            }
            marioPlaceable.placeRelative(
                monde.mario.x,
                constraints.maxHeight - monde.mario.y - marioPlaceable.height
            )
        }
    }
}

@Composable
fun MarioImage(mario: Mario) {
    Box(Modifier.fillMaxSize().background(Color.Red))
}

@Composable
fun ObstacleImage(obstacle: Obstacle){
    Box(Modifier.fillMaxSize().background(Color.Gray))
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val jeu = Jeu()
    jeu.monde = jeu.monde.copy(mario = Mario(50, 0))
    MyAppTheme {
        Interface(jeu)
    }
}
