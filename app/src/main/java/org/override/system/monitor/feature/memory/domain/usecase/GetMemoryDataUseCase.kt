package org.override.system.monitor.feature.memory.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.override.system.monitor.feature.memory.domain.model.MemoryData
import org.override.system.monitor.feature.memory.domain.repository.MemoryRepository

class GetMemoryDataUseCase(private val repository: MemoryRepository) {
    operator fun invoke(): Flow<MemoryData> = repository.memoryData
}