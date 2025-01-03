import org.jetbrains.kotlin.konan.properties.Properties

val keystoreProperties = Properties().apply {
    val file = File("keystore-info.txt")
    if (file.canRead()) {
        load(file.reader())
    }
}
apply("..//projectDependencyGraph.gradle")
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
    compileSdk = 35

    defaultConfig {
        applicationId = "com.agah.furkan.androidplayground"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "com.agah.furkan.androidplayground.TestRunner"
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
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
            enableAndroidTestCoverage = true
            enableUnitTestCoverage = true
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
            resValue("string", "app_name", "DEV-Android Playground")
        }

        create("prod") {
            resValue("string", "app_name", "PROD-Android Playground")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        dataBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
    packagingOptions {
        resources {
            excludes += setOf("META-INF/gradle/incremental.annotation.processors")
        }
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    namespace = "com.agah.furkan.androidplayground"

    dependencies {
        detektPlugins(libs.detekt.formatting)
        implementation(project(":project-x"))

        implementation(project(":core:session"))
        implementation(project(":core:data"))
        implementation(project(":core:preferences"))
        implementation(project(":core:logging"))
        implementation(project(":core:resources"))
        implementation(project(":core:ui:theme"))
        implementation(project(":core:util"))
        implementation(project(":core:remoteconfig"))
        implementation(project(":core:notification"))
        implementation(project(":core:ui:components"))

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
        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.compose.ui)
        implementation(libs.hilt.navigation.compose)
        implementation(libs.material3.compose)
        implementation(libs.accompanist.theme.adapter.material)
        implementation(libs.firebase.messaging.ktx)
        implementation(libs.androidx.hilt.work)
        kapt(libs.androidx.hilt.compiler)

        androidTestImplementation(libs.junitx)
        androidTestImplementation(libs.mockwebserver)
        androidTestImplementation(libs.espresso.core)
        androidTestImplementation(libs.androidx.core.ktx)
        androidTestImplementation(libs.androidx.ui.test.junit4)
        debugImplementation(libs.androidx.ui.test.manifest)
        androidTestImplementation(libs.hilt.testing)
        testImplementation(libs.robolectric)
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
    jvmTarget = "17"
}
ktlint {
    version.set("0.48.2")
}
