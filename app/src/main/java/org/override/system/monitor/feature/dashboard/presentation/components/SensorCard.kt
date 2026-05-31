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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.Speed
import androidx.compose.material.icons.rounded.Thermostat
import androidx.compose.material.icons.rounded.WaterDrop
import androidx.compose.material.icons.rounded.ScreenRotation
import androidx.compose.material.icons.rounded.NearMe
import androidx.compose.material.icons.rounded.Sensors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.util.Locale

data class SensorData(val x: Float = 0f, val y: Float = 0f, val z: Float = 0f, val value: Float = 0f)

@Composable
fun AccelerometerCard(
    data: SensorData?,
    expanded: Boolean = false,
    onClick: () -> Unit = {}
) {
    ExpressiveCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (expanded) 160.dp else 100.dp),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = MaterialTheme.shapes.extraLarge,
        onClick = onClick
    ) {
        if (expanded) {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconContainer(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        icon = {
                            Icon(
                                Icons.Rounded.Speed,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        "Accelerometer",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "m/s²",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    SensorAxis(label = "X", value = String.format(Locale.US, "%.2f", data?.x ?: 0f), color = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.width(24.dp))
                    SensorAxis(label = "Y", value = String.format(Locale.US, "%.2f", data?.y ?: 0f), color = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.width(24.dp))
                    SensorAxis(label = "Z", value = String.format(Locale.US, "%.2f", data?.z ?: 0f), color = MaterialTheme.colorScheme.primary)
                }
            }
        } else {
            Row(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconContainer(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        icon = {
                            Icon(
                                Icons.Rounded.Speed,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Accelerometer",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                if (data != null) {
                    Column(horizontalAlignment = Alignment.End) {
                        Row {
                            SensorAxis(label = "X", value = String.format(Locale.US, "%.2f", data.x), color = MaterialTheme.colorScheme.primary)
                            SensorAxis(label = "Y", value = String.format(Locale.US, "%.2f", data.y), color = MaterialTheme.colorScheme.primary)
                            SensorAxis(label = "Z", value = String.format(Locale.US, "%.2f", data.z), color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GyroscopeCard(
    data: SensorData?,
    expanded: Boolean = false,
    onClick: () -> Unit = {}
) {
    ExpressiveCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (expanded) 160.dp else 100.dp),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = MaterialTheme.shapes.extraLarge,
        onClick = onClick
    ) {
        if (expanded) {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconContainer(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        icon = {
                            Icon(
                                Icons.Rounded.Explore,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        "Gyroscope",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "rad/s",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    SensorAxis(label = "X", value = String.format(Locale.US, "%.2f", data?.x ?: 0f), color = MaterialTheme.colorScheme.secondary)
                    Spacer(modifier = Modifier.width(24.dp))
                    SensorAxis(label = "Y", value = String.format(Locale.US, "%.2f", data?.y ?: 0f), color = MaterialTheme.colorScheme.secondary)
                    Spacer(modifier = Modifier.width(24.dp))
                    SensorAxis(label = "Z", value = String.format(Locale.US, "%.2f", data?.z ?: 0f), color = MaterialTheme.colorScheme.secondary)
                }
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
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        icon = {
                            Icon(
                                Icons.Rounded.Explore,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Gyroscope",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                if (data != null) {
                    Column(horizontalAlignment = Alignment.End) {
                        Row {
                            SensorAxis(label = "X", value = String.format(Locale.US, "%.2f", data.x), color = MaterialTheme.colorScheme.secondary)
                            SensorAxis(label = "Y", value = String.format(Locale.US, "%.2f", data.y), color = MaterialTheme.colorScheme.secondary)
                            SensorAxis(label = "Z", value = String.format(Locale.US, "%.2f", data.z), color = MaterialTheme.colorScheme.secondary)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MagnetometerCard(
    data: SensorData?,
    expanded: Boolean = false,
    onClick: () -> Unit = {}
) {
    ExpressiveCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (expanded) 160.dp else 100.dp),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = MaterialTheme.shapes.extraLarge,
        onClick = onClick
    ) {
        if (expanded) {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconContainer(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.tertiaryContainer,
                        icon = {
                            Icon(
                                Icons.Rounded.Explore,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onTertiaryContainer,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        "Magnetometer",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "μT",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    SensorAxis(label = "X", value = String.format(Locale.US, "%.2f", data?.x ?: 0f), color = MaterialTheme.colorScheme.tertiary)
                    Spacer(modifier = Modifier.width(24.dp))
                    SensorAxis(label = "Y", value = String.format(Locale.US, "%.2f", data?.y ?: 0f), color = MaterialTheme.colorScheme.tertiary)
                    Spacer(modifier = Modifier.width(24.dp))
                    SensorAxis(label = "Z", value = String.format(Locale.US, "%.2f", data?.z ?: 0f), color = MaterialTheme.colorScheme.tertiary)
                }
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
                        color = MaterialTheme.colorScheme.tertiaryContainer,
                        icon = {
                            Icon(
                                Icons.Rounded.Explore,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onTertiaryContainer,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Magnetometer",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                if (data != null) {
                    Column(horizontalAlignment = Alignment.End) {
                        Row {
                            SensorAxis(label = "X", value = String.format(Locale.US, "%.2f", data.x), color = MaterialTheme.colorScheme.tertiary)
                            SensorAxis(label = "Y", value = String.format(Locale.US, "%.2f", data.y), color = MaterialTheme.colorScheme.tertiary)
                            SensorAxis(label = "Z", value = String.format(Locale.US, "%.2f", data.z), color = MaterialTheme.colorScheme.tertiary)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProximityCard(
    data: SensorData?,
    expanded: Boolean = false,
    onClick: () -> Unit = {}
) {
    ExpressiveCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (expanded) 140.dp else 100.dp),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = MaterialTheme.shapes.extraLarge,
        onClick = onClick
    ) {
        if (expanded) {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconContainer(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.primaryContainer,
                        icon = {
                            Icon(
                                Icons.Rounded.NearMe,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        "Proximity",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                if (data != null) {
                    Text(
                        text = if (data.value < 5f) "Near" else "Far",
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = String.format(Locale.US, "%.1f cm", data.value),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                } else {
                    Text(
                        text = "No data",
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
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
                        color = MaterialTheme.colorScheme.primaryContainer,
                        icon = {
                            Icon(
                                Icons.Rounded.NearMe,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Proximity",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                if (data != null) {
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = if (data.value < 5f) "Near" else "Far",
                            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = String.format(Locale.US, "%.1f cm", data.value),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RotationVectorCard(
    data: SensorData?,
    expanded: Boolean = false,
    onClick: () -> Unit = {}
) {
    ExpressiveCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (expanded) 160.dp else 100.dp),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = MaterialTheme.shapes.extraLarge,
        onClick = onClick
    ) {
        if (expanded) {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconContainer(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        icon = {
                            Icon(
                                Icons.Rounded.ScreenRotation,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        "Rotation Vector",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "dimensionless",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    SensorAxis(label = "X", value = String.format(Locale.US, "%.2f", data?.x ?: 0f), color = MaterialTheme.colorScheme.secondary)
                    Spacer(modifier = Modifier.width(24.dp))
                    SensorAxis(label = "Y", value = String.format(Locale.US, "%.2f", data?.y ?: 0f), color = MaterialTheme.colorScheme.secondary)
                    Spacer(modifier = Modifier.width(24.dp))
                    SensorAxis(label = "Z", value = String.format(Locale.US, "%.2f", data?.z ?: 0f), color = MaterialTheme.colorScheme.secondary)
                }
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
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        icon = {
                            Icon(
                                Icons.Rounded.ScreenRotation,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Rotation Vector",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                if (data != null) {
                    Column(horizontalAlignment = Alignment.End) {
                        Row {
                            SensorAxis(label = "X", value = String.format(Locale.US, "%.2f", data.x), color = MaterialTheme.colorScheme.secondary)
                            SensorAxis(label = "Y", value = String.format(Locale.US, "%.2f", data.y), color = MaterialTheme.colorScheme.secondary)
                            SensorAxis(label = "Z", value = String.format(Locale.US, "%.2f", data.z), color = MaterialTheme.colorScheme.secondary)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BarometerCard(
    data: SensorData?,
    expanded: Boolean = false,
    onClick: () -> Unit = {}
) {
    ExpressiveCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (expanded) 140.dp else 100.dp),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = MaterialTheme.shapes.extraLarge,
        onClick = onClick
    ) {
        if (expanded) {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconContainer(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.tertiaryContainer,
                        icon = {
                            Icon(
                                Icons.Rounded.Sensors,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onTertiaryContainer,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        "Barometer",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                if (data != null) {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = String.format(Locale.US, "%.1f", data.value),
                            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.tertiary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "hPa",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                } else {
                    Text(
                        text = "No data",
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
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
                        color = MaterialTheme.colorScheme.tertiaryContainer,
                        icon = {
                            Icon(
                                Icons.Rounded.Sensors,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onTertiaryContainer,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Barometer",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                if (data != null) {
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = String.format(Locale.US, "%.1f", data.value),
                            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.tertiary
                        )
                        Text(
                            text = "hPa",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AmbientTemperatureCard(
    data: SensorData?,
    expanded: Boolean = false,
    onClick: () -> Unit = {}
) {
    ExpressiveCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (expanded) 140.dp else 100.dp),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = MaterialTheme.shapes.extraLarge,
        onClick = onClick
    ) {
        if (expanded) {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconContainer(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.errorContainer,
                        icon = {
                            Icon(
                                Icons.Rounded.Thermostat,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onErrorContainer,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        "Temperature",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                if (data != null) {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = String.format(Locale.US, "%.1f", data.value),
                            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "°C",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                } else {
                    Text(
                        text = "No data",
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
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
                        color = MaterialTheme.colorScheme.errorContainer,
                        icon = {
                            Icon(
                                Icons.Rounded.Thermostat,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onErrorContainer,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Temperature",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                if (data != null) {
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = String.format(Locale.US, "%.1f", data.value),
                            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.error
                        )
                        Text(
                            text = "°C",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HumidityCard(
    data: SensorData?,
    expanded: Boolean = false,
    onClick: () -> Unit = {}
) {
    ExpressiveCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (expanded) 140.dp else 100.dp),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = MaterialTheme.shapes.extraLarge,
        onClick = onClick
    ) {
        if (expanded) {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconContainer(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.primaryContainer,
                        icon = {
                            Icon(
                                Icons.Rounded.WaterDrop,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        "Humidity",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                if (data != null) {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = String.format(Locale.US, "%.1f", data.value),
                            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "%",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                } else {
                    Text(
                        text = "No data",
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
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
                        color = MaterialTheme.colorScheme.primaryContainer,
                        icon = {
                            Icon(
                                Icons.Rounded.WaterDrop,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Humidity",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                if (data != null) {
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = String.format(Locale.US, "%.1f", data.value),
                            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "%",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LinearAccelerationCard(
    data: SensorData?,
    expanded: Boolean = false,
    onClick: () -> Unit = {}
) {
    ExpressiveCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(if (expanded) 160.dp else 100.dp),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = MaterialTheme.shapes.extraLarge,
        onClick = onClick
    ) {
        if (expanded) {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconContainer(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        icon = {
                            Icon(
                                Icons.Rounded.Speed,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        "Linear Accel",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "m/s²",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    SensorAxis(label = "X", value = String.format(Locale.US, "%.2f", data?.x ?: 0f), color = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.width(24.dp))
                    SensorAxis(label = "Y", value = String.format(Locale.US, "%.2f", data?.y ?: 0f), color = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.width(24.dp))
                    SensorAxis(label = "Z", value = String.format(Locale.US, "%.2f", data?.z ?: 0f), color = MaterialTheme.colorScheme.primary)
                }
            }
        } else {
            Row(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconContainer(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.primaryContainer,
                        icon = {
                            Icon(
                                Icons.Rounded.Speed,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "Linear Accel",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                if (data != null) {
                    Column(horizontalAlignment = Alignment.End) {
                        Row {
                            SensorAxis(label = "X", value = String.format(Locale.US, "%.2f", data.x), color = MaterialTheme.colorScheme.primary)
                            SensorAxis(label = "Y", value = String.format(Locale.US, "%.2f", data.y), color = MaterialTheme.colorScheme.primary)
                            SensorAxis(label = "Z", value = String.format(Locale.US, "%.2f", data.z), color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
            }
        }
    }
}
