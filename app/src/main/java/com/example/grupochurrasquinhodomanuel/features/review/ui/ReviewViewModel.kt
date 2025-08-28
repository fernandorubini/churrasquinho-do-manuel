package com.example.grupochurrasquinhodomanuel.features.review.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grupochurrasquinhodomanuel.data.repository.ReviewRepository   // ✅ caminho correto
import com.example.grupochurrasquinhodomanuel.features.review.data.local.ReviewEntity
import kotlinx.coroutines.launch

class ReviewViewModel(
    private val repository: ReviewRepository
) : ViewModel() {

    /**
     * Assinatura “canônica” alinhada ao ReviewEntity:
     * - productId: String
     * - rating: Float
     */
    fun submitReview(
        productId: String,
        customerName: String,
        comment: String,
        rating: Float
    ) {
        val review = ReviewEntity(
            productId = productId,
            customerName = customerName,
            comment = comment,
            rating = rating
        )
        viewModelScope.launch {
            repository.insertReview(review)   // ✅ método do repo
        }
    }

    /**
     * Overload para manter chamadas antigas que usam Long/Int.
     * Converte para os tipos esperados pelo ReviewEntity.
     */
    fun submitReview(
        productId: Long,
        customerName: String,
        comment: String,
        rating: Int
    ) = submitReview(
        productId = productId.toString(),
        customerName = customerName,
        comment = comment,
        rating = rating.toFloat()
    )
}
