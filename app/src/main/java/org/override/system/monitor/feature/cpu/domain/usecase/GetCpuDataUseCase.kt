package org.override.system.monitor.feature.cpu.domain.usecase

import org.override.system.monitor.feature.cpu.domain.CpuData
import org.override.system.monitor.feature.cpu.domain.repository.CpuRepository

class GetCpuDataUseCase(private val repository: CpuRepository) {
    operator fun invoke() = repository.getCpuData()
    val cpuCores: Int get() = repository.cpuCores
    val architecture: String get() = repository.architecture
}