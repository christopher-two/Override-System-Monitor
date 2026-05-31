package org.override.system.monitor.feature.dashboard.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Speed
import androidx.compose.material.icons.rounded.Thermostat
import androidx.compose.material.icons.rounded.WaterDrop
import androidx.compose.material.icons.rounded.ScreenRotation
import androidx.compose.material.icons.rounded.NearMe
import androidx.compose.material.icons.rounded.Sensors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.util.Locale

data class SensorDetail(
    val name: String,
    val icon: ImageVector,
    val iconColor: Color,
    val iconBackgroundColor: Color,
    val unit: String,
    val description: String,
    val howItWorks: String,
    val data: SensorData?
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SensorDetailBottomSheet(
    sensorDetail: SensorDetail,
    onDismiss: () -> Unit
) {
    val sheetState = rememberBottomSheetState(initialValue = SheetValue.Expanded)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(bottom = 32.dp)
        ) {
            // Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                IconContainer(
                    shape = CircleShape,
                    color = sensorDetail.iconBackgroundColor,
                    icon = {
                        Icon(
                            imageVector = sensorDetail.icon,
                            contentDescription = null,
                            tint = sensorDetail.iconColor,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = sensorDetail.name,
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            // Current State Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Current State",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    if (sensorDetail.data != null) {
                        Row(verticalAlignment = Alignment.Bottom) {
                            if (sensorDetail.unit.isNotEmpty() && !sensorDetail.unit.contains("X") && !sensorDetail.unit.contains("Y") && !sensorDetail.unit.contains("Z")) {
                                Text(
                                    text = formatSensorValue(sensorDetail.data, sensorDetail.unit),
                                    style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                                    color = sensorDetail.iconColor
                                )
                            } else {
                                Column {
                                    Row {
                                        AxisValue("X", sensorDetail.data.x, sensorDetail.iconColor)
                                        Spacer(modifier = Modifier.width(16.dp))
                                        AxisValue("Y", sensorDetail.data.y, sensorDetail.iconColor)
                                        Spacer(modifier = Modifier.width(16.dp))
                                        AxisValue("Z", sensorDetail.data.z, sensorDetail.iconColor)
                                    }
                                }
                            }
                        }
                        if (sensorDetail.unit.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = sensorDetail.unit,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    } else {
                        Text(
                            text = "No data available",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // What is it section
            InfoSection(
                title = "What is it?",
                content = sensorDetail.description
            )

            Spacer(modifier = Modifier.height(12.dp))

            // How it works section
            InfoSection(
                title = "How it works",
                content = sensorDetail.howItWorks
            )
        }
    }
}

@Composable
private fun AxisValue(label: String, value: Float, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = String.format(Locale.US, "%.2f", value),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = color
        )
    }
}

@Composable
private fun InfoSection(title: String, content: String) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Rounded.Info,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = content,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

private fun formatSensorValue(data: SensorData, unit: String): String {
    return when {
        unit.contains("%") -> String.format(Locale.US, "%.1f", data.value)
        unit.contains("C") -> String.format(Locale.US, "%.1f", data.value)
        unit.contains("hPa") -> String.format(Locale.US, "%.1f", data.value)
        unit.contains("cm") -> String.format(Locale.US, "%.1f", data.value)
        else -> String.format(Locale.US, "%.2f", data.value)
    }
}

@Composable
fun getAccelerometerSensorDetail(data: SensorData?): SensorDetail = SensorDetail(
    name = "Accelerometer",
    icon = Icons.Rounded.Speed,
    iconColor = MaterialTheme.colorScheme.onPrimaryContainer,
    iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
    unit = "m/s²",
    description = "Measures the acceleration force applied to the device on all three physical axes (x, y, and z), including gravity.",
    howItWorks = "Uses a micro-electromechanical system (MEMS) with a proof mass that deflects when acceleration is applied. The deflection is measured electrically to determine acceleration.",
    data = data
)

@Composable
fun getGyroscopeSensorDetail(data: SensorData?): SensorDetail = SensorDetail(
    name = "Gyroscope",
    icon = Icons.Rounded.Explore,
    iconColor = MaterialTheme.colorScheme.onSecondaryContainer,
    iconBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
    unit = "rad/s",
    description = "Measures the rate of rotation around the x, y, and z axes. It detects how fast the device is spinning.",
    howItWorks = "Uses the Coriolis effect on a vibrating mass. When the device rotates, the vibration pattern changes, and this change is measured to calculate rotational velocity.",
    data = data
)

@Composable
fun getMagnetometerSensorDetail(data: SensorData?): SensorDetail = SensorDetail(
    name = "Magnetometer",
    icon = Icons.Rounded.Explore,
    iconColor = MaterialTheme.colorScheme.onTertiaryContainer,
    iconBackgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
    unit = "μT",
    description = "Measures the ambient magnetic field in the x, y, and z axes. It's commonly used as a digital compass.",
    howItWorks = "Uses a Hall effect sensor or magnetoresistive material that changes its electrical resistance when exposed to a magnetic field.",
    data = data
)

@Composable
fun getProximitySensorDetail(data: SensorData?): SensorDetail = SensorDetail(
    name = "Proximity",
    icon = Icons.Rounded.NearMe,
    iconColor = MaterialTheme.colorScheme.onPrimaryContainer,
    iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
    unit = "cm",
    description = "Measures the distance of an object from the front of the device. Most sensors return only 'near' or 'far' values.",
    howItWorks = "Uses an infrared LED that emits light and a receiver that detects reflections. The time or intensity of the reflection indicates distance.",
    data = data
)

@Composable
fun getRotationVectorSensorDetail(data: SensorData?): SensorDetail = SensorDetail(
    name = "Rotation Vector",
    icon = Icons.Rounded.ScreenRotation,
    iconColor = MaterialTheme.colorScheme.onSecondaryContainer,
    iconBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
    unit = "dimensionless",
    description = "Represents the orientation of the device as a combination of an axis and an angle. It's more stable than using accelerometer alone.",
    howItWorks = "Fuses data from accelerometer, gyroscope, and magnetometer using sensor fusion algorithms to provide accurate device orientation in 3D space.",
    data = data
)

@Composable
fun getBarometerSensorDetail(data: SensorData?): SensorDetail = SensorDetail(
    name = "Barometer",
    icon = Icons.Rounded.Sensors,
    iconColor = MaterialTheme.colorScheme.onTertiaryContainer,
    iconBackgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
    unit = "hPa",
    description = "Measures atmospheric pressure. It's used to determine altitude and can help predict weather changes.",
    howItWorks = "Uses a MEMS piezoresistive sensor with a membrane that deflects with pressure changes. The deflection changes resistance, which is measured electrically.",
    data = data
)

@Composable
fun getAmbientTemperatureSensorDetail(data: SensorData?): SensorDetail = SensorDetail(
    name = "Temperature",
    icon = Icons.Rounded.Thermostat,
    iconColor = MaterialTheme.colorScheme.onErrorContainer,
    iconBackgroundColor = MaterialTheme.colorScheme.errorContainer,
    unit = "°C",
    description = "Measures the ambient (environmental) air temperature around the device.",
    howItWorks = "Uses a thermistor or RTD (resistance temperature detector) that changes resistance based on temperature. Not available on most smartphones.",
    data = data
)

@Composable
fun getHumiditySensorDetail(data: SensorData?): SensorDetail = SensorDetail(
    name = "Humidity",
    icon = Icons.Rounded.WaterDrop,
    iconColor = MaterialTheme.colorScheme.onPrimaryContainer,
    iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
    unit = "%",
    description = "Measures the relative humidity of the surrounding air as a percentage.",
    howItWorks = "Uses a capacitive sensor with a moisture-absorbing dielectric. Capacitance changes with humidity levels, which is measured electrically.",
    data = data
)

@Composable
fun getLinearAccelerationSensorDetail(data: SensorData?): SensorDetail = SensorDetail(
    name = "Linear Acceleration",
    icon = Icons.Rounded.Speed,
    iconColor = MaterialTheme.colorScheme.onPrimaryContainer,
    iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
    unit = "m/s²",
    description = "Measures the acceleration force applied to the device without gravity. Shows the pure motion of the device.",
    howItWorks = "Same MEMS technology as the accelerometer, but the system automatically subtracts the gravitational component (9.81 m/s²) to show only linear motion.",
    data = data
)
