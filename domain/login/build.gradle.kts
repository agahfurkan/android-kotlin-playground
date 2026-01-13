plugins {
    id("playground.android.library")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.domain.login"
}

dependencies {
    implementation(project(":core:preferences"))
    api(project(":core:domain"))

    implementation(project(":domain:user"))
}