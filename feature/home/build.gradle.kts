plugins {
    id("playground.android.library.compose")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.feature.home"
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:data"))
    implementation(project(":data:announcement"))
    implementation(libs.accompanist.system.ui.controller)
}