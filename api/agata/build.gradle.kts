/*
 *    Copyright 2024, Petr Laštovička as Lasta apps, All rights reserved
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

plugins {
    alias(libs.plugins.lastaapps.kmp.library)
    alias(libs.plugins.lastaapps.kmp.sqldelight)
}

android {
    namespace = "cz.lastaapps.api.agata"
}

dependencies {
    commonMainImplementation(projects.core)
    commonMainImplementation(projects.api.core)

    // skrape-it
    // androidMainImplementation(projects.htmlParser)
    androidMainImplementation("it.skrape:skrapeit:1.2.2")
    // fix security vulnerabilities in skrapeit libs
    androidMainImplementation("ch.qos.logback:logback-core:1.4.12")
    androidMainImplementation("ch.qos.logback:logback-classic:1.4.12")
    androidMainImplementation("commons-net:commons-net:3.9.0")
    androidMainImplementation("org.apache.commons:commons-text:1.10.0")
    androidMainImplementation("org.jsoup:jsoup:1.15.3")
    androidMainImplementation("xalan:xalan:2.7.3")

    commonMainImplementation(libs.ktor.client.core)
    commonMainImplementation(libs.ktor.client.contentNegotiation)
    commonMainImplementation(libs.ktor.client.logging)
    commonMainImplementation(libs.ktor.client.serialization)

    commonMainImplementation(libs.bundles.russhwolf.settings)
}

sqldelight {
    databases {
        create("AgataDatabase") {
            packageName.set("cz.lastaapps.api.agata")
            schemaOutputDirectory.set(file("src/main/sqldelight/databases"))
            verifyMigrations.set(true)
        }
    }
}
