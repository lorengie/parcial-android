package com.example.parcial.items
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.IconButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton


@Composable
fun PantallaCatalogo(navController: NavController, viewModel: AppViewModel) {
    var productoAEliminar by remember { mutableStateOf<Producto?>(null) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF3E0)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight()
                .padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(viewModel.productos) { producto ->
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.White)
                            .clickable { navController.navigate("detalle/${producto.id}") },
                        elevation = CardDefaults.cardElevation(6.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(producto.imagenUrl)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(80.dp)
                                    .padding(8.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                                Column(modifier = Modifier.weight(1f).padding(8.dp)) {
                                    Text(producto.nombre, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                                    Text("Precio: $${producto.precio}", color = Color(0xFF388E3C))
                                }
                                IconButton(onClick = { productoAEliminar = producto }) {
                                    Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = Color.Red)
                                }
                            }
                            Column(modifier = Modifier.padding(8.dp)) {
                                Text(text = producto.nombre, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                                Text(text = "Precio: $${producto.precio}", color = Color(0xFF388E3C))
                            }
                        }
                    }
                }
            }
            productoAEliminar?.let { producto ->
                AlertDialog(
                    onDismissRequest = { productoAEliminar = null },
                    title = { Text("Confirmar eliminación") },
                    text = { Text("¿Estás seguro de que quieres eliminar '${producto.nombre}'?") },
                    confirmButton = {
                        TextButton(onClick = {
                            viewModel.eliminarProductoPorId(producto.id)
                            productoAEliminar = null
                        }) {
                            Text("Sí", color = Color.Red)
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { productoAEliminar = null }) {
                            Text("Cancelar")
                        }
                    }
                )
            }



            Text(
                "Total carrito: $${viewModel.obtenerTotal()}",
                modifier = Modifier.padding(8.dp),
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { navController.navigate("registro") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF7043))
                ) {
                    Text("Agregar Producto")
                }
                Button(
                    onClick = { navController.navigate("carrito") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800))
                ) {
                    Text("Ver Carrito")
                }
            }

        }
    }
}

