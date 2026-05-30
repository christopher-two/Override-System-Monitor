package org.override.system.monitor.feature.dashboard.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.override.system.monitor.core.ui.components.BentoCard
import org.override.system.monitor.core.ui.components.MetricProgress
import org.override.system.monitor.core.ui.components.MetricValue
import org.override.system.monitor.core.ui.theme.NeonBlue
import org.override.system.monitor.core.ui.theme.NeonGreen
import org.override.system.monitor.core.ui.theme.NeonOrange
import org.override.system.monitor.core.ui.theme.NeonRed
import org.override.system.monitor.ui.Destination
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(viewModel: DashboardViewModel) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("SYSTEM MONITOR", style = MaterialTheme.typography.titleLarge.copy(fontFamily = FontFamily.Monospace, color = NeonBlue))
                },
                actions = {
                    IconButton(onClick = { viewModel.processAction(DashboardAction.Navigate(Destination.Settings)) }) {
                        Icon(Icons.Rounded.Settings, contentDescription = "Settings", tint = NeonBlue)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets.systemBars
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            item(span = { GridItemSpan(2) }) {
                state.batteryData?.let { battery ->
                    BentoCard(
                        modifier = Modifier.height(180.dp),
                        title = "BATTERY STATUS",
                        icon = Icons.Rounded.BatteryChargingFull,
                        accentColor = if (battery.percentage > 20) NeonGreen else NeonRed,
                        onClick = { viewModel.processAction(DashboardAction.Navigate(Destination.BatteryDetail)) }
                    ) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Column {
                                MetricValue(value = String.format(Locale.US, "%d", battery.percentage), unit = "%", color = if (battery.percentage > 20) NeonGreen else NeonRed)
                                Text(text = battery.status.uppercase(), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                            Column(horizontalAlignment = androidx.compose.ui.Alignment.End) {
                                MetricValue(value = String.format(Locale.US, "%.1f", battery.temperature), unit = "°C", shouldPulse = battery.temperature > 40f)
                                Text(text = "HEALTH: ${battery.health.uppercase()}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        MetricProgress(progress = battery.percentage / 100f, color = if (battery.percentage > 20) NeonGreen else NeonRed)
                    }
                }
            }

            item {
                state.memoryData?.let { ram ->
                    BentoCard(
                        modifier = Modifier.height(160.dp),
                        title = "MEMORY",
                        icon = Icons.Rounded.Memory,
                        accentColor = NeonBlue,
                        onClick = { viewModel.processAction(DashboardAction.Navigate(Destination.MemoryDetail)) }
                    ) {
                        MetricValue(value = String.format(Locale.US, "%.1f", ram.usedMemory / 1024f / 1024f / 1024f), unit = "GB")
                        Text(text = "OF ${String.format(Locale.US, "%.1f", ram.totalMemory / 1024f / 1024f / 1024f)} GB", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Spacer(modifier = Modifier.weight(1f))
                        MetricProgress(progress = ram.percentageUsed / 100f, color = NeonBlue)
                    }
                }
            }

            item {
                state.storageData?.let { storage ->
                    BentoCard(
                        modifier = Modifier.height(160.dp),
                        title = "STORAGE",
                        icon = Icons.Rounded.Storage,
                        accentColor = NeonOrange,
                        onClick = { viewModel.processAction(DashboardAction.Navigate(Destination.StorageDetail)) }
                    ) {
                        MetricValue(value = String.format(Locale.US, "%.0f", storage.usedStorage / 1024f / 1024f / 1024f), unit = "GB")
                        Text(text = "OF ${String.format(Locale.US, "%.0f", storage.totalStorage / 1024f / 1024f / 1024f)} GB", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Spacer(modifier = Modifier.weight(1f))
                        MetricProgress(progress = storage.percentageUsed / 100f, color = NeonOrange)
                    }
                }
            }

            item(span = { GridItemSpan(2) }) {
                Text(text = "LIVE SENSORS", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.padding(top = 8.dp))
            }

            item {
                state.accelerometerData?.let { data ->
                    BentoCard(modifier = Modifier.height(120.dp), title = "ACCEL", icon = Icons.Rounded.Speed, accentColor = NeonBlue) {
                        Text("X: ${String.format(Locale.US, "%.2f", data.x)}", style = MaterialTheme.typography.bodyMedium, fontFamily = FontFamily.Monospace)
                        Text("Y: ${String.format(Locale.US, "%.2f", data.y)}", style = MaterialTheme.typography.bodyMedium, fontFamily = FontFamily.Monospace)
                        Text("Z: ${String.format(Locale.US, "%.2f", data.z)}", style = MaterialTheme.typography.bodyMedium, fontFamily = FontFamily.Monospace)
                    }
                }
            }

            item {
                state.gyroscopeData?.let { data ->
                    BentoCard(modifier = Modifier.height(120.dp), title = "GYRO", icon = Icons.Rounded.Explore, accentColor = NeonGreen) {
                        Text("X: ${String.format(Locale.US, "%.2f", data.x)}", style = MaterialTheme.typography.bodyMedium, fontFamily = FontFamily.Monospace)
                        Text("Y: ${String.format(Locale.US, "%.2f", data.y)}", style = MaterialTheme.typography.bodyMedium, fontFamily = FontFamily.Monospace)
                        Text("Z: ${String.format(Locale.US, "%.2f", data.z)}", style = MaterialTheme.typography.bodyMedium, fontFamily = FontFamily.Monospace)
                    }
                }
            }

            item(span = { GridItemSpan(2) }) {
                state.systemIdentityData?.let { sys ->
                    BentoCard(
                        modifier = Modifier.height(140.dp),
                        title = "DEVICE SUMMARY",
                        icon = Icons.Rounded.Info,
                        accentColor = NeonBlue,
                        onClick = { viewModel.processAction(DashboardAction.Navigate(Destination.SensorDetail)) }
                    ) {
                        Text("${sys.manufacturer} ${sys.model}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        Text("Android ${sys.osVersion} (API ${sys.apiLevel})", style = MaterialTheme.typography.bodyMedium)
                        state.lightData?.let { light ->
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                                Icon(Icons.Rounded.LightMode, null, modifier = Modifier.size(14.dp), tint = NeonOrange)
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(text = "LIGHT: ${String.format(Locale.US, "%.0f lx", light.value)}", style = MaterialTheme.typography.labelSmall, fontFamily = FontFamily.Monospace, color = NeonOrange)
                            }
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                            Icon(Icons.Rounded.Schedule, null, modifier = Modifier.size(14.dp), tint = NeonGreen)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "UPTIME: ${formatUptime(sys.uptime)}", style = MaterialTheme.typography.labelSmall, fontFamily = FontFamily.Monospace, color = NeonGreen)
                        }
                    }
                }
            }
        }
    }
}

private fun formatUptime(uptimeMs: Long): String {
    val seconds = (uptimeMs / 1000) % 60
    val minutes = (uptimeMs / (1000 * 60)) % 60
    val hours = (uptimeMs / (1000 * 60 * 60)) % 24
    val days = uptimeMs / (1000 * 60 * 60 * 24)
    return if (days > 0) String.format(Locale.US, "%dd %02dh %02dm %02ds", days, hours, minutes, seconds)
    else String.format(Locale.US, "%02dh %02dm %02ds", hours, minutes, seconds)
}