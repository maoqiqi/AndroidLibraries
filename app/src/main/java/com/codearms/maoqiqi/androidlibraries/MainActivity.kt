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