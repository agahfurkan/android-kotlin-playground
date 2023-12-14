plugins {
    id("playground.android.library")
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

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
}
