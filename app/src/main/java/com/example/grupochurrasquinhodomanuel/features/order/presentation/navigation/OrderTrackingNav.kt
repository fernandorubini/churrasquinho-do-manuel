package com.example.grupochurrasquinhodomanuel.features.order.presentation.navigation

import androidx.navigation.NavController
import com.example.grupochurrasquinhodomanuel.core.constants.Strings.Routes

fun routeForOrderTracking(orderId: String): String =
    "${Routes.ORDER_TRACKING_ROUTE}/$orderId"

fun NavController.navigateToOrderTracking(orderId: String) {
    navigate(routeForOrderTracking(orderId))
}
