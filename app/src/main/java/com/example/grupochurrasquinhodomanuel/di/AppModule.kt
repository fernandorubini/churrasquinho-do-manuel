package com.example.grupochurrasquinhodomanuel.di

import com.example.grupochurrasquinhodomanuel.data.ReviewRepository
import com.example.grupochurrasquinhodomanuel.data.AppDatabase
import com.example.grupochurrasquinhodomanuel.data.BrandRepository
import com.example.grupochurrasquinhodomanuel.data.UnitRepository
import com.example.grupochurrasquinhodomanuel.features.customer.presentation.CustomerViewModel
import com.example.grupochurrasquinhodomanuel.features.login.presentation.LoginViewModel
import com.example.grupochurrasquinhodomanuel.features.brand.presentation.BrandViewModel
import com.example.grupochurrasquinhodomanuel.features.unit.presentation.UnitViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    // Banco de dados Room
    single { AppDatabase.getDatabase(androidContext()) }

    // DAOs
    single { get<AppDatabase>().reviewDao() }
    single { get<AppDatabase>().brandDao() }
    single { get<AppDatabase>().unitDao() }

    // Repositórios
    single { ReviewRepository(get()) }
    single { BrandRepository(get()) }
    single { UnitRepository(get()) }

    // ViewModels
    viewModel { BrandViewModel(get()) }
    viewModel { UnitViewModel(get()) }
    viewModel { CustomerViewModel(get()) }
    viewModel { LoginViewModel() }
}


