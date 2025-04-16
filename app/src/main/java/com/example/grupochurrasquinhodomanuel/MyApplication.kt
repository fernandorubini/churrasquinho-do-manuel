package com.example.grupochurrasquinhodomanuel

import android.app.Application
import com.example.grupochurrasquinhodomanuel.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Inicializando o Koin
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule) // Carrega o módulo que define as dependências
        }
    }
}
