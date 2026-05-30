package org.override.system.monitor.feature.systemidentity.data.datasource

import android.os.Build
import android.os.SystemClock
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.override.system.monitor.feature.systemidentity.domain.model.SystemIdentityData

class SystemIdentityDataSource {

    fun getSystemIdentityData(): Flow<SystemIdentityData> = flow {
        while (true) {
            emit(
                SystemIdentityData(
                    model = Build.MODEL,
                    osVersion = Build.VERSION.RELEASE,
                    apiLevel = Build.VERSION.SDK_INT,
                    uptime = SystemClock.elapsedRealtime(),
                    manufacturer = Build.MANUFACTURER
                )
            )
            delay(1000)
        }
    }
}