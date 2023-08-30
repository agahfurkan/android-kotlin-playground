plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.feature.login"
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
    implementation(project(":core:util"))
    implementation(project(":core:ui"))
    implementation(project(":core:logging"))
    implementation(project(":core:session"))
    implementation(project(":domain:login"))
    implementation(libs.lottie.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.hilt.navigation.compose)
    implementation(libs.material3.compose)
    implementation(libs.compose.ui)
}