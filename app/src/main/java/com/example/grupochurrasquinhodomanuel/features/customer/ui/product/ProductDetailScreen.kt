package com.example.grupochurrasquinhodomanuel.features.order.ui

import androidx.compose.foundation.layout.*
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
fun ProductDetailScreen(
    product: Product,
    onAddToCart: (Product) -> Unit = {}
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(product.name, maxLines = 1, overflow = TextOverflow.Ellipsis) }) }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(text = product.storeName, style = MaterialTheme.typography.labelLarge)
            Text(text = product.description, style = MaterialTheme.typography.bodyMedium)
            Text(text = product.price.toCurrency(), style = MaterialTheme.typography.headlineSmall)

            Button(
                onClick = { onAddToCart(product) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(Strings.Labels.ADD_TO_CART)
            }
        }
    }
}

private fun BigDecimal.toCurrency(): String = "R$ " + setScale(2).toPlainString()

@Preview(showBackground = true)
@Composable
private fun ProductDetailPreview() {
    val demo = Product(
        id = 1L,
        name = Strings.Labels.PRODUCT_CHURRASCO_MISTO,
        description = Strings.Labels.PRODUCT_CHURRASCO_MISTO_DESC,
        price = BigDecimal("39.90"),
        imageUrl = "",
        quantity = 1,
        isFavorite = false,
        storeName = Strings.Labels.STORE_CHURRASQUINHO
    )
    ProductDetailScreen(product = demo)
}
