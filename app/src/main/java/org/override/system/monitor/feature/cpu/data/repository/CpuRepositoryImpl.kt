package org.override.system.monitor.feature.cpu.data.repository

import org.override.system.monitor.feature.cpu.data.CpuProvider
import org.override.system.monitor.feature.cpu.domain.CpuData
import org.override.system.monitor.feature.cpu.domain.repository.CpuRepository
import kotlinx.coroutines.flow.Flow

class CpuRepositoryImpl(private val cpuProvider: CpuProvider) : CpuRepository {

    override fun getCpuData(): Flow<CpuData> = cpuProvider.getCpuStats()

    override val cpuCores: Int = cpuProvider.cpuCores

    override val architecture: String = cpuProvider.architecture
}