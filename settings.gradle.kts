@file:Suppress("UnstableApiUsage")

include(":feature:ftms")



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
include(":feature:compose")
include(":feature:legacy")
include(":arch:sunflower")
include(":arch:playground")
include(":libs:context")
include(":libs:uikit")
include(":libs:logger")
include(":libs:accompanist")
