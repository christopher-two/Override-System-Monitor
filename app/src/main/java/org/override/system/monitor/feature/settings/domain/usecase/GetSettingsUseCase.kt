package org.override.system.monitor.feature.settings.domain.usecase

import org.override.system.monitor.feature.settings.domain.model.SettingsData
import org.override.system.monitor.feature.settings.domain.repository.SettingsRepository

class GetSettingsUseCase(private val repository: SettingsRepository) {
    operator fun invoke(): SettingsData = repository.getSettings()
}