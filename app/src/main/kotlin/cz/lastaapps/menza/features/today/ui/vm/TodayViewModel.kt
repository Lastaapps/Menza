/*
 *    Copyright 2023, Petr Laštovička as Lasta apps, All rights reserved
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

package cz.lastaapps.menza.features.today.ui.vm

import cz.lastaapps.api.core.domain.model.common.Dish
import cz.lastaapps.api.core.domain.model.common.Menza
import cz.lastaapps.core.ui.vm.Appearing
import cz.lastaapps.core.ui.vm.StateViewModel
import cz.lastaapps.core.ui.vm.VMContext
import cz.lastaapps.menza.features.main.domain.usecase.GetSelectedMenzaUC
import cz.lastaapps.menza.features.settings.domain.model.ShowCzech
import cz.lastaapps.menza.features.settings.domain.usecase.GetShowCzechUC
import kotlinx.coroutines.flow.onEach

internal class TodayViewModel(
    context: VMContext,
    private val getSelectedMenza: GetSelectedMenzaUC,
    private val getShowCzech: GetShowCzechUC,
) : StateViewModel<TodayState>(TodayState(), context), Appearing {
    override var hasAppeared: Boolean = false

    override fun onAppeared() = launch {
        getSelectedMenza().onEach {
            updateState {
                copy(
                    selectedMenza = it,
                    selectedDish = null,
                )
            }
        }.launchInVM()

        getShowCzech().onEach {
            updateState { copy(showCzech = it) }
        }.launchInVM()
    }

    fun selectDish(dish: Dish?) =
        updateState { copy(selectedDish = dish) }
}

internal data class TodayState(
    val selectedMenza: Menza? = null,
    val selectedDish: Dish? = null,
    val showCzech: ShowCzech = ShowCzech(true),
) {
    val hasDish: Boolean get() = selectedDish != null
}
