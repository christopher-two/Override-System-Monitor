package org.override.system.monitor.feature.navigation.navigator

import androidx.compose.runtime.mutableStateListOf
import androidx.navigation3.runtime.NavKey
import org.override.system.monitor.ui.Destination

class AppNavigator {
    private val _backStack = mutableStateListOf<NavKey>(Destination.Dashboard)
    val backStack: List<NavKey> get() = _backStack

    fun back() {
        if (_backStack.isEmpty()) return
        _backStack.removeLastOrNull()
    }

    fun clear() {
        _backStack.clear()
    }

    fun navigateTo(route: Destination) {
        _backStack.add(route)
    }

    fun clearAndNavigateTo(route: Destination) {
        _backStack.clear()
        _backStack.add(route)
    }
}