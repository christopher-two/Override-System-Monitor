package org.override.system.monitor.feature.storage.domain.repository

import kotlinx.coroutines.flow.Flow
import org.override.system.monitor.feature.storage.domain.model.StorageData

interface StorageRepository {
    val storageData: Flow<StorageData>
}