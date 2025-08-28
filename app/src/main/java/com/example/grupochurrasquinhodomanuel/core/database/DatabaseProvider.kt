package com.example.grupochurrasquinhodomanuel.core.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    @Volatile
    private var INSTANCE: AppDatabase? = null

    /**
     * Retorna a instância única do banco.
     * Em DEV deixei fallbackToDestructiveMigration() para evitar crash por migração.
     * (Em produção, troque por migrações reais.)
     */
    fun getDatabase(context: Context): AppDatabase {
        // Se já existe, retorna; senão cria com Room.
        return INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            )
                .fallbackToDestructiveMigration() // DEV ONLY
                //.addMigrations(MIGRATION_1_2, MIGRATION_2_3, ...) // quando tiver
                .build()
                .also { INSTANCE = it }
        }
    }
}
