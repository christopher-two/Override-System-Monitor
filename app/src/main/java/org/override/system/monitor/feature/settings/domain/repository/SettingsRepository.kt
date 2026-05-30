package org.override.system.monitor.feature.settings.domain.repository

import org.override.system.monitor.feature.settings.domain.model.SettingsData

interface SettingsRepository {
    fun getSettings(): SettingsData
}