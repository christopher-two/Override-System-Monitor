package org.override.system.monitor.feature.settings.data.repository

import org.override.system.monitor.feature.settings.domain.model.SettingsData
import org.override.system.monitor.feature.settings.domain.repository.SettingsRepository

class SettingsRepositoryImpl : SettingsRepository {
    override fun getSettings(): SettingsData = SettingsData()
}