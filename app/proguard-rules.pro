# ==============================================
# PROJECT OVERRIDE SYSTEM MONITOR
# ProGuard Rules for Release Build
# ==============================================
# This file is used to configure ProGuard for
# obfuscation and optimization of the release APK.
# ==============================================

# ----------------------------------------------
# 1. KEEP ANNOTATIONS & ATTRIBUTES
# ----------------------------------------------
-keepattributes *Annotation*
-keepattributes RuntimeVisibleAnnotations
-keepattributes RuntimeVisibleParameterAnnotations
-keepattributes RuntimeInvisibleAnnotations
-keepattributes RuntimeInvisibleParameterAnnotations
-keepattributes SourceFile,LineNumberTable
-keepattributes Signature
-keepattributes InnerClasses
-keepattributes EnclosingMethod
-keepattributes Deprecated
-keepattributes Exceptions

# ----------------------------------------------
# 2. KOTLIN COROUTINES
# ----------------------------------------------
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepclassmembers class kotlinx.coroutines.** {
    volatile <fields>;
}
-keepclassmembers class kotlin.coroutines.SafeContinuation {
    volatile <fields>;
}
-dontwarn kotlinx.coroutines.**

# Keep suspend functions
-keepclassmembers class kotlinx.coroutines.** {
    public suspend ***(...);
}

# ----------------------------------------------
# 3. KOIN DEPENDENCY INJECTION
# ----------------------------------------------
-keepnames class * extends org.koin.core.module.Module
-keepclassmembers class * extends org.koin.core.module.Module { *; }
-keepclassmembers class * { public <init>(...); }
-keepclassmembers class * implements org.koin.dsl.Module { *; }
-keepclassmembers class * { public <init>(org.koin.core.qualifier.Qualifier); }
-dontwarn org.koin.core.**
-keep class org.koin.** { *; }

# Koin ViewModel integration
-keep class * extends androidx.lifecycle.ViewModel { *; }
-keepclassmembers class * extends androidx.lifecycle.ViewModel {
    public <init>(...);
    *** onCleared();
}
-keepclassmembers class * extends androidx.lifecycle.AndroidViewModel {
    public <init>(...);
}

# Koin experimental API
-keepclassmembers class * {
    @org.koin.core.annotation.* <methods>;
}

# ----------------------------------------------
# 4. NAVIGATION 3
# ----------------------------------------------
-keep class androidx.navigation3.** { *; }
-keep interface androidx.navigation3.** { *; }
-keepclassmembers class * implements androidx.navigation3.runtime.NavKey { *; }
-keepclassmembers interface androidx.navigation3.runtime.NavKey { *; }

# NavBackStack
-keep class androidx.navigation3.runtime.NavBackStack { *; }
-keep class androidx.navigation3.runtime.NavDisplay { *; }

# Project Destinations (sealed interface)
-keep class org.override.system.monitor.core.ui.Destination { *; }
-keepclassmembers class org.override.system.monitor.core.ui.Destination { *; }
-keepclassmembers class org.override.system.monitor.core.ui.Destination$* { *; }
-keepclassmembers class org.override.system.monitor.core.ui.Destination$*.* { *; }

# ----------------------------------------------
# 5. DATASTORE PREFERENCES
# ----------------------------------------------
-keep class androidx.datastore.** { *; }
-keep class androidx.datastore.preferences.** { *; }
-keep class androidx.datastore.preferences.core.** { *; }
-keepclassmembers class * {
    @androidx.datastore.preferences.* <fields>;
}
-keepclassmembers class androidx.datastore.preferences.ExperimentalDataStore { *; }
-dontwarn androidx.datastore.**

# ----------------------------------------------
# 6. COMPOSE / ANDROIDX
# ----------------------------------------------
-keep class androidx.compose.** { *; }
-keep interface androidx.compose.** { *; }
-keepclassmembers @androidx.compose.runtime.Composable class * { *; }
-dontwarn androidx.compose.**

# Compose Material 3
-keep class androidx.compose.material3.** { *; }
-keep class androidx.compose.material.** { *; }
-keepclassmembers class androidx.compose.material3.** { *; }

# Compose Adaptive (tablet/phone layouts)
-keep class androidx.compose.adaptive.** { *; }
-keep class androidx.compose.adaptive.layout.** { *; }
-keep class androidx.compose.adaptive.navigation3.** { *; }

# Compose UI
-keep class androidx.compose.ui.** { *; }
-keepclassmembers class androidx.compose.ui.** { *; }

# Compose Graphics
-keep class androidx.compose.ui.graphics.** { *; }

# Compose Foundation
-keep class androidx.compose.foundation.** { *; }

# ----------------------------------------------
# 7. CAMERAX
# ----------------------------------------------
-keep class androidx.camera.** { *; }
-keep interface androidx.camera.** { *; }
-keepclassmembers class androidx.camera.** { *; }
-dontwarn androidx.camera.**

# Camera lifecycle
-keep class androidx.camera.core.** { *; }
-keep class androidx.camera.camera2.** { *; }
-keep class androidx.camera.lifecycle.** { *; }
-keep class androidx.camera.view.** { *; }

# ----------------------------------------------
# 8. PLAY SERVICES LOCATION
# ----------------------------------------------
-keep class com.google.android.gms.location.** { *; }
-keep interface com.google.android.gms.location.** { *; }
-keepclassmembers class com.google.android.gms.location.** { *; }
-dontwarn com.google.android.gms.location.**

# FusedLocationProvider
-keep class com.google.android.gms.location.FusedLocationProviderClient { *; }
-keep class com.google.android.gms.location.LocationServices { *; }

# ----------------------------------------------
# 9. COIL IMAGE LOADING
# ----------------------------------------------
-keep class coil.** { *; }
-keep interface coil.** { *; }
-keepclassmembers class coil.** { *; }
-dontwarn coil.**

# Coil Compose
-keep class coil.compose.** { *; }

# ----------------------------------------------
# 10. OKHTTP (logging interceptor)
# ----------------------------------------------
-dontwarn okhttp3.**
-dontwarn okio.**
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-keep class okio.** { *; }
-keep interface okio.** { *; }

# OkHttp logging interceptor
-keep class okhttp3.logging.** { *; }

# ----------------------------------------------
# 11. KOTLIN REFLECTION
# ----------------------------------------------
-keep class kotlin.reflect.** { *; }
-keepclassmembers class kotlin.Metadata { *; }
-dontwarn kotlin.reflect.**

# Keep Kotlin metadata
-keep class kotlin.Metadata { *; }

# ----------------------------------------------
# 12. PROJECT DOMAIN MODELS
# ----------------------------------------------

# Battery feature
-keep class org.override.system.monitor.feature.battery.domain.model.** { *; }
-keep class org.override.system.monitor.feature.battery.data.datasource.** { *; }

# Memory feature
-keep class org.override.system.monitor.feature.memory.domain.model.** { *; }
-keep class org.override.system.monitor.feature.memory.data.datasource.** { *; }

# Storage feature
-keep class org.override.system.monitor.feature.storage.domain.model.** { *; }
-keep class org.override.system.monitor.feature.storage.data.datasource.** { *; }

# Network feature
-keep class org.override.system.monitor.feature.network.domain.model.** { *; }
-keep class org.override.system.monitor.feature.network.data.datasource.** { *; }
-keep class org.override.system.monitor.feature.network.data.repository.** { *; }
-keep enum org.override.system.monitor.feature.network.data.datasource.NetworkType { *; }

# CPU feature
-keep class org.override.system.monitor.feature.cpu.domain.** { *; }
-keep class org.override.system.monitor.feature.cpu.data.** { *; }

# System Identity feature
-keep class org.override.system.monitor.feature.systemidentity.domain.model.** { *; }
-keep class org.override.system.monitor.feature.systemidentity.data.datasource.** { *; }

# Sensor feature
-keep class org.override.system.monitor.feature.sensor.domain.model.** { *; }
-keep class org.override.system.monitor.feature.sensor.domain.repository.** { *; }
-keep class org.override.system.monitor.feature.sensor.data.datasource.** { *; }
-keep class org.override.system.monitor.feature.sensor.data.repository.** { *; }
-keep class org.override.system.monitor.feature.sensor.presentation.** { *; }

# Settings feature
-keep class org.override.system.monitor.feature.settings.domain.model.** { *; }
-keep class org.override.system.monitor.feature.settings.presentation.** { *; }

# Dashboard feature
-keep class org.override.system.monitor.feature.dashboard.domain.usecase.** { *; }
-keep class org.override.system.monitor.feature.dashboard.presentation.** { *; }
-keep class org.override.system.monitor.feature.dashboard.presentation.components.** { *; }

# Core common models
-keep class org.override.system.monitor.core.common.model.** { *; }

# Core preferences
-keep class org.override.system.monitor.core.preferences.** { *; }

# Core UI
-keep class org.override.system.monitor.core.ui.** { *; }
-keep class org.override.system.monitor.core.ui.theme.** { *; }

# Navigation
-keep class org.override.system.monitor.feature.navigation.** { *; }

# Main / App
-keep class org.override.system.monitor.MainViewModel { *; }
-keep class org.override.system.monitor.OverrideSystemMonitorApp { *; }
-keep class org.override.system.monitor.MainActivity { *; }

# ----------------------------------------------
# 13. GENERAL ANDROID RULES
# ----------------------------------------------
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.Application
-keep public class * extends androidx.fragment.app.Fragment
-keep public class * extends androidx.lifecycle.ViewModel
-keep public class * extends androidx.lifecycle.AndroidViewModel
-keep public class * extends androidx.lifecycle.ViewModelProvider$Factory
-keep public class * extends androidx.compose.runtime.Composable

# Parcelable
-keepclassmembers class * implements android.os.Parcelable {
    public static final ** CREATOR;
    public <fields>;
}

# Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# ----------------------------------------------
# 14. KEEP R8 / DESUGARING COMPATIBILITY
# ----------------------------------------------
-dontwarn java.lang.invoke.**
-keep class java.lang.invoke.** { *; }

# ----------------------------------------------
# 15. REMOVE DEBUGGING IN RELEASE
# ----------------------------------------------
-assumenosideeffects class android.util.Log {
    public static *** v(...);
    public static *** d(...);
    public static *** i(...);
}

# Remove logging from internal Android classes
-assumenosideeffects class android.util.Log {
    public static int v(...);
    public static int d(...);
    public static int i(...);
    public static int w(...);
    public static int e(...);
}

# ----------------------------------------------
# 16. R8 OPTIMIZATIONS
# ----------------------------------------------
-allowaccessmodification
-repackageclasses
-overloadaggressively

# Keep enum classes
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# ----------------------------------------------
# 17. KEEP GENERIC SIGNATURES
# ----------------------------------------------
-keepattributes Signature
-keepattributes *Annotation*
-keepattributes EnclosingMethod

# For anonymous classes
-keepclassmembers class * {
    synthetic <fields>;
    synthetic <methods>;
}

# ----------------------------------------------
# 18. MISC KEEP RULES
# ----------------------------------------------

# Keep ViewModel SavedStateHandle
-keep class * extends androidx.lifecycle.SavedStateHandle { *; }
-keep class androidx.lifecycle.SavedStateHandle { *; }

# Keep DataStore
-keep class androidx.datastore.preferences.PreferenceDataStore { *; }

# Keep Koin's module structure
-keep class org.koin.dsl.** { *; }
-keep class org.koin.core.** { *; }

# Keep Accompanist Permissions
-keep class com.google.accompanist.** { *; }
-keep interface com.google.accompanist.** { *; }

# Keep material-kolor (dynamic theming)
-keep class com.materialkolor.** { *; }
-keep interface com.materialkolor.** { *; }
