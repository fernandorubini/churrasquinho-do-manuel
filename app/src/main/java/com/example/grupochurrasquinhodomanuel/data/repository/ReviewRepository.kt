package com.example.grupochurrasquinhodomanuel.data.repository

import com.example.grupochurrasquinhodomanuel.features.review.data.local.ReviewDao
import com.example.grupochurrasquinhodomanuel.features.review.data.local.ReviewEntity
import kotlinx.coroutines.flow.Flow

class ReviewRepository(private val reviewDao: ReviewDao) {

    fun getAllReviews(): Flow<List<ReviewEntity>> {
        return reviewDao.getAllReviews()
    }

    suspend fun getReviewsByProduct(productId: String): List<ReviewEntity> {
        return reviewDao.getReviewsByProduct(productId)
    }

    suspend fun insertReview(review: ReviewEntity) {
        reviewDao.insertReview(review)
    }

    suspend fun clearAllReviews() {
        reviewDao.clearAllReviews()
    }

    fun getAllReviewsFlow(): Flow<List<ReviewEntity>> {
        return reviewDao.getAllReviews()
    }

}
