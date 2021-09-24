import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {
    //core
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val core = "androidx.core:core-ktx:${Versions.ktxVersion}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val material = "com.google.android.material:material:${Versions.materialVersion}"
    const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}"
    const val livedataCommon =
        "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycleVersion}"

    //layout
    const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"

    //retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofitMoshiConverter =
        "com.squareup.retrofit2:converter-moshi:${Versions.retrofitMoshiConverterVersion}"
    const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshiVersion}"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshiVersion}"
    const val okhttp3Logging =
        "com.squareup.okhttp3:logging-interceptor:${Versions.interceptorVersion}"

    //hilt
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hiltVersion}"

    //navigation component
    const val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navVersion}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navVersion}"

    //room
    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"

    //glide
    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"

    //testing
    const val jUnit = "androidx.test.ext:junit:${Versions.jUnitXVersion}"
    const val testRunner = "androidx.test:runner:${Versions.testRunnerVersion}"
    const val truth = "com.google.truth:truth:${Versions.truthVersion}"
    const val hiltTesting = "com.google.dagger:hilt-android-testing:${Versions.hiltVersion}"
    const val hiltAndroidCompiler =
        "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"

    val implementationLibraries = arrayListOf<String>().apply {
        add(stdlib)
        add(core)
        add(appcompat)
        add(material)
        add(livedata)
        add(livedataCommon)
        add(constraintlayout)
        add(retrofit)
        add(retrofitMoshiConverter)
        add(moshi)
        add(okhttp3Logging)
        add(hiltAndroid)
        add(navigationFragmentKtx)
        add(navigationUiKtx)
        add(roomRuntime)
        add(roomKtx)
        add(glide)
    }
    val kaptLibraries = arrayListOf<String>().apply {
        add(moshiCodegen)
        add(hiltCompiler)
        add(roomCompiler)
        add(glideCompiler)
    }
    val androidTestLibraries=arrayListOf<String>().apply {
        add(jUnit)
        add(truth)
        add(hiltTesting)
        add(hiltAndroidCompiler)
    }
    val testLibraries=arrayListOf<String>().apply {
        add(testRunner)
    }


    //util functions for adding the different type dependencies from build.gradle file
    fun DependencyHandler.kapt(list: List<String>) {
        list.forEach { dependency ->
            add("kapt", dependency)
        }
    }

    fun DependencyHandler.implementation(list: List<String>) {
        list.forEach { dependency ->
            add("implementation", dependency)
        }
    }

    fun DependencyHandler.androidTestImplementation(list: List<String>) {
        list.forEach { dependency ->
            add("androidTestImplementation", dependency)
        }
    }

    fun DependencyHandler.testImplementation(list: List<String>) {
        list.forEach { dependency ->
            add("testImplementation", dependency)
        }
    }
}