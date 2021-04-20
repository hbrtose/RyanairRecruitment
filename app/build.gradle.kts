plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(Dependencies.Apps.compileSdk)
    buildToolsVersion(Dependencies.Apps.buildTools)

    defaultConfig {
        applicationId = "com.ryanair.hosewski"
        minSdkVersion(Dependencies.Apps.minSdk)
        targetSdkVersion(Dependencies.Apps.targetSdk)
        versionCode = Dependencies.Apps.versionCode
        versionName = Dependencies.Apps.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
	implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Dependencies.Libs.kotlin)
    implementation(Dependencies.Libs.coreKtx)
    implementation(Dependencies.Libs.appcompat)
    implementation(Dependencies.Libs.fragment)
    implementation(Dependencies.Libs.constraintLayout)
    implementation(Dependencies.Libs.hilt)
    implementation(Dependencies.Libs.hiltViewModel)
    implementation(Dependencies.Libs.material)
    implementation(Dependencies.Libs.navigationFragment)
    implementation(Dependencies.Libs.navigationUi)


    implementation(Dependencies.Libs.retrofit)
    implementation(Dependencies.Libs.retrofitMoshiConverter)
    implementation(Dependencies.Libs.retrofitRxJava2Adapter)
    implementation(Dependencies.Libs.moshiAdapters)
    implementation(Dependencies.Libs.loggingInterceptor)

    kapt(Dependencies.Libs.androidxHiltCompiler)
    kapt(Dependencies.Libs.hiltCompiler)

    testImplementation(Dependencies.TestLibs.junit)

    androidTestImplementation(Dependencies.AndroidTestLibs.junit)
    androidTestImplementation(Dependencies.AndroidTestLibs.espressoCore)
}