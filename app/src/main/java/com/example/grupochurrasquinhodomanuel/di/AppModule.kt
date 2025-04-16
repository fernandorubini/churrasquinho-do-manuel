package com.example.grupochurrasquinhodomanuel.di

import com.example.grupochurrasquinhodomanuel.data.AvaliacaoRepository
import com.example.grupochurrasquinhodomanuel.data.AppDatabase
import com.example.grupochurrasquinhodomanuel.features.cliente.presentation.ClienteViewModel
import com.example.grupochurrasquinhodomanuel.features.login.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Repositório de avaliações
    single { AvaliacaoRepository(get()) }

    // ViewModels
    viewModel { ClienteViewModel(get()) }
    viewModel { LoginViewModel() }

    // Banco de dados Room
    single { AppDatabase.getDatabase(androidContext()).avaliacaoDao() }
}
