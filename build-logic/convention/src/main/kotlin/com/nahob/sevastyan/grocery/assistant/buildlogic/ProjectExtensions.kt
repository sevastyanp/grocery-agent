package com.nahob.sevastyan.grocery.assistant.buildlogic

import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask
import java.io.File

private const val INSTALL_GIT_HOOKS_PATH = ":installGitHooks"

internal val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun VersionCatalog.versionInt(alias: String): Int =
    findVersion(alias).get().requiredVersion.toInt()

internal fun VersionCatalog.library(alias: String): MinimalExternalModuleDependency =
    findLibrary(alias).get().get()

// Builds from the command line install the hooks too, not only IDE sync
internal fun Project.installGitHooksBeforeKotlinCompilation() {
    tasks.withType(KotlinCompilationTask::class.java).configureEach {
        dependsOn(INSTALL_GIT_HOOKS_PATH)
    }
}

internal fun Project.gitStagedKotlinFiles(): List<File> =
    providers.exec {
        commandLine("git", "--no-pager", "diff", "--name-only", "--cached", "--diff-filter=ACMR")
    }.standardOutput.asText.get()
        .lineSequence()
        .map(String::trim)
        .filter { filePath ->
            filePath.endsWith(".kt") || filePath.endsWith(".kts")
        }
        .map { filePath ->
            layout.settingsDirectory.file(filePath).asFile
        }
        .toList()
