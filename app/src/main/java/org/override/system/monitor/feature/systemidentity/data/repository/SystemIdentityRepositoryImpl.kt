package org.override.system.monitor.feature.systemidentity.data.repository

import kotlinx.coroutines.flow.Flow
import org.override.system.monitor.feature.systemidentity.data.datasource.SystemIdentityDataSource
import org.override.system.monitor.feature.systemidentity.domain.model.SystemIdentityData
import org.override.system.monitor.feature.systemidentity.domain.repository.SystemIdentityRepository

class SystemIdentityRepositoryImpl(private val dataSource: SystemIdentityDataSource) : SystemIdentityRepository {
    override val systemIdentityData: Flow<SystemIdentityData> = dataSource.getSystemIdentityData()
}