package org.override.system.monitor.feature.network.data.datasource

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.TrafficStats
import android.net.wifi.WifiManager
import android.os.Build
import android.telephony.TelephonyManager
import androidx.core.content.ContextCompat
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.override.system.monitor.feature.network.domain.model.NetworkData

class NetworkDataSource(private val context: Context) {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
    private val telephonyManager =
        context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

    fun hasNetworkPermissions(): Boolean {
        val wifiState = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_WIFI_STATE
        ) == PackageManager.PERMISSION_GRANTED

        val locationState = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val phoneState = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_PHONE_STATE
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }

        return wifiState && locationState && phoneState
    }

    fun getNetworkData(): Flow<NetworkData> = flow {
        var lastRxBytes = TrafficStats.getTotalRxBytes()
        var lastTxBytes = TrafficStats.getTotalTxBytes()
        var lastTime = System.currentTimeMillis()

        while (true) {
            val currentTime = System.currentTimeMillis()
            val timeDiffSec = (currentTime - lastTime) / 1000.0

            val currentRxBytes = TrafficStats.getTotalRxBytes()
            val currentTxBytes = TrafficStats.getTotalTxBytes()

            val rxDiff = if (lastRxBytes != TrafficStats.UNSUPPORTED.toLong() && currentRxBytes > lastRxBytes) {
                currentRxBytes - lastRxBytes
            } else 0L

            val txDiff = if (lastTxBytes != TrafficStats.UNSUPPORTED.toLong() && currentTxBytes > lastTxBytes) {
                currentTxBytes - lastTxBytes
            } else 0L

            val downloadSpeed = if (timeDiffSec > 0) (rxDiff / timeDiffSec).toLong() else 0L
            val uploadSpeed = if (timeDiffSec > 0) (txDiff / timeDiffSec).toLong() else 0L

            lastRxBytes = currentRxBytes
            lastTxBytes = currentTxBytes
            lastTime = currentTime

            emit(buildNetworkData(downloadSpeed, uploadSpeed))
            delay(2000)
        }
    }

    private fun buildNetworkData(downloadSpeed: Long = 0L, uploadSpeed: Long = 0L): NetworkData {
        val network = connectivityManager.activeNetwork
        val capabilities = network?.let { connectivityManager.getNetworkCapabilities(it) }

        val networkType = when {
            capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true -> NetworkType.WIFI
            capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true -> NetworkType.CELLULAR
            capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) == true -> NetworkType.ETHERNET
            else -> NetworkType.OFFLINE
        }

        var wifiSignalStrength = 0
        var wifiLinkSpeed = 0
        var wifiFrequency = 0
        var wifiSsid: String? = null

        if (networkType == NetworkType.WIFI) {
            getWifiInfo()?.let { info ->
                wifiSignalStrength = WifiManager.calculateSignalLevel(info.rssi, 100)
                wifiLinkSpeed = info.linkSpeed
                wifiFrequency = info.frequency
                wifiSsid = info.ssid?.replace("\"", "")?.let {
                    if (it == "<unknown ssid>" || it.isEmpty()) null else it
                }
            }
        }

        var cellularSignalStrength = 0
        var operatorName = "Unknown"
        var cellularNetworkType = "Unknown"

        if (networkType == NetworkType.CELLULAR) {
            operatorName = getCellularOperatorName()
            cellularNetworkType = getCellularNetworkType()
            cellularSignalStrength = getCellularSignalStrength()
        }

        val isConnected =
            capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
        val isValidated =
            capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED) == true

        return NetworkData(
            networkType = networkType,
            isConnected = isConnected && isValidated,
            isCaptivePortal = false,
            wifiSignalStrength = wifiSignalStrength,
            wifiLinkSpeed = wifiLinkSpeed,
            wifiFrequency = wifiFrequency,
            wifiSsid = wifiSsid,
            cellularSignalStrength = cellularSignalStrength,
            cellularOperator = if (networkType == NetworkType.CELLULAR) operatorName else null,
            cellularNetworkType = if (networkType == NetworkType.CELLULAR) cellularNetworkType else null,
            downloadSpeed = downloadSpeed,
            uploadSpeed = uploadSpeed,
            totalRxBytes = TrafficStats.getTotalRxBytes(),
            totalTxBytes = TrafficStats.getTotalTxBytes()
        )
    }

    private fun getWifiInfo(): android.net.wifi.WifiInfo? {
        return if (hasNetworkPermissions()) {
            try {
                wifiManager.connectionInfo
            } catch (e: SecurityException) {
                null
            }
        } else {
            null
        }
    }

    private fun getCellularOperatorName(): String {
        return if (hasNetworkPermissions()) {
            try {
                telephonyManager.networkOperatorName.ifEmpty { "Unknown" }
            } catch (e: SecurityException) {
                "Unknown"
            }
        } else {
            "Unknown"
        }
    }

    private fun getCellularNetworkType(): String {
        return if (hasNetworkPermissions()) {
            try {
                getNetworkTypeName(telephonyManager.dataNetworkType)
            } catch (e: SecurityException) {
                "Unknown"
            }
        } else {
            "Unknown"
        }
    }

    private fun getCellularSignalStrength(): Int {
        return if (hasNetworkPermissions()) {
            try {
                telephonyManager.signalStrength?.level ?: 0
            } catch (e: SecurityException) {
                0
            }
        } else {
            0
        }
    }

    private fun getNetworkTypeName(networkType: Int): String = when (networkType) {
        TelephonyManager.NETWORK_TYPE_GPRS -> "GPRS"
        TelephonyManager.NETWORK_TYPE_EDGE -> "EDGE"
        TelephonyManager.NETWORK_TYPE_UMTS -> "UMTS"
        TelephonyManager.NETWORK_TYPE_CDMA -> "CDMA"
        TelephonyManager.NETWORK_TYPE_EVDO_0 -> "EVDO 0"
        TelephonyManager.NETWORK_TYPE_EVDO_A -> "EVDO A"
        TelephonyManager.NETWORK_TYPE_1xRTT -> "1xRTT"
        TelephonyManager.NETWORK_TYPE_HSDPA -> "HSDPA"
        TelephonyManager.NETWORK_TYPE_HSUPA -> "HSUPA"
        TelephonyManager.NETWORK_TYPE_HSPA -> "HSPA"
        TelephonyManager.NETWORK_TYPE_EVDO_B -> "EVDO B"
        TelephonyManager.NETWORK_TYPE_EHRPD -> "eHRPD"
        TelephonyManager.NETWORK_TYPE_HSPAP -> "HSPA+"
        TelephonyManager.NETWORK_TYPE_LTE -> "LTE"
        TelephonyManager.NETWORK_TYPE_NR -> "5G NR"
        else -> "Unknown"
    }
}