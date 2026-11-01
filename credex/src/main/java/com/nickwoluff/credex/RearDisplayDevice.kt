package com.nickwoluff.credex

import android.os.Build

/**
 * 小米「妙享背屏」目前只随 17/18 Pro 系列出货，其他机型不展示背屏相关界面。
 *
 * 只比对设备代号（pandora / popsicle / hongkong / madrid）：canoe、art、maili 这类
 * 板级代号码同时覆盖非背屏机型，不能用来判断。区域版本可能带下划线后缀，例如
 * popsicle_global，因此用「相等或前缀 + 下划线」匹配。
 */
internal object RearDisplayDevice {
    private val SUPPORTED_CODENAMES = setOf("pandora", "popsicle", "hongkong", "madrid")

    fun isSupported(): Boolean =
        Build.MANUFACTURER.equals("Xiaomi", ignoreCase = true) &&
            isSupported(Build.DEVICE, Build.PRODUCT)

    internal fun isSupported(device: String?, product: String?): Boolean =
        listOf(device, product).any { candidate ->
            val normalized = candidate?.trim()?.lowercase().orEmpty()
            normalized.isNotEmpty() && SUPPORTED_CODENAMES.any { codename ->
                normalized == codename || normalized.startsWith("${codename}_")
            }
        }
}
