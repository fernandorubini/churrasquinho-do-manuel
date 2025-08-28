package com.example.grupochurrasquinhodomanuel.features.management.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width // ✅ faltava esse import
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.grupochurrasquinhodomanuel.core.constants.Strings
import com.example.grupochurrasquinhodomanuel.features.management.viewmodel.ManagementViewModel
import com.example.grupochurrasquinhodomanuel.model.Employee
import org.koin.androidx.compose.koinViewModel

// Pode mover para Strings.kt depois, se quiser.
private const val LABEL_ROLE = "Função"
private const val LABEL_DEPARTMENT = "Departamento"
private const val CONFIRM_DELETE_TITLE = "Excluir colaborador?"
private const val CONFIRM_DELETE_MSG = "Essa ação não pode ser desfeita."

@Composable
fun EmployeeManagementScreen(
    navController: NavController,
    viewModel: ManagementViewModel = koinViewModel()
) {
    val employees by viewModel.employeesState.collectAsStateWithLifecycle()

    var editing by remember { mutableStateOf<Employee?>(null) }
    var confirmingDelete by remember { mutableStateOf<Employee?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(Strings.Labels.EMPLOYEE_MANAGEMENT_TITLE) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = Strings.Labels.BACK)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Strings.Routes.EMPLOYEE_REGISTER) }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = Strings.Labels.ADD_EMPLOYEE)
            }
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            if (employees.isEmpty()) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(Strings.Messages.NO_EMPLOYEES)
                }
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(items = employees, key = { it.id }) { employee: Employee -> // ✅ tipo explícito
                        EmployeeCard(
                            employee = employee,
                            onEdit = { editing = it },
                            onDelete = { confirmingDelete = it }
                        )
                    }
                }
            }
        }
    }

    // Diálogo de edição
    editing?.let { emp ->
        EditEmployeeDialog(
            employee = emp,
            onDismiss = { editing = null },
            onSave = { updated ->
                viewModel.updateEmployee(updated)
                editing = null
            }
        )
    }

    // Confirmação de exclusão
    confirmingDelete?.let { emp ->
        AlertDialog(
            onDismissRequest = { confirmingDelete = null },
            title = { Text(CONFIRM_DELETE_TITLE) },
            text = { Text(CONFIRM_DELETE_MSG) },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.deleteEmployee(emp)
                    confirmingDelete = null
                }) { Text(Strings.Buttons.DELETE) }
            },
            dismissButton = {
                TextButton(onClick = { confirmingDelete = null }) { Text(Strings.Buttons.CANCEL) }
            }
        )
    }
}

@Composable
private fun EmployeeCard(
    employee: Employee,
    onEdit: (Employee) -> Unit,
    onDelete: (Employee) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text("${Strings.Labels.NAME}: ${employee.name}", style = MaterialTheme.typography.bodyLarge)
            Text("${Strings.Labels.EMAIL}: ${employee.email}", style = MaterialTheme.typography.bodyMedium)
            if (employee.role.isNotBlank()) {
                Text("$LABEL_ROLE: ${employee.role}", style = MaterialTheme.typography.bodyMedium)
            }
            if (employee.department.isNotBlank()) {
                Text("$LABEL_DEPARTMENT: ${employee.department}", style = MaterialTheme.typography.bodyMedium)
            }

            Spacer(Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = { onEdit(employee) }) {
                    Icon(Icons.Filled.Edit, contentDescription = Strings.Buttons.EDIT)
                    Spacer(Modifier.width(8.dp))
                    Text(Strings.Buttons.EDIT)
                }
                OutlinedButton(
                    onClick = { onDelete(employee) },
                    colors = ButtonDefaults.outlinedButtonColors()
                ) {
                    Icon(Icons.Filled.Delete, contentDescription = Strings.Buttons.DELETE)
                    Spacer(Modifier.width(8.dp))
                    Text(Strings.Buttons.DELETE)
                }
            }
        }
    }
}

@Composable
private fun EditEmployeeDialog(
    employee: Employee,
    onDismiss: () -> Unit,
    onSave: (Employee) -> Unit
) {
    var name by remember { mutableStateOf(TextFieldValue(employee.name)) }
    var email by remember { mutableStateOf(TextFieldValue(employee.email)) }
    var role by remember { mutableStateOf(TextFieldValue(employee.role)) }
    var department by remember { mutableStateOf(TextFieldValue(employee.department)) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(Strings.Buttons.EDIT) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text(Strings.Labels.NAME) }, singleLine = true)
                OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text(Strings.Labels.EMAIL) }, singleLine = true)
                OutlinedTextField(value = role, onValueChange = { role = it }, label = { Text(LABEL_ROLE) }, singleLine = true)
                OutlinedTextField(value = department, onValueChange = { department = it }, label = { Text(LABEL_DEPARTMENT) }, singleLine = true)
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onSave(
                    employee.copy(
                        name = name.text.trim(),
                        email = email.text.trim(),
                        role = role.text.trim(),
                        department = department.text.trim()
                    )
                )
            }) { Text(Strings.Buttons.SAVE) }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text(Strings.Buttons.CANCEL) } }
    )
}
