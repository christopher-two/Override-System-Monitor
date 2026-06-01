package org.override.system.monitor.feature.cpu.data;

import android.os.Build
import java.io.File
import java.io.RandomAccessFile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.delay
import org.override.system.monitor.feature.cpu.domain.CpuData

class CpuProvider {

    val cpuCores: Int = Runtime.getRuntime().availableProcessors()
    val architecture: String = Build.SUPPORTED_ABIS.firstOrNull() ?: "Unknown"

    // Emite las frecuencias actualizadas cada segundo
    fun getCpuStats(): Flow<CpuData> = flow {
        while (true) {
            val frequencies = mutableListOf<Long>()
            for (i in 0 until cpuCores) {
                frequencies.add(getCoreFrequency(i))
            }
            emit(CpuData(architecture, cpuCores, frequencies))
            delay(1000) // Intervalo de actualización
        }
    }

    private fun getCoreFrequency(coreIndex: Int): Long {
        return try {
            val file = File("/sys/devices/system/cpu/cpu$coreIndex/cpufreq/scaling_cur_freq")
            if (file.exists()) {
                val reader = RandomAccessFile(file, "r")
                val freqKHz = reader.readLine()?.toLong() ?: 0L
                reader.close()
                freqKHz / 1000 // Convertir a MHz
            } else 0L
        } catch (e: Exception) {
            0L
        }
    }
}