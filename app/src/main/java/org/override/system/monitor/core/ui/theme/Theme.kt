package org.override.system.monitor.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MotionScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.materialkolor.DynamicMaterialExpressiveTheme
import com.materialkolor.PaletteStyle
import com.materialkolor.dynamiccolor.ColorSpec
import com.materialkolor.rememberDynamicMaterialThemeState
import org.koin.compose.koinInject
import org.override.system.monitor.core.preferences.ThemeMode

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun OverrideSystemMonitorTheme(
    content: @Composable () -> Unit,
) {
    val preferencesRepository: org.override.system.monitor.core.preferences.PreferencesRepository = koinInject()
    val themeMode: ThemeMode by preferencesRepository.themeModeFlow.collectAsState(initial = ThemeMode.SYSTEM)

    val isDark = when (themeMode) {
        ThemeMode.SYSTEM -> isSystemInDarkTheme()
        ThemeMode.LIGHT -> false
        ThemeMode.DARK -> true
    }

    val dynamicThemeState = rememberDynamicMaterialThemeState(
        isDark = isDark,
        style = PaletteStyle.Expressive,
        specVersion = ColorSpec.SpecVersion.SPEC_2025,
        seedColor = SeedColor,
    )

    DynamicMaterialExpressiveTheme(
        state = dynamicThemeState,
        motionScheme = MotionScheme.expressive(),
        animate = true,
        content = content,
    )
}
