plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.core.data"
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
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":core:preferences"))
    implementation(project(":core:session"))
    api(libs.retrofit)
    api(libs.moshi)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.okhttp3.logging)
    kapt(libs.moshi.kotlin.codegen)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.truth)
}