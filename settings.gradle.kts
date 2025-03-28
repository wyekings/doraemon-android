@file:Suppress("UnstableApiUsage")



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
include(":base")
include(":app")
include(":common")
include(":feature:component")
include(":feature:compose")
include(":feature:legacy")
include(":feature:ftms")
include(":libs:context")
include(":libs:uikit")
include(":libs:logger")
include(":libs:accompanist")

