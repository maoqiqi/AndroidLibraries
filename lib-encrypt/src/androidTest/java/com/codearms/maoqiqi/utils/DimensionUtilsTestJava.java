package com.codearms.maoqiqi.utils;

import android.content.Context;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DimensionUtilsTestJava {

    @Test
    public void test() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Log.e("mfq", DimensionUtilsKt.px2dp(appContext, 4) + "");
    }
}
