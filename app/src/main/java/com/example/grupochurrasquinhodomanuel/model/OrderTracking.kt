package com.example.grupochurrasquinhodomanuel.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.grupochurrasquinhodomanuel.features.order.model.OrderStatus
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class OrderTracking(
    val id: Int,
    val status: OrderStatus,
    val progress: Float,
    val updates: List<OrderTrackingUpdate>
) {
    val validatedProgress: Float
        get() = progress.coerceIn(0f, 100f)
}

data class OrderTrackingUpdate(
    val timestamp: String,
    val description: String
) {
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun formattedTimestamp(): String {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            return LocalDateTime.now().format(formatter)
        }
    }
}

