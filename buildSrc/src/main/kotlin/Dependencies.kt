object Dependencies {

    object Androidx{
        const val CORE_KOTLIN = "androidx.core:core-ktx:${Versions.Androidx.CORE_KOTLIN}"

        const val LIFECYCLE_RUNTIME_KOTLIN = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Androidx.LIFECYCLE_RUNTIME_KOTLIN}"

        const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.Androidx.APPCOMPAT}"
        const val FRAGMENT = "androidx.fragment:fragment-ktx:${Versions.Androidx.FRAGMENT}"

        const val NAVIGATION_FRAGMENT_KOTLIN = "androidx.navigation:navigation-fragment-ktx:${Versions.Androidx.NAVIGATION_FRAGMENT_KOTLIN}"
        const val NAVIGATION_UI_KOTLIN = "androidx.navigation:navigation-ui-ktx:${Versions.Androidx.NAVIGATION_UI_KOTLIN}"

        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.Androidx.CONSTRAINT_LAYOUT}"
        const val SWIPE_REFRESH = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.Androidx.SWIPE_REFRESH}"

        const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versions.Androidx.ROOM_RUNTIME}"
        const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.Androidx.ROOM_COMPILER}"
        const val ROOM_KTX = "androidx.room:room-ktx:${Versions.Androidx.ROOM_KTX}"
    }

    object Material{
        const val MATERIAL = "com.google.android.material:material:${Versions.Material.MATERIAL}"
    }

    object Squareup{
        const val RETROFIT2 = "com.squareup.retrofit2:retrofit:${Versions.Squareup.RETROFIT2}"
        const val CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${Versions.Squareup.CONVERTER_GSON}"
        const val LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.Squareup.LOGGING_INTERCEPTOR}"
        const val OKHTTPS = "com.squareup.okhttp3:okhttp:${Versions.Squareup.OKHTTPS}"
    }

    object Google{
        const val GSON = "com.google.code.gson:gson:${Versions.Google.GSON}"
        const val HILT_ANDROID = "com.google.dagger:hilt-android:${Versions.Google.HILT_ANDROID}"
        const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.Google.HILT_ANDROID_COMPILER}"
        const val FIREBASE_CRASHLYTICS = "com.google.firebase:firebase-crashlytics-ktx:${Versions.Google.FIREBASE_CRASHLYTICS}"
        const val FIREBASE_ANALYTICS = "com.google.firebase:firebase-analytics-ktx:${Versions.Google.FIREBASE_ANALYTICS}"
        const val FIREBASE_CONFIG = "com.google.firebase:firebase-config-ktx:${Versions.Google.FIREBASE_CONFIG}"
    }

    object Airbnb{
        const val LOTTIE = "com.airbnb.android:lottie:${Versions.Airbnb.LOTTIE}"
    }

    object Github{
        const val GLIDE = "com.github.bumptech.glide:glide:${Versions.Github.GLIDE}"
    }

}