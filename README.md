# Override System Monitor

A native Android application for real-time system monitoring. Built with Jetpack Compose and Clean Architecture.

## Project Overview

Override System Monitor provides comprehensive system metrics monitoring including battery status, memory usage, storage analysis, sensor data, network connectivity, and device information. The app features an adaptive dashboard that works on both phones and tablets.

**Package:** `org.override.system.monitor`
**Min SDK:** 30 (Android 11+)
**Target SDK:** 37

## Architecture

```
Clean Architecture (MVVM)
├── UI Layer          → Jetpack Compose, ViewModels, Navigation 3
├── Domain Layer      → Use Cases, Repository Interfaces
├── Data Layer        → Repository Implementations, Room, DataStore
└── DI Layer          → Koin Modules
```

## Tech Stack

| Category | Technology | Version |
|----------|------------|---------|
| Language | Kotlin | 2.3.21 |
| UI Framework | Jetpack Compose | BOM 2026.05.01 |
| Navigation | Navigation 3 | 1.1.2 |
| DI | Koin | 4.2.1 |
| Database | Room | 2.8.4 |
| Preferences | DataStore | 1.2.1 |
| JSON | Moshi | 1.15.2 |
| Network | Retrofit + OkHttp | 3.0.0 / 5.3.2 |
| Image Loading | Coil | 2.7.0 |
| Theming | material-kolor | 5.0.0-alpha07 |
| Camera | CameraX | 1.6.1 |
| Code Generation | KSP | 2.3.6 |

## Module Structure

```
app/src/main/java/org/override/system/monitor/
├── MainActivity.kt              # Entry point
├── OverrideSystemMonitorApp.kt  # Application class
├── core/
│   ├── common/model/            # Shared models (SensorData, etc.)
│   ├── preferences/             # DataStore preferences
│   └── ui/theme/                # Theme configuration
├── di/                          # Koin modules (App, Data, Domain, ViewModel, Navigation)
└── feature/
    ├── battery/                 # Battery monitoring (data/domain/presentation)
    ├── memory/                  # RAM & storage metrics
    ├── storage/                 # Storage analysis
    ├── sensor/                  # Device sensors (accelerometer, gyro, etc.)
    ├── network/                 # WiFi & connectivity
    ├── systemidentity/          # Device & build info
    ├── dashboard/               # Main screen with adaptive layout
    ├── settings/                # App configuration
    └── navigation/              # Navigation wrappers & AppNavigator
```

## Key Dependencies

### Compose
- `androidx.compose.ui` (BOM)
- `androidx.compose.material3` 1.5.0-alpha20
- `androidx.compose.adaptive` 1.3.0-beta02
- `androidx.compose.adaptive.layout` / `navigation3` 1.3.+

### AndroidX
- `androidx.core:core-ktx` 1.18.0
- `androidx.activity:activity-compose` 1.13.0
- `androidx.lifecycle:lifecycle-*-ktx` 2.10.0
- `androidx.lifecycle:lifecycle-viewmodel-navigation3` 2.11.0-beta02

### Koin DI
- `io.insert-koin:koin-android` (BOM 4.2.1)
- `io.insert-koin:koin-compose`
- `io.insert-koin:koin-compose-viewmodel`
- `io.insert-koin:koin-compose-navigation3`

### Room + KSP
- `androidx.room:room-runtime` / `room-ktx` 2.8.4
- `androidx.room:room-compiler` (KSP)

### KotlinX
- `kotlinx-coroutines-core` / `android` 1.11.0
- `kotlinx-serialization-core` 1.11.0

## Build Commands

```bash
# Debug build
./gradlew assemble

# Unit tests
./gradlew test

# Instrumented tests (requires device/emulator)
./gradlew connectedAndroidTest

# Signing info
./gradlew signingReport
```

## Permissions

```xml
INTERNET, ACCESS_NETWORK_STATE, ACCESS_WIFI_STATE,
ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION,
READ_PHONE_STATE, ACTIVITY_RECOGNITION
```

## Navigation

Uses Navigation 3 (custom implementation, not standard Navigation Compose):
- `NavDisplay`, `rememberNavBackStack`, `NavKey`
- Managed by `AppNavigator`
- Routes: Dashboard, BatteryDetail, MemoryDetail, StorageDetail, SensorDetail, Settings

## State Management

- ViewModels expose `StateFlow` for UI state
- Unidirectional data flow with Actions/Events
- Compose integration via `lifecycle-viewmodel-compose`

## Testing

- JUnit 4.13.2 + AndroidX Test
- Mockito Kotlin 6.3.0
- MockK 1.14.11
- KotlinX Coroutines Test 1.11.0