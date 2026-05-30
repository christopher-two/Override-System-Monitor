package org.override.system.monitor.feature.settings.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.override.system.monitor.core.ui.theme.NeonBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(viewModel: SettingsViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("SETTINGS", style = MaterialTheme.typography.titleLarge.copy(fontFamily = FontFamily.Monospace)) },
                navigationIcon = {
                    IconButton(onClick = { viewModel.processAction(SettingsAction.NavigateBack) }) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Back", tint = NeonBlue)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets.systemBars
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            Text("App Version: ${state.appVersion}", color = Color.White, style = MaterialTheme.typography.bodyLarge)
            Text("Design Language: Bento M3", color = Color.Gray, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { viewModel.processAction(SettingsAction.ResetDashboard) },
                colors = ButtonDefaults.buttonColors(containerColor = NeonBlue, contentColor = Color.Black)
            ) {
                Text("RESET DASHBOARD")
            }
        }
    }
}