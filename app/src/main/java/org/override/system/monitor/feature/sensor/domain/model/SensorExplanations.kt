package org.override.system.monitor.feature.sensor.domain.model

import android.hardware.Sensor
import org.override.system.monitor.R

object SensorExplanations {
    private val explanations = mapOf(
        Sensor.TYPE_MAGNETIC_FIELD to R.string.missing_magnetometer_explanation,
        Sensor.TYPE_PROXIMITY to R.string.missing_proximity_explanation,
        Sensor.TYPE_ROTATION_VECTOR to R.string.missing_rotation_vector_explanation,
        Sensor.TYPE_PRESSURE to R.string.missing_barometer_explanation,
        Sensor.TYPE_AMBIENT_TEMPERATURE to R.string.missing_temperature_explanation,
        Sensor.TYPE_RELATIVE_HUMIDITY to R.string.missing_humidity_explanation,
        Sensor.TYPE_LINEAR_ACCELERATION to R.string.missing_linear_accel_explanation
    )

    fun getExplanationResId(sensorType: Int): Int =
        explanations[sensorType] ?: R.string.sensor_not_available

    fun getSensorNameResId(sensorType: Int): Int = when (sensorType) {
        Sensor.TYPE_MAGNETIC_FIELD -> R.string.magnetometer
        Sensor.TYPE_PROXIMITY -> R.string.proximity
        Sensor.TYPE_ROTATION_VECTOR -> R.string.rotation_vector
        Sensor.TYPE_PRESSURE -> R.string.barometer
        Sensor.TYPE_AMBIENT_TEMPERATURE -> R.string.temperature_label
        Sensor.TYPE_RELATIVE_HUMIDITY -> R.string.humidity
        Sensor.TYPE_LINEAR_ACCELERATION -> R.string.linear_accel
        else -> R.string.sensor_not_available
    }

    val allNewSensorTypes = listOf(
        Sensor.TYPE_MAGNETIC_FIELD,
        Sensor.TYPE_PROXIMITY,
        Sensor.TYPE_ROTATION_VECTOR,
        Sensor.TYPE_PRESSURE,
        Sensor.TYPE_AMBIENT_TEMPERATURE,
        Sensor.TYPE_RELATIVE_HUMIDITY,
        Sensor.TYPE_LINEAR_ACCELERATION
    )
}