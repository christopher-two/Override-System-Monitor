package org.override.system.monitor.feature.battery.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.override.system.monitor.core.ui.components.DetailItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BatteryDetailScreen(viewModel: BatteryViewModel) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Battery", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)) },
                navigationIcon = {
                    IconButton(onClick = { viewModel.processAction(BatteryAction.NavigateBack) }) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
            )
        },
        containerColor = MaterialTheme.colorScheme.surface,
        contentWindowInsets = WindowInsets.systemBars
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            state.data?.let { b ->
                item { DetailItem("Percentage", "${b.percentage}%", MaterialTheme.colorScheme.primary) }
                item { DetailItem("Status", b.status, if (b.isCharging) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary) }
                item { DetailItem("Temperature", "${b.temperature} °C", MaterialTheme.colorScheme.tertiary) }
                item { DetailItem("Voltage", "${b.voltage} mV", MaterialTheme.colorScheme.primary) }
                item { DetailItem("Health", b.health, MaterialTheme.colorScheme.secondary) }
                item { DetailItem("Charging", if (b.isCharging) "YES" else "NO", MaterialTheme.colorScheme.primary) }
            }
        }
    }
}