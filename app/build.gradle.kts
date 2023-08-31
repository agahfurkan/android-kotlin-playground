import org.jetbrains.kotlin.konan.properties.Properties

val keystoreProperties = Properties().apply {
    val file = File("keystore-info.txt")
    if (file.canRead()) {
        load(file.reader())
    }
}

plugins {
    id("com.android.application")
    kotlin("android")
    id("androidx.navigation.safeargs.kotlin")
    id("org.jlleitschuh.gradle.ktlint")
    id("playground.android.hilt")
    id("io.gitlab.arturbosch.detekt") version "1.23.1"
    id("com.diffplug.spotless") version "6.21.0"
    id("org.jetbrains.dokka") version "1.6.10"
    id("com.google.gms.google-services")
}
android {
    ndkVersion = "24.0.8215888"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.agah.furkan.androidplayground"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "com.agah.furkan.androidplayground.CustomTestRunner"
    }
    signingConfigs {
        if (keystoreProperties.getProperty("file") != null) {
            create("release") {
                storeFile = file(keystoreProperties.getProperty("file"))
                storePassword = keystoreProperties.getProperty("store_password")
                keyAlias = keystoreProperties.getProperty("key_alias")
                keyPassword = keystoreProperties.getProperty("key_password")
            }
        }
    }
    buildTypes {
        getByName("release") {
            if (signingConfigs.firstOrNull { it.name == "release" } != null) {
                signingConfig = signingConfigs.getByName("release")
            }
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
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
        detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.1")
        implementation(project(":core:session"))
        implementation(project(":core:data"))
        implementation(project(":core:preferences"))
        implementation(project(":core:logging"))
        implementation(project(":core:ui"))
        implementation(project(":core:util"))
        implementation(project(":core:remoteconfig"))

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
    config.setFrom("$rootDir/config/detekt.yml")
}
tasks.dokkaHtml.configure {
    outputDirectory.set(file("../documentation/html"))
    pluginsMapConfiguration.set(mapOf("org.jetbrains.dokka.base.DokkaBase" to """{ "separateInheritedMembers": true}"""))
}
tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    jvmTarget = "1.8"
}
ktlint {
    version.set("0.48.2")
}
