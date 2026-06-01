package org.override.system.monitor.feature.settings.presentation

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Speed
import androidx.compose.material.icons.rounded.Vibration
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.override.system.monitor.R
import androidx.compose.foundation.layout.Box
import org.override.system.monitor.core.preferences.ThemeMode
import androidx.core.net.toUri

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    if (state.showResetDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.processAction(SettingsAction.HideResetDialog) },
            title = { Text(stringResource(R.string.reset_dashboard)) },
            text = { Text(stringResource(R.string.reset_dashboard_confirm)) },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.processAction(SettingsAction.ResetDashboard)
                        viewModel.processAction(SettingsAction.HideResetDialog)
                    }
                ) {
                    Text(stringResource(R.string.confirm))
                }
            },
            dismissButton = {
                TextButton(onClick = { viewModel.processAction(SettingsAction.HideResetDialog) }) {
                    Text(stringResource(R.string.cancel))
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.settings_title), style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)) },
                navigationIcon = {
                    IconButton(onClick = { viewModel.processAction(SettingsAction.NavigateBack) }) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = stringResource(R.string.back))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface)
            )
        },
        containerColor = MaterialTheme.colorScheme.surface,
        contentWindowInsets = WindowInsets.systemBars
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // App Info Section
            SettingsSection(title = stringResource(R.string.about)) {
                SettingsInfoRow(
                    icon = Icons.Rounded.Info,
                    title = stringResource(R.string.app_version, state.appVersion),
                    subtitle = stringResource(R.string.design_language)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Appearance Section
            SettingsSection(title = stringResource(R.string.appearance)) {
                ThemeSelectorRow(
                    selectedMode = state.themeMode,
                    onModeSelected = { mode ->
                        viewModel.processAction(SettingsAction.SetThemeMode(mode))
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Monitoring Section
            SettingsSection(title = stringResource(R.string.monitoring_settings)) {
                SettingsSwitchRow(
                    icon = Icons.Rounded.Refresh,
                    title = stringResource(R.string.auto_refresh),
                    subtitle = stringResource(R.string.auto_refresh_description),
                    checked = state.isAutoRefresh,
                    onCheckedChange = { viewModel.processAction(SettingsAction.ToggleAutoRefresh(it)) }
                )

                if (state.isAutoRefresh) {
                    SettingsSliderRow(
                        title = stringResource(R.string.refresh_interval),
                        value = state.refreshInterval,
                        valueText = stringResource(R.string.seconds_unit, state.refreshInterval.toString()),
                        onValueChange = { viewModel.processAction(SettingsAction.SetRefreshInterval(it)) },
                        valueRange = 1f..10f
                    )
                }

                SettingsSwitchRow(
                    icon = Icons.Rounded.Speed,
                    title = stringResource(R.string.high_precision),
                    subtitle = stringResource(R.string.high_precision_description),
                    checked = state.isHighPrecision,
                    onCheckedChange = { viewModel.processAction(SettingsAction.ToggleHighPrecision(it)) }
                )

                SettingsSwitchRow(
                    icon = Icons.Rounded.CheckCircle,
                    title = stringResource(R.string.show_units),
                    subtitle = stringResource(R.string.show_units_description),
                    checked = state.isShowUnits,
                    onCheckedChange = { viewModel.processAction(SettingsAction.ToggleShowUnits(it)) }
                )

                SettingsSwitchRow(
                    icon = Icons.Rounded.Vibration,
                    title = stringResource(R.string.vibration_feedback),
                    subtitle = stringResource(R.string.vibration_feedback_description),
                    checked = state.isVibrationFeedback,
                    onCheckedChange = { viewModel.processAction(SettingsAction.ToggleVibrationFeedback(it)) }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Reset Dashboard Button
            Button(
                onClick = { viewModel.processAction(SettingsAction.ShowResetDialog) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.reset_dashboard))
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Legal Section
            SettingsSection(title = stringResource(R.string.about)) {
                SettingsClickableRow(
                    title = stringResource(R.string.terms_and_conditions),
                    onClick = {
                        val intent =
                            Intent(Intent.ACTION_VIEW, "https://monitor.override.com.mx/terms".toUri())
                        context.startActivity(intent)
                    }
                )
                SettingsClickableRow(
                    title = stringResource(R.string.privacy_policy),
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW,
                            "https://monitor.override.com.mx/privacy".toUri())
                        context.startActivity(intent)
                    }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Footer
            Text(
                text = stringResource(R.string.developed_by),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun ThemeSelectorRow(
    selectedMode: ThemeMode,
    onModeSelected: (ThemeMode) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Rounded.DarkMode,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = stringResource(R.string.theme_mode),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = stringResource(R.string.theme_mode_description),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        SingleChoiceSegmentedButtonRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            ThemeMode.entries.forEachIndexed { index, mode ->
                SegmentedButton(
                    selected = mode == selectedMode,
                    onClick = { onModeSelected(mode) },
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = ThemeMode.entries.size
                    ),
                    icon = {
                        when (mode) {
                            ThemeMode.SYSTEM -> {}
                            ThemeMode.LIGHT -> Icon(
                                Icons.Rounded.LightMode,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            ThemeMode.DARK -> Icon(
                                Icons.Rounded.DarkMode,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    },
                    label = {
                        Text(
                            text = when (mode) {
                                ThemeMode.SYSTEM -> stringResource(R.string.theme_system)
                                ThemeMode.LIGHT -> stringResource(R.string.theme_light)
                                ThemeMode.DARK -> stringResource(R.string.theme_dark)
                            }
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun SettingsSection(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
            shape = MaterialTheme.shapes.large
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                content()
            }
        }
    }
}

@Composable
private fun SettingsSwitchRow(
    icon: ImageVector,
    title: String,
    subtitle: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCheckedChange(!checked) }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Composable
private fun SettingsSliderRow(
    title: String,
    value: Int,
    valueText: String,
    onValueChange: (Int) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>
) {
    Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = valueText,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
        Slider(
            value = value.toFloat(),
            onValueChange = { onValueChange(it.toInt()) },
            valueRange = valueRange,
            steps = (valueRange.endInclusive - valueRange.start).toInt() - 1
        )
    }
}

@Composable
private fun SettingsClickableRow(
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .size(20.dp)
                .rotate(180f)
        )
    }
}

@Composable
private fun SettingsInfoRow(
    icon: ImageVector,
    title: String,
    subtitle: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
