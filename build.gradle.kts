buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Classpaths.Android.TOOLS_BUILD_GRADLE)
        classpath(Classpaths.Android.NAVIGATION_SAFE_ARGS)
        classpath(Classpaths.Jetbrains.KOTLIN_GRADLE_PLUGIN)
        classpath(Classpaths.Google.DAGGER_HILT_ANDROID_GRADLE)
        classpath(Classpaths.Google.GMS_GOOGLE_SERVICES)
        classpath(Classpaths.Google.FIREBASE_CRASHLYTICS)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}