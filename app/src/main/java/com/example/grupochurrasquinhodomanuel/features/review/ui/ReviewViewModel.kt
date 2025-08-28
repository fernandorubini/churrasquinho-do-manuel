package com.example.grupochurrasquinhodomanuel.features.review.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grupochurrasquinhodomanuel.data.repository.ReviewRepository
import com.example.grupochurrasquinhodomanuel.features.review.data.local.ReviewEntity
import kotlinx.coroutines.launch

class ReviewViewModel(
    private val repository: ReviewRepository
) : ViewModel() {

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
            repository.insertReview(review)
        }
    }

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
