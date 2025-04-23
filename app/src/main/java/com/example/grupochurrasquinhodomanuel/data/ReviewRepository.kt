package com.example.grupochurrasquinhodomanuel.data

import com.example.grupochurrasquinhodomanuel.model.Review

class ReviewRepository(private val reviewDao: ReviewDao) {

    suspend fun insert(review: Review) {
        reviewDao.insert(review) // Corrigido para "avaliacao" sem acento
    }
}
