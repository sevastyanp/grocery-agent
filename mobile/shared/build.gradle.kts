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
}
