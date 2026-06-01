package org.override.system.monitor.feature.memory.domain.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MemoryDataTest {

    @Test
    fun `totalGB calculates correctly`() {
        // 10 GB in bytes = 10 * 1024 * 1024 * 1024 = 10737418240
        val memoryData = MemoryData(
            totalMemory = 10737418240L,
            availableMemory = 5368709120L,
            usedMemory = 5368709120L,
            percentageUsed = 50f
        )

        assertEquals(10f, memoryData.totalGB, 0.1f)
    }

    @Test
    fun `availableGB calculates correctly`() {
        // 5 GB in bytes = 5 * 1024 * 1024 * 1024 = 5368709120
        val memoryData = MemoryData(
            totalMemory = 10737418240L,
            availableMemory = 5368709120L,
            usedMemory = 5368709120L,
            percentageUsed = 50f
        )

        assertEquals(5f, memoryData.availableGB, 0.1f)
    }

    @Test
    fun `usedGB calculates correctly`() {
        // 5 GB in bytes = 5 * 1024 * 1024 * 1024 = 5368709120
        val memoryData = MemoryData(
            totalMemory = 10737418240L,
            availableMemory = 5368709120L,
            usedMemory = 5368709120L,
            percentageUsed = 50f
        )

        assertEquals(5f, memoryData.usedGB, 0.1f)
    }

    @Test
    fun `percentageUsed is stored correctly`() {
        val memoryData = MemoryData(
            totalMemory = 10737418240L,
            availableMemory = 2684354560L,
            usedMemory = 8053063680L,
            percentageUsed = 75f
        )

        assertEquals(75f, memoryData.percentageUsed, 0f)
    }

    @Test
    fun `zero memory values handled correctly`() {
        val memoryData = MemoryData(
            totalMemory = 0L,
            availableMemory = 0L,
            usedMemory = 0L,
            percentageUsed = 0f
        )

        assertEquals(0f, memoryData.totalGB, 0f)
        assertEquals(0f, memoryData.availableGB, 0f)
        assertEquals(0f, memoryData.usedGB, 0f)
    }
}