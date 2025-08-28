package com.example.grupochurrasquinhodomanuel.features.review.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDao {

    @Query("SELECT * FROM reviews")
    fun getAllReviews(): Flow<List<ReviewEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReview(review: ReviewEntity)

    @Query("SELECT * FROM reviews WHERE productId = :productId")
    suspend fun getReviewsByProduct(productId: String): List<ReviewEntity>

    @Query("DELETE FROM reviews")
    suspend fun clearAllReviews()
}
