package com.example.grupochurrasquinhodomanuel.di

import com.example.grupochurrasquinhodomanuel.data.repository.EmployeeRepository
import com.example.grupochurrasquinhodomanuel.data.repository.EmployeeRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<EmployeeRepository> { EmployeeRepositoryImpl(get()) }
}
