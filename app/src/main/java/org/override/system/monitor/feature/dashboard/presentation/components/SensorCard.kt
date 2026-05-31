package org.override.system.monitor.feature.dashboard.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.NearMe
import androidx.compose.material.icons.rounded.Sensors
import androidx.compose.material.icons.rounded.ScreenRotation
import androidx.compose.material.icons.rounded.Speed
import androidx.compose.material.icons.rounded.Thermostat
import androidx.compose.material.icons.rounded.WaterDrop
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.override.system.monitor.R
import java.util.Locale

data class SensorData(val x: Float = 0f, val y: Float = 0f, val z: Float = 0f, val value: Float = 0f)

// ============== BASE SENSOR CARD COMPOSABLES ==============

@Composable
private fun BaseSensorCard(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    icon: ImageVector,
    iconBackgroundColor: Color,
    iconColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    title: String,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    ExpressiveCard(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = MaterialTheme.shapes.extraLarge
    ) {
        if (expanded) {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconContainer(
                        shape = CircleShape,
                        color = iconBackgroundColor,
                        icon = {
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                tint = iconColor,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                content()
            }
        } else {
            Row(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconContainer(
                        shape = CircleShape,
                        color = iconBackgroundColor,
                        icon = {
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                tint = iconColor,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                content()
            }
        }
    }
}

@Composable
private fun TriAxisSensorContent(
    data: SensorData?,
    unit: String,
    axisColor: Color,
    expanded: Boolean
) {
    if (data != null) {
        if (expanded) {
            Text(
                text = unit,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                SensorAxis(label = "X", value = String.format(Locale.US, "%.2f", data.x), color = axisColor)
                SensorAxis(label = "Y", value = String.format(Locale.US, "%.2f", data.y), color = axisColor)
                SensorAxis(label = "Z", value = String.format(Locale.US, "%.2f", data.z), color = axisColor)
            }
        } else {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                SensorAxis(label = "X", value = String.format(Locale.US, "%.2f", data.x), color = axisColor)
                SensorAxis(label = "Y", value = String.format(Locale.US, "%.2f", data.y), color = axisColor)
                SensorAxis(label = "Z", value = String.format(Locale.US, "%.2f", data.z), color = axisColor)
            }
        }
    }
}

@Composable
private fun SingleValueSensorContent(
    data: SensorData?,
    displayValue: String,
    unit: String,
    valueColor: Color,
    expanded: Boolean
) {
    if (data != null) {
        if (expanded) {
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = displayValue,
                    style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                    color = valueColor
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = unit,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = displayValue,
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    color = valueColor
                )
                Text(
                    text = unit,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    } else {
        Text(
            text = stringResource(R.string.no_data),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

// ============== SENSOR CARDS ==============

@Composable
fun AccelerometerCard(
    data: SensorData?,
    expanded: Boolean = false,
    onClick: () -> Unit = {}
) {
    val height: Dp = if (expanded) 160.dp else 100.dp
    BaseSensorCard(
        modifier = Modifier.height(height),
        expanded = expanded,
        icon = Icons.Rounded.Speed,
        iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
        title = stringResource(R.string.accelerometer),
        onClick = onClick
    ) {
        TriAxisSensorContent(
            data = data,
            unit = stringResource(R.string.unit_m_s2),
            axisColor = MaterialTheme.colorScheme.primary,
            expanded = expanded
        )
    }
}

@Composable
fun GyroscopeCard(
    data: SensorData?,
    expanded: Boolean = false,
    onClick: () -> Unit = {}
) {
    val height: Dp = if (expanded) 160.dp else 100.dp
    BaseSensorCard(
        modifier = Modifier.height(height),
        expanded = expanded,
        icon = Icons.Rounded.Explore,
        iconBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
        title = stringResource(R.string.gyroscope),
        onClick = onClick
    ) {
        TriAxisSensorContent(
            data = data,
            unit = stringResource(R.string.unit_rad_s),
            axisColor = MaterialTheme.colorScheme.secondary,
            expanded = expanded
        )
    }
}

@Composable
fun MagnetometerCard(
    data: SensorData?,
    expanded: Boolean = false,
    onClick: () -> Unit = {}
) {
    val height: Dp = if (expanded) 160.dp else 100.dp
    BaseSensorCard(
        modifier = Modifier.height(height),
        expanded = expanded,
        icon = Icons.Rounded.Explore,
        iconBackgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
        title = stringResource(R.string.magnetometer),
        onClick = onClick
    ) {
        TriAxisSensorContent(
            data = data,
            unit = stringResource(R.string.unit_micro_t),
            axisColor = MaterialTheme.colorScheme.tertiary,
            expanded = expanded
        )
    }
}

@Composable
fun ProximityCard(
    data: SensorData?,
    expanded: Boolean = false,
    onClick: () -> Unit = {}
) {
    val height: Dp = if (expanded) 140.dp else 100.dp
    BaseSensorCard(
        modifier = Modifier.height(height),
        expanded = expanded,
        icon = Icons.Rounded.NearMe,
        iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
        title = stringResource(R.string.proximity),
        onClick = onClick
    ) {
        if (data != null) {
            val displayText = if (data.value < 5f) stringResource(R.string.proximity_near) else stringResource(R.string.proximity_far)
            val displayValue = String.format(Locale.US, "%.1f cm", data.value)
            SingleValueSensorContent(
                data = data,
                displayValue = displayText,
                unit = displayValue,
                valueColor = MaterialTheme.colorScheme.primary,
                expanded = expanded
            )
        } else {
            Text(
                text = stringResource(R.string.no_data),
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun RotationVectorCard(
    data: SensorData?,
    expanded: Boolean = false,
    onClick: () -> Unit = {}
) {
    val height: Dp = if (expanded) 160.dp else 100.dp
    BaseSensorCard(
        modifier = Modifier.height(height),
        expanded = expanded,
        icon = Icons.Rounded.ScreenRotation,
        iconBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
        title = stringResource(R.string.rotation_vector),
        onClick = onClick
    ) {
        TriAxisSensorContent(
            data = data,
            unit = stringResource(R.string.unit_dimensionless),
            axisColor = MaterialTheme.colorScheme.secondary,
            expanded = expanded
        )
    }
}

@Composable
fun BarometerCard(
    data: SensorData?,
    expanded: Boolean = false,
    onClick: () -> Unit = {}
) {
    val height: Dp = if (expanded) 140.dp else 100.dp
    BaseSensorCard(
        modifier = Modifier.height(height),
        expanded = expanded,
        icon = Icons.Rounded.Sensors,
        iconBackgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
        title = stringResource(R.string.barometer),
        onClick = onClick
    ) {
        if (data != null) {
            val displayValue = String.format(Locale.US, "%.1f", data.value)
            SingleValueSensorContent(
                data = data,
                displayValue = displayValue,
                unit = stringResource(R.string.unit_hpa),
                valueColor = MaterialTheme.colorScheme.tertiary,
                expanded = expanded
            )
        } else {
            Text(
                text = stringResource(R.string.no_data),
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun AmbientTemperatureCard(
    data: SensorData?,
    expanded: Boolean = false,
    onClick: () -> Unit = {}
) {
    val height: Dp = if (expanded) 140.dp else 100.dp
    BaseSensorCard(
        modifier = Modifier.height(height),
        expanded = expanded,
        icon = Icons.Rounded.Thermostat,
        iconBackgroundColor = MaterialTheme.colorScheme.errorContainer,
        title = stringResource(R.string.temperature_label),
        onClick = onClick
    ) {
        if (data != null) {
            val displayValue = String.format(Locale.US, "%.1f", data.value)
            SingleValueSensorContent(
                data = data,
                displayValue = displayValue,
                unit = stringResource(R.string.unit_celsius),
                valueColor = MaterialTheme.colorScheme.error,
                expanded = expanded
            )
        } else {
            Text(
                text = stringResource(R.string.no_data),
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun HumidityCard(
    data: SensorData?,
    expanded: Boolean = false,
    onClick: () -> Unit = {}
) {
    val height: Dp = if (expanded) 140.dp else 100.dp
    BaseSensorCard(
        modifier = Modifier.height(height),
        expanded = expanded,
        icon = Icons.Rounded.WaterDrop,
        iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
        title = stringResource(R.string.humidity),
        onClick = onClick
    ) {
        if (data != null) {
            val displayValue = String.format(Locale.US, "%.1f", data.value)
            SingleValueSensorContent(
                data = data,
                displayValue = displayValue,
                unit = stringResource(R.string.unit_percent),
                valueColor = MaterialTheme.colorScheme.primary,
                expanded = expanded
            )
        } else {
            Text(
                text = stringResource(R.string.no_data),
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun LinearAccelerationCard(
    data: SensorData?,
    expanded: Boolean = false,
    onClick: () -> Unit = {}
) {
    val height: Dp = if (expanded) 160.dp else 100.dp
    BaseSensorCard(
        modifier = Modifier.height(height),
        expanded = expanded,
        icon = Icons.Rounded.Speed,
        iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
        title = stringResource(R.string.linear_accel),
        onClick = onClick
    ) {
        TriAxisSensorContent(
            data = data,
            unit = stringResource(R.string.unit_m_s2),
            axisColor = MaterialTheme.colorScheme.primary,
            expanded = expanded
        )
    }
}
