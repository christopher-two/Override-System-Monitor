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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.util.Locale

data class SensorData(val x: Float, val y: Float, val z: Float)

@Composable
fun AccelerometerCard(data: SensorData?) {
    ExpressiveCard(
        modifier = Modifier.height(140.dp),
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
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
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
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