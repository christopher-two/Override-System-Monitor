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

@Composable
fun getAccelerometerSensorDetail(data: SensorData?): SensorDetail = SensorDetail(
    name = "Accelerometer",
    icon = Icons.Rounded.Speed,
    iconColor = MaterialTheme.colorScheme.onPrimaryContainer,
    iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
    unit = "m/s²",
    description = "Measures the acceleration force applied to the device on all three physical axes (x, y, and z), including gravity.",
    howItWorks = "Uses a micro-electromechanical system (MEMS) with a proof mass that deflects when acceleration is applied. The deflection is measured electrically to determine acceleration.",
    data = data,
    isTriAxis = true
)

@Composable
fun getGyroscopeSensorDetail(data: SensorData?): SensorDetail = SensorDetail(
    name = "Gyroscope",
    icon = Icons.Rounded.Explore,
    iconColor = MaterialTheme.colorScheme.onSecondaryContainer,
    iconBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
    unit = "rad/s",
    description = "Measures the rate of rotation around the x, y, and z axes. It detects how fast the device is spinning.",
    howItWorks = "Uses the Coriolis effect on a vibrating mass. When the device rotates, the vibration pattern changes, and this change is measured to calculate rotational velocity.",
    data = data,
    isTriAxis = true
)

@Composable
fun getMagnetometerSensorDetail(data: SensorData?): SensorDetail = SensorDetail(
    name = "Magnetometer",
    icon = Icons.Rounded.Explore,
    iconColor = MaterialTheme.colorScheme.onTertiaryContainer,
    iconBackgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
    unit = "μT",
    description = "Measures the ambient magnetic field in the x, y, and z axes. It's commonly used as a digital compass.",
    howItWorks = "Uses a Hall effect sensor or magnetoresistive material that changes its electrical resistance when exposed to a magnetic field.",
    data = data,
    isTriAxis = true
)

@Composable
fun getProximitySensorDetail(data: SensorData?): SensorDetail = SensorDetail(
    name = "Proximity",
    icon = Icons.Rounded.NearMe,
    iconColor = MaterialTheme.colorScheme.onPrimaryContainer,
    iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
    unit = "cm",
    description = "Measures the distance of an object from the front of the device. Most sensors return only 'near' or 'far' values.",
    howItWorks = "Uses an infrared LED that emits light and a receiver that detects reflections. The time or intensity of the reflection indicates distance.",
    data = data,
    isTriAxis = false
)

@Composable
fun getRotationVectorSensorDetail(data: SensorData?): SensorDetail = SensorDetail(
    name = "Rotation Vector",
    icon = Icons.Rounded.ScreenRotation,
    iconColor = MaterialTheme.colorScheme.onSecondaryContainer,
    iconBackgroundColor = MaterialTheme.colorScheme.secondaryContainer,
    unit = "dimensionless",
    description = "Represents the orientation of the device as a combination of an axis and an angle. It's more stable than using accelerometer alone.",
    howItWorks = "Fuses data from accelerometer, gyroscope, and magnetometer using sensor fusion algorithms to provide accurate device orientation in 3D space.",
    data = data,
    isTriAxis = true
)

@Composable
fun getBarometerSensorDetail(data: SensorData?): SensorDetail = SensorDetail(
    name = "Barometer",
    icon = Icons.Rounded.Sensors,
    iconColor = MaterialTheme.colorScheme.onTertiaryContainer,
    iconBackgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
    unit = "hPa",
    description = "Measures atmospheric pressure. It's used to determine altitude and can help predict weather changes.",
    howItWorks = "Uses a MEMS piezoresistive sensor with a membrane that deflects with pressure changes. The deflection changes resistance, which is measured electrically.",
    data = data,
    isTriAxis = false
)

@Composable
fun getAmbientTemperatureSensorDetail(data: SensorData?): SensorDetail = SensorDetail(
    name = "Temperature",
    icon = Icons.Rounded.Thermostat,
    iconColor = MaterialTheme.colorScheme.onErrorContainer,
    iconBackgroundColor = MaterialTheme.colorScheme.errorContainer,
    unit = "°C",
    description = "Measures the ambient (environmental) air temperature around the device.",
    howItWorks = "Uses a thermistor or RTD (resistance temperature detector) that changes resistance based on temperature. Not available on most smartphones.",
    data = data,
    isTriAxis = false
)

@Composable
fun getHumiditySensorDetail(data: SensorData?): SensorDetail = SensorDetail(
    name = "Humidity",
    icon = Icons.Rounded.WaterDrop,
    iconColor = MaterialTheme.colorScheme.onPrimaryContainer,
    iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
    unit = "%",
    description = "Measures the relative humidity of the surrounding air as a percentage.",
    howItWorks = "Uses a capacitive sensor with a moisture-absorbing dielectric. Capacitance changes with humidity levels, which is measured electrically.",
    data = data,
    isTriAxis = false
)

@Composable
fun getLinearAccelerationSensorDetail(data: SensorData?): SensorDetail = SensorDetail(
    name = "Linear Acceleration",
    icon = Icons.Rounded.Speed,
    iconColor = MaterialTheme.colorScheme.onPrimaryContainer,
    iconBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
    unit = "m/s²",
    description = "Measures the acceleration force applied to the device without gravity. Shows the pure motion of the device.",
    howItWorks = "Same MEMS technology as the accelerometer, but the system automatically subtracts the gravitational component (9.81 m/s²) to show only linear motion.",
    data = data,
    isTriAxis = true
)
