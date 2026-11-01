package com.nickwoluff.credex

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class RearDisplayDeviceTest {
    @Test
    fun `all rear display models are supported`() {
        listOf("pandora", "popsicle", "hongkong", "madrid").forEach { codename ->
            assertTrue("$codename should be supported", RearDisplayDevice.isSupported(codename, codename))
        }
    }

    @Test
    fun `regional product suffix still matches`() {
        assertTrue(RearDisplayDevice.isSupported("popsicle", "popsicle_global"))
        assertTrue(RearDisplayDevice.isSupported("MADRID", null))
    }

    @Test
    fun `other devices and board platforms are not supported`() {
        assertFalse(RearDisplayDevice.isSupported("sdk_gphone64_x86_64", "sdk_gphone64_x86_64"))
        assertFalse(RearDisplayDevice.isSupported("pudding", "pudding"))
        assertFalse(RearDisplayDevice.isSupported("canoe", "canoe"))
        assertFalse(RearDisplayDevice.isSupported("art", "art"))
        assertFalse(RearDisplayDevice.isSupported("maili", "maili"))
        assertFalse(RearDisplayDevice.isSupported("", null))
        assertFalse(RearDisplayDevice.isSupported(null, null))
    }

    @Test
    fun `codename must match the whole segment`() {
        assertFalse(RearDisplayDevice.isSupported("pandora2", null))
        assertFalse(RearDisplayDevice.isSupported("xmadrid", null))
    }
}
