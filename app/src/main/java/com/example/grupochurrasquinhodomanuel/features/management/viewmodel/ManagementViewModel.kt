package com.example.grupochurrasquinhodomanuel.features.management.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grupochurrasquinhodomanuel.data.repository.EmployeeRepository
import com.example.grupochurrasquinhodomanuel.data.repository.ReviewRepository
import com.example.grupochurrasquinhodomanuel.features.management.model.ManagementDashboardState
import com.example.grupochurrasquinhodomanuel.features.order.data.repository.OrderRepository
import com.example.grupochurrasquinhodomanuel.model.Employee
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ManagementViewModel(
    private val employeeRepository: EmployeeRepository,
    private val orderRepository: OrderRepository,
    private val reviewRepository: ReviewRepository
) : ViewModel() {

    // ----- Lista de colaboradores (para EmployeeManagementScreen) -----
    val employeesState: StateFlow<List<Employee>> =
        employeeRepository
            .getAllEmployees()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    // ----- Dashboard (para ManagementHomeScreen) -----
    private val _dashboardState = MutableStateFlow(ManagementDashboardState())
    val dashboardState: StateFlow<ManagementDashboardState> = _dashboardState.asStateFlow()

    init {
        // Exemplo simples: atualiza pedidos ativos / concluídos a partir dos pedidos.
        // (Os demais campos ficam com placeholders até você conectar suas fontes reais.)
        viewModelScope.launch {
            orderRepository.getAllOrders().collect { orders ->
                // Se seu OrderStatus tiver um "ENTREGUE", ajuste aqui como preferir:
                val completed = orders.count { it.status.name.equals("ENTREGUE", ignoreCase = true) }
                val active = orders.size - completed

                _dashboardState.value = _dashboardState.value.copy(
                    activeOrders = active,
                    completedOrders = completed
                    // averageDeliveryTime, averageRating, totalSalesToday:
                    // mantenha por enquanto com os valores padrões (“--”)
                    // ou preencha aqui quando tiver as métricas reais.
                )
            }
        }
    }

    // ----- Ações de colaboradores (usadas nos dialogs da tela de gestão) -----
    fun addEmployee(employee: Employee) {
        viewModelScope.launch { employeeRepository.insertEmployee(employee) }
    }

    fun updateEmployee(employee: Employee) {
        viewModelScope.launch { employeeRepository.updateEmployee(employee) }
    }

    fun deleteEmployee(employee: Employee) {
        viewModelScope.launch { employeeRepository.deleteEmployee(employee) }
    }

    fun deleteEmployeeById(id: Long) {
        viewModelScope.launch {
            employeeRepository.getEmployeeById(id)?.let { employeeRepository.deleteEmployee(it) }
        }
    }
}
