plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    // Hilt
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.armhansa.neversituptesting"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.armhansa.neversituptesting"
        minSdk = 28
        targetSdk = 34
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // View
    val kotlinXVersion = "1.13.1"
    val appCompatVersion = "1.6.1"
    val materialVersion = "1.12.0"
    implementation("androidx.core:core-ktx:$kotlinXVersion")
    implementation("androidx.appcompat:appcompat:$appCompatVersion")
    implementation("com.google.android.material:material:$materialVersion")

    // Hilt DI
    val hiltVersion = "2.51.1"
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")

    // RxAndroid
    val rxAndroidVersion = "3.0.2"
    val rxJavaVersion = "3.1.8"
    implementation("io.reactivex.rxjava3:rxandroid:$rxAndroidVersion")
    // Because RxAndroid releases are few and far between, it is recommended you also
    // explicitly depend on RxJava's latest version for bug fixes and new features.
    // (see https://github.com/ReactiveX/RxJava/releases for latest 3.x.x version)
    implementation("io.reactivex.rxjava3:rxjava:$rxJavaVersion")

    // Retrofit with rxJava adapter
    val retrofitVersion = "2.9.0"
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:adapter-rxjava3:$retrofitVersion")

    // Testing
    val jUnitVersion = "4.13.2"
    val jUnitExtVersion = "1.1.5"
    val espressoVersion = "3.5.1"
    testImplementation("junit:junit:$jUnitVersion")
    androidTestImplementation("androidx.test.ext:junit:$jUnitExtVersion")
    androidTestImplementation("androidx.test.espresso:espresso-core:$espressoVersion")
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}