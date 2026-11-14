import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("com.nahob.sevastyan.grocery.assistant.compose.multiplatform")
}

kotlin {
    targets.withType<KotlinNativeTarget>().configureEach {
        binaries.framework {
            baseName = "Shared"
            isStatic = true
            binaryOption("bundleId", "com.nahob.sevastyan.grocery.assistant.mobile.shared")
        }
    }

    android {
        namespace = "com.nahob.sevastyan.grocery.assistant.mobile.shared"
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.mobile.core.network)
            implementation(projects.mobile.core.notifications)
            implementation(projects.mobile.feature.sessions)
            implementation(projects.mobile.feature.memory)
            implementation(projects.mobile.feature.settings)
        }
    }
}
