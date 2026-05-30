package org.override.system.monitor.core.ui

import androidx.navigation3.runtime.NavKey

sealed interface Destination : NavKey {
    data object Dashboard : Destination
    data object BatteryDetail : Destination
    data object MemoryDetail : Destination
    data object StorageDetail : Destination
    data object SensorDetail : Destination
    data object Settings : Destination
}