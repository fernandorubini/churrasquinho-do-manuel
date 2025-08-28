package com.example.grupochurrasquinhodomanuel.features.management.model


data class ManagementDashboardState(
    val activeOrders: Int = 0,
    val averageDeliveryTime: String = "--",
    val averageRating: String = "--",
    val totalSalesToday: String = "--",
    val completedOrders: Int = 0
)
