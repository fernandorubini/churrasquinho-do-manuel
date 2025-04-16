package com.example.grupochurrasquinhodomanuel.model  // ou o pacote adequado

data class ItemCardapio(
    val id: Int,
    val nome: String,
    val descricao: String,
    val preco: Double,
    val imagemUrl: String  // Link para a imagem ou ID da imagem
)

