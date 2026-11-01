package com.nickwoluff.credex

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.Instant
import java.time.ZoneId

class QuotaResetTextTest {
    @Test
    fun appIncludesAbsoluteResetAndCountdownWhenTimestampIsKnown() {
        val now = 1_700_000_000L
        val reset = now + (6 * 24 * 60 + 14 * 60 + 22) * 60L

        assertEquals(
            "07-21 16:44 · 于6天14小时22分钟后更新",
            QuotaResetText.app("07-21 16:44", reset, now),
        )
    }

    @Test
    fun compactWidgetDropsAbsoluteTimestampToKeepOneLineShort() {
        val now = 1_700_000_000L
        val reset = now + (6 * 24 * 60 + 14 * 60 + 22) * 60L

        assertEquals(
            "6天14小时后更新",
            QuotaResetText.widgetCompact("07-21 16:44", reset, now),
        )
    }

    @Test
    fun mediumStatusKeepsThePrefixForTheRelativeCountdown() {
        val now = 1_700_000_000L
        val reset = now + 3 * 60L + 10

        assertEquals("于4分钟后更新", QuotaResetText.widgetStatus(reset, now))
    }

    @Test
    fun expiredOrLegacyResetKeepsOriginalText() {
        assertEquals("07-21 16:44", QuotaResetText.app("07-21 16:44", 0L, 1_700_000_000L))
        assertEquals("07-21 16:44", QuotaResetText.widgetCompact("07-21 16:44", 1_699_999_000L, 1_700_000_000L))
    }

    @Test
    fun planWindowFormatsUtcIsoIntoLocalTimeAndCountdown() {
        val now = Instant.parse("2026-10-31T19:40:00Z").epochSecond

        assertEquals(
            "10-31 21:14 · 于1小时35分钟后更新",
            QuotaResetText.planWindow("2026-10-31T21:14:40.000Z", now, ZoneId.of("UTC")),
        )
        assertEquals(
            "11-01 05:14 · 于1小时35分钟后更新",
            QuotaResetText.planWindow("2026-10-31T21:14:40.000Z", now, ZoneId.of("Asia/Shanghai")),
        )
    }

    @Test
    fun planWindowKeepsExpiredIsoTimestampWithoutCountdown() {
        val now = Instant.parse("2026-11-01T00:00:00Z").epochSecond

        assertEquals(
            "10-31 21:14",
            QuotaResetText.planWindow("2026-10-31T21:14:40.000Z", now, ZoneId.of("UTC")),
        )
    }

    @Test
    fun planWindowPassesThroughHumanReadableOrBlankValues() {
        assertEquals("11-02 00:00", QuotaResetText.planWindow("11-02 00:00"))
        assertEquals("", QuotaResetText.planWindow("   "))
    }

    @Test
    fun planWindowHidesZeroAndSentinelTimestamps() {
        val now = Instant.parse("2026-11-01T00:00:00Z").epochSecond

        assertEquals("", QuotaResetText.planWindow("0001-01-01T00:00:00Z", now, ZoneId.of("UTC")))
        assertEquals("", QuotaResetText.planWindow("1970-01-01T00:00:00Z", now, ZoneId.of("UTC")))
        assertEquals("", QuotaResetText.planWindow("0", now, ZoneId.of("UTC")))
    }

    @Test
    fun planWindowAcceptsUnixMillisecondsFromGlm() {
        val now = Instant.parse("2026-10-31T19:40:00Z").epochSecond

        assertEquals(
            "10-31 21:14 · 于1小时35分钟后更新",
            QuotaResetText.planWindow("1793481280000", now, ZoneId.of("UTC")),
        )
    }
}
