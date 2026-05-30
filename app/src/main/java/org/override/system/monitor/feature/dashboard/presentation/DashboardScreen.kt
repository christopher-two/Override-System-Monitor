package org.override.system.monitor.feature.dashboard.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import org.override.system.monitor.core.ui.Destination
import org.override.system.monitor.feature.dashboard.presentation.components.AccelerometerCard
import org.override.system.monitor.feature.dashboard.presentation.components.BatteryCard
import org.override.system.monitor.feature.dashboard.presentation.components.DeviceCard
import org.override.system.monitor.feature.dashboard.presentation.components.GyroscopeCard
import org.override.system.monitor.feature.dashboard.presentation.components.MemoryCard
import org.override.system.monitor.feature.dashboard.presentation.components.StorageCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(viewModel: DashboardViewModel) {
    val state by viewModel.state.collectAsState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

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

            item {
                state.accelerometerData?.let { data ->
                    AccelerometerCard(x = data.x, y = data.y, z = data.z)
                }
            }

            item {
                state.gyroscopeData?.let { data ->
                    GyroscopeCard(x = data.x, y = data.y, z = data.z)
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