package org.override.system.monitor.feature.dashboard.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.override.system.monitor.R
import org.override.system.monitor.core.ui.Destination
import org.override.system.monitor.feature.dashboard.presentation.DashboardState

@Composable
fun TabletDashboardContent(
    state: DashboardState,
    onNavigate: (Destination) -> Unit,
    onSensorClick: (SensorDetail) -> Unit,
    onNetworkClick: () -> Unit
) {
    val sensorDetails = rememberSensorDetails(state)

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

        item {
            NetworkCard(data = state.networkData, onClick = onNetworkClick)
        }

        item(span = { GridItemSpan(2) }) {
            LiveSensorsHeader()
        }

        item {
            AccelerometerCard(
                data = sensorDetails.accelerometerData,
                expanded = false,
                onClick = { onSensorClick(sensorDetails.accelerometerDetail) }
            )
        }

        item {
            GyroscopeCard(
                data = sensorDetails.gyroscopeData,
                expanded = false,
                onClick = { onSensorClick(sensorDetails.gyroscopeDetail) }
            )
        }

        if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_MAGNETIC_FIELD }) {
            item {
                MagnetometerCard(
                    data = sensorDetails.magnetometerData,
                    expanded = false,
                    onClick = { onSensorClick(sensorDetails.magnetometerDetail) }
                )
            }
        }

        if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_PROXIMITY }) {
            item {
                ProximityCard(
                    data = sensorDetails.proximityData,
                    expanded = false,
                    onClick = { onSensorClick(sensorDetails.proximityDetail) }
                )
            }
        }

        if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_ROTATION_VECTOR }) {
            item {
                RotationVectorCard(
                    data = sensorDetails.rotationVectorData,
                    expanded = false,
                    onClick = { onSensorClick(sensorDetails.rotationVectorDetail) }
                )
            }
        }

        if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_PRESSURE }) {
            item {
                BarometerCard(
                    data = sensorDetails.barometerData,
                    expanded = false,
                    onClick = { onSensorClick(sensorDetails.barometerDetail) }
                )
            }
        }

        if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_AMBIENT_TEMPERATURE }) {
            item {
                AmbientTemperatureCard(
                    data = sensorDetails.temperatureData,
                    expanded = false,
                    onClick = { onSensorClick(sensorDetails.temperatureDetail) }
                )
            }
        }

        if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_RELATIVE_HUMIDITY }) {
            item {
                HumidityCard(
                    data = sensorDetails.humidityData,
                    expanded = false,
                    onClick = { onSensorClick(sensorDetails.humidityDetail) }
                )
            }
        }

        if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_LINEAR_ACCELERATION }) {
            item {
                LinearAccelerationCard(
                    data = sensorDetails.linearAccelData,
                    expanded = false,
                    onClick = { onSensorClick(sensorDetails.linearAccelDetail) }
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
    onSensorClick: (SensorDetail) -> Unit,
    onNetworkClick: () -> Unit
) {
    val sensorDetails = rememberSensorDetails(state)

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
            NetworkCard(data = state.networkData, onClick = onNetworkClick)
        }

        item {
            LiveSensorsHeader()
        }

        item {
            AccelerometerCard(
                data = sensorDetails.accelerometerData,
                expanded = true,
                onClick = { onSensorClick(sensorDetails.accelerometerDetail) }
            )
        }

        item {
            GyroscopeCard(
                data = sensorDetails.gyroscopeData,
                expanded = true,
                onClick = { onSensorClick(sensorDetails.gyroscopeDetail) }
            )
        }

        if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_MAGNETIC_FIELD }) {
            item {
                MagnetometerCard(
                    data = sensorDetails.magnetometerData,
                    expanded = true,
                    onClick = { onSensorClick(sensorDetails.magnetometerDetail) }
                )
            }
        }

        if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_PROXIMITY }) {
            item {
                ProximityCard(
                    data = sensorDetails.proximityData,
                    expanded = true,
                    onClick = { onSensorClick(sensorDetails.proximityDetail) }
                )
            }
        }

        if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_ROTATION_VECTOR }) {
            item {
                RotationVectorCard(
                    data = sensorDetails.rotationVectorData,
                    expanded = true,
                    onClick = { onSensorClick(sensorDetails.rotationVectorDetail) }
                )
            }
        }

        if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_PRESSURE }) {
            item {
                BarometerCard(
                    data = sensorDetails.barometerData,
                    expanded = true,
                    onClick = { onSensorClick(sensorDetails.barometerDetail) }
                )
            }
        }

        if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_AMBIENT_TEMPERATURE }) {
            item {
                AmbientTemperatureCard(
                    data = sensorDetails.temperatureData,
                    expanded = true,
                    onClick = { onSensorClick(sensorDetails.temperatureDetail) }
                )
            }
        }

        if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_RELATIVE_HUMIDITY }) {
            item {
                HumidityCard(
                    data = sensorDetails.humidityData,
                    expanded = true,
                    onClick = { onSensorClick(sensorDetails.humidityDetail) }
                )
            }
        }

        if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_LINEAR_ACCELERATION }) {
            item {
                LinearAccelerationCard(
                    data = sensorDetails.linearAccelData,
                    expanded = true,
                    onClick = { onSensorClick(sensorDetails.linearAccelDetail) }
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
            text = stringResource(R.string.live_sensors),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

private data class SensorDetailsBundle(
    val accelerometerData: SensorData?,
    val accelerometerDetail: SensorDetail,
    val gyroscopeData: SensorData?,
    val gyroscopeDetail: SensorDetail,
    val magnetometerData: SensorData?,
    val magnetometerDetail: SensorDetail,
    val proximityData: SensorData?,
    val proximityDetail: SensorDetail,
    val rotationVectorData: SensorData?,
    val rotationVectorDetail: SensorDetail,
    val barometerData: SensorData?,
    val barometerDetail: SensorDetail,
    val temperatureData: SensorData?,
    val temperatureDetail: SensorDetail,
    val humidityData: SensorData?,
    val humidityDetail: SensorDetail,
    val linearAccelData: SensorData?,
    val linearAccelDetail: SensorDetail
)

@Composable
private fun rememberSensorDetails(state: DashboardState): SensorDetailsBundle {
    return SensorDetailsBundle(
        accelerometerData = state.accelerometerData?.let { SensorData(it.x, it.y, it.z) },
        accelerometerDetail = getAccelerometerSensorDetail(state.accelerometerData?.let { SensorData(it.x, it.y, it.z) }),
        gyroscopeData = state.gyroscopeData?.let { SensorData(it.x, it.y, it.z) },
        gyroscopeDetail = getGyroscopeSensorDetail(state.gyroscopeData?.let { SensorData(it.x, it.y, it.z) }),
        magnetometerData = state.magnetometerData?.let { SensorData(it.x, it.y, it.z) },
        magnetometerDetail = getMagnetometerSensorDetail(state.magnetometerData?.let { SensorData(it.x, it.y, it.z) }),
        proximityData = state.proximityData?.let { SensorData(0f, 0f, it.value) },
        proximityDetail = getProximitySensorDetail(state.proximityData?.let { SensorData(0f, 0f, it.value) }),
        rotationVectorData = state.rotationVectorData?.let { SensorData(it.x, it.y, it.z) },
        rotationVectorDetail = getRotationVectorSensorDetail(state.rotationVectorData?.let { SensorData(it.x, it.y, it.z) }),
        barometerData = state.barometerData?.let { SensorData(0f, 0f, it.value) },
        barometerDetail = getBarometerSensorDetail(state.barometerData?.let { SensorData(0f, 0f, it.value) }),
        temperatureData = state.ambientTemperatureData?.let { SensorData(0f, 0f, it.value) },
        temperatureDetail = getAmbientTemperatureSensorDetail(state.ambientTemperatureData?.let { SensorData(0f, 0f, it.value) }),
        humidityData = state.humidityData?.let { SensorData(0f, 0f, it.value) },
        humidityDetail = getHumiditySensorDetail(state.humidityData?.let { SensorData(0f, 0f, it.value) }),
        linearAccelData = state.linearAccelerationData?.let { SensorData(it.x, it.y, it.z) },
        linearAccelDetail = getLinearAccelerationSensorDetail(state.linearAccelerationData?.let { SensorData(it.x, it.y, it.z) })
    )
}
