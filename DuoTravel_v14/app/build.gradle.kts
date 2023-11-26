plugins {
    id("com.android.application")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("kotlin-parcelize")                                  // dla
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")// dziennika
                                                            // podrozy
}

android {
    namespace = "pl.duotravel.duotravel_v14"
    compileSdk = 34

    defaultConfig {
        applicationId = "pl.duotravel.duotravel_v14"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        kapt {
            correctErrorTypes = true
        }

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures{                            // dodaję binding żeby edytować tekst z xml w kotlinie
        viewBinding = true
    }

}


dependencies {




    implementation ("com.google.devtools.ksp:symbol-processing-api:1.9.20-1.0.14")




    implementation ("pub.devrel:easypermissions:3.0.0") // biblioteka EasyPermissions żeby apka pytała o dostęp do pamięci
    implementation("androidx.fragment:fragment-ktx:1.6.2")           // potrzebne żeby budować fragmenty tj mini-aktywności
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")  // implementacja dla map google
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //wszystko od tego moementu wrzucone dla dziennika prodrozy


    // Architectural Components
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    ksp("androidx.lifecycle:lifecycle-compiler:2.6.2")
    ksp("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.2")

    // Room
    implementation("androidx.room:room-runtime:2.6.0")
    implementation("androidx.room:room-ktx:2.6.0")
    ksp("androidx.room:room-compiler:2.6.0")
    ksp("androidx.room:room-compiler-processing:2.6.0")


    // Navigation Components
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:2.7.5")

}