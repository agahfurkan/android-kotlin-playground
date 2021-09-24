import Dependencies.androidTestImplementation
import Dependencies.implementation
import Dependencies.kapt
import Dependencies.testImplementation

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("org.jlleitschuh.gradle.ktlint")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = AppConfig.compileSdk
    buildToolsVersion = AppConfig.buildTools

    defaultConfig {
        applicationId = "com.agah.furkan.androidplayground"
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
        testInstrumentationRunner = (AppConfig.testInstrumentationRunner)
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }
    flavorDimensions.add("default")
    productFlavors {
        create("dev") {
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            buildConfigField("String", "BASE_URL", "\"http://10.0.2.2:19120/api/\"")
            resValue("string", "app_name", "DEV-Android Playground")
        }

        create("prod") {
            applicationIdSuffix = ".prod"
            versionNameSuffix = "-prod"
            buildConfigField("String", "BASE_URL", "\"http://10.0.2.2:19120/api/\"")
            resValue("string", "app_name", "PROD-Android Playground")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
    dependencies {
        implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
        implementation(Dependencies.implementationLibraries)
        kapt(Dependencies.kaptLibraries)
        androidTestImplementation(Dependencies.androidTestLibraries)
        testImplementation(Dependencies.testLibraries)
    }
}


