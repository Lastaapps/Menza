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

package cz.lastaapps.entity.allergens

import io.kotest.matchers.string.shouldNotBeBlank

/**
 * Holds info about one type of allergen
 * https://agata.suz.cvut.cz/jidelnicky/alergenyall.php
 */
data class Allergen(
    val id: AllergenId,
    val name: String,
    val description: String
) : Comparable<Allergen> {

    init {
        name.shouldNotBeBlank()
        description.shouldNotBeBlank()
    }

    override fun compareTo(other: Allergen): Int {
        return id.id.compareTo(other.id.id)
    }
}