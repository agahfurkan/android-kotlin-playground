import com.google.protobuf.gradle.id

plugins {
    id("playground.android.library")
    id("playground.android.hilt")
    id("com.google.protobuf") version "0.9.4"
}

android {
    namespace = "com.agah.furkan.core.preferences"
}

dependencies {
    implementation(libs.androidx.datastore)
    implementation(libs.protobuf.javalite)
}


protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.24.1"
    }
    // Generates the java Protobuf-lite code for the Protobufs in this project. See
    // https://github.com/google/protobuf-gradle-plugin#customizing-protobuf-compilation
    // for more information.
    generateProtoTasks {
        // see https://github.com/google/protobuf-gradle-plugin/issues/518
        // see https://github.com/google/protobuf-gradle-plugin/issues/491
        // all() here because of android multi-variant
        all().forEach { task ->
            // this only works on version 3.8+ that has buildins for javalite / kotlin lite
            // with previous version the java build in is to be removed and a new plugin
            // need to be declared
            task.builtins {
                id("java") { // id is imported above
                    option("lite")
                }
            }
        }
    }
}