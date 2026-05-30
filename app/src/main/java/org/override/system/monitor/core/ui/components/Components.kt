package org.override.system.monitor.core.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.override.system.monitor.core.ui.theme.DarkSurface
import org.override.system.monitor.core.ui.theme.NeonBlue

@Composable
fun BentoCard(
    modifier: Modifier = Modifier,
    title: String,
    icon: ImageVector,
    accentColor: Color,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val gradientColors = listOf(
        DarkSurface,
        DarkSurface.copy(alpha = 0.95f)
    )

    Card(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.verticalGradient(colors = gradientColors))
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    Icon(imageVector = icon, contentDescription = null, tint = accentColor, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = title,
                        style = MaterialTheme.typography.labelMedium,
                        color = NeonBlue,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                content()
            }
        }
    }
}

@Composable
fun MetricValue(
    value: String,
    unit: String,
    color: Color = NeonBlue,
    shouldPulse: Boolean = false
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = if (shouldPulse) 0.5f else 1f,
        animationSpec = infiniteRepeatable(animation = tween(500), repeatMode = RepeatMode.Reverse),
        label = "alpha"
    )

    Row(verticalAlignment = Alignment.Bottom) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineLarge.copy(fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold),
            color = if (shouldPulse) color.copy(alpha = alpha) else color
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = unit,
            style = MaterialTheme.typography.bodySmall,
            color = color.copy(alpha = 0.7f)
        )
    }
}

@Composable
fun MetricProgress(progress: Float, color: Color = NeonBlue, modifier: Modifier = Modifier) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress.coerceIn(0f, 1f),
        animationSpec = tween(durationMillis = 300),
        label = "progress"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(4.dp)
            .clip(RoundedCornerShape(2.dp))
            .background(DarkSurface)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(animatedProgress)
                .clip(RoundedCornerShape(2.dp))
                .background(color)
        )
    }
}

@Composable
fun DetailItem(label: String, value: String, accentColor: Color) {
    Card(
        colors = CardDefaults.cardColors(containerColor = DarkSurface),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(label, style = MaterialTheme.typography.labelLarge, color = Color.LightGray)
            Text(
                value,
                style = MaterialTheme.typography.titleMedium.copy(fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold),
                color = accentColor
            )
        }
    }
}