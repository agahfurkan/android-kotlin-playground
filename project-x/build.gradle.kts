plugins {
    id("playground.android.library.compose")
    id("playground.android.hilt")
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.agah.furkan.x"

    buildFeatures {
        dataBinding = true
    }
}
tasks.withType(Test::class.java) {
    jvmArgs = jvmArgs?.plus("-XX:+AllowRedefinitionToAddDeleteMethods")
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
