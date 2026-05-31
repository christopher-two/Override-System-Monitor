package org.override.system.monitor.feature.network.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.override.system.monitor.feature.network.domain.model.NetworkData
import org.override.system.monitor.feature.network.domain.repository.NetworkRepository

class GetNetworkDataUseCase(private val repository: NetworkRepository) {
    operator fun invoke(): Flow<NetworkData> = repository.getNetworkData()
    fun hasNetworkPermissions(): Boolean = repository.hasNetworkPermissions()
}
