package org.override.system.monitor.feature.cpu.presentation

sealed class CpuAction {
    data object NavigateBack : CpuAction()
}