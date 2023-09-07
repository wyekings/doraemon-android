@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("doraemon.android.library")
    id("doraemon.android.library.compose")
    id("doraemon.android.hilt")
}

android {
    namespace = "com.wyekings.composable"
}

dependencies {

    implementation(project(":base"))
    implementation(project(":common"))

    // compose
    implementation(libs.activity.ktx)
//    implementation(libs.activity.compose)
    implementation(libs.material3)
    implementation(libs.ui.tooling.preview)

    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    implementation(libs.navigation.compose)

    implementation(libs.coil.kt.compose)
    implementation(libs.coil.kt.gif)
    implementation(libs.coil.kt.gif)
    implementation(libs.landscapist.coil)

//    implementation(libs.core.ktx)
//    implementation(libs.appcompat)
//    implementation(libs.material)

    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}