plugins {
    id("com.nahob.sevastyan.grocery.assistant.android.application")
}

android {
    namespace = "com.nahob.sevastyan.grocery.assistant"

    defaultConfig {
        applicationId = "com.nahob.sevastyan.grocery.assistant"
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
}

dependencies {
    implementation(projects.mobile.shared)

    implementation(libs.androidx.activity.compose)
}
