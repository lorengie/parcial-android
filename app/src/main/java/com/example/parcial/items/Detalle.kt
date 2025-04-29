package com.example.parcial.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter

@Composable
fun Detalle(navController: NavController, productoId: Int, viewModel: MainViewModel) {
    val producto = viewModel.obtenerProductoPorId(productoId)

    if (producto != null) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text(text = "Detalle del Producto", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = rememberImagePainter(producto.imagenUrl),
                contentDescription = producto.nombre,
                modifier = Modifier.size(200.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Nombre: ${producto.nombre}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Descripción: ${producto.descripcion}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Precio: $${producto.precio}", style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.agregarAlCarrito(producto)
                    navController.popBackStack()
                }
            ) {
                Text("Agregar al carrito")
            }
        }
    } else {
        Text("Producto no encontrado")
    }
}

