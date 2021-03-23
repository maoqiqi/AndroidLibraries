package com.codearms.maoqiqi.databinding

import android.view.View
import androidx.annotation.MainThread
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Fragment data binding lazy
 * author: March
 * date: 2021-03-23 21:01
 * version v1.0.0
 */
@MainThread
inline fun <reified DB : ViewDataBinding> Fragment.binding(): Lazy<DB> = lazy {
    val currentView: View = view ?: throw IllegalStateException("Fragment $this does not have a view")
    DataBindingUtil.bind<DB>(currentView)?.apply { lifecycleOwner = this@binding } ?: throw IllegalStateException("DataBindingUtil.bind fail")
}