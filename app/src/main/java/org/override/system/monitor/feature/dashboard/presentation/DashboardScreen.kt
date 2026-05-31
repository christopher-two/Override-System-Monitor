package org.override.system.monitor.feature.dashboard.presentation

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Warning
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.override.system.monitor.core.ui.Destination
import org.override.system.monitor.feature.dashboard.presentation.components.MobileDashboardContent
import org.override.system.monitor.feature.dashboard.presentation.components.SensorDetailBottomSheet
import org.override.system.monitor.feature.dashboard.presentation.components.TabletDashboardContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(viewModel: DashboardViewModel) {
    val state by viewModel.state.collectAsState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    var showMissingSensorsSheet by remember { mutableStateOf(false) }
    var showSensorDetail by remember { mutableStateOf(false) }
    var selectedSensorDetail by remember { mutableStateOf<org.override.system.monitor.feature.dashboard.presentation.components.SensorDetail?>(null) }
    var hasRequestedNetworkPermission by remember { mutableStateOf(false) }

    val networkPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { _ ->
        viewModel.processAction(DashboardAction.RefreshNetworkPermission)
    }

    LaunchedEffect(state.hasNetworkPermission) {
        if (!state.hasNetworkPermission && !hasRequestedNetworkPermission) {
            hasRequestedNetworkPermission = true
            val permissions = mutableListOf(
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                permissions.add(Manifest.permission.READ_PHONE_STATE)
            }
            networkPermissionLauncher.launch(permissions.toTypedArray())
        }
    }

    if (showMissingSensorsSheet) {
        org.override.system.monitor.feature.dashboard.presentation.components.MissingSensorsBottomSheet(
            missingSensors = state.missingSensors,
            onDismiss = { showMissingSensorsSheet = false }
        )
    }

    if (showSensorDetail && selectedSensorDetail != null) {
        SensorDetailBottomSheet(
            sensorDetail = selectedSensorDetail!!,
            onDismiss = {
                showSensorDetail = false
                selectedSensorDetail = null
            }
        )
    }

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
                    if (state.missingSensors.isNotEmpty()) {
                        FilledTonalIconButton(
                            onClick = { showMissingSensorsSheet = true }
                        ) {
                            Icon(
                                Icons.Rounded.Warning,
                                contentDescription = "Missing Sensors",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    }
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
        BoxWithConstraints(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            val isMobile = maxWidth < 600.dp

            if (isMobile) {
                MobileDashboardContent(
                    state = state,
                    onNavigate = { dest -> viewModel.processAction(DashboardAction.Navigate(dest)) },
                    onSensorClick = { detail ->
                        selectedSensorDetail = detail
                        showSensorDetail = true
                    }
                )
            } else {
                TabletDashboardContent(
                    state = state,
                    onNavigate = { dest -> viewModel.processAction(DashboardAction.Navigate(dest)) },
                    onSensorClick = { detail ->
                        selectedSensorDetail = detail
                        showSensorDetail = true
                    }
                )
            }
        }
    }
}
