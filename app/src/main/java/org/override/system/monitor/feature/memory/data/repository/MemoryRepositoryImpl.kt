package org.override.system.monitor.feature.memory.data.repository

import kotlinx.coroutines.flow.Flow
import org.override.system.monitor.feature.memory.data.datasource.MemoryDataSource
import org.override.system.monitor.feature.memory.domain.model.MemoryData
import org.override.system.monitor.feature.memory.domain.repository.MemoryRepository

class MemoryRepositoryImpl(private val dataSource: MemoryDataSource) : MemoryRepository {
    override val memoryData: Flow<MemoryData> = dataSource.getMemoryData()
}