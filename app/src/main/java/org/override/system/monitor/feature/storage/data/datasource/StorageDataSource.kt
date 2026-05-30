package org.override.system.monitor.feature.storage.data.datasource

import android.os.Environment
import android.os.StatFs
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.override.system.monitor.feature.storage.domain.model.StorageData

class StorageDataSource {

    fun getStorageData(): Flow<StorageData> = flow {
        while (true) {
            val path = Environment.getDataDirectory()
            val stat = StatFs(path.path)
            val blockSize = stat.blockSizeLong
            val totalBlocks = stat.blockCountLong
            val availableBlocks = stat.availableBlocksLong

            val total = totalBlocks * blockSize
            val available = availableBlocks * blockSize
            val used = total - available
            val percentage = if (total > 0) (used.toFloat() / total) * 100 else 0f

            emit(StorageData(total, available, used, percentage))
            delay(10000)
        }
    }
}