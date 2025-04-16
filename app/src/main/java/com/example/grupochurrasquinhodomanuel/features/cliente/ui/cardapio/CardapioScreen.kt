package com.example.grupochurrasquinhodomanuel.features.cliente.ui.cardapio

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.res.painterResource
import com.example.grupochurrasquinhodomanuel.model.ItemCardapio // Certifique-se de que o pacote esteja correto

@Composable
fun CardapioScreen(navController: NavController) {
    val itensCardapio = listOf(
        ItemCardapio(1, "Churrasquinho de Frango", "Delicioso churrasquinho de frango", 15.00, "link_imagem_frango"),
        ItemCardapio(2, "Sushikay", "Sushi fresco e delicioso", 25.00, "link_imagem_sushi"),
        ItemCardapio(3, "Ai & Oli", "Macarrão gourmet com queijo", 20.00, "link_imagem_macarrao"),
        ItemCardapio(4, "Buffalos Red", "Sanduíches deliciosos com diversos recheios", 22.00, "link_imagem_sanduiche")
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Cardápio", style = MaterialTheme.typography.headlineLarge, modifier = Modifier.align(Alignment.CenterHorizontally))

        Spacer(modifier = Modifier.height(16.dp))

        itensCardapio.forEach { item ->
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
                    // Aqui você pode usar um Image real com recursos locais ou URL
                    Image(painter = painterResource(id = android.R.drawable.ic_menu_camera), contentDescription = "Item", modifier = Modifier.size(80.dp))

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(modifier = Modifier.fillMaxWidth()) {
                        // Usando h6 para o nome do item
                        Text(text = item.nome, style = MaterialTheme.typography.titleMedium)
                        // Usando body2 para a descrição
                        Text(text = item.descricao, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                        // Usando body1 para o preço
                        Text(text = "R$ ${item.preco}", style = MaterialTheme.typography.bodyLarge)

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = { /* Adicionar ao carrinho */ },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Adicionar ao Carrinho")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Navegar para tela de carrinho */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver Carrinho")
        }
    }
}
