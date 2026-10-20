package com.nahob.sevastyan.grocery.assistant.buildlogic

import org.jetbrains.kotlin.gradle.dsl.KotlinCommonCompilerToolOptions
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinMetadataTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinSharedNativeCompilation

internal fun KotlinCommonCompilerToolOptions.enableStrictWarnings() {
    allWarningsAsErrors.set(true)
    extraWarnings.set(true)
}

// Workaround: expect/actual classes are Beta and warn under allWarningsAsErrors
internal fun KotlinCommonCompilerToolOptions.allowExpectActualClasses() {
    freeCompilerArgs.add("-Xexpect-actual-classes")
}

// Workaround for CMP-8498 (KT-76785), remove when fixed
internal fun KotlinMultiplatformExtension.allowWarningsInSharedNativeMetadata() {
    targets.withType(KotlinMetadataTarget::class.java).configureEach {
        compilations.withType(KotlinSharedNativeCompilation::class.java).configureEach {
            compileTaskProvider.configure {
                compilerOptions.allWarningsAsErrors.set(false)
            }
        }
    }
}
