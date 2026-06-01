package org.override.system.monitor.feature.cpu.domain

data class CpuData(
    val architecture: String,
    val cores: Int,
    val coreFrequencies: List<Long> // Frecuencias actuales en MHz
)