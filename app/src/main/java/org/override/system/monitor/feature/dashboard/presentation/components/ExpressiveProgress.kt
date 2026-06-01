package org.override.system.monitor.feature.dashboard.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearWavyProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ExpressiveProgress(
    progress: Float,
    color: Color,
    modifier: Modifier = Modifier
) {
    LinearWavyProgressIndicator(
        modifier = modifier.fillMaxWidth(),
        color = color,
        trackColor = MaterialTheme.colorScheme.surfaceContainerHighest,
        progress = { progress.coerceIn(0f, 1f) }
    )
}