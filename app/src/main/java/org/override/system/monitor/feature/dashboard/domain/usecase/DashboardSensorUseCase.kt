package org.override.system.monitor.feature.dashboard.domain.usecase

import android.hardware.Sensor
import kotlinx.coroutines.flow.Flow
import org.override.system.monitor.core.common.model.SensorData
import org.override.system.monitor.feature.sensor.domain.model.MissingSensorInfo
import org.override.system.monitor.feature.sensor.domain.model.SensorExplanations
import org.override.system.monitor.feature.sensor.domain.repository.SensorRepository

class DashboardSensorUseCase(private val repository: SensorRepository) {
    fun getAccelerometerFlow(): Flow<SensorData> = repository.getSensorData(Sensor.TYPE_ACCELEROMETER)
    fun getGyroscopeFlow(): Flow<SensorData> = repository.getSensorData(Sensor.TYPE_GYROSCOPE)
    fun getLightFlow(): Flow<SensorData> = repository.getSensorData(Sensor.TYPE_LIGHT)
    fun getMagnetometerFlow(): Flow<SensorData> = repository.getSensorData(Sensor.TYPE_MAGNETIC_FIELD)
    fun getProximityFlow(): Flow<SensorData> = repository.getSensorData(Sensor.TYPE_PROXIMITY)
    fun getRotationVectorFlow(): Flow<SensorData> = repository.getSensorData(Sensor.TYPE_ROTATION_VECTOR)
    fun getBarometerFlow(): Flow<SensorData> = repository.getSensorData(Sensor.TYPE_PRESSURE)
    fun getAmbientTemperatureFlow(): Flow<SensorData> = repository.getSensorData(Sensor.TYPE_AMBIENT_TEMPERATURE)
    fun getHumidityFlow(): Flow<SensorData> = repository.getSensorData(Sensor.TYPE_RELATIVE_HUMIDITY)
    fun getLinearAccelerationFlow(): Flow<SensorData> = repository.getSensorData(Sensor.TYPE_LINEAR_ACCELERATION)

    fun getMissingSensorsInfo(): List<MissingSensorInfo> {
        val missingTypes = repository.getMissingSensors(SensorExplanations.allNewSensorTypes)
        return missingTypes.map { sensorType ->
            MissingSensorInfo(
                sensorType = sensorType,
                sensorNameResId = SensorExplanations.getSensorNameResId(sensorType),
                explanationResId = SensorExplanations.getExplanationResId(sensorType)
            )
        }
    }
}