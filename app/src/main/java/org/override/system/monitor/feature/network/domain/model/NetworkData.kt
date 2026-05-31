package org.override.system.monitor.feature.network.domain.model

import org.override.system.monitor.feature.network.data.datasource.NetworkType

data class NetworkData(
    val networkType: NetworkType = NetworkType.OFFLINE,
    val isConnected: Boolean = false,
    val isCaptivePortal: Boolean = false,
    // WiFi
    val wifiSignalStrength: Int = 0, // 0-100
    val wifiLinkSpeed: Int = 0, // Mbps
    val wifiFrequency: Int = 0, // MHz (2400, 5180, etc)
    val wifiSsid: String? = null,
    // Cellular
    val cellularSignalStrength: Int = 0, // 0-4 (signal bars)
    val cellularOperator: String? = null,
    val cellularNetworkType: String? = null,
    // Traffic
    val downloadSpeed: Long = 0L, // bytes per second
    val uploadSpeed: Long = 0L, // bytes per second
    val totalRxBytes: Long = 0L,
    val totalTxBytes: Long = 0L
) {
    val wifiBand: String
        get() = when {
            wifiFrequency == 0 -> "N/A"
            wifiFrequency < 3000 -> "2.4 GHz"
            wifiFrequency < 6000 -> "5 GHz"
            else -> "6 GHz"
        }

    val formattedDownloadSpeed: String
        get() = formatSpeed(downloadSpeed)

    val formattedUploadSpeed: String
        get() = formatSpeed(uploadSpeed)

    val formattedTotalRx: String
        get() = formatBytes(totalRxBytes)

    val formattedTotalTx: String
        get() = formatBytes(totalTxBytes)

    private fun formatSpeed(bytesPerSecond: Long): String {
        return when {
            bytesPerSecond < 1024 -> "$bytesPerSecond B/s"
            bytesPerSecond < 1024 * 1024 -> "${bytesPerSecond / 1024} KB/s"
            bytesPerSecond < 1024 * 1024 * 1024 -> "${bytesPerSecond / (1024 * 1024)} MB/s"
            else -> "${bytesPerSecond / (1024 * 1024 * 1024)} GB/s"
        }
    }

    private fun formatBytes(bytes: Long): String {
        return when {
            bytes < 1024 -> "$bytes B"
            bytes < 1024 * 1024 -> "${bytes / 1024} KB"
            bytes < 1024 * 1024 * 1024 -> "${bytes / (1024 * 1024)} MB"
            else -> "${bytes / (1024L * 1024L * 1024L * 1024L)} TB"
        }
    }
}
