package com.nahob.sevastyan.grocery.assistant.buildlogic

import org.jetbrains.kotlin.gradle.dsl.KotlinCommonCompilerToolOptions
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinMetadataTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinSharedNativeCompilation

internal fun KotlinCommonCompilerToolOptions.enableStrictWarnings() {
    allWarningsAsErrors.set(true)
    extraWarnings.set(true)
}

// Expect/actual classes are Beta and the compiler warns about each one;
// with allWarningsAsErrors that warning would fail the build.
internal fun KotlinCommonCompilerToolOptions.allowExpectActualClasses() {
    freeCompilerArgs.add("-Xexpect-actual-classes")
}

// CMP-8498: Compose Multiplatform ships org.jetbrains.* forks next to
// androidx.* KMP artifacts with the same klib unique_name; the loader
// warning cannot be suppressed on its own (KT-76785).
internal fun KotlinMultiplatformExtension.allowWarningsInSharedNativeMetadata() {
    targets.withType(KotlinMetadataTarget::class.java).configureEach {
        compilations.withType(KotlinSharedNativeCompilation::class.java).configureEach {
            compileTaskProvider.configure {
                compilerOptions.allWarningsAsErrors.set(false)
            }
        }
    }
}
