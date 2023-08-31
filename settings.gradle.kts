@file:Suppress("UnstableApiUsage")

include(":features:sunflower")


pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Doraemon"
include(":app")
include(":context")
include(":base")
include(":uikit")
include(":logger")
include(":accompanist")
include(":features:sunflower")
