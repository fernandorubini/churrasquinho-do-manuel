package com.example.grupochurrasquinhodomanuel.features.cliente.ui.cardapio

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.grupochurrasquinhodomanuel.model.ItemCardapio  // Importando a classe ItemCardapio
import androidx.compose.ui.Alignment


@Composable
fun ItemCardapio(navController: NavController) {
    val itensCardapio = listOf(
        ItemCardapio(1, "Churrasquinho de Frango", "Delicioso churrasquinho de frango", 15.00, "link_imagem_frango"),
        ItemCardapio(2, "Sushikay", "Sushi fresco e delicioso", 25.00, "link_imagem_sushi"),
        ItemCardapio(3, "Ai & Oli", "Macarrão gourmet com queijo", 20.00, "link_imagem_macarrao"),
        ItemCardapio(4, "Buffalos Red", "Sanduíches deliciosos com diversos recheios", 22.00, "link_imagem_sanduiche")
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Cardápio",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally) // Use o Alignment.CenterHorizontally
        )


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
                    // Carregando a imagem do item
                    Image(
                        painter = rememberImagePainter(item.imagemUrl),  // Substitua com a URL correta da imagem
                        contentDescription = item.nome,
                        modifier = Modifier.size(80.dp)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = item.nome,
                            style = MaterialTheme.typography.titleMedium // Use titleMedium em vez de h6
                        )
                        Text(
                            text = item.descricao,
                            style = MaterialTheme.typography.bodyMedium, // Use bodyMedium em vez de body2
                            color = Color.Gray,
                            maxLines = 2, // Limita as linhas para evitar que o texto saia da tela
                            overflow = TextOverflow.Ellipsis // Adiciona reticências no final, se necessário
                        )
                        Text(
                            text = "R$ ${item.preco}",
                            style = MaterialTheme.typography.bodyLarge // Use bodyLarge em vez de body1
                        )

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
