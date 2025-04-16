package com.example.grupochurrasquinhodomanuel.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class RastreamentoPedido(
    val id: Int,
    val status: String, // Status atual do pedido
    val progresso: Float, // Porcentagem do progresso (0 a 100)
    val atualizacoes: List<String> // Histórico de atualizações com data e hora
) {
    // Função para garantir que o progresso esteja entre 0 e 100
    val progressoValidado: Float
        get() = progresso.coerceIn(0f, 100f)
}

data class RastreamentoAtualizacao(
    val dataHora: String, // Data e hora da atualização
    val descricao: String // Descrição da atualização
) {
    // Função para formatar a data e hora
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun formatDataHora(): String {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            return LocalDateTime.now().format(formatter)
        }
    }
}
