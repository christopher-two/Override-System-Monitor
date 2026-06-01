package org.override.system.monitor.feature.sensor.domain.model

import android.hardware.Sensor
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SensorExplanationsTest {

    @Test
    fun `getExplanationResId returns correct resource for MAGNETIC_FIELD`() {
        val result = SensorExplanations.getExplanationResId(Sensor.TYPE_MAGNETIC_FIELD)
        assertEquals(org.override.system.monitor.R.string.missing_magnetometer_explanation, result)
    }

    @Test
    fun `getExplanationResId returns correct resource for PROXIMITY`() {
        val result = SensorExplanations.getExplanationResId(Sensor.TYPE_PROXIMITY)
        assertEquals(org.override.system.monitor.R.string.missing_proximity_explanation, result)
    }

    @Test
    fun `getExplanationResId returns correct resource for ROTATION_VECTOR`() {
        val result = SensorExplanations.getExplanationResId(Sensor.TYPE_ROTATION_VECTOR)
        assertEquals(org.override.system.monitor.R.string.missing_rotation_vector_explanation, result)
    }

    @Test
    fun `getExplanationResId returns correct resource for PRESSURE`() {
        val result = SensorExplanations.getExplanationResId(Sensor.TYPE_PRESSURE)
        assertEquals(org.override.system.monitor.R.string.missing_barometer_explanation, result)
    }

    @Test
    fun `getExplanationResId returns correct resource for AMBIENT_TEMPERATURE`() {
        val result = SensorExplanations.getExplanationResId(Sensor.TYPE_AMBIENT_TEMPERATURE)
        assertEquals(org.override.system.monitor.R.string.missing_temperature_explanation, result)
    }

    @Test
    fun `getExplanationResId returns correct resource for RELATIVE_HUMIDITY`() {
        val result = SensorExplanations.getExplanationResId(Sensor.TYPE_RELATIVE_HUMIDITY)
        assertEquals(org.override.system.monitor.R.string.missing_humidity_explanation, result)
    }

    @Test
    fun `getExplanationResId returns correct resource for LINEAR_ACCELERATION`() {
        val result = SensorExplanations.getExplanationResId(Sensor.TYPE_LINEAR_ACCELERATION)
        assertEquals(org.override.system.monitor.R.string.missing_linear_accel_explanation, result)
    }

    @Test
    fun `getExplanationResId returns sensor_not_available for unknown sensor`() {
        val result = SensorExplanations.getExplanationResId(Sensor.TYPE_ACCELEROMETER)
        assertEquals(org.override.system.monitor.R.string.sensor_not_available, result)
    }

    @Test
    fun `getSensorNameResId returns correct resource for MAGNETIC_FIELD`() {
        val result = SensorExplanations.getSensorNameResId(Sensor.TYPE_MAGNETIC_FIELD)
        assertEquals(org.override.system.monitor.R.string.magnetometer, result)
    }

    @Test
    fun `getSensorNameResId returns correct resource for PROXIMITY`() {
        val result = SensorExplanations.getSensorNameResId(Sensor.TYPE_PROXIMITY)
        assertEquals(org.override.system.monitor.R.string.proximity, result)
    }

    @Test
    fun `getSensorNameResId returns correct resource for ROTATION_VECTOR`() {
        val result = SensorExplanations.getSensorNameResId(Sensor.TYPE_ROTATION_VECTOR)
        assertEquals(org.override.system.monitor.R.string.rotation_vector, result)
    }

    @Test
    fun `getSensorNameResId returns correct resource for PRESSURE`() {
        val result = SensorExplanations.getSensorNameResId(Sensor.TYPE_PRESSURE)
        assertEquals(org.override.system.monitor.R.string.barometer, result)
    }

    @Test
    fun `getSensorNameResId returns correct resource for AMBIENT_TEMPERATURE`() {
        val result = SensorExplanations.getSensorNameResId(Sensor.TYPE_AMBIENT_TEMPERATURE)
        assertEquals(org.override.system.monitor.R.string.temperature_label, result)
    }

    @Test
    fun `getSensorNameResId returns correct resource for RELATIVE_HUMIDITY`() {
        val result = SensorExplanations.getSensorNameResId(Sensor.TYPE_RELATIVE_HUMIDITY)
        assertEquals(org.override.system.monitor.R.string.humidity, result)
    }

    @Test
    fun `getSensorNameResId returns correct resource for LINEAR_ACCELERATION`() {
        val result = SensorExplanations.getSensorNameResId(Sensor.TYPE_LINEAR_ACCELERATION)
        assertEquals(org.override.system.monitor.R.string.linear_accel, result)
    }

    @Test
    fun `getSensorNameResId returns sensor_not_available for unknown sensor`() {
        val result = SensorExplanations.getSensorNameResId(Sensor.TYPE_ACCELEROMETER)
        assertEquals(org.override.system.monitor.R.string.sensor_not_available, result)
    }

    @Test
    fun `allNewSensorTypes contains expected sensor types`() {
        val expectedTypes = listOf(
            Sensor.TYPE_MAGNETIC_FIELD,
            Sensor.TYPE_PROXIMITY,
            Sensor.TYPE_ROTATION_VECTOR,
            Sensor.TYPE_PRESSURE,
            Sensor.TYPE_AMBIENT_TEMPERATURE,
            Sensor.TYPE_RELATIVE_HUMIDITY,
            Sensor.TYPE_LINEAR_ACCELERATION
        )
        assertEquals(7, SensorExplanations.allNewSensorTypes.size)
        expectedTypes.forEach { type ->
            assertTrue(SensorExplanations.allNewSensorTypes.contains(type))
        }
    }
}