package com.example.grupochurrasquinhodomanuel.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.grupochurrasquinhodomanuel.model.Review

@Dao
interface ReviewDao {

    @Insert
    suspend fun insert(review: Review)

    @Query("SELECT * FROM avaliacoes WHERE id = :id")
    suspend fun getReviewById(id: Long): Review?

    @Query("SELECT * FROM avaliacoes")
    suspend fun getAllReviews(): List<Review>
}
