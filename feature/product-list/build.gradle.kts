plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.agah.furkan.feature.product_list"
    compileSdk = 33

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":data:cart"))
    implementation(project(":domain:product"))
    implementation(project(":core:data"))
    implementation(project(":core:ui"))
    implementation(project(":core:preferences"))
    implementation(project(":core:util"))
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.hilt.navigation.compose)
    implementation(libs.material3.compose)
    implementation(libs.compose.ui)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.paging.compose)
}