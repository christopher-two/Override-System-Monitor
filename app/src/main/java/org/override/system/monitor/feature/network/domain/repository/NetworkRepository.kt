package org.override.system.monitor.feature.network.domain.repository

import kotlinx.coroutines.flow.Flow
import org.override.system.monitor.feature.network.domain.model.NetworkData

interface NetworkRepository {
    fun getNetworkData(): Flow<NetworkData>
    fun hasNetworkPermissions(): Boolean
}
