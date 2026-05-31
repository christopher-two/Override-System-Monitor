package org.override.system.monitor.feature.dashboard.presentation.components

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
import androidx.compose.material.icons.rounded.Memory
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearWavyProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.util.Locale

@Composable
fun MemoryCard(
    usedMemory: Long,
    totalMemory: Long,
    percentageUsed: Float,
    onClick: () -> Unit
) {
    val ramColor = MaterialTheme.colorScheme.secondary

    ExpressiveCard(
        modifier = Modifier.height(180.dp),
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            IconContainer(
                shape = CircleShape,
                color = MaterialTheme.colorScheme.secondaryContainer,
                icon = {
                    Icon(
                        Icons.Rounded.Memory,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.size(22.dp)
                    )
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                "Memory",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = String.format(Locale.US, "%.1f", usedMemory / 1024f / 1024f / 1024f),
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Black
                    ),
                    color = ramColor
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "GB",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = String.format(Locale.US, "of %.1f GB", totalMemory / 1024f / 1024f / 1024f),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.weight(1f))

            LinearWavyProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
                color = ramColor,
                trackColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                progress = { percentageUsed / 100f }
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}