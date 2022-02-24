/*
 *    Copyright 2022, Petr Laštovička as Lasta apps, All rights reserved
 *
 *     This file is part of Menza.
 *
 *     Menza is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Menza is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Menza.  If not, see <https://www.gnu.org/licenses/>.
 */

import org.gradle.api.JavaVersion

object Versions {

    val JAVA = JavaVersion.VERSION_11
    const val JVM_TARGET = "11"

    const val GRADLE = "7.1.1"
    const val OSS_PLUGIN = "0.10.4"
    const val OSS_LICENSE_ACCESS = "1.0"

    //Versions
    //Studio
    const val DESUGAR = "1.1.5"

    //JetBrains
    const val KOTLIN = "1.6.10"
    const val KOTLIN_LANGUAGE_VERSION = "1.6"
    const val KSP = "$KOTLIN-1.0.2"
    const val COROUTINES = "1.6.0"
    const val KTOR = "2.0.0-beta-1"

    //androidx
    const val ACTIVITY = "1.4.0"
    const val APPCOMPAT = "1.4.1"
    const val ANNOTATION = "1.3.0"
    const val COLLECTION = "1.2.0"
    const val CONSTRAINTLAYOUT = "2.1.3"
    const val CORE = "1.7.0"
    const val DAGGER_HILT = "2.38.1"
    const val DATASTORE = "1.0.0"
    const val DOCUMENT_FILE = "1.1.0-alpha01"
    const val EMOJI = "1.1.0-rc01"
    const val FRAGMENT = "1.4.0"
    const val HILT_COMMON = "1.0.0"
    const val HILT_COMPILER = "1.0.0"
    const val HILT_NAVIGATION = "1.0.0"
    const val HILT_VIEWMODEL = "1.0.0-alpha03"
    const val HILT_WORK = "1.0.0"
    const val LIFECYCLE = "2.4.1"
    const val NAVIGATION = "2.4.1"
    const val PREFERENCES = "1.2.0-rc01"
    const val RECYCLER_VIEW = "1.3.0-alpha01"
    const val ROOM = "2.4.1"
    const val SPLASHSCREEN = "1.0.0-beta01"
    const val STARTUP = "1.1.1"
    const val SWIPE_REFRESH_LAYOUT = "1.2.0-alpha01"
    const val TRACING = "1.1.0-alpha02"
    const val VECTOR_DRAWABLES = "1.2.0-alpha02"
    const val WINDOW_MANAGER = "1.0.0"
    const val WORK = "2.7.1"


    //compose
    const val COMPOSE = "1.2.0-alpha03"
    const val COMPOSE_COMPILER = COMPOSE
    const val COMPOSE_STABLE = "1.1.0"
    const val COMPOSE_COMPILER_STABLE = COMPOSE_STABLE
    const val COMPOSE_MATERIAL_3 = "1.0.0-alpha05"
    const val CONSTRAINTLAYOUT_COMPOSE = "1.0.0"
    const val VIEWMODEL_COMPOSE = LIFECYCLE
    const val HILT_NAVIGATION_COMPOSE = "1.0.0"

    //google
    const val GOOGLE_MATERIAL = "1.5.0"
    const val OSS_LICENSE = "17.0.0"
    const val PLAY_SERVICES = "1.8.1"
    const val ACCOMPANIST = "0.24.2-alpha"

    //firebase
    const val FIREBASE_BOM = "29.0.4"

    //others
    const val KOTEST = "5.1.0"
    const val KOTLINX_DATETIME = "0.3.1"
    const val SQLDELIGHT = "1.5.3"
    const val KM_LOGGING = "1.1.1"
    const val COIL = "2.0.0-alpha09"
    const val SKRAPE_IT = "1.2.0"
    const val ABOUT_LIBRARIES = "10.0.0-rc01"

    //testing android
    const val TEST_JUNIT = "4.13.2"
    const val TEST_ARCH = "2.1.0"
    const val TEST_ANDROIDX = "1.4.0"
    const val TEST_ANDROIDX_JUNIT = "1.1.3"
    const val TEST_KOTLIN_COROUTINES = COROUTINES
    const val TEST_ROBOELECTRIC = "4.6.1"
    const val TEST_GOOGLE_TRUTH = "1.1.3"
    const val TEST_ESPRESSO_CORE = "3.4.0"

}
