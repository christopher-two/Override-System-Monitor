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

// Position& Orientation Sensors
class GetMagnetometerDataUseCase(private val repository: SensorRepository) {
    operator fun invoke(): Flow<SensorData> = repository.getSensorData(Sensor.TYPE_MAGNETIC_FIELD)
}

class GetProximityDataUseCase(private val repository: SensorRepository) {
    operator fun invoke(): Flow<SensorData> = repository.getSensorData(Sensor.TYPE_PROXIMITY)
}

class GetRotationVectorDataUseCase(private val repository: SensorRepository) {
    operator fun invoke(): Flow<SensorData> = repository.getSensorData(Sensor.TYPE_ROTATION_VECTOR)
}

// Environmental Sensors
class GetBarometerDataUseCase(private val repository: SensorRepository) {
    operator fun invoke(): Flow<SensorData> = repository.getSensorData(Sensor.TYPE_PRESSURE)
}

class GetAmbientTemperatureDataUseCase(private val repository: SensorRepository) {
    operator fun invoke(): Flow<SensorData> = repository.getSensorData(Sensor.TYPE_AMBIENT_TEMPERATURE)
}

class GetHumidityDataUseCase(private val repository: SensorRepository) {
    operator fun invoke(): Flow<SensorData> = repository.getSensorData(Sensor.TYPE_RELATIVE_HUMIDITY)
}

// Motion & Health Sensors
class GetStepCounterDataUseCase(private val repository: SensorRepository) {
    operator fun invoke(): Flow<SensorData> = repository.getSensorData(Sensor.TYPE_STEP_COUNTER)
}

class GetLinearAccelerationDataUseCase(private val repository: SensorRepository) {
    operator fun invoke(): Flow<SensorData> = repository.getSensorData(Sensor.TYPE_LINEAR_ACCELERATION)
}

class GetMissingSensorsUseCase(private val repository: SensorRepository) {
    operator fun invoke(requestedSensorTypes: List<Int>): List<Int> = repository.getMissingSensors(requestedSensorTypes)
}