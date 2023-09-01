plugins {
    id("playground.android.library")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.core.notification"
}
dependencies {
    implementation(project(":core:ui"))
}