plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("org.jlleitschuh.gradle.ktlint")
    id("dagger.hilt.android.plugin")
    id("io.gitlab.arturbosch.detekt") version AppConfig.detektVersion
    id("com.diffplug.spotless") version AppConfig.spotlessVersion
    id("org.jetbrains.dokka") version AppConfig.dokkaVersion
}
android {
    ndkVersion = "24.0.8215888"
    compileSdk = AppConfig.compileSdk

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
        create("benchmark") {
            initWith(buildTypes.getByName("release"))
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks += listOf("release")
            isDebuggable = false
        }
    }
    externalNativeBuild {
        ndkBuild {
            path("src/main/jni/Android.mk")
        }
    }
    flavorDimensions.add("default")
    productFlavors {
        create("dev") {
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            buildConfigField("String", "BASE_URL", "\"https://10.0.2.2:5000/api/\"")
            resValue("string", "app_name", "DEV-Android Playground")
        }

        create("prod") {
            applicationIdSuffix = ".prod"
            versionNameSuffix = "-prod"
            buildConfigField("String", "BASE_URL", "\"https://10.0.2.2:5000/api/\"")
            resValue("string", "app_name", "PROD-Android Playground")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packagingOptions {
        resources {
            excludes += setOf("META-INF/gradle/incremental.annotation.processors")
        }
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    namespace = "com.agah.furkan.androidplayground"

    dependencies {
        implementation(project(":core:session"))
        implementation(project(":core:data"))
        implementation(project(":core:preferences"))
        implementation(project(":core:logging"))
        implementation(project(":core:ui"))
        implementation(project(":core:util"))

        implementation(project(":data:cart"))

        implementation(project(":feature:cart"))
        implementation(project(":feature:home"))
        implementation(project(":feature:splash"))
        implementation(project(":feature:login"))
        implementation(project(":feature:category-list"))
        implementation(project(":feature:profile"))
        implementation(project(":feature:product-detail"))
        implementation(project(":feature:product-list"))
        implementation(project(":feature:product-detail-tabbed"))
        implementation(project(":feature:register"))
        implementation(project(":feature:search"))

        implementation(libs.hilt.navigation.compose)
        implementation(libs.material3.compose)
        implementation(libs.compose.ui)
        implementation(libs.accompanist.theme.adapter.material)
        implementation(libs.hilt.android)
        kapt(libs.hilt.compiler)
    }
}
spotless {
    java {
        target("**/*.java")
        googleJavaFormat().aosp()
        removeUnusedImports()
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }
    kotlin {
        target("**/*.kt")
        ktlint()
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }
    format("misc") {
        target("**/*.gradle", "**/*.md", "**/.gitignore")
        indentWithSpaces()
        trimTrailingWhitespace()
        endWithNewline()
    }

    format("xml") {
        target("**/*.xml")
        indentWithSpaces()
        trimTrailingWhitespace()
        endWithNewline()
    }
}
detekt {
    config = files("$rootDir/config/detekt.yml")
}
tasks.dokkaHtml.configure {
    outputDirectory.set(file("../documentation/html"))
    pluginsMapConfiguration.set(mapOf("org.jetbrains.dokka.base.DokkaBase" to """{ "separateInheritedMembers": true}"""))
}
