package org.override.system.monitor.feature.navigation.wrappers

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import org.koin.compose.koinInject
import org.koin.compose.navigation3.koinEntryProvider
import org.koin.core.annotation.KoinExperimentalAPI
import org.override.system.monitor.feature.navigation.navigator.AppNavigator
import org.override.system.monitor.core.ui.Destination

@OptIn(KoinExperimentalAPI::class)
@Composable
fun AppNavigationWrapper(
    navigator: AppNavigator = koinInject()
) {
    val backStack = navigator.backStack

    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        onBack = { navigator.back() },
        entryProvider = koinEntryProvider(),
        transitionSpec = {
            slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(300)
            ) togetherWith fadeOut(animationSpec = tween(300))
        },
        popTransitionSpec = {
            fadeIn(animationSpec = tween(300)) togetherWith slideOutVertically(
                targetOffsetY = { it },
                animationSpec = tween(300)
            )
        }
    )
}