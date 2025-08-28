package com.example.grupochurrasquinhodomanuel.features.order.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.grupochurrasquinhodomanuel.core.constants.Strings
import com.example.grupochurrasquinhodomanuel.features.order.model.Product
import java.math.BigDecimal

@Composable
fun OrderHistoryScreen(
    orders: List<Product>,
    onItemClick: (Product) -> Unit = {}
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(Strings.Labels.ORDER_HISTORY) }) }
    ) { inner ->
        LazyColumn(
            modifier = Modifier
                .padding(inner)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(orders) { p ->
                ElevatedCard(
                    onClick = { onItemClick(p) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(p.name, style = MaterialTheme.typography.titleMedium, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Text(p.storeName, style = MaterialTheme.typography.labelMedium)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("${Strings.Labels.QUANTITY}: ${p.quantity}", style = MaterialTheme.typography.bodySmall)
                            Text("${Strings.Labels.TOTAL}: ${p.totalPrice().toCurrency()}", style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}

private fun BigDecimal.toCurrency(): String = "R$ " + setScale(2).toPlainString()

@Preview(showBackground = true)
@Composable
private fun OrderHistoryPreview() {
    val list = listOf(
        Product(
            id = 1L,
            name = Strings.Labels.PRODUCT_CHURRASCO_MISTO,
            description = Strings.Labels.PRODUCT_CHURRASCO_MISTO_DESC,
            price = BigDecimal("39.90"),
            imageUrl = "",
            quantity = 2,
            isFavorite = false,
            storeName = Strings.Labels.STORE_CHURRASQUINHO
        ),
        Product(
            id = 2L,
            name = Strings.Labels.PRODUCT_GUARANA,
            description = Strings.Labels.PRODUCT_GUARANA_DESC,
            price = BigDecimal("8.00"),
            imageUrl = "",
            quantity = 1,
            isFavorite = false,
            storeName = Strings.Labels.STORE_AI_OLI
        ),
        Product(
            id = 3L,
            name = Strings.Labels.PRODUCT_SANDWICHS,
            description = Strings.Labels.PRODUCT_SANDWICHS_DESC,
            price = BigDecimal("5.00"),
            imageUrl = "",
            quantity = 3,
            isFavorite = false,
            storeName = Strings.Labels.STORE_BUFFALOS_RED
        )
    )
    OrderHistoryScreen(orders = list)
}
