package org.override.system.monitor.feature.storage.presentation

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
fun StorageDetailScreen(viewModel: StorageViewModel) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("STORAGE INFO", style = MaterialTheme.typography.titleLarge.copy(fontFamily = FontFamily.Monospace)) },
                navigationIcon = {
                    IconButton(onClick = { viewModel.processAction(StorageAction.NavigateBack) }) {
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
            state.data?.let { s ->
                item { DetailItem("Total Storage", String.format(Locale.US, "%.2f GB", s.totalStorage / 1024f / 1024f / 1024f), NeonBlue) }
                item { DetailItem("Used Storage", String.format(Locale.US, "%.2f GB", s.usedStorage / 1024f / 1024f / 1024f), NeonOrange) }
                item { DetailItem("Available Storage", String.format(Locale.US, "%.2f GB", s.availableStorage / 1024f / 1024f / 1024f), NeonGreen) }
                item { DetailItem("Usage", String.format(Locale.US, "%.1f%%", s.percentageUsed), NeonRed) }
            }
        }
    }
}