package com.codearms.maoqiqi.androidlibraries

import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.codearms.maoqiqi.androidlibraries.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

//    private val binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

//        "123456".verboseLog()
//        "123456".debugLog()
//        "123456".infoLog()
//        "123456".warnLog()
//        "123456".errorLog()
//        LogUtils.v("123456")
//        LogUtils.d("123456")
//        LogUtils.i("123456")
//        LogUtils.w("123456")
//        LogUtils.e("123456")
//        LogUtils.v("1", "2", "3", "4", "5", "6")
//        LogUtils.d("1", "2", "3", "4", "5", "6")
//        LogUtils.i("1", "2", "3", "4", "5", "6")
//        LogUtils.w("1", "2", "3", "4", "5", "6")
//        LogUtils.e("1", "2", "3", "4", "5", "6")

//        binding.rv.setCurrentValue(4.7f)
//        binding.rv.setCurrentValue(0.0f)
        binding.seekBarFrameRate.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                seekBar?.let {
                    if (it.secondaryProgress < it.progress) {
                        it.progress = it.secondaryProgress
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}