plugins {
    id("playground.android.library")
    id("playground.android.hilt")
}

android {
    namespace = "com.agah.furkan.data.pdf"
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":domain:pdf"))
    implementation("com.agah.furkan.playgrounddatamodule:shared:1.0")
}