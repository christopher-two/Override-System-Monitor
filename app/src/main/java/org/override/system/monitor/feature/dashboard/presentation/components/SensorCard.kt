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
import androidx.compose.material.icons.automirrored.rounded.DirectionsWalk
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
fun AccelerometerCard(data: SensorData?) {
    ExpressiveCard(
        modifier = Modifier.height(140.dp),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = RoundedCornerShape(20.dp)
    ) {
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
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    SensorAxis(label = "X", value = String.format(Locale.US, "%.2f", data.x), color = MaterialTheme.colorScheme.primary)
                    SensorAxis(label = "Y", value = String.format(Locale.US, "%.2f", data.y), color = MaterialTheme.colorScheme.primary)
                    SensorAxis(label = "Z", value = String.format(Locale.US, "%.2f", data.z), color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}

@Composable
fun GyroscopeCard(data: SensorData?) {
    ExpressiveCard(
        modifier = Modifier.height(140.dp),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = RoundedCornerShape(20.dp)
    ) {
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
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    SensorAxis(label = "X", value = String.format(Locale.US, "%.2f", data.x), color = MaterialTheme.colorScheme.secondary)
                    SensorAxis(label = "Y", value = String.format(Locale.US, "%.2f", data.y), color = MaterialTheme.colorScheme.secondary)
                    SensorAxis(label = "Z", value = String.format(Locale.US, "%.2f", data.z), color = MaterialTheme.colorScheme.secondary)
                }
            }
        }
    }
}

// ============== NEW SENSOR CARDS ==============

@Composable
fun MagnetometerCard(data: SensorData?) {
    ExpressiveCard(
        modifier = Modifier.height(140.dp),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = RoundedCornerShape(20.dp)
    ) {
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
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    SensorAxis(label = "X", value = String.format(Locale.US, "%.2f", data.x), color = MaterialTheme.colorScheme.tertiary)
                    SensorAxis(label = "Y", value = String.format(Locale.US, "%.2f", data.y), color = MaterialTheme.colorScheme.tertiary)
                    SensorAxis(label = "Z", value = String.format(Locale.US, "%.2f", data.z), color = MaterialTheme.colorScheme.tertiary)
                }
            }
        }
    }
}

@Composable
fun ProximityCard(data: SensorData?) {
    ExpressiveCard(
        modifier = Modifier.height(100.dp),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = RoundedCornerShape(20.dp)
    ) {
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
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (data.value< 5f) "Near" else "Far",
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

@Composable
fun RotationVectorCard(data: SensorData?) {
    ExpressiveCard(
        modifier = Modifier.height(140.dp),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = RoundedCornerShape(20.dp)
    ) {
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
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    SensorAxis(label = "X", value = String.format(Locale.US, "%.2f", data.x), color = MaterialTheme.colorScheme.secondary)
                    SensorAxis(label = "Y", value = String.format(Locale.US, "%.2f", data.y), color = MaterialTheme.colorScheme.secondary)
                    SensorAxis(label = "Z", value = String.format(Locale.US, "%.2f", data.z), color = MaterialTheme.colorScheme.secondary)
                }
            }
        }
    }
}

@Composable
fun BarometerCard(data: SensorData?) {
    ExpressiveCard(
        modifier = Modifier.height(100.dp),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = RoundedCornerShape(20.dp)
    ) {
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
                Spacer(modifier = Modifier.height(8.dp))
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

@Composable
fun AmbientTemperatureCard(data: SensorData?) {
    ExpressiveCard(
        modifier = Modifier.height(100.dp),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = RoundedCornerShape(20.dp)
    ) {
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
                Spacer(modifier = Modifier.height(8.dp))
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

@Composable
fun HumidityCard(data: SensorData?) {
    ExpressiveCard(
        modifier = Modifier.height(100.dp),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = RoundedCornerShape(20.dp)
    ) {
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
                Spacer(modifier = Modifier.height(8.dp))
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

@Composable
fun StepCounterCard(data: SensorData?) {
    ExpressiveCard(
        modifier = Modifier.height(100.dp),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconContainer(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    icon = {
                        Icon(
                            Icons.AutoMirrored.Rounded.DirectionsWalk,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSecondaryContainer,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "Step Counter",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            if (data != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = String.format(Locale.US, "%.0f", data.value),
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = "steps",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun LinearAccelerationCard(data: SensorData?) {
    ExpressiveCard(
        modifier = Modifier.height(140.dp),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = RoundedCornerShape(20.dp)
    ) {
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
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    SensorAxis(label = "X", value = String.format(Locale.US, "%.2f", data.x), color = MaterialTheme.colorScheme.primary)
                    SensorAxis(label = "Y", value = String.format(Locale.US, "%.2f", data.y), color = MaterialTheme.colorScheme.primary)
                    SensorAxis(label = "Z", value = String.format(Locale.US, "%.2f", data.z), color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}
