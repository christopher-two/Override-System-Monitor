package org.override.system.monitor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.override.system.monitor.core.ui.theme.OverrideSystemMonitorTheme
import org.override.system.monitor.feature.navigation.wrappers.AppNavigationWrapper

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OverrideSystemMonitorTheme {
                AppNavigationWrapper()
            }
        }
    }
}