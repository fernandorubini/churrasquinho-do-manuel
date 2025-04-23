package com.example.grupochurrasquinhodomanuel.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.grupochurrasquinhodomanuel.core.model.Brand
import com.example.grupochurrasquinhodomanuel.model.Review

@Database(
    entities = [Review::class, Brand::class, UnitEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun reviewDao(): ReviewDao
    abstract fun brandDao(): BrandDao
    abstract fun unitDao(): UnitDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database_name"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
