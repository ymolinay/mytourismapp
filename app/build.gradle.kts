plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.dev.tools.ksp)
    id(libs.plugins.dagger.hilt.android.get().pluginId)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.heyrex.mytourismapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.heyrex.mytourismapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "2.0.5"

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
            buildConfigField("String", "HOST_API", "\"https://4d407a80-2d62-48c2-8936-dc418a4d9d96.mock.pstmn.io\"")
            buildConfigField(
                "okhttp3.logging.HttpLoggingInterceptor.Level",
                "LEVEL_LOGS",
                "okhttp3.logging.HttpLoggingInterceptor.Level.NONE"
            )
        }

        debug {
            isMinifyEnabled = false
            isDebuggable = true
            buildConfigField("String", "HOST_API", "\"https://4d407a80-2d62-48c2-8936-dc418a4d9d96.mock.pstmn.io\"")
            buildConfigField(
                "okhttp3.logging.HttpLoggingInterceptor.Level",
                "LEVEL_LOGS",
                "okhttp3.logging.HttpLoggingInterceptor.Level.BODY"
            )
        }
    }

    flavorDimensions("environment")

    productFlavors {
        create("dev") {
            dimension = "environment"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            resValue("string", "app_name", "MyTourismApp (DEV)")
        }
        create("qa") {
            dimension = "environment"
            applicationIdSuffix = ".qa"
            versionNameSuffix = "-qa"
            resValue("string", "app_name", "MyTourismApp (QA)")
        }
        create("pro") {
            dimension = "environment"
            resValue("string", "app_name", "MyTourismApp")
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

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.bundles.views)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.okhttp)
    implementation(libs.crypto)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.fragment.kotlin)

    // Test mockito
    testImplementation(libs.mockito)
    androidTestImplementation(libs.mockitoAndroid)
    testImplementation(libs.mockitoKotlin)

    // Test MockWebServer
    testImplementation(libs.mockk)
    testImplementation(libs.coroutineTest)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.firestore)
}