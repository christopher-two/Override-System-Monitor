package org.override.system.monitor.feature.battery.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import org.override.system.monitor.core.ui.components.DetailItem
import org.override.system.monitor.core.ui.theme.DarkSurface
import org.override.system.monitor.core.ui.theme.NeonBlue
import org.override.system.monitor.core.ui.theme.NeonGreen
import org.override.system.monitor.core.ui.theme.NeonOrange
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BatteryDetailScreen(viewModel: BatteryViewModel) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("BATTERY INFO", style = MaterialTheme.typography.titleLarge.copy(fontFamily = FontFamily.Monospace)) },
                navigationIcon = {
                    IconButton(onClick = { viewModel.processAction(BatteryAction.NavigateBack) }) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Back", tint = NeonBlue)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets.systemBars
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            state.data?.let { b ->
                item { DetailItem("Percentage", "${b.percentage}%", NeonGreen) }
                item { DetailItem("Status", b.status, if (b.isCharging) NeonBlue else NeonGreen) }
                item { DetailItem("Temperature", "${b.temperature} °C", NeonOrange) }
                item { DetailItem("Voltage", "${b.voltage} mV", NeonBlue) }
                item { DetailItem("Health", b.health, NeonGreen) }
                item { DetailItem("Charging", if (b.isCharging) "YES" else "NO", NeonBlue) }
            }
        }
    }
}