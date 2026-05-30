package org.override.system.monitor.feature.sensor.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.override.system.monitor.core.ui.theme.DarkSurface
import org.override.system.monitor.core.ui.theme.NeonBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SensorDetailScreen(viewModel: SensorViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ALL SENSORS", style = MaterialTheme.typography.titleLarge.copy(fontFamily = FontFamily.Monospace)) },
                navigationIcon = {
                    IconButton(onClick = { viewModel.processAction(SensorAction.NavigateBack) }) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Back", tint = NeonBlue)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets.systemBars
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(viewModel.sensors) { sensor ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = DarkSurface),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(sensor.name, style = MaterialTheme.typography.titleMedium, color = NeonBlue, fontWeight = FontWeight.Bold)
                        Text("Vendor: ${sensor.vendor}", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                        Text("Power: ${sensor.power} mA", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                    }
                }
            }
        }
    }
}