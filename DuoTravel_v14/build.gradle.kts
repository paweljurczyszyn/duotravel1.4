import com.android.build.gradle.internal.dsl.decorator.SupportedPropertyType.Collection.List.type

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.3" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
    id ("com.google.devtools.ksp") version "1.9.20-1.0.14" apply false
    id ("org.jetbrains.kotlin.jvm") version "1.9.20" apply false
}

// dla dziennika podrozy

buildscript {

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:7.5.1")
        classpath(kotlin("gradle-plugin", version = "1.9.20"))
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin")
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.5")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}




