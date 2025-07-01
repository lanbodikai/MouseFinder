plugins {
    id("com.android.application")
    // Google Services plugin removed to avoid requiring google-services.json
}

android {
    namespace = "com.example.mousefinder"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.mousefinder"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.gms:play-services-auth:20.6.0")
}