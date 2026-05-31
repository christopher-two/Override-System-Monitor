package org.override.system.monitor.feature.sensor.data.repository

import android.hardware.Sensor
import kotlinx.coroutines.flow.Flow
import org.override.system.monitor.core.common.model.SensorData
import org.override.system.monitor.feature.sensor.data.datasource.SensorDataSource
import org.override.system.monitor.feature.sensor.domain.repository.SensorRepository

class SensorRepositoryImpl(private val dataSource: SensorDataSource) : SensorRepository {
    override fun getSensorData(sensorType: Int): Flow<SensorData> = dataSource.getSensorFlow(sensorType)
    override fun getAllSensors(): List<Sensor> = dataSource.getAllSensors()
    override fun isSensorAvailable(sensorType: Int): Boolean = dataSource.isSensorAvailable(sensorType)
    override fun getMissingSensors(requestedSensorTypes: List<Int>): List<Int> = dataSource.getMissingSensors(requestedSensorTypes)
}
