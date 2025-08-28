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

        // Observando as mudanças no estado do customer (Customer?).
        lifecycleScope.launch {
            customerViewModel.customer.collect { customer ->
                // Aqui você pode atualizar a UI com os dados do cliente.
                customer?.let {
                    // Exemplo de exibição do nome do cliente (ou outras informações)
                    println("Nome do cliente: ${it.name}")
                    // Atualize sua UI, por exemplo, exibindo o nome do cliente em um TextView.
                    // Exemplo: myTextView.text = it.name
                }
            }
        }

        // Exemplo de operação: Inserir um novo cliente
        val newCustomer = Customer(id = 0, name = "Maria Oliveira", email = "maria.oliveira@example.com")
        customerViewModel.insertCustomer(newCustomer)
    }
}
