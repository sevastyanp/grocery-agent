package com.nahob.sevastyan.grocery.assistant.buildlogic

import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import java.io.File

internal val Project.versionCatalog: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun VersionCatalog.requireVersionAsInt(alias: String): Int =
    findVersion(alias).get().requiredVersion.toInt()

internal fun VersionCatalog.requireLibrary(alias: String): MinimalExternalModuleDependency =
    findLibrary(alias).get().get()

internal fun Project.findGitStagedKotlinFiles(): List<File> =
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
