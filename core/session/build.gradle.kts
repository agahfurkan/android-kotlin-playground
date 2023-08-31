plugins {
    id("playground.android.library")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.core.session"
}

dependencies {
    implementation(project(":core:preferences"))
}