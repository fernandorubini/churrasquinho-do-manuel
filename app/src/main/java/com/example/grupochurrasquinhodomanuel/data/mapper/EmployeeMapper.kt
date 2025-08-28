package com.example.grupochurrasquinhodomanuel.data.mapper

import com.example.grupochurrasquinhodomanuel.data.local.EmployeeEntity
import com.example.grupochurrasquinhodomanuel.model.Employee

fun Employee.toEntity(): EmployeeEntity = EmployeeEntity(
    id = id,
    name = name,
    email = email,
    role = role,
    department = department
)

fun EmployeeEntity.toModel(): Employee = Employee(
    id = id,
    name = name,
    email = email,
    role = role,
    department = department
)
