# Agents

[SYSTEM INSTRUCTION]
Actúa como un ingeniero de software senior. Adopta una política estricta de "cero relleno" (zero-fluff).

Reglas de interacción:
1. Prohibidas las introducciones de cortesía ("¡Claro!", "Es una excelente pregunta...").
2. Prohibidas las conclusiones, resúmenes o explicaciones obvias al final del mensaje.
3. Entrega el código corregido o la respuesta técnica de manera inmediata.
4. Si la explicación es indispensable, limítala a viñetas (bullet points) de una sola frase.
5. Sé sumamente conciso. Maximiza la densidad de información por token.

## Build Commands

- `./gradlew assemble` - Build debug APK
- `./gradlew test` - Unit tests only
- `./gradlew connectedAndroidTest` - Instrumented tests (requires device/emulator)
- `./gradlew signingReport` - Show signing info

## Project Structure

- **Package**: `org.override.system.monitor`
- **Single module**: `:app` (root `settings.gradle.kts` only includes `app`)
- **Source sets**: `app/src/main/java/`, `app/src/test/java/`, `app/src/androidTest/java/`

## Architecture Notes

- **Navigation 3** (not standard Navigation Compose): Uses `NavDisplay`, `rememberNavBackStack`, `NavEntry`, `NavKey` from `androidx.navigation3.*`
- Entry point: `MainActivity.kt` -> `SystemMonitorApp()` composable
- Dashboard: `ui/dashboard/DashboardScreen.kt` with `DashboardViewModel`
- Detail screens: `ui/detail/DetailScreens.kt`
- Monitors: `data/monitoring/` (Battery, Memory, Storage, Sensor, SystemIdentity)

## Dependencies

- **KSP** for codegen (Room, Moshi): use `"ksp"(libs.xxx)` syntax, not `"ksp"("...")`  
- **Compose BOM**: `2024.09.00`
- **Kotlin**: `2.2.10` with compose plugin

## Key Config

- `minSdk = 30` (Android 11+)
- `targetSdk = 37`
- `namespace = "org.override.system.monitor"`
- Uses `kotlin.code.style=official`
- foojay toolchain resolver: `org.gradle.toolchains.foojay-resolver-convention`
