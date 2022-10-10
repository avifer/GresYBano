plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_KAPT)
    id(Plugins.DAGGER_HILT)
}

android {
    compileSdk = ConfigApp.COMPILE_SDK
    defaultConfig {
        minSdk = ConfigApp.MIN_SDK
        targetSdk = ConfigApp.TARGET_SDK
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(project(Modules.COMMON))
    implementation(project(Modules.DOMAIN))

    implementation(Dependencies.Androidx.ROOM_RUNTIME)
    implementation(Dependencies.Androidx.ROOM_KTX)
    kapt(Dependencies.Androidx.ROOM_COMPILER)

    implementation(Dependencies.Google.FIREBASE_CONFIG)

    implementation(Dependencies.Kotlin.KOTLIN_COROUTINES_PLAY_SERVICES)

    implementation(Dependencies.Squareup.RETROFIT2)
    implementation(Dependencies.Google.GSON)
    implementation(Dependencies.Squareup.CONVERTER_GSON)
    implementation(Dependencies.Squareup.LOGGING_INTERCEPTOR)
    implementation(Dependencies.Squareup.OKHTTPS)

    implementation(Dependencies.Google.HILT_ANDROID)
    kapt(Dependencies.Google.HILT_ANDROID_COMPILER)
}