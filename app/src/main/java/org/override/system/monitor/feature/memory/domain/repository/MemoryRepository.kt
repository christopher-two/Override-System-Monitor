package org.override.system.monitor.feature.memory.domain.repository

import kotlinx.coroutines.flow.Flow
import org.override.system.monitor.feature.memory.domain.model.MemoryData

interface MemoryRepository {
    val memoryData: Flow<MemoryData>
}