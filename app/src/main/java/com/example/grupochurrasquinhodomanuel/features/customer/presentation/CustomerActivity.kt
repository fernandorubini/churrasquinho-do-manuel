package com.example.grupochurrasquinhodomanuel.features.customer.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.grupochurrasquinhodomanuel.R
import com.example.grupochurrasquinhodomanuel.model.Customer
import kotlinx.coroutines.launch

class CustomerActivity : AppCompatActivity(R.layout.activity_customer) {

    private val customerViewModel: CustomerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            customerViewModel.customer.collect { customer ->
                customer?.let {
                    println("Nome do cliente: ${it.name}")
                }
            }
        }

        val newCustomer =
            Customer(id = 0, name = "Maria Oliveira", email = "maria.oliveira@example.com")
        customerViewModel.insertCustomer(newCustomer)
    }
}
