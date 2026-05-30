package org.override.system.monitor.feature.storage.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.override.system.monitor.feature.storage.domain.model.StorageData
import org.override.system.monitor.feature.storage.domain.repository.StorageRepository

class GetStorageDataUseCase(private val repository: StorageRepository) {
    operator fun invoke(): Flow<StorageData> = repository.storageData
}