package com.example.fireapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.fireapp.ui.theme.FireAppTheme
import com.google.firebase.firestore.FirebaseFirestore

data class Mensagem(
    val id: String,
    val texto: String
)

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            FireAppTheme {
                var texto by remember { mutableStateOf("") }
                var mensagens by remember { mutableStateOf(listOf<Mensagem>()) }

                fun buscarMensagens() {
                    val db = FirebaseFirestore.getInstance()
                    db.collection("mensagens")
                        .orderBy("mensagem")
                        .get()
                        .addOnSuccessListener { result ->
                            val lista = result.mapNotNull { doc ->
                                val msg = doc.getString("mensagem")
                                if (msg != null) Mensagem(doc.id, msg) else null
                            }
                            mensagens = lista
                        }
                }

                fun apagarMensagem(id: String) {
                    val db = FirebaseFirestore.getInstance()
                    db.collection("mensagens").document(id)
                        .delete()
                        .addOnSuccessListener {
                            buscarMensagens()
                        }
                }

                LaunchedEffect(Unit) {
                    buscarMensagens()
                }

                Scaffold(
                    containerColor = Color(0xFF121212)
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(horizontal = 24.dp, vertical = 16.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            text = "\uD83D\uDD25 FireApp",
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color(0xFFBB86FC),
                            modifier = Modifier.fillMaxWidth(),
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = texto,
                            onValueChange = { texto = it },
                            label = { Text("Escreva uma mensagem", color = Color(0xFFBB86FC)) },
                            textStyle = TextStyle(color = Color.White),
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color(0xFFBB86FC),
                                unfocusedBorderColor = Color.Gray,
                                cursorColor = Color.White,
                                containerColor = Color.Transparent
                            )
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                val db = FirebaseFirestore.getInstance()
                                val dados = hashMapOf("mensagem" to texto)

                                db.collection("mensagens")
                                    .add(dados)
                                    .addOnSuccessListener {
                                        texto = ""
                                        buscarMensagens()
                                    }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFBB86FC),
                                contentColor = Color.White
                            )
                        ) {
                            Text("\uD83D\uDCBE Salvar no Firestore")
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            "\uD83D\uDCDC Mensagens:",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        mensagens.forEach { msg ->
                            Card(
                                colors = CardDefaults.cardColors(containerColor = Color(0xFF1F1F1F)),
                                elevation = CardDefaults.cardElevation(6.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        msg.texto,
                                        color = Color.White,
                                        modifier = Modifier.weight(1f)
                                    )
                                    Button(
                                        onClick = { apagarMensagem(msg.id) },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.Red,
                                            contentColor = Color.White
                                        )
                                    ) {
                                        Text("\uD83D\uDDD1")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}