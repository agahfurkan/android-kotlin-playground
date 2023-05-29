import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {
    //core
    private const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    private const val core = "androidx.core:core-ktx:${Versions.ktxVersion}"
    private const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    private const val livedata =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}"
    private const val livedataCommon =
        "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycleVersion}"
    private const val lifecycleRuntimeKtx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleVersion}"

    //layout
    private const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"

    //retrofit
    private const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    private const val retrofitMoshiConverter =
        "com.squareup.retrofit2:converter-moshi:${Versions.retrofitMoshiConverterVersion}"
    private const val moshiCodegen =
        "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshiVersion}"
    private const val moshi = "com.squareup.moshi:moshi:${Versions.moshiVersion}"
    private const val okhttp3Logging =
        "com.squareup.okhttp3:logging-interceptor:${Versions.interceptorVersion}"

    //hilt
    private const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
    private const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hiltVersion}"

    //navigation component
    private const val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navVersion}"
    private const val navigationUiKtx =
        "androidx.navigation:navigation-ui-ktx:${Versions.navVersion}"

    //room
    private const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    private const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
    private const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"

    //glide
    private const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    private const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"

    //testing
    private const val jUnit = "androidx.test.ext:junit:${Versions.jUnitXVersion}"
    private const val testRunner = "androidx.test:runner:${Versions.testRunnerVersion}"
    private const val truth = "com.google.truth:truth:${Versions.truthVersion}"
    private const val hiltTesting = "com.google.dagger:hilt-android-testing:${Versions.hiltVersion}"
    private const val hiltAndroidCompiler =
        "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"
    private const val lottie = "com.airbnb.android:lottie:${Versions.lottieVersion}"
    private const val paging = "androidx.paging:paging-runtime:${Versions.pagingVersion}"
    private const val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"

    //compose
    private const val composeRuntime = "androidx.compose.runtime:runtime"
    private const val composeUi = "androidx.compose.runtime:runtime"
    private const val composeFoundation = "androidx.compose.runtime:runtime"
    private const val composeFoundationLayout = "androidx.compose.runtime:runtime"
    private const val composeMaterial = "androidx.compose.material:material"
    private const val composeRuntimeLivedata = "androidx.compose.runtime:runtime-livedata"
    private const val composeUiTooling = "androidx.compose.ui:ui-tooling"
    private const val composeActivity =
        "androidx.activity:activity-compose:${Versions.composeActivityVersion}"
    private const val composeAccompanist =
        "com.google.accompanist:accompanist-themeadapter-material:${Versions.accompanistVersion}"
    private const val glideCompose =
        "com.github.bumptech.glide:compose:${Versions.glideComposeVersion}"
    private const val material3 =
        "androidx.compose.material3:material3:${Versions.material3Version}"
    private const val material3WindowSize =
        "androidx.compose.material3:material3-window-size-class:${Versions.material3Version}"
    private const val hiltCompose =
        "androidx.hilt:hilt-navigation-compose:${Versions.hiltComposeVersion}"
    private const val lottieCompose = "com.airbnb.android:lottie-compose:${Versions.lottieVersion}"
    private const val constraintLayoutCompose =
        "androidx.constraintlayout:constraintlayout-compose:${Versions.constraintLayoutComposeVersion}"
    private const val pagingCompose =
        "androidx.paging:paging-compose:${Versions.pagingComposeVersion}"

    private val composeLibraries = arrayListOf<String>(
        composeRuntime,
        composeUi,
        composeFoundation,
        composeFoundationLayout,
        composeMaterial,
        composeRuntimeLivedata,
        composeUiTooling,
        composeAccompanist,
        glideCompose,
        composeActivity,
        hiltCompose,
        lottieCompose,
        constraintLayoutCompose,
        pagingCompose
    )
    val implementationLibraries = arrayListOf<String>().apply {
        add(stdlib)
        add(core)
        add(appcompat)
        add(livedata)
        add(livedataCommon)
        add(lifecycleRuntimeKtx)
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
        add(lottie)
        add(paging)
        add(timber)
        add(material3)
        add(material3WindowSize)
        addAll(composeLibraries)
    }

    val kaptLibraries = arrayListOf<String>().apply {
        add(moshiCodegen)
        add(hiltCompiler)
        add(roomCompiler)
        add(glideCompiler)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(jUnit)
        add(truth)
        add(hiltTesting)
        add(hiltAndroidCompiler)
    }

    val testLibraries = arrayListOf<String>().apply {
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