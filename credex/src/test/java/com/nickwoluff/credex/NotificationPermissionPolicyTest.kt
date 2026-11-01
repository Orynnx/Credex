package com.nickwoluff.credex

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class NotificationPermissionPolicyTest {

    @Test
    fun nonRearDeviceRequestsPermissionOnFirstAuthenticatedSync() {
        assertTrue(
            shouldRequestNotificationPermission(
                rearDisplaySupported = false,
                forceEducation = false,
                educationSeen = false,
            )
        )
    }

    @Test
    fun nonRearDeviceDoesNotRepeatSilentStartupRequest() {
        assertFalse(
            shouldRequestNotificationPermission(
                rearDisplaySupported = false,
                forceEducation = false,
                educationSeen = true,
            )
        )
    }

    @Test
    fun userInitiatedRetryAlwaysRequests() {
        assertTrue(
            shouldRequestNotificationPermission(
                rearDisplaySupported = false,
                forceEducation = true,
                educationSeen = true,
            )
        )
    }

    @Test
    fun rearDeviceUsesEducationDialogInsteadOfSystemPermission() {
        assertFalse(
            shouldRequestNotificationPermission(
                rearDisplaySupported = true,
                forceEducation = false,
                educationSeen = false,
            )
        )
    }
}
