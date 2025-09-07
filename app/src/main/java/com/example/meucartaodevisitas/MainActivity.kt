package com.example.meucartaodevisitas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meucartaodevisitas.ui.theme.MeuCartaoDeVisitasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeuCartaoDeVisitasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = Color(0xFFF5F5F5) // fundo da tela (cinza claro)
                    ) {
                        CartaoDeVisitas()
                    }
                }
            }
        }
    }
}

@Composable
fun CartaoDeVisitas() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // CABEÇALHO
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1A237E)) // azul marinho (ou troque para 0xFF0288D1 se quiser igual aos ícones)
                .padding(vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.avatar),
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
            )

            Spacer(Modifier.height(16.dp))

            Text(
                "Francisco Fernandes",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                "Tecnólogo em Sistemas para Internet",
                fontSize = 16.sp,
                color = Color(0xFFBBDEFB) // azul claro para contraste
            )
        }

        Spacer(Modifier.weight(1f)) // empurra contatos para baixo

        // CONTATOS
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, bottom = 32.dp)
        ) {
            LinhaContato(
                icon = ImageVector.vectorResource(R.drawable.phone),
                texto = "(xx) xxxxx-xxxx"
            )
            Spacer(Modifier.height(12.dp))
            LinhaContato(
                icon = ImageVector.vectorResource(R.drawable.mail),
                texto = "francisco20240029341@alu.uern.br"
            )
        }
    }
}

@Composable
fun LinhaContato(icon: ImageVector, texto: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = Color(0xFF0288D1) // azul moderno
        )
        Spacer(Modifier.width(8.dp))
        Text(
            texto,
            fontSize = 14.sp,
            color = Color(0xFF424242)
        )
    }
}
