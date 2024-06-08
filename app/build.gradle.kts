plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id ("kotlin-parcelize")
    id ("kotlin-kapt")
}

android {
    namespace = "com.dicoding.githubuser"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dicoding.githubuser"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        
        buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
        buildConfigField("String", "API_KEY", "\"token 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\"")

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation (libs.androidx.legacy.support.v4)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.activity.ktx)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.coil)
    implementation (libs.gson)
    implementation (libs.androidx.fragment.ktx)
    implementation (libs.lifecycle.viewmodel.compose)
    implementation (libs.lifecycle.common.java8)
    implementation (libs.lifecycle.viewmodel.savedstate)
    implementation (libs.androidx.room.runtime)
    implementation (libs.room.rxjava3)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.database.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation (libs.circleimageview)
    implementation(libs.glide)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
    implementation (libs.androidx.datastore.preferences.core)
    implementation (libs.datastore.preferences)
    implementation (libs.androidx.room.runtime)
    kapt (libs.androidx.room.compiler.v242)
    implementation (libs.room.rxjava3)
}