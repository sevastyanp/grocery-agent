import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.nahob.sevastyan.grocery.assistant.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
        allWarningsAsErrors = true
        extraWarnings = true
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.detekt.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "com.nahob.sevastyan.grocery.assistant.android.application"
            implementationClass = "com.nahob.sevastyan.grocery.assistant.buildlogic.android.AndroidApplicationConventionPlugin"
        }
        register("kotlinMultiplatformMobile") {
            id = "com.nahob.sevastyan.grocery.assistant.kotlin.multiplatform.mobile"
            implementationClass = "com.nahob.sevastyan.grocery.assistant.buildlogic.multiplatform.KotlinMultiplatformMobileConventionPlugin"
        }
        register("composeMultiplatform") {
            id = "com.nahob.sevastyan.grocery.assistant.compose.multiplatform"
            implementationClass = "com.nahob.sevastyan.grocery.assistant.buildlogic.multiplatform.ComposeMultiplatformConventionPlugin"
        }
        register("mobileFeature") {
            id = "com.nahob.sevastyan.grocery.assistant.mobile.feature"
            implementationClass = "com.nahob.sevastyan.grocery.assistant.buildlogic.multiplatform.MobileFeatureConventionPlugin"
        }
        register("kotlinMultiplatformCore") {
            id = "com.nahob.sevastyan.grocery.assistant.kotlin.multiplatform.core"
            implementationClass = "com.nahob.sevastyan.grocery.assistant.buildlogic.multiplatform.KotlinMultiplatformCoreConventionPlugin"
        }
        register("kotlinJvmLibrary") {
            id = "com.nahob.sevastyan.grocery.assistant.kotlin.jvm.library"
            implementationClass = "com.nahob.sevastyan.grocery.assistant.buildlogic.jvm.KotlinJvmLibraryConventionPlugin"
        }
        register("kotlinJvmApplication") {
            id = "com.nahob.sevastyan.grocery.assistant.kotlin.jvm.application"
            implementationClass = "com.nahob.sevastyan.grocery.assistant.buildlogic.jvm.KotlinJvmApplicationConventionPlugin"
        }
        register("detekt") {
            id = "com.nahob.sevastyan.grocery.assistant.detekt"
            implementationClass = "com.nahob.sevastyan.grocery.assistant.buildlogic.quality.DetektConventionPlugin"
        }
        register("gitHooks") {
            id = "com.nahob.sevastyan.grocery.assistant.git.hooks"
            implementationClass = "com.nahob.sevastyan.grocery.assistant.buildlogic.git.GitHooksConventionPlugin"
        }
    }
}

tasks.validatePlugins {
    enableStricterValidation = true
    failOnWarning = true
}
