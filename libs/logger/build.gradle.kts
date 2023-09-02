@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("doraemon.android.library")
}

android {
    namespace = "com.wyekings.logger"
}

dependencies {
    implementation(libs.startup)
    api(libs.timber)
//    api(libs.stram.log.android)
//    debugImplementation(libs.stram.log.android.file)

    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}