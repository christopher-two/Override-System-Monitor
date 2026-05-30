package org.override.system.monitor.feature.battery.data.datasource

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.override.system.monitor.feature.battery.domain.model.BatteryData

class BatteryDataSource(private val context: Context) {

    fun getBatteryData(): Flow<BatteryData> = callbackFlow {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                val percentage = (level * 100 / scale.toFloat()).toInt()

                val temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0) / 10f
                val voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0)
                val statusInt = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
                val healthInt = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, -1)

                val status = when (statusInt) {
                    BatteryManager.BATTERY_STATUS_CHARGING -> "Charging"
                    BatteryManager.BATTERY_STATUS_DISCHARGING -> "Discharging"
                    BatteryManager.BATTERY_STATUS_FULL -> "Full"
                    BatteryManager.BATTERY_STATUS_NOT_CHARGING -> "Not Charging"
                    else -> "Unknown"
                }

                val health = when (healthInt) {
                    BatteryManager.BATTERY_HEALTH_COLD -> "Cold"
                    BatteryManager.BATTERY_HEALTH_DEAD -> "Dead"
                    BatteryManager.BATTERY_HEALTH_GOOD -> "Good"
                    BatteryManager.BATTERY_HEALTH_OVERHEAT -> "Overheat"
                    BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE -> "Over Voltage"
                    BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE -> "Unspecified Failure"
                    else -> "Unknown"
                }

                val isCharging = statusInt == BatteryManager.BATTERY_STATUS_CHARGING ||
                                statusInt == BatteryManager.BATTERY_STATUS_FULL

                trySend(BatteryData(percentage, temperature, status, voltage, health, isCharging))
            }
        }

        context.registerReceiver(receiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))

        awaitClose {
            context.unregisterReceiver(receiver)
        }
    }
}