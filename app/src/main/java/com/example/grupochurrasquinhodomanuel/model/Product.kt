package com.example.grupochurrasquinhodomanuel.model

import java.io.Serializable

data class Product(
    val id: Long,
    val nome: String,
    val descricao: String,
    val preco: Double,
    val imagemUrl: String,
    val quantidade: Int = 1  // Certifique-se de que quantidade existe
)  : Serializable  // Tornando a classe serializável para ser facilmente passada entre componentes, como nas navegações


