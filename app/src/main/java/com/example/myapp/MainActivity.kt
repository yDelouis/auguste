package com.example.myapp

import android.graphics.Paint.Align
import android.os.Bundle
import android.text.style.BulletSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapp.ui.theme.MyAppTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    var ecranModel by mutableStateOf(
        EcranModel(
            bulles = listOf(Bulle(text = "Devine le nombre", Position.Gauche)),
            nombre = null
        )
    )

    var randomNumber = Random.nextInt(0, 10001) // La borne supérieure (10001) est exclusive

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App(ecranModel, ::onNumberChange, ::onvalidate)
        }
    }

    fun onNumberChange(number: Int?) {
        var newEcranModel = ecranModel.copy(nombre = number)
        ecranModel = newEcranModel
    }

    fun onvalidate() {
        var nombre = ecranModel.nombre ?: return
        var reponse = if (nombre > randomNumber) "C'est moins"
                    else if (nombre < randomNumber) "C'est plus"
                    else "Bien joué!"
        var newEcranModel = ecranModel.copy(
            bulles = ecranModel.bulles
                    + Bulle(text = ecranModel.nombre.toString(), Position.Droite)
                    + Bulle(text = reponse, Position.Gauche),
            nombre = null
        )
        ecranModel = newEcranModel

    }
}

data class EcranModel(
    val bulles: List<Bulle>,
    val nombre: Int?
)

data class Bulle(
    val text: String,
    val position: Position
)

enum class Position {
    Droite, Gauche
}


@Composable
fun App(
    ecranModel: EcranModel,
    onNumberChange: (Int?) -> Unit,
    onvalidate: () -> Unit,
    modifier: Modifier = Modifier
) {
    MyAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Ecran(
                ecranModel,
                onNumberChange,
                onvalidate,
                modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun Ecran(
    ecranModel: EcranModel,
    onNumberChange: (Int?) -> Unit,
    onvalidate: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            reverseLayout = true,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(ecranModel.bulles.reversed()) { bulle: Bulle ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = if (bulle.position == Position.Gauche) Arrangement.Start else Arrangement.End
                ) {
                    Text(
                        bulle.text,
                        modifier = Modifier
                            .background(
                                color = Color.Gray,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }

            }
        }
        Row(modifier = Modifier.padding(16.dp)) {
            TextField(
                value = if (ecranModel.nombre == null) "" else ecranModel.nombre.toString(),
                onValueChange = { newText ->
                    var newnumber = newText.toIntOrNull()
                    if (newnumber!=null) onNumberChange(newnumber)
                    else if (newText.isBlank()) onNumberChange(null)
                },
                label = { Text("Entrez un nombre") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                keyboardActions = KeyboardActions(onDone = { onvalidate() }),
                modifier = Modifier.weight(1f)
            )

            Button(
                onClick = { onvalidate() },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Valider")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    var ecranModel = EcranModel(
        bulles = listOf(
            Bulle("Devine le nombre", Position.Gauche),
            Bulle("12", Position.Droite),
            Bulle("C'est plus", Position.Gauche)
        ),
        nombre = 145
    )
    App(ecranModel, {}, {})

}
