package com.example.grupochurrasquinhodomanuel.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.grupochurrasquinhodomanuel.model.Avaliacao

@Database(entities = [Avaliacao::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun avaliacaoDao(): AvaliacaoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // Usando um bloco synchronized para garantir que só uma instância do banco seja criada
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database_name"
                ).build() // Aqui você pode adicionar configurações como migrations se necessário
                INSTANCE = instance
                instance
            }
        }
    }
}
