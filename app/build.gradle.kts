plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "ir.hajkarami.moviepaginationlibraryjava"
    compileSdk = 35

    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        applicationId = "ir.hajkarami.moviepaginationlibraryjava"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Retrofit 2.9.0
//    val retrofitVersions = "2.11.0"
//    implementation("com.squareup.retrofit2:retrofit:$retrofitVersionstVersions")
//    implementation("com.google.code.gson:gson:$retrofitVersions")
//    implementation("com.squareup.retrofit2:converter-gson::$retrofitVersions")
//    // RxJava3 with retrofit
//    implementation("com.squareup.retrofit2:adapter-rxjava3:$retrofitVersions")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0")
    implementation("io.reactivex.rxjava3:rxjava:3.1.5")
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")
    implementation("com.squareup.okhttp3:okhttp:4.9.3")

    // paging Library
    val pagingVersions = "3.1.1"
    implementation("androidx.paging:paging-runtime:$pagingVersions")
    // optional - RxJava3 support
    implementation("androidx.paging:paging-rxjava3:$pagingVersions")

    // Hilt Dagger
    implementation("com.google.dagger:hilt-android:2.51.1")
    annotationProcessor("com.google.dagger:hilt-compiler:2.51.1")

    // Glide
    implementation("com.github.bumptech.glide:glide:4.13.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.13.0")

    // LIfeCycle
    val lifecycleVersion = "2.5.0-alpha04"

    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel:$lifecycleVersion")
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")


}