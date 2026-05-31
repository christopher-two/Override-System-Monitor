package org.override.system.monitor.feature.dashboard.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Sensors
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.override.system.monitor.core.ui.Destination
import org.override.system.monitor.feature.dashboard.presentation.components.AccelerometerCard
import org.override.system.monitor.feature.dashboard.presentation.components.AmbientTemperatureCard
import org.override.system.monitor.feature.dashboard.presentation.components.BarometerCard
import org.override.system.monitor.feature.dashboard.presentation.components.BatteryCard
import org.override.system.monitor.feature.dashboard.presentation.components.DeviceCard
import org.override.system.monitor.feature.dashboard.presentation.components.GyroscopeCard
import org.override.system.monitor.feature.dashboard.presentation.components.HumidityCard
import org.override.system.monitor.feature.dashboard.presentation.components.LinearAccelerationCard
import org.override.system.monitor.feature.dashboard.presentation.components.MagnetometerCard
import org.override.system.monitor.feature.dashboard.presentation.components.MemoryCard
import org.override.system.monitor.feature.dashboard.presentation.components.MissingSensorsBottomSheet
import org.override.system.monitor.feature.dashboard.presentation.components.ProximityCard
import org.override.system.monitor.feature.dashboard.presentation.components.RotationVectorCard
import org.override.system.monitor.feature.dashboard.presentation.components.SensorData
import org.override.system.monitor.feature.dashboard.presentation.components.StepCounterCard
import org.override.system.monitor.feature.dashboard.presentation.components.StorageCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(viewModel: DashboardViewModel) {
    val state by viewModel.state.collectAsState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    var showMissingSensorsSheet by remember { mutableStateOf(false) }

    if (showMissingSensorsSheet) {
        MissingSensorsBottomSheet(
            missingSensors = state.missingSensors,
            onDismiss = { showMissingSensorsSheet = false }
        )
    }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "System Monitor",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                actions = {
                    if (state.missingSensors.isNotEmpty()) {
                        FilledTonalIconButton(
                            onClick = { showMissingSensorsSheet = true }
                        ) {
                            Icon(
                                Icons.Rounded.Warning,
                                contentDescription = "Missing Sensors",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                    FilledTonalIconButton(
                        onClick = { viewModel.processAction(DashboardAction.Navigate(Destination.Settings)) }
                    ) {
                        Icon(Icons.Rounded.Settings, contentDescription = "Settings")
                    }
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.surface,
        contentWindowInsets = WindowInsets.systemBars
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
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
                        onClick = { viewModel.processAction(DashboardAction.Navigate(Destination.BatteryDetail)) }
                    )
                }
            }

            item {
                state.memoryData?.let { ram ->
                    MemoryCard(
                        usedMemory = ram.usedMemory,
                        totalMemory = ram.totalMemory,
                        percentageUsed = ram.percentageUsed,
                        onClick = { viewModel.processAction(DashboardAction.Navigate(Destination.MemoryDetail)) }
                    )
                }
            }

            item {
                state.storageData?.let { storage ->
                    StorageCard(
                        usedStorage = storage.usedStorage,
                        totalStorage = storage.totalStorage,
                        percentageUsed = storage.percentageUsed,
                        onClick = { viewModel.processAction(DashboardAction.Navigate(Destination.StorageDetail)) }
                    )
                }
            }

            item(span = { GridItemSpan(2) }) {
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

            // Existing sensors (always show)
            item {
                AccelerometerCard(
                    data = state.accelerometerData?.let { SensorData(it.x, it.y, it.z) }
                )
            }

            item {
                GyroscopeCard(
                    data = state.gyroscopeData?.let { SensorData(it.x, it.y, it.z) }
                )
            }

            // Position& Orientation sensors - only show if NOT missing
            if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_MAGNETIC_FIELD }) {
                item {
                    MagnetometerCard(
                        data = state.magnetometerData?.let { SensorData(it.x, it.y, it.z) }
                    )
                }
            }

            if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_PROXIMITY }) {
                item {
                    ProximityCard(
                        data = state.proximityData?.let { SensorData(0f, 0f, it.value) }
                    )
                }
            }

            if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_ROTATION_VECTOR }) {
                item {
                    RotationVectorCard(
                        data = state.rotationVectorData?.let { SensorData(it.x, it.y, it.z) }
                    )
                }
            }

            // Environmental sensors - only show if NOT missing
            if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_PRESSURE }) {
                item {
                    BarometerCard(
                        data = state.barometerData?.let { SensorData(0f, 0f, it.value) }
                    )
                }
            }

            if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_AMBIENT_TEMPERATURE }) {
                item {
                    AmbientTemperatureCard(
                        data = state.ambientTemperatureData?.let { SensorData(0f, 0f, it.value) }
                    )
                }
            }

            if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_RELATIVE_HUMIDITY }) {
                item {
                    HumidityCard(
                        data = state.humidityData?.let { SensorData(0f, 0f, it.value) }
                    )
                }
            }

            // Motion & Health sensors - only show if NOT missing
            if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_STEP_COUNTER }) {
                item {
                    StepCounterCard(
                        data = state.stepCounterData?.let { SensorData(0f, 0f, it.value) }
                    )
                }
            }

            if (!state.missingSensors.any { it.sensorType == android.hardware.Sensor.TYPE_LINEAR_ACCELERATION }) {
                item {
                    LinearAccelerationCard(
                        data = state.linearAccelerationData?.let { SensorData(it.x, it.y, it.z) }
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
                        onClick = { viewModel.processAction(DashboardAction.Navigate(Destination.SensorDetail)) }
                    )
                }
            }
        }
    }
}
