plugins {
    id("playground.android.library")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.core.validation"
}
dependencies {
    testImplementation(project(":core:test"))
}
