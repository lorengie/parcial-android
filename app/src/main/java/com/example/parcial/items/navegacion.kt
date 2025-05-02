package com.example.parcial.items
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navegacion(viewModel: AppViewModel = viewModel()) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "catalogo") {
        composable("catalogo") {
            PantallaCatalogo(navController, viewModel)
        }
        composable("registro") {
            PantallaRegistro(navController, viewModel)
        }
        composable("carrito") {
            PantallaCarrito(navController, viewModel)
        }
        composable("detalle/{productoId}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("productoId")?.toIntOrNull() ?: -1
            PantallaDetalle(id, navController, viewModel)
        }
    }
}





