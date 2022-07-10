plugins {
    id(Plugins.ANDROID_LIBRARY)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.JETBRAINS_KOTLIN)
}

android {
    compileSdk = ConfigApp.COMPILE_SDK
    defaultConfig {
        minSdk = ConfigApp.MIN_SDK
        targetSdk = ConfigApp.TARGET_SDK
    }
    viewBinding {
        isEnabled = true
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
dependencies {
    implementation(project(Modules.DOMAIN))
    implementation(project(Modules.NAVIGATION))

    implementation(Dependencies.Androidx.CONSTRAINT_LAYOUT)
    implementation(Dependencies.Androidx.APPCOMPAT)
    implementation(Dependencies.Androidx.FRAGMENT)

    implementation(Dependencies.Google.FIREBASE_CONFIG)

    implementation(Dependencies.Airbnb.LOTTIE)

    implementation(Dependencies.Github.GLIDE)

    implementation(Dependencies.Google.GSON)

    implementation(Dependencies.Androidx.NAVIGATION_FRAGMENT_KOTLIN)
    implementation(Dependencies.Androidx.NAVIGATION_UI_KOTLIN)
}
