package com.example.parcial.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.parcial.R
@Composable
fun PantallaDetalle(productoId: Int, navController: NavController, viewModel: AppViewModel) {
    val producto = viewModel.productos.find { it.id == productoId }
    var cantidad by remember { mutableStateOf(1) }

    if (producto == null) {
        Text("Producto no encontrado")
        return
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFF5F5F5)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AsyncImage(
                model = producto.imagenUrl,
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Text(producto.nombre, fontWeight = FontWeight.Bold, fontSize = 24.sp)

            Text("Precio: $${producto.precio}", fontSize = 18.sp, color = Color(0xFF388E3C))
            Text("Descripción: ${producto.descripcion}", fontSize = 16.sp)

            // Selector de cantidad
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { if (cantidad > 1) cantidad-- },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7043))
                ) {
                    Text("-")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text("$cantidad", fontSize = 18.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { cantidad++ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7043))
                ) {
                    Text("+")
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        repeat(cantidad) {
                            viewModel.agregarAlCarrito(producto)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800))
                ) {
                    Text("Agregar $cantidad al Carrito")
                }

                Button(onClick = { navController.popBackStack() }) {
                    Text("Volver")
                }
            }
        }
    }
}



