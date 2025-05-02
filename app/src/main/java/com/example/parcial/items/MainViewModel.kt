package com.example.parcial.items
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppViewModel : ViewModel() {
    var productos = mutableStateListOf<Producto>()
        private set

    var carrito = mutableStateListOf<Producto>()
        private set
    fun agregarProducto(producto: Producto) {
        productos.add(producto)
    }

    fun agregarAlCarrito(producto: Producto) {
        val existente = carrito.find { it.id == producto.id }
        if (existente != null) {
            existente.cantidad += 1
        } else {
            carrito.add(producto.copy(cantidad = 1))
        }
    }

    fun obtenerTotal(): Double {
        return carrito.sumOf { it.precio * it.cantidad }
    }

    fun finalizarCompra() {
        carrito.clear()
    }
    fun eliminarProductoPorId(id: Int) {
        productos.removeIf { it.id == id }
    }
}