@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("doraemon.android.library")
    id("doraemon.android.hilt")
}

android {
    namespace = "com.wyekings.components"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":base"))
    implementation(project(":common"))
    implementation(project(":libs:uikit"))
    implementation(project(":libs:logger"))

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity.ktx)
    implementation(libs.fragment.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.constraintlayout)

    implementation(libs.view.binding)

    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}