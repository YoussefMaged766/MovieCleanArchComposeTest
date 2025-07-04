plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.devYoussef.cleanarchtest"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.devYoussef.cleanarchtest"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
    buildFeatures {
        compose = true
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

    // Retrofit + GSON
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    // Dagger Hilt
    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.compiler)
    //logging-interceptor
    implementation(libs.logging.interceptor)
    //navigation
    implementation(libs.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)

    // DataStore
    implementation(libs.datastore.preferences)

    // serialization
    implementation(libs.kotlinx.serialization.json)

    // splash screen api 
    implementation(libs.core.splashscreen)

    // external icons
    implementation(libs.androidx.material.icons.extended)

    // Coil for image loading
    implementation(libs.coil.compose)
    implementation(libs.coil.network.compose)

}