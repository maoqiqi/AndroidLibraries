package com.codearms.maoqiqi.androidlibraries;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogUtils.v("123456");
        LogUtils.d("123456");
        LogUtils.i("123456");
        LogUtils.w("123456");
        LogUtils.e("123456");
        LogUtils.v("1", "2", "3", "4", "5", "6");
        LogUtils.d("1", "2", "3", "4", "5", "6");
        LogUtils.i("1", "2", "3", "4", "5", "6");
        LogUtils.w("1", "2", "3", "4", "5", "6");
        LogUtils.e("1", "2", "3", "4", "5", "6");
    }
}
