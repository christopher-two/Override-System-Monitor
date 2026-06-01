package org.override.system.monitor.feature.battery.data.datasource

import android.content.Context
import android.os.BatteryManager
import kotlin.math.abs

class BatterySessionTracker(context: Context) {
    private val batteryManager = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
    
    private var totalMInjected = 0.0
    private var startPercentage = 0

    // 1. Iniciar el rastreo cuando el cargador se conecta
    fun startSession(currentPercentage: Int) {
        startPercentage = currentPercentage
        totalMInjected = 0.0
    }

    // 2. Ejecutar este método en un loop cada 1 segundo (1000 ms) mientras carga
    fun trackEverySecond() {
        val currentMicro = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW)
        
        // Nota: En algunos dispositivos el valor es negativo al cargar, usamos valor absoluto
        val currentMilli = abs(currentMicro / 1000.0) 
        
        // mAh agregados en este segundo exacto (1 hora = 3600 segundos)
        val mAhThisSecond = currentMilli / 3600.0
        
        totalMInjected += mAhThisSecond
    }

    // 3. Calcular el resultado final cuando el usuario desconecta el cargador
    fun finalizeSession(endPercentage: Int, designCapacityMah: Double): BatteryHealthResult {
        val deltaPercentage = endPercentage - startPercentage
        
        // Evitamos división por cero o cargas insignificantes (se recomienda un delta > 5%)
        if (deltaPercentage <= 0) {
            return BatteryHealthResult(estimatedCapacity = 0.0, healthPercentage = 0.0)
        }

        // Fórmula de proyección para el 100%
        val estimatedCapacity = (totalMInjected / deltaPercentage) * 100.0
        
        // Porcentaje de salud comparado con la de fábrica (ej. 4500 mAh)
        val healthPercentage = (estimatedCapacity / designCapacityMah) * 100.0

        return BatteryHealthResult(
            estimatedCapacity = estimatedCapacity,
            healthPercentage = healthPercentage.coerceIn(0.0, 100.0)
        )
    }
}

data class BatteryHealthResult(
    val estimatedCapacity: Double, // Capacidad real actual en mAh
    val healthPercentage: Double   // % de degradación (ej. 88.5%)
)