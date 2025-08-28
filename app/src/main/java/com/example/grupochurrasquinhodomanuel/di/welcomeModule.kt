package com.example.grupochurrasquinhodomanuel.di

import com.example.grupochurrasquinhodomanuel.features.welcome.WelcomePreferences
import com.example.grupochurrasquinhodomanuel.features.welcome.presentation.WelcomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val welcomeModule = module {

    single { WelcomePreferences(androidContext()) }

    viewModel {
        WelcomeViewModel(
            preferences = get()
        )
    }
}
