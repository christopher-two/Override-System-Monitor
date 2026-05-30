package org.override.system.monitor.feature.sensor.domain.model

import android.hardware.Sensor

data class SensorInfo(
    val sensor: Sensor,
    val sensors: List<Sensor> = emptyList()
)