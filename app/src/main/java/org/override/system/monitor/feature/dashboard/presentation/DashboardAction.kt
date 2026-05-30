package org.override.system.monitor.feature.dashboard.presentation

import org.override.system.monitor.core.ui.Destination

sealed class DashboardAction {
    data object LoadData : DashboardAction()
    data class Navigate(val destination: Destination) : DashboardAction()
}