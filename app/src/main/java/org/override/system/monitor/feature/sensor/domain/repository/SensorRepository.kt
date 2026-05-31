package org.override.system.monitor.feature.sensor.domain.repository

import android.hardware.Sensor
import kotlinx.coroutines.flow.Flow
import org.override.system.monitor.core.common.model.SensorData

interface SensorRepository {
    fun getSensorData(sensorType: Int): Flow<SensorData>
    fun getAllSensors(): List<Sensor>
    fun isSensorAvailable(sensorType: Int): Boolean
    fun getMissingSensors(requestedSensorTypes: List<Int>): List<Int>
}
