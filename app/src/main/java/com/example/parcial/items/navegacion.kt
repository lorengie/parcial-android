package com.example.parcial.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navegacion() {
    val navController = rememberNavController()
    val viewModel: MainViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "principal"
    ) {
        composable("principal") {
            PantallaPrincipal(navController, viewModel)
        }
        composable("registro") {
            Registro(navController, viewModel)
        }
        composable("detalle/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: -1
            Detalle(navController, id, viewModel)
        }
        composable("carrito") {
            Carrito(navController, viewModel)
        }
    }
}




