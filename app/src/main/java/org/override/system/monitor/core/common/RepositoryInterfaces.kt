package org.override.system.monitor.core.common

import kotlinx.coroutines.flow.Flow

interface FlowRepository<T> {
    val data: Flow<T>
}

interface SensorRepository {
    fun getSensorFlow(sensorType: Int): Flow<org.override.system.monitor.core.common.model.SensorData>
}

interface SensorDataSource {
    fun getSensorFlow(sensorType: Int): Flow<org.override.system.monitor.core.common.model.SensorData>
}