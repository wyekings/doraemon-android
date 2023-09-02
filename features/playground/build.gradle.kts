@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("doraemon.android.library")
    id("doraemon.android.library.compose")
}

android {
    namespace = "com.wyekings.playground"
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.core.ktx)
    implementation(libs.activity.ktx)
    implementation(libs.fragment.ktx)
    implementation(libs.material)

    // compose
    implementation(libs.activity.compose)
    implementation(libs.material3)
    implementation(libs.ui.tooling.preview)
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    implementation(libs.coil.kt.compose)
    implementation(libs.coil.kt.gif)
    implementation(libs.coil.kt.gif)
    implementation(libs.landscapist.coil)

    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}