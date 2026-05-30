package org.override.system.monitor.feature.storage.data.repository

import kotlinx.coroutines.flow.Flow
import org.override.system.monitor.feature.storage.data.datasource.StorageDataSource
import org.override.system.monitor.feature.storage.domain.model.StorageData
import org.override.system.monitor.feature.storage.domain.repository.StorageRepository

class StorageRepositoryImpl(private val dataSource: StorageDataSource) : StorageRepository {
    override val storageData: Flow<StorageData> = dataSource.getStorageData()
}