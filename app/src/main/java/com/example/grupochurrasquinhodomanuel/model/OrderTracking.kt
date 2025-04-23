package com.example.grupochurrasquinhodomanuel.core.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class OrderTracking(
    val id: Int,
    val status: String,              // Current status of the order
    val progress: Float,             // Progress percentage (0 to 100)
    val updates: List<String>        // History of updates with timestamp
) {
    val validatedProgress: Float
        get() = progress.coerceIn(0f, 100f)
}

data class OrderTrackingUpdate(
    val timestamp: String,           // Date and time of the update
    val description: String          // Description of the update
) {
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun formattedTimestamp(): String {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            return LocalDateTime.now().format(formatter)
        }
    }
}
