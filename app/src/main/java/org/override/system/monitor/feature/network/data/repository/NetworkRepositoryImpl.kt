package org.override.system.monitor.feature.network.data.repository

import kotlinx.coroutines.flow.Flow
import org.override.system.monitor.feature.network.data.datasource.NetworkDataSource
import org.override.system.monitor.feature.network.domain.model.NetworkData
import org.override.system.monitor.feature.network.domain.repository.NetworkRepository

class NetworkRepositoryImpl(
    private val networkDataSource: NetworkDataSource
) : NetworkRepository {
    override fun getNetworkData(): Flow<NetworkData> = networkDataSource.getNetworkData()
    override fun hasNetworkPermissions(): Boolean = networkDataSource.hasNetworkPermissions()
}