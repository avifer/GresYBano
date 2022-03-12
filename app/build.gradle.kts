import FunctionsGradle.BuildType.RELEASE
import FunctionsGradle.getFileStore
import FunctionsGradle.getKeyAlias
import FunctionsGradle.getKeyPassword
import FunctionsGradle.getStorePassword
import FunctionsGradle.getVersionCode

plugins {
    id(Plugins.ANDROID_APPLICATION)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KOTLIN_KAPT)
    id(Plugins.DAGGER_HILT)
    id(Plugins.JETBRAINS_KOTLIN)
    id(Plugins.NAVIGATION_SAFEARGS)
    id(Plugins.GMS_GOOGLE_SERVICES)
    id(Plugins.FIREBASE_CRASHLYTICS)
}

android {
    compileSdk = ConfigApp.COMPILE_SDK
    buildToolsVersion = ConfigApp.BUILD_TOOLS_VERSION
    defaultConfig {
        applicationId = ConfigApp.APPLICATION_ID
        minSdk = ConfigApp.MIN_SDK
        targetSdk = ConfigApp.TARGET_SDK
        versionCode = getVersionCode()
        versionName = ConfigApp.VERSION_NAME

        vectorDrawables {
            useSupportLibrary = true
        }
    }
    signingConfigs {
        create("release") {
            storeFile = getFileStore(RELEASE)
            storePassword = getStorePassword(RELEASE)
            keyAlias = getKeyAlias(RELEASE)
            keyPassword = getKeyPassword(RELEASE)
        }
    }
    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
            manifestPlaceholders["crashlyticsCollectionEnabled"] = false
            manifestPlaceholders["analyticsCollectionEnabled"] = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
        getByName("release") {
            isMinifyEnabled = false
            isDebuggable = false
            manifestPlaceholders["crashlyticsCollectionEnabled"] = true
            manifestPlaceholders["analyticsCollectionEnabled"] = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    viewBinding {
        isEnabled = true
    }
}

dependencies {
    implementation(project(Modules.COMMON))
    implementation(project(Modules.DATA))
    implementation(project(Modules.DOMAIN))
    implementation(project(Modules.NAVIGATION))
    implementation(project(Modules.FEATURE_SPLASH))
    implementation(project(Modules.FEATURE_ONBOARDING))

    implementation(Dependencies.Androidx.CORE_KOTLIN)

    implementation(Dependencies.Androidx.LIFECYCLE_RUNTIME_KOTLIN)
    implementation(Dependencies.Material.MATERIAL)
    implementation(Dependencies.Androidx.CONSTRAINT_LAYOUT)
    implementation(Dependencies.Androidx.APPCOMPAT)

    implementation(Dependencies.Androidx.NAVIGATION_FRAGMENT_KOTLIN)
    implementation(Dependencies.Androidx.NAVIGATION_UI_KOTLIN)

    implementation(Dependencies.Google.FIREBASE_CRASHLYTICS)
    implementation(Dependencies.Google.FIREBASE_ANALYTICS)
    implementation(Dependencies.Google.FIREBASE_CONFIG)

    implementation(Dependencies.Airbnb.LOTTIE)

    implementation(Dependencies.Androidx.ROOM_RUNTIME)
    implementation(Dependencies.Androidx.ROOM_KTX)
    kapt(Dependencies.Androidx.ROOM_COMPILER)

    implementation(Dependencies.Google.HILT_ANDROID)
    kapt(Dependencies.Google.HILT_ANDROID_COMPILER)
}