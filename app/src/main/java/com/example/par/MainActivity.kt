package com.example.par

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.par.ui.theme.ParTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ParTheme {
                AppContent()
            }
        }
    }
}

@Composable
fun AppContent() {
    val keyboardController = LocalSoftwareKeyboardController.current
    var notaText by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var dialogMessage by remember { mutableStateOf("") }

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .imePadding(),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .padding(top = 150.dp)
            ) {
                Text(
                    text = "Parcial # 1 Desarrollo Móvil",
                    fontSize = 24.sp,
                    color = Color(0xFFFF9800)
                )

                Text(
                    text = "Estudiante # 1: Andrés Valdés 6-726-1938",
                    fontSize = 16.sp,
                    color = Color(0xFFFF9800)
                )
                Text(
                    text = "Estudiante # 2: Kenshin Ng 3-750-941",
                    fontSize = 16.sp,
                    color = Color(0xFFFF9800)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Ingrese la nota a validar:",
                    fontSize = 18.sp,
                    color = Color(0xF00F9800)
                )

                TextField(
                    value = notaText,
                    onValueChange = { notaText = it },
                    label = { Text("Nota") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        dialogMessage = validarNota(notaText)
                        showDialog = true
                        keyboardController?.hide()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("Validar")
                }
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    confirmButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text("Clic para realizar otra validacion" ,color = Color(0xF00F9800))
                        }
                    },
                    title = { Text("Resultado de Validación") },
                    text = { Text(dialogMessage) }
                )
            }
        }
    }
}

fun validarNota(notaStr: String): String {
    val nota = notaStr.toIntOrNull()
    return when {
        nota == null -> "Por favor, ingrese un número válido"
        nota in 91..100 -> "Resultado: A (Excelente)"
        nota in 81..90 -> "Resultado: B (Bueno)"
        nota in 71..80 -> "Resultado: C (Regular)"
        nota in 61..70 -> "Resultado: D (Más o menos regular)"
        else -> "Resultado: F (No Aprobado, gracias por participar)"
    }
}
