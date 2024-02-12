plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "be.marche.apptravaux"
    compileSdk = 34

    defaultConfig {
        applicationId = "be.marche.apptravaux"
        minSdk = 30//v11
        targetSdk = 34
        versionCode = 29
        versionName = "1.4.4"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    //implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    val materialVersion = "1.5.4"
    implementation("androidx.compose.material:material:$materialVersion")
    /**
     * viewmodel in compose
     */
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    /**
     * Using hilt for DI
     */
    //annotationProcessor("com.google.dagger:hilt-compiler:2.50")
    implementation("com.google.dagger:hilt-android:2.50")
    ksp("com.google.dagger:dagger-compiler:2.50") // Dagger compiler
    ksp("com.google.dagger:hilt-compiler:2.50")   // Hilt compiler

    /**
     * My networking stuff cause Retrofit is da best.
     */
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    /**
     * coroutines for async operations
     */
    val lifecycleVersionKt = "2.7.0"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersionKt")

    /**
     * Room
     */
    val roomVersion = "2.6.1"
    implementation("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")

    ksp("androidx.room:room-compiler:$roomVersion")
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$roomVersion")

    /**
     * Navigation
     */
    implementation("androidx.navigation:navigation-compose:2.7.6")
    /**
     * Tools
     */
    implementation("com.github.bumptech.glide:glide:4.16.0")
    //ksp("com.github.bumptech.glide:compiler:4.16.0")
    implementation("io.coil-kt:coil-compose:2.5.0")
    val timberVersion = "5.0.1"
    implementation("com.jakewharton.timber:timber:$timberVersion")
    implementation("androidx.tracing:tracing:1.2.0")
    implementation("androidx.tracing:tracing-ktx:1.2.0")

    /**
     * Firebase
     */
    implementation(platform("com.google.firebase:firebase-bom:32.7.1"))
    implementation("com.google.firebase:firebase-crashlytics-ktx")
    implementation("com.google.firebase:firebase-analytics-ktx")

    /**
     * Permission
     */
    implementation("com.google.accompanist:accompanist-permissions:0.32.0")

    /**
     * Google Map
     */
    implementation("com.google.maps.android:maps-compose:4.3.1")
    implementation("com.google.android.gms:play-services-location:21.1.0")
    // Make sure to also include the latest version of the Maps SDK for Android
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation("androidx.localbroadcastmanager:localbroadcastmanager:1.1.0")
    /**
     * work manager
     */
    val workVersion = "2.9.0"
    implementation("androidx.work:work-runtime:$workVersion")
    implementation("androidx.work:work-runtime-ktx:$workVersion")
    implementation("androidx.hilt:hilt-work:1.1.0")
    ksp("androidx.hilt:hilt-compiler:1.1.0")

    // optional - GCMNetworkManager support
    implementation("androidx.work:work-gcm:$workVersion")
    //app start up
    implementation("androidx.startup:startup-runtime:1.1.1")

    //FlowLayout
    implementation("com.google.accompanist:accompanist-flowlayout:0.32.0")
}