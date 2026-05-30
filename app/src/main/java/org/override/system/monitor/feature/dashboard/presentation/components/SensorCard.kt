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

@Composable
fun AccelerometerCard(x: Float, y: Float, z: Float) {
    val sensorColor = MaterialTheme.colorScheme.primary

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
                            tint = sensorColor,
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

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SensorAxis(label = "X", value = String.format(Locale.US, "%.2f", x), color = sensorColor)
                SensorAxis(label = "Y", value = String.format(Locale.US, "%.2f", y), color = sensorColor)
                SensorAxis(label = "Z", value = String.format(Locale.US, "%.2f", z), color = sensorColor)
            }
        }
    }
}

@Composable
fun GyroscopeCard(x: Float, y: Float, z: Float) {
    val gyroColor = MaterialTheme.colorScheme.secondary

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
                            tint = gyroColor,
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

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SensorAxis(label = "X", value = String.format(Locale.US, "%.2f", x), color = gyroColor)
                SensorAxis(label = "Y", value = String.format(Locale.US, "%.2f", y), color = gyroColor)
                SensorAxis(label = "Z", value = String.format(Locale.US, "%.2f", z), color = gyroColor)
            }
        }
    }
}