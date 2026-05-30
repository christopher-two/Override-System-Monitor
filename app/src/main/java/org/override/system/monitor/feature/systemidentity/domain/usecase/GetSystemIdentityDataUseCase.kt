package org.override.system.monitor.feature.systemidentity.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.override.system.monitor.feature.systemidentity.domain.model.SystemIdentityData
import org.override.system.monitor.feature.systemidentity.domain.repository.SystemIdentityRepository

class GetSystemIdentityDataUseCase(private val repository: SystemIdentityRepository) {
    operator fun invoke(): Flow<SystemIdentityData> = repository.systemIdentityData
}