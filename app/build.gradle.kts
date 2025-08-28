plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.services)
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.example.grupochurrasquinhodomanuel"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.grupochurrasquinhodomanuel"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    // Argumentos do kapt (Room)
    kapt {
        arguments {
            // Exporta o schema do Room em /app/schemas (útil para migrações)
            arg("room.schemaLocation", "$projectDir/schemas")
            arg("room.incremental", "true")
            arg("room.expandProjection", "true")
        }
        correctErrorTypes = true
    }

    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += listOf(
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
        )
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    packaging {
        resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
}

dependencies {
    // --- Compose ---
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.engage.core)
    debugImplementation(libs.androidx.ui.tooling)

    // --- Core & Lifecycle ---
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx) // viewModelScope
    // Para collectAsStateWithLifecycle (seu código usa):
    implementation(libs.androidx.lifecycle.runtime.compose)

    // --- Navigation ---
    implementation(libs.navigation.compose)

    // --- Room ---
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
    // Observação: os TypeConverters (Gson) são registrados via @TypeConverters no AppDatabase.
    // Não use .addTypeConverter(Converters()) no databaseBuilder.

    // --- Firebase ---
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.auth)
    implementation(libs.coroutines.play.services) // .await() em Tasks

    // --- Koin ---
    implementation(libs.koin.android)
    implementation(libs.koin.compose)

    // --- Retrofit (+ Gson) ---
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson) // traz com.google.code.gson:gson de forma transitiva

    // --- Coroutines ---
    implementation(libs.coroutines.android)

    // --- Coil ---
    implementation(libs.coil.compose)

    // --- DataStore ---
    implementation(libs.datastore.preferences)

    // --- Testes ---
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)
}
