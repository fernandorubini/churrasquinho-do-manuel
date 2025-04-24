package com.example.grupochurrasquinhodomanuel.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.grupochurrasquinhodomanuel.core.model.Brand
import com.example.grupochurrasquinhodomanuel.features.customer.model.Customer
import com.example.grupochurrasquinhodomanuel.model.Review

@Database(
    entities = [Customer::class, Brand::class, UnitEntity::class, Review::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerDao
    abstract fun brandDao(): BrandDao
    abstract fun unitDao(): UnitDao
    abstract fun reviewDao(): ReviewDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}