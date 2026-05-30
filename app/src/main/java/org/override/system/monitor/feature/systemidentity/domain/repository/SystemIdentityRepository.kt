package org.override.system.monitor.feature.systemidentity.domain.repository

import kotlinx.coroutines.flow.Flow
import org.override.system.monitor.feature.systemidentity.domain.model.SystemIdentityData

interface SystemIdentityRepository {
    val systemIdentityData: Flow<SystemIdentityData>
}