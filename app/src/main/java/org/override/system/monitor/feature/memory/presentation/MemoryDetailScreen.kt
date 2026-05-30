package org.override.system.monitor.feature.memory.presentation

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
import org.override.system.monitor.core.ui.theme.NeonBlue
import org.override.system.monitor.core.ui.theme.NeonGreen
import org.override.system.monitor.core.ui.theme.NeonOrange
import org.override.system.monitor.core.ui.theme.NeonRed
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoryDetailScreen(viewModel: MemoryViewModel) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("MEMORY INFO", style = MaterialTheme.typography.titleLarge.copy(fontFamily = FontFamily.Monospace)) },
                navigationIcon = {
                    IconButton(onClick = { viewModel.processAction(MemoryAction.NavigateBack) }) {
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
            state.data?.let { m ->
                item { DetailItem("Total RAM", String.format(Locale.US, "%.2f GB", m.totalMemory / 1024f / 1024f / 1024f), NeonBlue) }
                item { DetailItem("Used RAM", String.format(Locale.US, "%.2f GB", m.usedMemory / 1024f / 1024f / 1024f), NeonOrange) }
                item { DetailItem("Available RAM", String.format(Locale.US, "%.2f GB", m.availableMemory / 1024f / 1024f / 1024f), NeonGreen) }
                item { DetailItem("Usage", String.format(Locale.US, "%.1f%%", m.percentageUsed), NeonRed) }
            }
        }
    }
}