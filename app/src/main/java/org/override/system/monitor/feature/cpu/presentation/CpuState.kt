package org.override.system.monitor.feature.cpu.presentation

import org.override.system.monitor.feature.cpu.domain.CpuData

data class CpuState(
    val data: CpuData? = null,
    val isLoading: Boolean = true
)