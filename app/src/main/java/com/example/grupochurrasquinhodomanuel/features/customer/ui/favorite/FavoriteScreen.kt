package com.example.grupochurrasquinhodomanuel.features.customer.ui.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.grupochurrasquinhodomanuel.core.util.toCurrencyString
import com.example.grupochurrasquinhodomanuel.features.customer.presentation.FavoriteViewModel
import com.example.grupochurrasquinhodomanuel.features.order.model.Product
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = koinViewModel(),
    onProductClick: (Product) -> Unit = {}
) {
    val products by viewModel.products.collectAsState(initial = emptyList())
    val favorites = products.filter { it.isFavorite }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Seus Favoritos",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (favorites.isEmpty()) {
            Text("Nenhum item favorito no momento.")
        } else {
            favorites.forEach { product ->
                FavoriteItem(
                    product = product,
                    onToggle = { viewModel.toggleFavorite(product) },
                    onClick = { onProductClick(product) }
                )
                Divider()
            }
        }
    }
}

@Composable
private fun FavoriteItem(
    product: Product,
    onToggle: () -> Unit,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .clickable { onClick() }
        ) {
            Text(text = product.name, style = MaterialTheme.typography.titleMedium)
            if (product.storeName.isNotBlank()) {
                Text(
                    text = product.storeName,
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Text(
                text = product.price.toCurrencyString(),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        IconButton(onClick = onToggle) {
            Icon(
                imageVector = if (product.isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = if (product.isFavorite) "Remover dos favoritos" else "Adicionar aos favoritos",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}
