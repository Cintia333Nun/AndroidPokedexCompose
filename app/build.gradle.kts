import org.jetbrains.kotlin.gradle.plugin.extraProperties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    //id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.androidpokedexcompose"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.androidpokedexcompose"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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

val versions = mapOf(
    "roomVersion" to "2.6.1",
    "dagger" to "2.50",
    "hilt" to "1.1.0",
    "composeBom" to "2023.08.00",
)
extra["versions"] = versions

dependencies {
    val roomVersion: String by versions
    val dagger: String by versions
    val hilt: String by versions
    val composeBom: String by versions
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.paging:paging-common-ktx:3.2.1")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
    implementation(platform("androidx.compose:compose-bom:$composeBom"))
    implementation(platform("androidx.compose:compose-bom:$composeBom"))
    implementation("com.airbnb.android:lottie-compose:4.0.0")
    implementation("io.coil-kt:coil-compose:2.5.0")

    // Retrofit
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // ROOM
    implementation ("androidx.room:room-runtime:$roomVersion")
    implementation ("androidx.room:room-ktx:$roomVersion")
    implementation ("androidx.room:room-paging:$roomVersion")
    ksp ("androidx.room:room-compiler:$roomVersion")

    //COIL
    implementation("io.coil-kt:coil-compose:2.5.0")

    //NAVIGATION
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // HILT
    /*implementation ("com.google.dagger:hilt-android:$dagger")
    ksp ("com.google.dagger:hilt-compiler:$dagger")
    implementation ("androidx.hilt:hilt-work:$hilt")
    ksp ("androidx.hilt:hilt-compiler:$hilt") */

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:$composeBom"))
    androidTestImplementation(platform("androidx.compose:compose-bom:$composeBom"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}