plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
    id ("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.myownphone"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myownphone"
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
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.navigation.compose)


    // Lifecycle + ViewModel & LiveData
    implementation( "androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation( "androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation( "androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    // Activity KTX for viewModels()
    implementation( "androidx.activity:activity-ktx:1.8.2")

    // Coroutines
    implementation( "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation( "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // HiltViewModel
    implementation( "androidx.hilt:hilt-navigation-compose:1.1.0")

    // Material 3
    implementation( "androidx.compose.material3:material3:1.2.0-beta01")

    // Material
    implementation( "androidx.compose.material:material:1.5.4")

    // Coil
    implementation( "io.coil-kt:coil-compose:2.2.2")
    implementation( "io.coil-kt:coil-gif:2.2.2")
    implementation( "io.coil-kt:coil-svg:2.2.2")

    // Glide
    implementation( "com.github.bumptech.glide:glide:4.15.1")

    // Retrofit
    implementation( "com.squareup.retrofit2:retrofit:2.9.0")
    implementation( "com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation( "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.9")

    //sandwich
    implementation( "com.github.skydoves:sandwich:1.3.7")

    // Dagger Hilt
    implementation(libs.hilt.andriod)
    ksp(libs.hilt.andriod.compiler)

    // Moshi
    implementation( "com.squareup.moshi:moshi:1.14.0")
    ksp( "com.squareup.moshi:moshi-kotlin-codegen:1.14.0")

    //Gson
    implementation("com.google.code.gson:gson:2.10.1")

    implementation(libs.kotlinx.serialization.json)

    //slider
    implementation("androidx.compose.foundation:foundation:1.4.1")
    implementation("com.tbuonomo:dotsindicator:5.0")

    // Room
    implementation("androidx.room:room-ktx:2.5.2")
    ksp("androidx.room:room-compiler:2.5.2")

    //splash
    implementation("androidx.core:core-splashscreen:1.2.0-alpha01")
}