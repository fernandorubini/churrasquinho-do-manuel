package com.example.grupochurrasquinhodomanuel.data

import com.example.grupochurrasquinhodomanuel.model.Avaliacao

class AvaliacaoRepository(private val avaliacaoDao: AvaliacaoDao) {

    suspend fun insert(avaliacao: Avaliacao) {
        avaliacaoDao.insert(avaliacao) // Corrigido para "avaliacao" sem acento
    }
}
