package com.codearms.maoqiqi.log

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Log fragment
 * author: March
 * date: 2021-02-01 21:01
 * version v1.0.0
 */
abstract class LogFragment : Fragment() {

    @Suppress("DEPRECATION")
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        LogUtils.v("setUserVisibleHint(isVisibleToUser: Boolean),isVisibleToUser=$isVisibleToUser")
    }

    override fun onInflate(context: Context, attrs: AttributeSet, savedInstanceState: Bundle?) {
        super.onInflate(context, attrs, savedInstanceState)
        LogUtils.v("onInflate(context: Context, attrs: AttributeSet, savedInstanceState: Bundle?)")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        LogUtils.v("onAttach(context: Context)")
    }

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
        LogUtils.v("onAttachFragment(childFragment: Fragment),childFragment=${childFragment.javaClass.name}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.v("onCreate(savedInstanceState: Bundle?)")
        lifecycle.addObserver(LogLifecycleObserver())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        LogUtils.v("onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LogUtils.v("onViewCreated(view: View, savedInstanceState: Bundle?)")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        LogUtils.v("onActivityCreated(savedInstanceState: Bundle?)")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        LogUtils.v("onViewStateRestored(savedInstanceState: Bundle?)")
    }

    override fun onStart() {
        super.onStart()
        LogUtils.v("onStart()")
    }

    override fun onResume() {
        super.onResume()
        LogUtils.v("onResume()")
    }

    override fun onPause() {
        super.onPause()
        LogUtils.v("onPause()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        LogUtils.v("onSaveInstanceState(outState: Bundle)")
    }

    override fun onStop() {
        super.onStop()
        LogUtils.v("onStop()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        LogUtils.v("onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.v("onDestroy()")
    }

    override fun onDetach() {
        super.onDetach()
        LogUtils.v("onDetach()")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LogUtils.v("onConfigurationChanged(newConfig: Configuration)")
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        LogUtils.v("onHiddenChanged(hidden: Boolean),hidden=$hidden")
    }
}