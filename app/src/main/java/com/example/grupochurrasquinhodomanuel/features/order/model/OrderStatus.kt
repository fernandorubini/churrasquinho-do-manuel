package com.example.grupochurrasquinhodomanuel.features.order.model

import com.example.grupochurrasquinhodomanuel.core.constants.Strings

enum class OrderStatus {
    RECEBIDO,
    PREPARANDO,
    A_CAMINHO,
    ENTREGUE;

    fun getDisplayLabel(): String {
        return when (this) {
            RECEBIDO -> Strings.OrderStatusLabels.RECEBIDO
            PREPARANDO -> Strings.OrderStatusLabels.PREPARANDO
            A_CAMINHO -> Strings.OrderStatusLabels.A_CAMINHO
            ENTREGUE -> Strings.OrderStatusLabels.ENTREGUE
        }
    }

    fun nextStatus(): OrderStatus? {
        return when (this) {
            RECEBIDO -> PREPARANDO
            PREPARANDO -> A_CAMINHO
            A_CAMINHO -> ENTREGUE
            ENTREGUE -> null // último status
        }
    }
}
