package org.override.system.monitor.feature.memory.presentation

import org.override.system.monitor.feature.memory.domain.model.MemoryData

data class MemoryState(
    val data: MemoryData? = null,
    val isLoading: Boolean = true
)

sealed class MemoryAction {
    data object NavigateBack : MemoryAction()
}