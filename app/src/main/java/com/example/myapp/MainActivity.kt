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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapp.ui.theme.MyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }
    }
}

data class EcranModel(
    val bulles: List<Bulle>,
    val nombre: Int
)

data class Bulle(
    val text: String,
    val position : Position
)

enum class Position {
    Droite, Gauche
}


@Composable
fun App(ecranModel: EcranModel, modifier: Modifier = Modifier) {
    MyAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Ecran(ecranModel, modifier.padding(innerPadding))
        }
    }
}

@Composable
fun Ecran(ecranModel: EcranModel, modifier: Modifier = Modifier) {
    Column(modifier) {
        LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(ecranModel.bulles) { bulle: Bulle ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = if(bulle.position == Position.Gauche) Arrangement.Start else Arrangement.End
                ) {
                    Text(
                        bulle.text,
                        modifier = Modifier
                            .background(
                                color = Color.Gray,
                                shape = RoundedCornerShape(8.dp)
                            ).padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }

            }
        }
        Row(modifier = Modifier.padding(16.dp)) {
            TextField(
                value = ecranModel.nombre.toString(),
                onValueChange = { newText ->

                },
                label = { Text("Entrez un nombre") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.weight(1f)
            )

            Button(
                onClick = { /* Action pour valider le nombre ou faire quelque chose avec le texte */ },
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
    App(ecranModel)

}