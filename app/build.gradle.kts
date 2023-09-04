plugins {
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("dagger.hilt.android.plugin")
}

android {
    namespace = "com.hasitha.daggethilttest2"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.hasitha.daggethilttest2"
        minSdk = 21
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"
    }
    buildFeatures{
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation ("androidx.core:core-ktx:1.10.1")
    implementation ("androidx.appcompat:appcompat:1.6.1")

    implementation ("com.google.android.material:material:1.9.0")

    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")

    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")

    //Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")


    //  ———  gson ——— ++
    implementation ("com.google.code.gson:gson:2.9.0")

    //  ———  retrofit ——— ++
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    //  ———  coroutines ——— ++
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")

    // Glide
    implementation ("com.github.bumptech.glide:glide:4.13.2")
    //annotationProcessor ("com.github.bumptech.glide:compiler:4.13.2")
    kapt ("com.github.bumptech.glide:compiler:4.13.2")

    // lifecycle & ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    // Room
//    implementation ("androidx.room:room-runtime:2.3.0")
//    kapt ("androidx.room:room-compiler:2.3.0")
//    implementation ("androidx.room:room-runtime:2.5.2")
    implementation ("androidx.room:room-ktx:2.5.2")
//    kapt ("androidx.room:room-compiler:2.5.2")
    val room_version = "2.5.2"

    implementation("androidx.room:room-runtime:$room_version")
//    annotationProcessor("androidx.room:room-compiler:$room_version")
    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:$room_version")


    // ViewModel and LiveData
//    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1'
//    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.3.1'
//    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.3.1'

    // Coroutines
    //implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.1'
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}