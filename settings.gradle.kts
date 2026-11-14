pluginManagement {
    includeBuild("build-logic")

    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "grocery-assistant"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(":mobile:androidApp")
include(":mobile:shared")
include(":mobile:core:network")
include(":mobile:core:notifications")
include(":mobile:feature:sessions")
include(":mobile:feature:memory")
include(":mobile:feature:settings")
include(":core")
include(":server:app")
include(":server:mcp")
