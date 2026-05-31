package org.override.system.monitor.feature.storage.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.override.system.monitor.R
import org.override.system.monitor.core.ui.components.DetailItem
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StorageDetailScreen(viewModel: StorageViewModel) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.storage), style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)) },
                navigationIcon = {
                    IconButton(onClick = { viewModel.processAction(StorageAction.NavigateBack) }) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = stringResource(R.string.back))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
            )
        },
        containerColor = MaterialTheme.colorScheme.surface,
        contentWindowInsets = WindowInsets.systemBars
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            state.data?.let { s ->
                item { DetailItem("Total Storage", String.format(Locale.US, "%.2f GB", s.totalStorage / 1024f / 1024f / 1024f), MaterialTheme.colorScheme.primary) }
                item { DetailItem("Used Storage", String.format(Locale.US, "%.2f GB", s.usedStorage / 1024f / 1024f / 1024f), MaterialTheme.colorScheme.tertiary) }
                item { DetailItem("Available Storage", String.format(Locale.US, "%.2f GB", s.availableStorage / 1024f / 1024f / 1024f), MaterialTheme.colorScheme.secondary) }
                item { DetailItem("Usage", String.format(Locale.US, "%.1f%%", s.percentageUsed), MaterialTheme.colorScheme.error) }
            }
        }
    }
}