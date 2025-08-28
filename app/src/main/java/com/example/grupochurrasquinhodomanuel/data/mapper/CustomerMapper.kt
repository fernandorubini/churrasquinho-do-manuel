package com.example.grupochurrasquinhodomanuel.data.mapper

import com.example.grupochurrasquinhodomanuel.model.Customer
import com.example.grupochurrasquinhodomanuel.data.local.CustomerEntity

fun Customer.toEntity(): CustomerEntity {
    return CustomerEntity(
        id = this.id,
        name = this.name,
        email = this.email
    )
}

fun CustomerEntity.toModel(): Customer {
    return Customer(
        id = this.id,
        name = this.name,
        email = this.email
    )
}
