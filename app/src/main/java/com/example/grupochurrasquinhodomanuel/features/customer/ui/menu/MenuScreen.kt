package com.example.grupochurrasquinhodomanuel.features.customer.ui.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.grupochurrasquinhodomanuel.core.model.CartItem
import com.example.grupochurrasquinhodomanuel.core.model.MenuItem
import com.example.grupochurrasquinhodomanuel.core.util.toCurrencyString
import com.example.grupochurrasquinhodomanuel.features.customer.ui.cart.CartViewModel
import org.koin.androidx.compose.koinViewModel
import java.math.BigDecimal

@Composable
fun MenuScreen(navController: NavController, viewModel: CartViewModel = koinViewModel()) {
    val menuItems = listOf(
        MenuItem(
            1,
            "Churrasquinho de Frango",
            "Delicioso churrasquinho de frango",
            BigDecimal("15.00"),
            "https://via.placeholder.com/80"
        ),
        MenuItem(
            2,
            "Sushikay",
            "Sushi fresco e delicioso",
            BigDecimal("25.00"),
            "https://via.placeholder.com/80"
        ),
        MenuItem(
            3,
            "Ai & Oli",
            "Macarrão gourmet com queijo",
            BigDecimal("20.00"),
            "https://via.placeholder.com/80"
        ),
        MenuItem(
            4,
            "Buffalos Red",
            "Sanduíches deliciosos com diversos recheios",
            BigDecimal("22.00"),
            "https://via.placeholder.com/80"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Cardápio",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        menuItems.forEach { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Image(
                        painter = rememberImagePainter(item.imageUrl),
                        contentDescription = item.name,
                        modifier = Modifier.size(80.dp)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(item.name, style = MaterialTheme.typography.titleMedium)
                        Text(
                            text = item.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = item.price.toCurrencyString(),
                            style = MaterialTheme.typography.bodyLarge
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                viewModel.addItem(
                                    CartItem(
                                        id = item.id,
                                        name = item.name,
                                        price = item.price,
                                        imageUrl = item.imageUrl,
                                        quantity = 1
                                    )
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Adicionar ao carrinho")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("cart")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver carrinho")
        }
    }
}
