package com.example.parcial.items
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Registro(navController: NavController, viewModel: MainViewModel) {
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var imagenUrl by remember { mutableStateOf("") }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Agregar Producto") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre del producto") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = precio,
                onValueChange = { precio = it },
                label = { Text("Precio") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                maxLines = 5
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = imagenUrl,
                onValueChange = { imagenUrl = it },
                label = { Text("URL de la imagen (opcional)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer,
                        contentColor = MaterialTheme.colorScheme.onErrorContainer
                    )
                ) {
                    Text("Cancelar")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = {
                        val precioValido = precio.toDoubleOrNull()

                        if (nombre.isBlank()) {
                            Toast.makeText(context, "El nombre es requerido", Toast.LENGTH_SHORT).show()
                            return@Button
                        }

                        if (precioValido == null || precioValido <= 0) {
                            Toast.makeText(context, "Ingrese un precio válido", Toast.LENGTH_SHORT).show()
                            return@Button
                        }

                        if (descripcion.isBlank()) {
                            Toast.makeText(context, "La descripción es requerida", Toast.LENGTH_SHORT).show()
                            return@Button
                        }

                        viewModel.agregarProducto(nombre, precioValido, descripcion, imagenUrl)
                        navController.popBackStack()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Guardar")
                }
            }
        }
    }
}
