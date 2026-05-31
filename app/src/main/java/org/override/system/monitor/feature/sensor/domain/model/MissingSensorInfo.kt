package org.override.system.monitor.feature.sensor.domain.model

import android.hardware.Sensor

data class MissingSensorInfo(
    val sensorType: Int,
    val sensorName: String,
    val explanation: String
)

object SensorExplanations {
    private val explanations = mapOf(
        Sensor.TYPE_MAGNETIC_FIELD to "El magnetómetro requiere hardware dedicado (brújula). Muchos dispositivos modernos de gama media/alta no lo incluyen para reducir costos o espacio interno.",
        Sensor.TYPE_PROXIMITY to "El sensor de proximidad usa un LED infrarrojo y detector. Algunos dispositivos usan soluciones alternativas (software) o no lo incluyen.",
        Sensor.TYPE_ROTATION_VECTOR to "El vector de rotación combina acelerómetro, giroscopio y magnetómetro. Si alguno falta, este sensor no estará disponible.",
        Sensor.TYPE_PRESSURE to "El barómetro (sensor de presión) es raro en smartphones. Se encuentra principalmente en dispositivos de gama alta o tablets diseñadas para uso profesional.",
        Sensor.TYPE_AMBIENT_TEMPERATURE to "Este sensor mide la temperatura ambiental real del entorno. La mayoría de smartphones no lo incluyen porque usan sensores de temperatura de batería/procesador.",
        Sensor.TYPE_RELATIVE_HUMIDITY to "El sensor de humedad relativa generalmente solo está en dispositivos rugged (resistentes) o de gama alta con enfoque en salud/aventura.",
        Sensor.TYPE_STEP_COUNTER to "El contador de pasos requiere hardware especializado o el permiso ACTIVITY_RECOGNITION. Algunos dispositivos no lo soportan o lo deshabilitan.",
        Sensor.TYPE_LINEAR_ACCELERATION to "Similar al acelerómetro pero sin gravedad. Si el acelerómetro base no está disponible, este tampoco lo estará."
    )

    fun getExplanation(sensorType: Int): String =
        explanations[sensorType] ?: "Este sensor no está disponible en tu dispositivo."

    fun getSensorName(sensorType: Int): String = when (sensorType) {
        Sensor.TYPE_MAGNETIC_FIELD -> "Magnetómetro"
        Sensor.TYPE_PROXIMITY -> "Proximidad"
        Sensor.TYPE_ROTATION_VECTOR -> "Vector de Rotación"
        Sensor.TYPE_PRESSURE -> "Barómetro"
        Sensor.TYPE_AMBIENT_TEMPERATURE -> "Temperatura Ambiental"
        Sensor.TYPE_RELATIVE_HUMIDITY -> "Humedad"
        Sensor.TYPE_STEP_COUNTER -> "Contador de Pasos"
        Sensor.TYPE_LINEAR_ACCELERATION -> "Aceleración Lineal"
        else -> "Sensor Desconocido"
    }

    val allNewSensorTypes = listOf(
        Sensor.TYPE_MAGNETIC_FIELD,
        Sensor.TYPE_PROXIMITY,
        Sensor.TYPE_ROTATION_VECTOR,
        Sensor.TYPE_PRESSURE,
        Sensor.TYPE_AMBIENT_TEMPERATURE,
        Sensor.TYPE_RELATIVE_HUMIDITY,
        Sensor.TYPE_STEP_COUNTER,
        Sensor.TYPE_LINEAR_ACCELERATION
    )
}
