package com.example.grupochurrasquinhodomanuel.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.grupochurrasquinhodomanuel.data.dao.BrandDao
import com.example.grupochurrasquinhodomanuel.data.dao.CustomerDao
import com.example.grupochurrasquinhodomanuel.data.dao.EmployeeDao
import com.example.grupochurrasquinhodomanuel.data.dao.UnitDao
import com.example.grupochurrasquinhodomanuel.features.order.data.local.OrderDao
import com.example.grupochurrasquinhodomanuel.features.review.data.local.ReviewDao
import com.example.grupochurrasquinhodomanuel.data.entity.BrandEntity
import com.example.grupochurrasquinhodomanuel.core.model.UnitEntity
import com.example.grupochurrasquinhodomanuel.data.local.CustomerEntity
import com.example.grupochurrasquinhodomanuel.features.order.data.local.OrderEntity
import com.example.grupochurrasquinhodomanuel.data.local.EmployeeEntity
import com.example.grupochurrasquinhodomanuel.features.review.data.local.ReviewEntity

@Database(
    entities = [
        BrandEntity::class,
        UnitEntity::class,
        CustomerEntity::class,
        ReviewEntity::class,
        OrderEntity::class,
        EmployeeEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun brandDao(): BrandDao
    abstract fun unitDao(): UnitDao
    abstract fun customerDao(): CustomerDao
    abstract fun reviewDao(): ReviewDao
    abstract fun orderDao(): OrderDao
    abstract fun employeeDao(): EmployeeDao
}
