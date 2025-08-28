package com.example.grupochurrasquinhodomanuel.features.review.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.koin.androidx.compose.koinViewModel

@Composable
fun ReviewScreen(
    navController: NavHostController,
    productId: Long,
    viewModel: ReviewViewModel = koinViewModel()
) {
    var reviewText by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf(1) }
    var customerName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Avaliar Produto #$productId", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = customerName,
            onValueChange = { customerName = it },
            label = { Text("Seu nome") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = reviewText,
            onValueChange = { reviewText = it },
            label = { Text("Comentário") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Nota:")
        Row {
            (1..5).forEach { i ->
                RadioButton(
                    selected = rating == i,
                    onClick = { rating = i }
                )
                Text(text = i.toString(), modifier = Modifier.padding(end = 8.dp))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.submitReview(productId, customerName, reviewText, rating)
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enviar Avaliação")
        }
    }
}
