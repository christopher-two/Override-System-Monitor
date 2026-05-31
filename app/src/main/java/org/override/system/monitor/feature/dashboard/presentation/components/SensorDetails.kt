package org.override.system.monitor.feature.dashboard.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Explore
import androidx.compose.material.icons.rounded.NearMe
import androidx.compose.material.icons.rounded.Sensors
import androidx.compose.material.icons.rounded.ScreenRotation
import androidx.compose.material.icons.rounded.Speed
import androidx.compose.material.icons.rounded.Thermostat
import androidx.compose.material.icons.rounded.WaterDrop
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import org.override.system.monitor.R
import org.override.system.monitor.core.common.model.SensorData

@Composable
fun getAccelerometerSensorDetail(data: SensorData?): SensorDetail = SensorDetail(
    name = stringResource(R.string.accelerometer),
    icon = Icons.Rounded.Speed,
    iconColor = MaterialTheme.colorScheme.onPrimaryContainer,
    iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
    unit = stringResource(R.string.unit_m_s2),
    description = stringResource(R.string.desc_accelerometer),
    howItWorks = stringResource(R.string.how_accelerometer),
    data = data,
    isTriAxis = true
)

@Composable
fun getGyroscopeSensorDetail(data: SensorData?): SensorDetail = SensorDetail(
    name = stringResource(R.string.gyroscope),
    icon = Icons.Rounded.Explore,
    iconColor = MaterialTheme.colorScheme.onSecondaryContainer,
    iconBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
    unit = stringResource(R.string.unit_rad_s),
    description = stringResource(R.string.desc_gyroscope),
    howItWorks = stringResource(R.string.how_gyroscope),
    data = data,
    isTriAxis = true
)

@Composable
fun getMagnetometerSensorDetail(data: SensorData?): SensorDetail = SensorDetail(
    name = stringResource(R.string.magnetometer),
    icon = Icons.Rounded.Explore,
    iconColor = MaterialTheme.colorScheme.onTertiaryContainer,
    iconBackgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
    unit = stringResource(R.string.unit_micro_t),
    description = stringResource(R.string.desc_magnetometer),
    howItWorks = stringResource(R.string.how_magnetometer),
    data = data,
    isTriAxis = true
)

@Composable
fun getProximitySensorDetail(data: SensorData?): SensorDetail = SensorDetail(
    name = stringResource(R.string.proximity),
    icon = Icons.Rounded.NearMe,
    iconColor = MaterialTheme.colorScheme.onPrimaryContainer,
    iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
    unit = stringResource(R.string.unit_cm),
    description = stringResource(R.string.desc_proximity),
    howItWorks = stringResource(R.string.how_proximity),
    data = data,
    isTriAxis = false
)

@Composable
fun getRotationVectorSensorDetail(data: SensorData?): SensorDetail = SensorDetail(
    name = stringResource(R.string.rotation_vector),
    icon = Icons.Rounded.ScreenRotation,
    iconColor = MaterialTheme.colorScheme.onSecondaryContainer,
    iconBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
    unit = stringResource(R.string.unit_dimensionless),
    description = stringResource(R.string.desc_rotation_vector),
    howItWorks = stringResource(R.string.how_rotation_vector),
    data = data,
    isTriAxis = true
)

@Composable
fun getBarometerSensorDetail(data: SensorData?): SensorDetail = SensorDetail(
    name = stringResource(R.string.barometer),
    icon = Icons.Rounded.Sensors,
    iconColor = MaterialTheme.colorScheme.onTertiaryContainer,
    iconBackgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
    unit = stringResource(R.string.unit_hpa),
    description = stringResource(R.string.desc_barometer),
    howItWorks = stringResource(R.string.how_barometer),
    data = data,
    isTriAxis = false
)

@Composable
fun getAmbientTemperatureSensorDetail(data: SensorData?): SensorDetail = SensorDetail(
    name = stringResource(R.string.temperature_label),
    icon = Icons.Rounded.Thermostat,
    iconColor = MaterialTheme.colorScheme.onErrorContainer,
    iconBackgroundColor = MaterialTheme.colorScheme.errorContainer,
    unit = stringResource(R.string.unit_celsius),
    description = stringResource(R.string.desc_temperature),
    howItWorks = stringResource(R.string.how_temperature),
    data = data,
    isTriAxis = false
)

@Composable
fun getHumiditySensorDetail(data: SensorData?): SensorDetail = SensorDetail(
    name = stringResource(R.string.humidity),
    icon = Icons.Rounded.WaterDrop,
    iconColor = MaterialTheme.colorScheme.onPrimaryContainer,
    iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
    unit = stringResource(R.string.unit_percent),
    description = stringResource(R.string.desc_humidity),
    howItWorks = stringResource(R.string.how_humidity),
    data = data,
    isTriAxis = false
)

@Composable
fun getLinearAccelerationSensorDetail(data: SensorData?): SensorDetail = SensorDetail(
    name = stringResource(R.string.linear_accel),
    icon = Icons.Rounded.Speed,
    iconColor = MaterialTheme.colorScheme.onPrimaryContainer,
    iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
    unit = stringResource(R.string.unit_m_s2),
    description = stringResource(R.string.desc_linear_accel),
    howItWorks = stringResource(R.string.how_linear_accel),
    data = data,
    isTriAxis = true
)
