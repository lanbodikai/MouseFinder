plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.mousefinder"
    compileSdk = 34                      // ← bump this to 34

    defaultConfig {
        applicationId = "com.example.mousefinder"
        minSdk = 24
        targetSdk = 34                   // ← optionally bump targetSdk too
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
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation("com.google.android.material:material:1.8.0")
}
