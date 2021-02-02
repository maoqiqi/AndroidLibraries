package com.codearms.maoqiqi.utils

object DateUtils {

    const val format_y = "yyyy"
    const val format_m = "MM"
    const val format_dd = "dd"
    const val format_hh = "HH"
    const val format_mm = "mm"
    const val format_ss = "ss"
    const val format_hh_mm = "HH:mm"
    const val format_h_mm_a = "h:mm a"
    const val format_hh_mm_ss = "HH:mm:ss"
    const val format_yyyy_mm_dd = "yyyy-MM-dd"
    const val format_yyyy_mm_dd_hh_mm = "yyyy-MM-dd HH:mm"
    const val format_yyyy_mm_dd_hh_mm_ss = "yyyy-MM-dd HH:mm:ss"
    const val format_yyyy_mm_dd_hh_mm_ss_ = "yyyy年MM月dd日 HH时mm分ss秒"

    /**
     * Get the current timestamp (accurate to seconds)
     * @return the current timestamp
     */
    @JvmStatic
    fun getTimeStamp() = System.currentTimeMillis()
}