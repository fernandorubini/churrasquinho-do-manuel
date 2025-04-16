package com.example.grupochurrasquinhodomanuel.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.grupochurrasquinhodomanuel.model.Avaliacao

@Dao
interface AvaliacaoDao {

    @Insert
    suspend fun insert(avaliacao: Avaliacao)

    @Query("SELECT * FROM avaliacoes WHERE id = :id")
    suspend fun getAvaliacaoById(id: Long): Avaliacao?

    @Query("SELECT * FROM avaliacoes")
    suspend fun getAllAvaliacoes(): List<Avaliacao>
}
