package com.codearms.maoqiqi.utils

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DimensionUtilsTest {

    @Test
    fun test() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Log.e("mfq", appContext.px2dp(4).toString())
    }
}