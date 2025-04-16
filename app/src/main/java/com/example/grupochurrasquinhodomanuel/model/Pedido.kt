package com.example.grupochurrasquinhodomanuel.model

data class Pedido(
    val id: Int,
    val data: String, // Exemplo: "2025-04-05"
    val status: String, // Exemplo: "Em Preparação", "Entregue"
    val itens: List<Produto>, // Lista dos produtos do pedido
    val total: Double // Total do pedido
)
