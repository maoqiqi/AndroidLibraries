package com.codearms.maoqiqi.databinding

import androidx.activity.ComponentActivity
import androidx.annotation.LayoutRes
import androidx.annotation.MainThread
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

@MainThread
inline fun <reified DB : ViewDataBinding> ComponentActivity.binding(@LayoutRes resId: Int): Lazy<DB> = lazy {
    DataBindingUtil.setContentView<DB>(this, resId).apply { lifecycleOwner = this@binding }
}