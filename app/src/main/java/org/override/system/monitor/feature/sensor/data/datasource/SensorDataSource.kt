package org.override.system.monitor.feature.sensor.data.datasource

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.override.system.monitor.core.common.model.SensorData

class SensorDataSource(context: Context) {
    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    fun getSensorFlow(sensorType: Int): Flow<SensorData> = callbackFlow {
        val sensor = sensorManager.getDefaultSensor(sensorType)
        if (sensor == null) {
            close()
            return@callbackFlow
        }

        val listener = object : SensorEventListener {
            private var lastEmitTime = 0L

            override fun onSensorChanged(event: SensorEvent) {
                val currentTime = System.currentTimeMillis()
                if (currentTime - lastEmitTime >= 300L) {
                    lastEmitTime = currentTime
                    val data = when (event.values.size) {
                        1 -> SensorData(value = event.values[0])
                        else -> SensorData(
                            x = event.values.getOrNull(0) ?: 0f,
                            y = event.values.getOrNull(1) ?: 0f,
                            z = event.values.getOrNull(2) ?: 0f
                        )
                    }
                    trySend(data)
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }

        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI)

        awaitClose {
            sensorManager.unregisterListener(listener)
        }
    }

    fun getAllSensors(): List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)

    fun isSensorAvailable(sensorType: Int): Boolean = sensorManager.getDefaultSensor(sensorType) != null

    fun getMissingSensors(requestedSensorTypes: List<Int>): List<Int> =
        requestedSensorTypes.filter { !isSensorAvailable(it) }
}
