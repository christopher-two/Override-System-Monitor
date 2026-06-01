package org.override.system.monitor.feature.battery.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.BatteryChargingFull
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.rounded.BatteryAlert
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.override.system.monitor.R
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BatteryDetailScreen(viewModel: BatteryViewModel) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.battery), style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)) },
                navigationIcon = {
                    IconButton(onClick = { viewModel.processAction(BatteryAction.NavigateBack) }) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = stringResource(R.string.back))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
            )
        },
        containerColor = MaterialTheme.colorScheme.surface,
        contentWindowInsets = WindowInsets.systemBars
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            state.data?.let { b ->
                item {
                    val percentageColor = if (b.percentage > 20) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                    BatteryCard(
                        title = stringResource(R.string.percentage),
                        value = b.percentage.toString(),
                        unit = "%",
                        icon = if (b.isCharging) Icons.Default.BatteryChargingFull else Icons.Rounded.CheckCircle,
                        progress = b.percentage / 100f,
                        progressColor = percentageColor,
                        backgroundColor = percentageColor.copy(alpha = 0.15f)
                    )
                }
                item {
                    BatteryCard(
                        title = stringResource(R.string.status),
                        value = b.status,
                        unit = "",
                        icon = if (b.isCharging) Icons.Default.BatteryChargingFull else Icons.Rounded.BatteryAlert,
                        progress = if (b.isCharging) 1f else 0f,
                        progressColor = if (b.isCharging) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                        backgroundColor = if (b.isCharging) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f) else MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.3f)
                    )
                }
                item {
                    val tempColor = if (b.temperature > 40f) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.tertiary
                    BatteryCard(
                        title = stringResource(R.string.temperature),
                        value = String.format(Locale.US, "%.1f", b.temperature),
                        unit = "°C",
                        icon = Icons.Default.Thermostat,
                        progress = (b.temperature / 60f).coerceIn(0f, 1f),
                        progressColor = tempColor,
                        backgroundColor = tempColor.copy(alpha = 0.15f)
                    )
                }
                item {
                    BatteryCard(
                        title = stringResource(R.string.voltage),
                        value = b.voltage.toString(),
                        unit = "mV",
                        icon = Icons.Default.Bolt,
                        progress = (b.voltage / 5000f).coerceIn(0f, 1f),
                        progressColor = MaterialTheme.colorScheme.primary,
                        backgroundColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                    )
                }
                item {
                    BatteryCard(
                        title = stringResource(R.string.health),
                        value = b.health,
                        unit = "",
                        icon = Icons.Rounded.CheckCircle,
                        progress = 1f,
                        progressColor = MaterialTheme.colorScheme.secondary,
                        backgroundColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.3f)
                    )
                }
                item {
                    BatteryCard(
                        title = stringResource(R.string.charging),
                        value = if (b.isCharging) stringResource(R.string.charging_yes) else stringResource(R.string.charging_no),
                        unit = "",
                        icon = if (b.isCharging) Icons.Default.BatteryChargingFull else Icons.Rounded.BatteryAlert,
                        progress = if (b.isCharging) 1f else 0f,
                        progressColor = if (b.isCharging) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                        backgroundColor = if (b.isCharging) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f) else MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.3f)
                    )
                }
            }
        }
    }
}

@Composable
private fun BatteryCard(
    title: String,
    value: String,
    unit: String,
    icon: ImageVector,
    progress: Float,
    progressColor: Color,
    backgroundColor: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraExtraLarge,
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(56.dp),
                shape = MaterialTheme.shapes.medium,
                color = progressColor.copy(alpha = 0.15f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.size(28.dp),
                        tint = progressColor
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = value,
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    if (unit.isNotEmpty()) {
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = unit,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = { progress.coerceIn(0f, 1f) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp),
                    color = progressColor,
                    trackColor = progressColor.copy(alpha = 0.2f)
                )
            }
        }
    }
}