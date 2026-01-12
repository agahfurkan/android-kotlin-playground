plugins {
    id("playground.android.library.compose")
    id("playground.android.hilt")
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.agah.furkan.x"

    buildFeatures {
        viewBinding = true
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    testImplementation(project(":core:test"))
    implementation(project(":core:view"))
    implementation(project(":core:util"))
    implementation(project(":core:ui:components"))
    implementation(project(":core:ui:theme"))

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
}
