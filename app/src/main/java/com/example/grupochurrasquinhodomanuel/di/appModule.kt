package com.example.grupochurrasquinhodomanuel.di

import androidx.room.Room
import com.example.grupochurrasquinhodomanuel.core.database.AppDatabase
import com.example.grupochurrasquinhodomanuel.core.firebase.FirebaseAuthManager
import com.example.grupochurrasquinhodomanuel.core.preferences.AuthPreferences
import com.example.grupochurrasquinhodomanuel.core.preferences.FavoritePreferences
import com.example.grupochurrasquinhodomanuel.core.preferences.SessionPreferences
import com.example.grupochurrasquinhodomanuel.features.welcome.WelcomePreferences
import com.example.grupochurrasquinhodomanuel.data.dao.BrandDao
import com.example.grupochurrasquinhodomanuel.data.dao.CustomerDao
import com.example.grupochurrasquinhodomanuel.data.dao.EmployeeDao
import com.example.grupochurrasquinhodomanuel.data.dao.UnitDao
import com.example.grupochurrasquinhodomanuel.features.order.data.local.OrderDao
import com.example.grupochurrasquinhodomanuel.features.review.data.local.ReviewDao
import com.example.grupochurrasquinhodomanuel.data.repository.BrandRepository
import com.example.grupochurrasquinhodomanuel.data.repository.CustomerRepository
import com.example.grupochurrasquinhodomanuel.data.repository.EmployeeRepository
import com.example.grupochurrasquinhodomanuel.data.repository.EmployeeRepositoryImpl
import com.example.grupochurrasquinhodomanuel.data.repository.ReviewRepository
import com.example.grupochurrasquinhodomanuel.data.repository.UnitRepository
import com.example.grupochurrasquinhodomanuel.features.order.data.OrderTrackingRepository
import com.example.grupochurrasquinhodomanuel.features.order.data.repository.OrderRepository
import com.example.grupochurrasquinhodomanuel.features.order.data.repository.OrderRepositoryImpl
import com.example.grupochurrasquinhodomanuel.features.brand.presentation.BrandViewModel
import com.example.grupochurrasquinhodomanuel.features.brand.presentation.UnitViewModel
import com.example.grupochurrasquinhodomanuel.features.customer.presentation.CustomerViewModel
import com.example.grupochurrasquinhodomanuel.features.customer.presentation.FavoriteViewModel
import com.example.grupochurrasquinhodomanuel.features.customer.ui.cart.CartViewModel
import com.example.grupochurrasquinhodomanuel.features.customer.ui.order.presentation.OrderTrackingViewModel
import com.example.grupochurrasquinhodomanuel.features.login.viewmodel.AuthViewModel
import com.example.grupochurrasquinhodomanuel.features.management.viewmodel.EmployeeViewModel
import com.example.grupochurrasquinhodomanuel.features.management.viewmodel.ManagementViewModel
import com.example.grupochurrasquinhodomanuel.features.order.presentation.EmployeeOrderViewModel
import com.example.grupochurrasquinhodomanuel.features.register.presentation.RegisterViewModel
import com.example.grupochurrasquinhodomanuel.features.register.ui.EmployeeRegisterViewModel
import com.example.grupochurrasquinhodomanuel.features.review.ui.ReviewViewModel
import com.example.grupochurrasquinhodomanuel.features.welcome.presentation.WelcomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single<BrandDao> { get<AppDatabase>().brandDao() }
    single<UnitDao> { get<AppDatabase>().unitDao() }
    single<CustomerDao> { get<AppDatabase>().customerDao() }
    single<ReviewDao> { get<AppDatabase>().reviewDao() }
    single<OrderDao> { get<AppDatabase>().orderDao() }
    single<EmployeeDao> { get<AppDatabase>().employeeDao() }

    single { SessionPreferences(androidContext()) }
    single { FavoritePreferences(androidContext()) }
    single { WelcomePreferences(androidContext()) }
    single { AuthPreferences(androidContext()) }
    single { FirebaseAuthManager() }

    single { BrandRepository(brandDao = get()) }
    single { UnitRepository(unitDao = get()) }
    single { CustomerRepository(customerDao = get()) }
    single { ReviewRepository(reviewDao = get()) }
    single<OrderRepository> { OrderRepositoryImpl(dao = get()) }
    single { OrderTrackingRepository(dao = get()) }
    single<EmployeeRepository> { EmployeeRepositoryImpl(dao = get()) }



    viewModel { BrandViewModel(repository = get()) }
    viewModel { UnitViewModel(repository = get()) }

    viewModel { CustomerViewModel(repository = get(), session = get()) }
    viewModel { FavoriteViewModel(preferences = get()) }
    viewModel { CartViewModel() }

    viewModel { AuthViewModel(firebaseAuthManager = get(), authPreferences = get()) }
    viewModel { RegisterViewModel(firebaseAuthManager = get(), authPreferences = get()) }
    viewModel { EmployeeRegisterViewModel(repository = get(), auth = get()) }

    viewModel { WelcomeViewModel(preferences = get()) }
    viewModel { ReviewViewModel(repository = get()) }

    viewModel {
        ManagementViewModel(
            employeeRepository = get(),
            orderRepository = get(),
            reviewRepository = get()
        )
    }

    viewModel { OrderTrackingViewModel(repository = get()) }
    viewModel { EmployeeOrderViewModel(repository = get()) }
    viewModel { EmployeeViewModel(repository = get()) }
}
