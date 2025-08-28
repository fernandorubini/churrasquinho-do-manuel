package com.example.grupochurrasquinhodomanuel.features.management.model

/**
 * Estado exibido no dashboard de gestão.
 * Mantém valores em formato já pronto para UI (Strings onde faz sentido).
 */
data class ManagementDashboardState(
    val activeOrders: Int = 0,
    val averageDeliveryTime: String = "--",
    val averageRating: String = "--",
    val totalSalesToday: String = "--",
    val completedOrders: Int = 0
)
