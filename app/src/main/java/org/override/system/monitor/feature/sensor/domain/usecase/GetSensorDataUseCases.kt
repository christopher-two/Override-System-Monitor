package org.override.system.monitor.feature.sensor.domain.usecase

import android.hardware.Sensor
import kotlinx.coroutines.flow.Flow
import org.override.system.monitor.core.common.model.SensorData
import org.override.system.monitor.feature.sensor.domain.repository.SensorRepository

class GetAccelerometerDataUseCase(private val repository: SensorRepository) {
    operator fun invoke(): Flow<SensorData> = repository.getSensorData(Sensor.TYPE_ACCELEROMETER)
}

class GetGyroscopeDataUseCase(private val repository: SensorRepository) {
    operator fun invoke(): Flow<SensorData> = repository.getSensorData(Sensor.TYPE_GYROSCOPE)
}

class GetLightDataUseCase(private val repository: SensorRepository) {
    operator fun invoke(): Flow<SensorData> = repository.getSensorData(Sensor.TYPE_LIGHT)
}