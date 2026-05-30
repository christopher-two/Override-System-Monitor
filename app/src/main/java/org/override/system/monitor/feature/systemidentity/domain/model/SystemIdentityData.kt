package org.override.system.monitor.feature.systemidentity.domain.model

data class SystemIdentityData(
    val model: String,
    val osVersion: String,
    val apiLevel: Int,
    val uptime: Long,
    val manufacturer: String
)