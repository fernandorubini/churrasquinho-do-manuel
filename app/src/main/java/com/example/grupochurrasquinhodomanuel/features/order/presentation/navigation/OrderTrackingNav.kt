package com.example.grupochurrasquinhodomanuel.features.order.presentation.navigation

import androidx.navigation.NavController
import com.example.grupochurrasquinhodomanuel.core.constants.Strings.Routes

/** Monta a rota para rastreamento de pedido. */
fun routeForOrderTracking(orderId: String): String =
    "${Routes.ORDER_TRACKING_ROUTE}/$orderId"

/** Extensão: navega para a tela de rastreamento. */
fun NavController.navigateToOrderTracking(orderId: String) {
    navigate(routeForOrderTracking(orderId))
}
