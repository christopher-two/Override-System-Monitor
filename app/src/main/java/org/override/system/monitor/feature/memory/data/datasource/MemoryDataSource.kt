package org.override.system.monitor.feature.memory.data.datasource

import android.app.ActivityManager
import android.content.Context
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.override.system.monitor.core.preferences.PreferencesRepository
import org.override.system.monitor.feature.memory.domain.model.MemoryData

class MemoryDataSource(
    private val context: Context,
    private val preferencesRepository: PreferencesRepository
) {

    private val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    private val memoryInfo = ActivityManager.MemoryInfo()

    fun getMemoryData(): Flow<MemoryData> = flow {
        preferencesRepository.preferencesFlow.collect { prefs ->
            while (true) {
                activityManager.getMemoryInfo(memoryInfo)
                val total = memoryInfo.totalMem
                val available = memoryInfo.availMem
                val used = total - available
                val percentage = (used.toFloat() / total) * 100

                emit(MemoryData(total, available, used, percentage))
                delay(prefs.refreshInterval * 1000L)
            }
        }
    }
}