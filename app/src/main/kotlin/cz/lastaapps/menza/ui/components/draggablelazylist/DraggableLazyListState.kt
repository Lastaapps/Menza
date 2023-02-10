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

package cz.lastaapps.menza.ui.components.draggablelazylist

import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import kotlinx.coroutines.Job

@Composable
fun rememberDraggableLazyListState(
    lazyListState: LazyListState = rememberLazyListState(),
    onMove: (Int, Int) -> Unit,
    onMoveFinished: () -> Unit,
    reverse: Boolean = false,
): DraggableLazyListState =
    remember(lazyListState, reverse) {
        DraggableLazyListState(
            lazyListState = lazyListState,
            onMove = onMove,
            onMoveFinished = onMoveFinished,
            reverse = reverse,
        )
    }

class DraggableLazyListState(
    val lazyListState: LazyListState,
    private val onMove: (Int, Int) -> Unit,
    private val onMoveFinished: () -> Unit,
    private val reverse: Boolean = false,
) {
    var draggedDistance by mutableStateOf(0f)

    // used to obtain initial offsets on drag start
    var initiallyDraggedElement by mutableStateOf<LazyListItemInfo?>(null)

    var currentIndexOfDraggedItem by mutableStateOf<Int?>(null)
    var lastIndexOfDraggedItem by mutableStateOf<Int?>(null)

    val initialOffsets: Pair<Int, Int>?
        get() = initiallyDraggedElement?.let { Pair(it.offset, it.offsetEnd) }

    val elementDisplacement: Float?
        get() = currentElement
            ?.let { item ->
                (initiallyDraggedElement?.offset ?: 0f).toFloat() + draggedDistance - item.offset
            }?.let {
                if (reverse) -1 * it else it
            }

    private val currentElement: LazyListItemInfo?
        get() = currentIndexOfDraggedItem?.let {
            lazyListState.getVisibleItemInfoFor(absoluteIndex = it)
        }

    var overscrollJob by mutableStateOf<Job?>(null)

    fun onDragStart(offset: Offset) {
        val relativeYOffset =
            if (reverse) (lazyListState.layoutInfo.viewportEndOffset - offset.y) else offset.y

        lazyListState.layoutInfo.visibleItemsInfo
            .firstOrNull { item -> relativeYOffset.toInt() in item.offset..(item.offset + item.size) }
            ?.also {
                currentIndexOfDraggedItem = it.index
                lastIndexOfDraggedItem = currentIndexOfDraggedItem
                initiallyDraggedElement = it
            }
    }

    fun onDragEnd() {
        onDragInterrupted()
    }

    fun onDragInterrupted() {
        draggedDistance = 0f
        currentIndexOfDraggedItem = null
        initiallyDraggedElement = null
        overscrollJob?.cancel()
        onMoveFinished()
    }

    fun onDrag(offset: Offset) {
        draggedDistance += offset.y * if (reverse) -1 else 1

        initialOffsets?.let { (topOffset, bottomOffset) ->
            val startOffset = topOffset + draggedDistance
            val endOffset = bottomOffset + draggedDistance

            currentElement?.let { hovered ->
                lazyListState.layoutInfo.visibleItemsInfo
                    .filterNot { item -> item.offsetEnd < startOffset || item.offset > endOffset || hovered.index == item.index }
                    .firstOrNull { item ->
                        val delta = startOffset - hovered.offset
                        when {
                            delta > 0 -> (endOffset > item.offsetEnd)
                            else -> (startOffset < item.offset)
                        }
                    }
                    ?.also { item ->
                        currentIndexOfDraggedItem?.let { current ->
                            onMove.invoke(
                                current,
                                item.index
                            )
                        }
                        currentIndexOfDraggedItem = item.index
                        lastIndexOfDraggedItem = currentIndexOfDraggedItem
                    }
            }
        }
    }

    fun checkForOverScroll(): Float {
        return initiallyDraggedElement?.let {
            val startOffset = it.offset + draggedDistance
            val endOffset = it.offsetEnd + draggedDistance

            val betterScrollRation = .05f
            val total = with(lazyListState.layoutInfo) { viewportEndOffset - viewportStartOffset }
            val scrollOffset = betterScrollRation * total

            return@let when {
                draggedDistance > 0 -> (endOffset - lazyListState.layoutInfo.viewportEndOffset + scrollOffset).takeIf { diff -> diff > 0 }
                draggedDistance < 0 -> (startOffset - lazyListState.layoutInfo.viewportStartOffset - scrollOffset).takeIf { diff -> diff < 0 }
                else -> null
            }
        } ?: 0f
    }
}