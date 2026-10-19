package com.nahob.sevastyan.grocery.assistant.buildlogic

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

internal fun DependencyHandler.detektPlugins(dependency: Any): Dependency? =
    add("detektPlugins", dependency)
