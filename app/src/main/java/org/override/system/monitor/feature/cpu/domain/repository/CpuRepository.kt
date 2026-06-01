package org.override.system.monitor.feature.cpu.domain.repository

import kotlinx.coroutines.flow.Flow
import org.override.system.monitor.feature.cpu.domain.CpuData

interface CpuRepository {
    fun getCpuData(): Flow<CpuData>
    val cpuCores: Int
    val architecture: String
}