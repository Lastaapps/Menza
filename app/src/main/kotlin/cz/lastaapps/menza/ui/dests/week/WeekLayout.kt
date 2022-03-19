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

package cz.lastaapps.menza.ui.dests.week

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import cz.lastaapps.entity.menza.MenzaId
import cz.lastaapps.menza.ui.LocalWindowWidth
import cz.lastaapps.menza.ui.WindowSizeClass
import cz.lastaapps.menza.ui.layout.menza.MenzaViewModel
import cz.lastaapps.menza.ui.root.AppLayoutCompact
import cz.lastaapps.menza.ui.root.AppLayoutExpandedSimple
import cz.lastaapps.menza.ui.root.AppLayoutMedium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeekLayout(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    drawerState: DrawerState,
    menzaId: MenzaId?,
    onMenzaSelected: (MenzaId?) -> Unit,
    menzaViewModel: MenzaViewModel,
    weekViewModel: WeekViewModel,
) {
    Crossfade(targetState = menzaId) { currentMenzaId ->
        when (LocalWindowWidth.current) {
            WindowSizeClass.COMPACT ->
                WeekLayoutCompact(
                    navController = navController,
                    snackbarHostState = snackbarHostState,
                    drawerState = drawerState,
                    menzaId = currentMenzaId,
                    onMenzaSelected = onMenzaSelected,
                    menzaViewModel = menzaViewModel,
                    viewModel = weekViewModel,
                )
            WindowSizeClass.MEDIUM ->
                WeekLayoutMedium(
                    navController = navController,
                    snackbarHostState = snackbarHostState,
                    drawerState = drawerState,
                    menzaId = currentMenzaId,
                    onMenzaSelected = onMenzaSelected,
                    menzaViewModel = menzaViewModel,
                    viewModel = weekViewModel,
                )
            WindowSizeClass.EXPANDED ->
                WeekLayoutExpanded(
                    navController = navController,
                    snackbarHostState = snackbarHostState,
                    drawerState = drawerState,
                    menzaId = currentMenzaId,
                    onMenzaSelected = onMenzaSelected,
                    menzaViewModel = menzaViewModel,
                    viewModel = weekViewModel,
                )

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WeekLayoutCompact(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    drawerState: DrawerState,
    menzaId: MenzaId?,
    onMenzaSelected: (MenzaId?) -> Unit,
    menzaViewModel: MenzaViewModel,
    viewModel: WeekViewModel,
) {
    AppLayoutCompact(
        navController = navController,
        menzaId = menzaId,
        onMenzaSelected = onMenzaSelected,
        menzaViewModel = menzaViewModel,
        snackbarHostState = snackbarHostState,
        drawerState = drawerState,
        showBackArrow = false,
    ) {
        WeekDishList(
            navController = navController,
            menzaId = menzaId,
            viewModel = viewModel,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WeekLayoutMedium(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    drawerState: DrawerState,
    menzaId: MenzaId?,
    onMenzaSelected: (MenzaId?) -> Unit,
    menzaViewModel: MenzaViewModel,
    viewModel: WeekViewModel,
) {
    AppLayoutMedium(
        navController = navController,
        menzaId = menzaId,
        onMenzaSelected = onMenzaSelected,
        menzaViewModel = menzaViewModel,
        snackbarHostState = snackbarHostState,
        drawerState = drawerState,
        showBackArrow = false
    ) {
        WeekDishList(
            navController = navController,
            menzaId = menzaId,
            viewModel = viewModel,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WeekLayoutExpanded(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    drawerState: DrawerState,
    menzaId: MenzaId?,
    onMenzaSelected: (MenzaId?) -> Unit,
    menzaViewModel: MenzaViewModel,
    viewModel: WeekViewModel,
) {
    AppLayoutExpandedSimple(
        navController = navController,
        menzaId = menzaId,
        onMenzaSelected = onMenzaSelected,
        menzaViewModel = menzaViewModel,
        snackbarHostState = snackbarHostState,
        drawerState = drawerState,
        showBackArrow = false
    ) {
        WeekDishList(
            navController = navController,
            menzaId = menzaId,
            viewModel = viewModel,
            modifier = Modifier.fillMaxSize(),
        )
    }
}






