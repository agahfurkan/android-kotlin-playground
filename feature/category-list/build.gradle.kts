plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.feature.category_list"
    compileSdk = 33

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:data"))
    implementation(project(":data:category"))
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.material3.compose)
    implementation(libs.activity.compose)
    implementation(libs.compose.ui)
    implementation(libs.hilt.navigation.compose)
}