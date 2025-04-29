package com.example.parcial.items
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainViewModel : ViewModel() {
    private val _listaProductos = mutableStateListOf<Producto>()
    val listaProductos: List<Producto> get() = _listaProductos

    private val _carrito = mutableStateListOf<Producto>()
    val carrito: List<Producto> get() = _carrito

    init {
        agregarProducto("Smartphone", 599.99, "Teléfono inteligente", "https://example.com/phone.jpg")
        agregarProducto("Laptop", 999.99, "Computadora portátil", "https://example.com/laptop.jpg")
    }

    fun agregarProducto(nombre: String, precio: Double, descripcion: String, imagenUrl: String) {
        val nuevoId = if (_listaProductos.isEmpty()) 1 else _listaProductos.maxOf { it.id } + 1
        _listaProductos.add(
            Producto(
                id = nuevoId,
                nombre = nombre,
                precio = precio,
                descripcion = descripcion,
                imagenUrl = imagenUrl
            )
        )
    }

    fun obtenerProductoPorId(id: Int): Producto? {
        return _listaProductos.find { it.id == id }
    }

    fun agregarAlCarrito(producto: Producto) {
        _carrito.add(producto)
    }

    fun removerDelCarrito(producto: Producto) {
        _carrito.remove(producto)
    }

    fun limpiarCarrito() {
        _carrito.clear()
    }
}