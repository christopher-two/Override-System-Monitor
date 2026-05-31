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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Storage
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearWavyProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.override.system.monitor.R
import java.util.Locale

@Composable
fun StorageCard(
    usedStorage: Long,
    totalStorage: Long,
    percentageUsed: Float,
    onClick: () -> Unit
) {
    val storageColor = MaterialTheme.colorScheme.tertiary

    ExpressiveCard(
        modifier = Modifier.height(180.dp),
        onClick = onClick,
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            IconContainer(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.tertiaryContainer,
                icon = {
                    Icon(
                        Icons.Rounded.Storage,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onTertiaryContainer,
                        modifier = Modifier.size(22.dp)
                    )
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                stringResource(R.string.storage),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = String.format(Locale.US, "%.0f", usedStorage / 1024f / 1024f / 1024f),
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Black
                    ),
                    color = storageColor
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.gb_unit),
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = String.format(Locale.US, "of %.0f GB", totalStorage / 1024f / 1024f / 1024f),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.weight(1f))

            LinearWavyProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
                color = storageColor,
                trackColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                progress = { percentageUsed / 100f }
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}