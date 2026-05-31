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
import androidx.compose.material.icons.rounded.Info
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.override.system.monitor.R
import java.util.Locale

data class SensorDetail(
    val name: String,
    val icon: ImageVector,
    val iconColor: Color,
    val iconBackgroundColor: Color,
    val unit: String,
    val description: String,
    val howItWorks: String,
    val data: SensorData?,
    val isTriAxis: Boolean = false
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
                        text = stringResource(R.string.current_state),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    if (sensorDetail.data != null) {
                        Row(verticalAlignment = Alignment.Bottom) {
                            if (sensorDetail.isTriAxis) {
                                Column {
                                    Row {
                                        AxisValue("X", sensorDetail.data.x, sensorDetail.iconColor)
                                        Spacer(modifier = Modifier.width(16.dp))
                                        AxisValue("Y", sensorDetail.data.y, sensorDetail.iconColor)
                                        Spacer(modifier = Modifier.width(16.dp))
                                        AxisValue("Z", sensorDetail.data.z, sensorDetail.iconColor)
                                    }
                                }
                            } else {
                                Text(
                                    text = formatSensorValue(sensorDetail.data, sensorDetail.unit),
                                    style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                                    color = sensorDetail.iconColor
                                )
                            }
                        }
                        if (sensorDetail.unit.isNotEmpty() && !sensorDetail.isTriAxis) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = sensorDetail.unit,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    } else {
                        Text(
                            text = stringResource(R.string.no_data_available),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // What is it section
            InfoSection(
                title = stringResource(R.string.what_is_it),
                content = sensorDetail.description
            )

            Spacer(modifier = Modifier.height(12.dp))

            // How it works section
            InfoSection(
                title = stringResource(R.string.how_it_works),
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
