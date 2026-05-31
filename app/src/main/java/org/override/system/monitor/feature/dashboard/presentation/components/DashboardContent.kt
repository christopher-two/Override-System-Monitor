package org.override.system.monitor.feature.dashboard.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Sensors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.override.system.monitor.core.ui.Destination
import org.override.system.monitor.feature.dashboard.presentation.DashboardState

@Composable
fun TabletDashboardContent(
    state: DashboardState,
    onNavigate: (Destination) -> Unit,
    onSensorClick: (SensorDetail) -> Unit
) {
    val accelerometerDetail = getAccelerometerSensorDetail(state.accelerometerData?.let { SensorData(it.x, it.y, it.z) })
    val gyroscopeDetail = getGyroscopeSensorDetail(state.gyroscopeData?.let { SensorData(it.x, it.y, it.z) })
    val magnetometerDetail = getMagnetometerSensorDetail(state.magnetometerData?.let { SensorData(it.x, it.y, it.z) })
    val proximityDetail = getProximitySensorDetail(state.proximityData?.let { SensorData(0f, 0f, it.value) })
    val rotationVectorDetail = getRotationVectorSensorDetail(state.rotationVectorData?.let { SensorData(it.x, it.y, it.z) })
    val barometerDetail = getBarometerSensorDetail(state.barometerData?.let { SensorData(0f, 0f, it.value) })
    val temperatureDetail = getAmbientTemperatureSensorDetail(state.ambientTemperatureData?.let { SensorData(0f, 0f, it.value) })
    val humidityDetail = getHumiditySensorDetail(state.humidityData?.let { SensorData(0f, 0f, it.value) })
    val linearAccelDetail = getLinearAccelerationSensorDetail(state.linearAccelerationData?.let { SensorData(it.x, it.y, it.z) })

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item(span = { GridItemSpan(2) }) {
            state.batteryData?.let { battery ->
                BatteryCard(
                    percentage = battery.percentage,
                    status = battery.status,
                    health = battery.health,
                    temperature = battery.temperature,
                    onClick = { onNavigate(Destination.BatteryDetail) }
                )
            }
        }

        item {
            state.memoryData?.let { ram ->
                MemoryCard(
                    usedMemory = ram.usedMemory,
                    totalMemory = ram.totalMemory,
                    percentageUsed = ram.percentageUsed,
                    onClick = { onNavigate(Destination.MemoryDetail) }
                )
            }
        }

        item {
            state.storageData?.let { storage ->
                StorageCard(
                    usedStorage = storage.usedStorage,
                    totalStorage = storage.totalStorage,
                    percentageUsed = storage.percentageUsed,
                    onClick = { onNavigate(Destination.StorageDetail) }
                )
            }
        }

        item(span = { GridItemSpan(2) }) {
            LiveSensorsHeader()
        }

        item {
            AccelerometerCard(
                data = state.accelerometerData?.let { SensorData(it.x, it.y, it.z) },
                expanded = false,
                onClick = { onSensorClick(accelerometerDetail) }
            )
        }

        item {
            GyroscopeCard(
                data = state.gyroscopeData?.let { SensorData(it.x, it.y, it.z) },
                expanded = false,
                onClick = { onSensorClick(gyroscopeDetail) }
            )
        }

        if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_MAGNETIC_FIELD }) {
            item {
                MagnetometerCard(
                    data = state.magnetometerData?.let { SensorData(it.x, it.y, it.z) },
                    expanded = false,
                    onClick = { onSensorClick(magnetometerDetail) }
                )
            }
        }

        if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_PROXIMITY }) {
            item {
                ProximityCard(
                    data = state.proximityData?.let { SensorData(0f, 0f, it.value) },
                    expanded = false,
                    onClick = { onSensorClick(proximityDetail) }
                )
            }
        }

        if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_ROTATION_VECTOR }) {
            item {
                RotationVectorCard(
                    data = state.rotationVectorData?.let { SensorData(it.x, it.y, it.z) },
                    expanded = false,
                    onClick = { onSensorClick(rotationVectorDetail) }
                )
            }
        }

        if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_PRESSURE }) {
            item {
                BarometerCard(
                    data = state.barometerData?.let { SensorData(0f, 0f, it.value) },
                    expanded = false,
                    onClick = { onSensorClick(barometerDetail) }
                )
            }
        }

        if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_AMBIENT_TEMPERATURE }) {
            item {
                AmbientTemperatureCard(
                    data = state.ambientTemperatureData?.let { SensorData(0f, 0f, it.value) },
                    expanded = false,
                    onClick = { onSensorClick(temperatureDetail) }
                )
            }
        }

        if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_RELATIVE_HUMIDITY }) {
            item {
                HumidityCard(
                    data = state.humidityData?.let { SensorData(0f, 0f, it.value) },
                    expanded = false,
                    onClick = { onSensorClick(humidityDetail) }
                )
            }
        }

        if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_LINEAR_ACCELERATION }) {
            item {
                LinearAccelerationCard(
                    data = state.linearAccelerationData?.let { SensorData(it.x, it.y, it.z) },
                    expanded = false,
                    onClick = { onSensorClick(linearAccelDetail) }
                )
            }
        }

        item(span = { GridItemSpan(2) }) {
            state.systemIdentityData?.let { sys ->
                DeviceCard(
                    manufacturer = sys.manufacturer,
                    model = sys.model,
                    osVersion = sys.osVersion,
                    apiLevel = sys.apiLevel,
                    uptime = sys.uptime,
                    lightValue = state.lightData?.value,
                    onClick = { onNavigate(Destination.SensorDetail) }
                )
            }
        }
    }
}

@Composable
fun MobileDashboardContent(
    state: DashboardState,
    onNavigate: (Destination) -> Unit,
    onSensorClick: (SensorDetail) -> Unit
) {
    val accelerometerDetail = getAccelerometerSensorDetail(state.accelerometerData?.let { SensorData(it.x, it.y, it.z) })
    val gyroscopeDetail = getGyroscopeSensorDetail(state.gyroscopeData?.let { SensorData(it.x, it.y, it.z) })
    val magnetometerDetail = getMagnetometerSensorDetail(state.magnetometerData?.let { SensorData(it.x, it.y, it.z) })
    val proximityDetail = getProximitySensorDetail(state.proximityData?.let { SensorData(0f, 0f, it.value) })
    val rotationVectorDetail = getRotationVectorSensorDetail(state.rotationVectorData?.let { SensorData(it.x, it.y, it.z) })
    val barometerDetail = getBarometerSensorDetail(state.barometerData?.let { SensorData(0f, 0f, it.value) })
    val temperatureDetail = getAmbientTemperatureSensorDetail(state.ambientTemperatureData?.let { SensorData(0f, 0f, it.value) })
    val humidityDetail = getHumiditySensorDetail(state.humidityData?.let { SensorData(0f, 0f, it.value) })
    val linearAccelDetail = getLinearAccelerationSensorDetail(state.linearAccelerationData?.let { SensorData(it.x, it.y, it.z) })

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item {
            state.batteryData?.let { battery ->
                BatteryCard(
                    percentage = battery.percentage,
                    status = battery.status,
                    health = battery.health,
                    temperature = battery.temperature,
                    onClick = { onNavigate(Destination.BatteryDetail) }
                )
            }
        }

        item {
            state.memoryData?.let { ram ->
                MemoryCard(
                    usedMemory = ram.usedMemory,
                    totalMemory = ram.totalMemory,
                    percentageUsed = ram.percentageUsed,
                    onClick = { onNavigate(Destination.MemoryDetail) }
                )
            }
        }

        item {
            state.storageData?.let { storage ->
                StorageCard(
                    usedStorage = storage.usedStorage,
                    totalStorage = storage.totalStorage,
                    percentageUsed = storage.percentageUsed,
                    onClick = { onNavigate(Destination.StorageDetail) }
                )
            }
        }

        item {
            LiveSensorsHeader()
        }

        item {
            AccelerometerCard(
                data = state.accelerometerData?.let { SensorData(it.x, it.y, it.z) },
                expanded = true,
                onClick = { onSensorClick(accelerometerDetail) }
            )
        }

        item {
            GyroscopeCard(
                data = state.gyroscopeData?.let { SensorData(it.x, it.y, it.z) },
                expanded = true,
                onClick = { onSensorClick(gyroscopeDetail) }
            )
        }

        if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_MAGNETIC_FIELD }) {
            item {
                MagnetometerCard(
                    data = state.magnetometerData?.let { SensorData(it.x, it.y, it.z) },
                    expanded = true,
                    onClick = { onSensorClick(magnetometerDetail) }
                )
            }
        }

        if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_PROXIMITY }) {
            item {
                ProximityCard(
                    data = state.proximityData?.let { SensorData(0f, 0f, it.value) },
                    expanded = true,
                    onClick = { onSensorClick(proximityDetail) }
                )
            }
        }

        if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_ROTATION_VECTOR }) {
            item {
                RotationVectorCard(
                    data = state.rotationVectorData?.let { SensorData(it.x, it.y, it.z) },
                    expanded = true,
                    onClick = { onSensorClick(rotationVectorDetail) }
                )
            }
        }

        if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_PRESSURE }) {
            item {
                BarometerCard(
                    data = state.barometerData?.let { SensorData(0f, 0f, it.value) },
                    expanded = true,
                    onClick = { onSensorClick(barometerDetail) }
                )
            }
        }

        if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_AMBIENT_TEMPERATURE }) {
            item {
                AmbientTemperatureCard(
                    data = state.ambientTemperatureData?.let { SensorData(0f, 0f, it.value) },
                    expanded = true,
                    onClick = { onSensorClick(temperatureDetail) }
                )
            }
        }

        if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_RELATIVE_HUMIDITY }) {
            item {
                HumidityCard(
                    data = state.humidityData?.let { SensorData(0f, 0f, it.value) },
                    expanded = true,
                    onClick = { onSensorClick(humidityDetail) }
                )
            }
        }

        if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_LINEAR_ACCELERATION }) {
            item {
                LinearAccelerationCard(
                    data = state.linearAccelerationData?.let { SensorData(it.x, it.y, it.z) },
                    expanded = true,
                    onClick = { onSensorClick(linearAccelDetail) }
                )
            }
        }

        item {
            state.systemIdentityData?.let { sys ->
                DeviceCard(
                    manufacturer = sys.manufacturer,
                    model = sys.model,
                    osVersion = sys.osVersion,
                    apiLevel = sys.apiLevel,
                    uptime = sys.uptime,
                    lightValue = state.lightData?.value,
                    onClick = { onNavigate(Destination.SensorDetail) }
                )
            }
        }
    }
}

@Composable
private fun LiveSensorsHeader() {
    Row(
        modifier = Modifier.padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Rounded.Sensors,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Live Sensors",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
