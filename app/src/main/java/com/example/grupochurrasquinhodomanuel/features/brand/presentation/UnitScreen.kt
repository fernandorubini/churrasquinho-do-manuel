package com.example.grupochurrasquinhodomanuel.features.unit.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun UnitScreen(viewModel: UnitViewModel = koinViewModel()) {
    val units by viewModel.units.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text(
            text = "Units",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            items(units) { unit ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = unit.name, style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Location: ${unit.location}", style = MaterialTheme.typography.bodySmall)
                        Text(text = "Brand: ${unit.brandName}", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}
