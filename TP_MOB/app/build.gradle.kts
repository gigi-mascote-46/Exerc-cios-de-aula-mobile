// app/build.gradle.kts

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt") // Para processamento de anotações (Room, Dagger/Hilt etc.)
    // Se usar Hilt:
    // id("com.google.dagger.hilt.android")
    // Se usar Safe Args do Navigation:
    // id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.tp_mob"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tp_mob"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled   = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            // applicationIdSuffix = ".debug"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-Xjvm-default=all" // se você precisar de métodos default em interfaces
        )
    }

    buildFeatures {
        viewBinding = true
        // dataBinding = true
        compose     = false // true se usar Jetpack Compose
    }

    // packagingOptions {
    //     resources {
    //         excludes += "/META-INF/{AL2.0,LGPL2.1}"
    //     }
    // }
}

dependencies {
    // Core
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("androidx.fragment:fragment-ktx:1.6.2")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    // RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.0")


    val roomVersion = "2.6.1"
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Retrofit (networking) — exemplo
    // val retrofitVersion = "2.9.0"
    // implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    // implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")

    // Testes
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

// Kapt options (opcional)
// kapt {
//    correctErrorTypes = true
// }
